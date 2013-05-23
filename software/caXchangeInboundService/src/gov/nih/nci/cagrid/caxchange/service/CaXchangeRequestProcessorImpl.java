/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.cagrid.caxchange.service;

import gov.nih.nci.caXchange.CaxchangeErrors;
import gov.nih.nci.cagrid.caxchange.context.service.globus.resource.CaXchangeResponseServiceResource;
import gov.nih.nci.cagrid.caxchange.listener.CaxchangeResponseExceptionListener;
import gov.nih.nci.cagrid.caxchange.listener.CaxchangeResponseListener;
import gov.nih.nci.cagrid.caxchange.listener.ResponseHandler;
import gov.nih.nci.cagrid.caxchange.stubs.types.CaXchangeFault;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.caxchange.ResponseMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.namespace.QName;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.globus.wsrf.ResourceKey;
import org.globus.wsrf.security.SecurityManager;

/**
 *
 * @created by Introduce Toolkit version 1.1
 *
 */
public class CaXchangeRequestProcessorImpl extends CaXchangeRequestProcessorImplBase {
	protected static Pattern emptyNamespacePattern = Pattern.compile("xmlns:[a-zA-Z0-9].[a-zA-Z0-9]*=\"\"|xmlns=\"\"");
	protected static String brokerURL = "tcp://localhost:61618";
	protected static String destinationName = "caxchangeInboundQueue";
	protected static String replyDestinationName = "caxchangeOutboundQueue";
	protected static Destination destination = null;
	protected static Destination replyDestination = null;
	protected static Logger logger = LogManager.getLogger(CaXchangeRequestProcessorImpl.class.getName());
	protected static ConnectionFactory connectionFactory = null;
	protected static Connection connection = null;
	public static Map<CaxchangeResponseExceptionListener, Connection> responseListeners = null;
	protected static Long synchronousServiceClientTimeout = null;
	
	private static final String MIRTH_HTTP_LISTENER_USER = "mirth.http.listener.user";
	private static final String MIRTH_HTTP_LISTENER_PASSWORD = "mirth.http.listener.password";
	private static final String MIRTH_HTTP_LISTENER_URL = "mirth.http.listener.url";
	
	
	public CaXchangeRequestProcessorImpl() throws RemoteException {
		super();
	}

	/**
	 * Processes a request by sending it to the caXchange inbound queue.
	 *
	 * @param caXchangeRequestMessage
	 * @return
	 * @throws RemoteException
	 */
  public gov.nih.nci.cagrid.caxchange.context.stubs.types.CaXchangeResponseServiceReference processRequestAsynchronously(gov.nih.nci.caxchange.Message caXchangeRequestMessage) throws RemoteException, gov.nih.nci.cagrid.caxchange.stubs.types.CaXchangeFault {
		logger.debug("request:" + caXchangeRequestMessage);
		try {
			gov.nih.nci.cagrid.caxchange.context.service.globus.resource.CaXchangeResponseServiceResourceHome ctxResourceHome = getCaXchangeResponseServiceResourceHome();
			ResourceKey resKey = ctxResourceHome.createResource();
			logger.info("Performance Request Received," + resKey.getValue()
					+ ","
					+ caXchangeRequestMessage.getMetadata().getServiceType()
					+ "," + new java.util.Date().getTime());
			caXchangeRequestMessage.getMetadata().setCaXchangeIdentifier(resKey.getValue().toString());
			String caller = SecurityManager.getManager().getCaller();
			logger.debug("The caller is:'" + caller +"'");
			if ((caller == null)||("".equals(caller)) || ("<anonymous>".equals(caller.trim()))) {
				updateErrorResponse(caXchangeRequestMessage, resKey,
						CaxchangeErrors.PERMISSION_DENIED_FAULT,
						"Unable to get the identity of the caller.Caller identity:"
								+ caller);
				return ctxResourceHome.getResourceReference(resKey);
			}
			logger.debug("Sending message for the caller:" + caller);
			if (caXchangeRequestMessage.getMetadata().getCredentials() == null){
				caXchangeRequestMessage.getMetadata().setCredentials(new gov.nih.nci.caxchange.Credentials());
			}
			caXchangeRequestMessage.getMetadata().getCredentials().setGridIdentifier(caller);
			try { // Sending the request to the caXchange inbound queue
				logger.info("Before sending messge " + new Date().getTime());
				sendMessage(caXchangeRequestMessage);
				logger.info("Before sending messge " + new Date().getTime());
			} catch (Exception e) {
				updateErrorResponse(caXchangeRequestMessage, resKey,CaxchangeErrors.UNKNOWN,
						"An error occured sending message to the caXchange hub."+ e.getMessage());
			}

			if ((responseListeners == null) || (responseListeners.size() == 0)) {
				registerResponseListeners();
			}
			logger.debug("Request send to ESB for key " + resKey);

			return ctxResourceHome.getResourceReference(resKey);
		} catch (Exception e) {
			logger.error("Error processing message request.", e);
			CaXchangeFault caXchangeFault = new CaXchangeFault();
			caXchangeFault.setFaultDetailString("Error processing message request."+ e.getMessage());
			caXchangeFault.setStackTrace(e.getStackTrace());
			throw caXchangeFault;
		}
	}

	/**
	 * Send the message to the caXchange inbound JMS component.
	 *
	 * @param caXchangeRequestMessage
	 * @throws Exception
	 */
	protected void sendMessage(
			gov.nih.nci.caxchange.Message caXchangeRequestMessage)
			throws Exception {
		Session session = null;
		try {
			CaXchangeRequestProcessorConfiguration configuration = getConfiguration();
			CaXchangeExternalProperties properties = CaXchangeExternalProperties.getInstance();
			String inboundJmsBrokerUrl = properties.getProperty("inbound.jms.brokerURL");
			String jmsUserName = properties.getProperty("amq.caxchange.user");
			String jmsUserPassword = properties.getProperty("amq.caxchange.password");
			if (inboundJmsBrokerUrl == null) {
				inboundJmsBrokerUrl = configuration.getCaXchangeInboundBrokerURL();
			}
			if (connectionFactory == null) {
				connectionFactory = new ActiveMQConnectionFactory(jmsUserName,jmsUserPassword,inboundJmsBrokerUrl);
			}
			if (destination == null) {
				destination = new ActiveMQQueue(destinationName);
			}
			if (connection == null) {
				connection = connectionFactory.createConnection();
			}
			try {
				session = connection.createSession(false,
						Session.AUTO_ACKNOWLEDGE);
			} catch (JMSException connectionFailed) {
				// Connection may have failed try to create a new connection:
				connection = connectionFactory.createConnection();
				session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			}
			MessageProducer producer = session.createProducer(destination);
			RequestGeneratorHelper rgh = new RequestGeneratorHelper();
			rgh.setCaxchangeRequest(caXchangeRequestMessage);
			String req = rgh.getRequestForCaxchange();
			TextMessage message = session.createTextMessage();
			logger.debug("Sending message:" + req);
			message.setText(req);

			// commented lines below can be used for simulating synchronous
			// response to the client
			// TemporaryQueue outDest = session.createTemporaryQueue();
			// message.setJMSReplyTo(outDest);
			producer.send(message);

			// MessageConsumer consumer = session.createConsumer(outDest);
			// Message msgBack = null;
			// msgBack = consumer.receive(12000);
		} catch (Exception ex) {
			logger.error("Error sending message to the ESB.", ex);
			responseListeners = null;
			throw new Exception("Error sending message to the ESB.", ex);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Updating the Resource with the error Response.
	 *
	 * @param caXchangeRequestMessage
	 * @throws Exception
	 */
	public void updateErrorResponse(
			gov.nih.nci.caxchange.Message caXchangeRequestMessage,
			ResourceKey resKey, String errorCode, String errorMessage)
			throws Exception {
		try {
			ResponseHandler responseHandler = new ResponseHandler();
			ResponseMessage response = responseHandler.getResponseFromError(
					caXchangeRequestMessage, errorCode, errorMessage);
			gov.nih.nci.cagrid.caxchange.context.service.globus.resource.CaXchangeResponseServiceResourceHome ctxResourceHome = getCaXchangeResponseServiceResourceHome();
			CaXchangeResponseServiceResource resource = (CaXchangeResponseServiceResource) ctxResourceHome
					.find(resKey);
			resource.setCaXchangeResponseMessage(response);
		} catch (Exception e) {
			logger.error("Error updating resource with the error response.", e);
			throw e;
		}
	}

	/**
	 * Register listeners for the response queue to update the Resources.
	 *
	 * @throws Exception
	 */
	public void registerResponseListeners() throws Exception {
		try {
			CaXchangeRequestProcessorConfiguration configuration = getConfiguration();
			CaXchangeExternalProperties properties = CaXchangeExternalProperties.getInstance();
			String outboundJmsBrokerUrl = properties.getProperty("outbound.jms.brokerURL");
			String jmsUserName = properties.getProperty("amq.caxchange.user");
			String jmsUserPassword = properties.getProperty("amq.caxchange.password");
			if (outboundJmsBrokerUrl == null) {
				outboundJmsBrokerUrl = configuration.getCaXchangeInboundBrokerURL();
			}
			if (connectionFactory == null) {
				connectionFactory = new ActiveMQConnectionFactory(jmsUserName, jmsUserPassword,
						outboundJmsBrokerUrl);
			}
			if (replyDestination == null) {
				replyDestination = new ActiveMQQueue(replyDestinationName);
			}
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			if ((responseListeners == null) || (responseListeners.entrySet().size() == 0)) {
				logger.info("Listener initiated.");
				responseListeners = new HashMap<CaxchangeResponseExceptionListener, Connection>(1);
				MessageConsumer consumer = session.createConsumer(replyDestination);
				CaxchangeResponseListener listener = new CaxchangeResponseListener();
				listener.setResourceHome(getCaXchangeResponseServiceResourceHome());
				consumer.setMessageListener(listener);
				CaxchangeResponseExceptionListener el = new CaxchangeResponseExceptionListener();
				connection.setExceptionListener(el);
				responseListeners.put(el, connection);
			}
		} catch (Exception ex) {
			logger.error("Error registering response listeners.", ex);
			throw new Exception("Error registers response listeners.", ex);
		} finally {
		}
	}

	/**
	 *
	 * @param caXchangeRequestMessage
	 * @return
	 * @throws RemoteException
	 * @throws gov.nih.nci.cagrid.caxchange.stubs.types.CaXchangeFault
	 */
  public gov.nih.nci.caxchange.ResponseMessage processRequestSynchronously(gov.nih.nci.caxchange.Message caXchangeRequestMessage)
												throws RemoteException, gov.nih.nci.cagrid.caxchange.stubs.types.CaXchangeFault {
		logger.debug("Request in processRequestSynchronously: "+ caXchangeRequestMessage);
		
		CaXchangeExternalProperties properties = CaXchangeExternalProperties.getInstance();
		try {
			gov.nih.nci.cagrid.caxchange.context.service.globus.resource.CaXchangeResponseServiceResourceHome ctxResourceHome = getCaXchangeResponseServiceResourceHome();
			ResourceKey resKey = ctxResourceHome.createResource();
			logger.info("Performance Request Received," + resKey.getValue()
					+ ","
					+ caXchangeRequestMessage.getMetadata().getServiceType()
					+ "," + new java.util.Date().getTime());			
			caXchangeRequestMessage.getMetadata().setCaXchangeIdentifier(resKey.getValue().toString());

			String caller = SecurityManager.getManager().getCaller();
			logger.debug("The caller is:'" + caller + "'");			
			if ((caller == null) || ("".equals(caller))	|| ("<anonymous>".equals(caller.trim()))) {
				logger.debug("Error occured: Unable to get the identity of the caller.Caller identity");
				return buildErrorResponse(caXchangeRequestMessage, CaxchangeErrors.PERMISSION_DENIED_FAULT,
						"Unable to get the identity of the caller.Caller identity:"+ caller);
			}
			logger.debug("Sending message for the caller:'" + caller + "'");			
			if (caXchangeRequestMessage.getMetadata().getCredentials() == null) {
				caXchangeRequestMessage.getMetadata().setCredentials(new gov.nih.nci.caxchange.Credentials());
			}
			caXchangeRequestMessage.getMetadata().getCredentials().setGridIdentifier(caller);

			HttpClient client = new HttpClient();
			BufferedReader br = null;
			String mirthHttpUrl = properties.getProperty(MIRTH_HTTP_LISTENER_URL);
			String mirthHttpListenerUser = properties.getProperty(MIRTH_HTTP_LISTENER_USER);
			String mirthHttpListenerPassowrd = properties.getProperty(MIRTH_HTTP_LISTENER_PASSWORD);
						
			ResponseMessage responseMessageToClient = new ResponseMessage();

			PostMethod method = new PostMethod(mirthHttpUrl);
						
			RequestGeneratorHelper rgh = new RequestGeneratorHelper();
			rgh.setCaxchangeRequest(caXchangeRequestMessage);
			String reqMessage = rgh.getRequestForCaxchange();
			
			logger.debug("Synchronous Message: "+reqMessage);
			
			method.addParameter("synchronous_msg", reqMessage);
			method.addRequestHeader(MIRTH_HTTP_LISTENER_USER, mirthHttpListenerUser);
			method.addRequestHeader(MIRTH_HTTP_LISTENER_PASSWORD, mirthHttpListenerPassowrd);

			try {
				int returnCode = client.executeMethod(method);

				if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {										
					method.getResponseBodyAsString();
				} else {
					br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
					StringBuffer responseStringBuffer = new StringBuffer();
					String readLine = "";
					while ((readLine = br.readLine()) != null) {			
						responseStringBuffer.append(readLine);
					}
					
					ResponseHandler responseHandler = new ResponseHandler();
			        responseHandler.setResponseText(responseStringBuffer.toString());
			        return responseHandler.getResponse();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				method.releaseConnection();
				if (br != null)
					try {
						br.close();
					} catch (Exception fe) {
						fe.printStackTrace();
					}
			}
			logger.debug("After sending message " + new Date().getTime());
			StringWriter stringWriter = new StringWriter();
			if (logger.isDebugEnabled()) {
				Utils.serializeObject(responseMessageToClient, new QName("http://caXchange.nci.nih.gov/messaging","caXchangeResponseMessage"), stringWriter);
				logger.debug(stringWriter);
			}
			return responseMessageToClient;
		} catch (Exception e) {
			logger.error("An error occured sending Synchronous message to the caXchange hub.",e);
			try {
				return buildErrorResponse(caXchangeRequestMessage,CaxchangeErrors.UNKNOWN,
						"An error occured sending message to the caXchange hub."+ e.getMessage());
			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			}
		}		
	}
		
	protected String replaceEmptyNamespaces(String documentAsString) {
		Matcher matcher = emptyNamespacePattern.matcher(documentAsString);
		return matcher.replaceAll("");
	}
	/**
	 * If there is an error in the processing, this method gets called which
	 * builds the ResponseMessage object using caXchangeRequestmessage,
	 * errorCode, and errormessage
	 *
	 * @param caXchangeRequestMessage
	 * @param errorCode
	 * @param errorMessage
	 * @return ResponseMessage
	 * @throws Exception
	 */
	private ResponseMessage buildErrorResponse(
			gov.nih.nci.caxchange.Message caXchangeRequestMessage,
			String errorCode, String errorMessage) throws Exception {
		try {
			ResponseHandler responseHandler = new ResponseHandler();
			ResponseMessage response = responseHandler.getResponseFromError(
					caXchangeRequestMessage, errorCode, errorMessage);
			return response;
		} catch (Exception e) {
			logger.error("Error updating resource with the error response.", e);
			throw e;
		}
	}

}
