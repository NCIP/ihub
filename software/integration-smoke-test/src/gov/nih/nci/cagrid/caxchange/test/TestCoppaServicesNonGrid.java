/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.cagrid.caxchange.test;

import gov.nih.nci.caxchange.caxchangerequest.CaXchangeRequestPortType;
import gov.nih.nci.caxchange.cxfsamples.SampleAsyncHandler;
import gov.nih.nci.caxchange.messaging.Credentials;
import gov.nih.nci.caxchange.messaging.Message;
import gov.nih.nci.caxchange.messaging.MessagePayload;
import gov.nih.nci.caxchange.messaging.Metadata;
import gov.nih.nci.caxchange.messaging.ObjectFactory;
import gov.nih.nci.caxchange.messaging.Request;
import gov.nih.nci.caxchange.messaging.Response;
import gov.nih.nci.caxchange.messaging.Statuses;
import gov.nih.nci.caxchange.messaging.TransactionControls;

import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.concurrent.Future;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

//import org.apache.axis.message.MessageElement;
//import org.apache.axis.types.URI;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TestCoppaServicesNonGrid extends TestCase {

	private String serviceType = null;
	private String payloadFileName = null;
	private String operationName = null;
	private String cxfAppContext = null;
	private String synchronousProcessing = null;
	private String multiplePayloads = null;
	private DocumentBuilderFactory dbf;

	public TestCoppaServicesNonGrid(String name) {
		super(name);
	}

	public void testCoppaServiceNonGrid() {
		try {
			serviceType = System.getProperty("service.type");
			payloadFileName = System.getProperty("payload.file.name");
			operationName = System.getProperty("operation.name");
			cxfAppContext = System.getProperty("cxfappcontext.file.name");
			synchronousProcessing = System.getProperty("synchronous");
			multiplePayloads = System.getProperty("multiple.payloads");

			Message messageToCXFBC = buildMessageToCXFBC(serviceType,
					payloadFileName, operationName);


			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"/resources/" + cxfAppContext);
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = (JaxWsProxyFactoryBean) context
					.getBean("clientFactory");
			CaXchangeRequestPortType caXchangeRequestPortType = (CaXchangeRequestPortType) jaxWsProxyFactoryBean
					.create();

			if ("true".equals(synchronousProcessing)) {
				System.out.println("Invoking the service Synchronously.");
				gov.nih.nci.caxchange.messaging.ResponseMessage _processRequest_return = caXchangeRequestPortType
						.processRequest(messageToCXFBC);
				System.out.println("RESPONSE STATUS: "
						+ _processRequest_return.getResponse()
								.getResponseStatus());
				printResponse(_processRequest_return);
				assertNotNull(_processRequest_return);
				assertEquals(_processRequest_return.getResponse()
						.getResponseStatus(), Statuses.SUCCESS);
			} else {
				System.out.println("Invoking the service Asynchronously.");
				// Callback approach - asynchronous
				SampleAsyncHandler testAsyncHandler = new SampleAsyncHandler();

				Future<?> response = caXchangeRequestPortType
						.processRequestAsync(messageToCXFBC, testAsyncHandler);
				while (!response.isDone()) {
					Thread.sleep(100);
				}
				Response resp = testAsyncHandler.getResponse();
				printResponse(testAsyncHandler.getResponseMessage());
				assertNotNull(resp);
				assertEquals(resp.getResponseStatus(), Statuses.SUCCESS);
				System.out.println("CALLBACK RESPONSE: "
						+ resp.getResponseStatus());

				// non-blocking polling approach - asynchronous
				// System.out.println("Invoking processRequestAsync...");
				// javax.xml.ws.Response<gov.nih.nci.caxchange.messaging.ResponseMessage>
				// _processRequestAsync__return = caXchangeRequestPortType
				// .processRequestAsync(messageToESB);
				// while (!_processRequestAsync__return.isDone()) {
				// System.out.println("SLEEPING");
				// Thread.sleep(100);
				//			
				// }
				// System.out.println("RESPONSE STATUS: "
				// +
				// _processRequestAsync__return.get().getResponse().getResponseStatus());

			}
		} catch (Exception e) {

			System.out.println("Error sending message .");
			e.printStackTrace();
			fail();
		}

	}

	public static Test suite() {
		TestSuite suite = new TestSuite();

		suite.addTest(new TestCoppaServicesNonGrid("testCoppaServiceNonGrid"));

		return suite;
	}

	public static void main(String[] args) {
		System.out.println("STARTING TEST");
		junit.textui.TestRunner.run(suite());
	}

	/**
	 * Builds the request message to CXF binding component
	 * 
	 * @param serviceType
	 * @param payloadFileName
	 * @param operationName
	 * @return
	 */
	private Message buildMessageToCXFBC(String serviceType,
			String payloadFileName, String operationName) throws JAXBException {

		ObjectFactory objectFactory = new ObjectFactory();
		Message requestMessageToESB = objectFactory.createMessage();
		try {
			// Create and set the metadata
			Metadata metadata = objectFactory.createMetadata();
			metadata.setTransactionControl(TransactionControls.PROCESS);

			// build the credentials object. Only one of the credential choices
			// can be set the last choice values will reset the other choices in
			// the group

			Credentials credentials = objectFactory.createCredentials();
			credentials.setUserName("ccts@nih.gov");
			credentials.setPassword("!Ccts@nih.gov1");
			metadata.setCredentials(credentials);

			metadata
					.setCaXchangeIdentifier("037068f0-23a8-11de-a5f1-d00caf9050fd");
			metadata.setExternalIdentifier("myExternalIdentifier");
			if ((operationName!=null)&&(!(operationName.equals("${operation.name}")))) {
			   metadata.setOperationName(new ObjectFactory()
				   	 .createMetadataOperationName(operationName));
			}
			metadata.setServiceType(serviceType);
			requestMessageToESB.setMetadata(metadata);

			// Create and set the request
			Request request = objectFactory.createRequest();

			MessagePayload messagePayload =  objectFactory.createMessagePayload();
			dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			if ("true".equalsIgnoreCase(multiplePayloads)) {
				messagePayload = getMessagePayloadForGetByIds(payloadFileName);
			} else {
				messagePayload.setXmlSchemaDefinition("");
				InputStream testMessage = getResourceInputStream("/payloads/"
						+ payloadFileName);				
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document payload = db.parse(testMessage);
				//JAXBElement<Element> je = new JAXBElement<Element>(new QName("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain","registration"),Element.class, payload.getDocumentElement());
				messagePayload.getAny().add(payload.getDocumentElement());

			}

			request.setBusinessMessagePayload(messagePayload);

			requestMessageToESB.setRequest(request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JAXBContext jc = JAXBContext.newInstance("gov.nih.nci.caxchange.messaging");
		Marshaller marshaller = jc.createMarshaller();
		JAXBElement<Message> jaxbElement = new JAXBElement<Message>(new QName("http://caXchange.nci.nih.gov/messaging","caXchangeRequestMessage"),Message.class,requestMessageToESB);
        marshaller.marshal(jaxbElement, System.out); 		
		return requestMessageToESB;
	}
	
	public void printResponse(gov.nih.nci.caxchange.messaging.ResponseMessage response) throws Exception{
		JAXBContext jc = JAXBContext.newInstance("gov.nih.nci.caxchange.messaging");
		Marshaller marshaller = jc.createMarshaller();
		JAXBElement<gov.nih.nci.caxchange.messaging.ResponseMessage> jaxbElement = new JAXBElement<gov.nih.nci.caxchange.messaging.ResponseMessage>(new QName("http://caXchange.nci.nih.gov/messaging","caXchangeResponseMessage"),gov.nih.nci.caxchange.messaging.ResponseMessage.class,response);
        marshaller.marshal(jaxbElement, System.out);
	}

	public MessagePayload getMessagePayloadForGetByIds(String payloadFiles)
			throws Exception {
		try {
			MessagePayload messagePayload = new MessagePayload();
			messagePayload.setXmlSchemaDefinition("");
			StringTokenizer st = new StringTokenizer(payloadFiles, ",");
			System.out.println("Payloads:" + payloadFiles);
			while (st.hasMoreTokens()) {
				String nextFile = st.nextToken(); 
				System.out.println("Adding file:" + nextFile);
				InputStream testMessage = getResourceInputStream("/payloads/"
						+ nextFile);
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document payload = db.parse(testMessage);
				messagePayload.getAny().add(payload.getDocumentElement());
			}
			return messagePayload;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public InputStream getResourceInputStream(String fileName) throws Exception {

		InputStream testMessage = this.getClass().getResourceAsStream(fileName);
		if (testMessage == null) {
			throw new Exception("Test message does not exist.");
		}
		return testMessage;

		/*
		 * ClassPathResource cpr = new ClassPathResource(fileName); if
		 * (!cpr.exists()) { throw new Exception(fileName + " does not exist.");
		 * } try { InputStream inputStream = cpr.getInputStream(); return
		 * inputStream; } catch (IOException e) { throw new
		 * Exception("Error loading file " + fileName); }
		 */
	}

}
