/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.reader.cxf.impl;

import gov.nih.nci.ihub.reader.cxf.CaXchangeRequestPortType;
import gov.nih.nci.ihub.reader.cxf.Message;
import gov.nih.nci.ihub.reader.cxf.ResponseMessage;
import gov.nih.nci.ihub.util.HubConstants;
import gov.nih.nci.ihub.util.IntegrationHubUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@javax.jws.WebService(serviceName = "CaXchangeRequestService", portName = "soap", targetNamespace = "http://caXchange.nci.nih.gov/caxchangerequest", wsdlLocation = "classpath:caXchangeRequest.wsdl", endpointInterface = "gov.nih.nci.ihub.reader.cxf.CaXchangeRequestPortType")
public class IhubCXFInboundImpl implements CaXchangeRequestPortType {

	private static Properties properties;
	protected static Logger logger = LogManager
			.getLogger(IhubCXFInboundImpl.class.getName());

	static {
		URL propsUrl = IHubInboundServer.class.getClassLoader().getResource(
				"ihub.cxf.inbound.properties");
		properties = new Properties();
		if (propsUrl != null) {
			try {
				properties.load(propsUrl.openStream());
			} catch (IOException e) {
				logger.error("Error loading ihub.cxf.inbound.properties file.",
						e);
			}
		}
	}

	/**
	 * Invokes the HttpListener interface of Mirth and sends the response back
	 */
	public ResponseMessage processRequest(Message cxfRequestMessage) {
		logger.info("Executing operation processRequest");

		HttpClient client = new HttpClient();
		BufferedReader br = null;
		String mirthHttpUrl = properties
				.getProperty(HubConstants.MIRTH_HTTP_LISTENER_URL);
		String mirthHttpListenerUser = properties
				.getProperty(HubConstants.MIRTH_HTTP_LISTENER_USER);
		String mirthHttpListenerPassowrd = properties
				.getProperty(HubConstants.MIRTH_HTTP_LISTENER_PASSWORD);

		ResponseMessage cxfResponseMessage = new ResponseMessage();

		PostMethod method = new PostMethod(mirthHttpUrl);
		logger.info("Sending message to mirth http listener URL."+mirthHttpUrl);
		try {
			logger.info("Synchronous Message: "
					+ IntegrationHubUtil
							.getStringFromCXFMessage(cxfRequestMessage));
			method.addParameter("synchronous_msg", IntegrationHubUtil
					.getStringFromCXFMessage(cxfRequestMessage));
			method.addRequestHeader(HubConstants.MIRTH_HTTP_LISTENER_USER,
					mirthHttpListenerUser);
			method.addRequestHeader(HubConstants.MIRTH_HTTP_LISTENER_PASSWORD,
					mirthHttpListenerPassowrd);

			int returnCode = client.executeMethod(method);

			if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
				method.getResponseBodyAsString();
			} else {
				br = new BufferedReader(new InputStreamReader(method
						.getResponseBodyAsStream()));
				StringBuffer responseStringBuffer = new StringBuffer();
				String readLine = "";
				while ((readLine = br.readLine()) != null) {
					responseStringBuffer.append(readLine);
				}

				cxfResponseMessage = IntegrationHubUtil
						.getCXFResponseFromString(responseStringBuffer
								.toString());
			}
		} catch (Exception e) {
			logger.error("Error sending message to the Mirth Http listener.",e);
			
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
		return cxfResponseMessage;

	}

}
