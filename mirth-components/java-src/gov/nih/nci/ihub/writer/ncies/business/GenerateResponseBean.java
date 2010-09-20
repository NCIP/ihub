package gov.nih.nci.ihub.writer.ncies.business;

import gov.nih.nci.caXchange.messaging.CaXchangeResponseMessageDocument;
import gov.nih.nci.caXchange.messaging.ErrorDetails;
import gov.nih.nci.caXchange.messaging.MessagePayload;
import gov.nih.nci.caXchange.messaging.MessageStatuses;
import gov.nih.nci.caXchange.messaging.Response;
import gov.nih.nci.caXchange.messaging.ResponseMessage;
import gov.nih.nci.caXchange.messaging.ResponseMetadata;
import gov.nih.nci.caXchange.messaging.Statuses;
import gov.nih.nci.caXchange.messaging.TargetResponseMessage;
import gov.nih.nci.ihub.util.HubConstants;
import gov.nih.nci.ihub.util.IntegrationHubNamespaceContext;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;
import gov.nih.nci.ihub.writer.ncies.exception.TransformationException;

import java.io.IOException;
import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.axis.AxisFault;
import org.apache.axis.types.URI.MalformedURIException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.apache.xmlbeans.XmlOptions;
import org.globus.wsrf.encoding.DeserializationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GenerateResponseBean {

	public static Logger logger = LogManager
			.getLogger(GenerateResponseBean.class);

	/**
	 * Creates the output document for target grid service
	 * 
	 * @param exchange
	 * @return output
	 * @throws Exception
	 */
	public Document createBaseOutputDocument(String targetName,
			String operationName) throws Exception {
		Document output = new SourceTransformer().createDocument();
		Element root = output.createElement(HubConstants.RESPONSE_ELEMENT);
		Element targetServiceId = output
				.createElement(HubConstants.TARGET_SERVICE_ID_ELEMENT);
		targetServiceId.setTextContent(targetName);
		Element targetServiceOp = output
				.createElement(HubConstants.TARGET_SERVICE_OP_ELEMENT);

		targetServiceOp.setTextContent(operationName);
		root.appendChild(targetServiceId);
		root.appendChild(targetServiceOp);
		output.appendChild(root);
		return output;
	}

	/**
	 * This methods creates output document with grid message
	 * 
	 * @param exchange
	 * @param gridMessage
	 * @param result
	 * @return output
	 * @throws Exception
	 */
	public Document createOutputDocument(String targetName,
			String operationName, String inboundMessageSchemaDefinition,
			GridInvocationResult result) throws Exception {

		Document output = createBaseOutputDocument(targetName, operationName);
		Element root = output.getDocumentElement();
		Element targetStatus = output
				.createElement(HubConstants.TARGET_STATUS_ELEMENT);
		if (result.isFault()) {
			targetStatus.setTextContent("FAULT");
		} else {
			targetStatus.setTextContent("RESPONSE");
		}
		root.appendChild(targetStatus);

		Element payloadElement = output
				.createElement(HubConstants.RESPONSE_PAYLOAD_ELEMENT);

		if (inboundMessageSchemaDefinition != null) {
			Element schemaDefElement = output
					.createElement(HubConstants.SCHEMA_DEFINITION_ELEMENT);
			schemaDefElement.setTextContent(inboundMessageSchemaDefinition);
			payloadElement.appendChild(schemaDefElement);
		}
		payloadElement.appendChild(output.importNode(result.getResult(), true));
		root.appendChild(payloadElement);
		return output;
	}

	/**
	 * This method creates the error code document with the description of the
	 * causes of error
	 * 
	 * @param exchange
	 * @param e
	 * @return output
	 * @throws Exception
	 */
	public Document createErrorDocument(String targetName,
			String operationName, Exception e) throws Exception {
		Document output = createBaseOutputDocument(targetName, operationName);
		Element root = output.getDocumentElement();
		Element targetStatus = output
				.createElement(HubConstants.TARGET_STATUS_ELEMENT);
		targetStatus.setTextContent("ERROR");
		root.appendChild(targetStatus);
		Element payloadElement = output
				.createElement(HubConstants.ERROR_RESPONSE_ELEMENT);
		Element errorCode = output
				.createElement(HubConstants.ERROR_CODE_ELEMENT);
		Element errorDescription = output
				.createElement(HubConstants.ERROR_DESCRIPTION_ELEMENT);
		String errorCodeText = findErrorCodeForException(e);
		if ("".equals(errorCodeText)) {
			errorCodeText = HubConstants.UNKNOWN;
		}
		errorCode.setTextContent(errorCodeText);

		String errorDesc = e.getMessage();
		if (e instanceof AxisFault) {
			errorDesc = ((AxisFault) e).getFaultString();
		}
		errorDescription.setTextContent(errorDesc);
		payloadElement.appendChild(errorCode);
		payloadElement.appendChild(errorDescription);
		root.appendChild(payloadElement);
		return output;
	}

	/**
	 * This methods gives the error code for the exception
	 * 
	 * @param e
	 * @return errorCode
	 * @throws
	 */
	public static String findErrorCodeForException(Throwable e) {

		String errorCode = "";
		if (e instanceof GridInvocationException) {
			errorCode = findErrorCodeForException(e.getCause());
		}
		if (e instanceof AxisFault) {
			errorCode = findErrorCodeForException(e.getCause());
		}
		if ("".equals(errorCode) || HubConstants.UNKNOWN.equals(errorCode)) {
			if (e instanceof AxisFault) {
				errorCode = HubConstants.AXIS_FAULT;
			} else if (e instanceof MalformedURIException) {
				errorCode = HubConstants.MALFORMED_URI;
			} else if (e instanceof RemoteException) {
				errorCode = HubConstants.REMOTE_EXCEPTION;
			} else if (e instanceof TransformationException) {
				errorCode = HubConstants.TRANSFORMER_EXCEPTION;
			} else if (e instanceof DeserializationException) {
				errorCode = HubConstants.DESERIALIZATION_EXCEPTION;
			} else if (e instanceof SAXException) {
				errorCode = HubConstants.SAX_EXCEPTION;
			} else if (e instanceof ParserConfigurationException) {
				errorCode = HubConstants.PARSER_CONFIGURATION_EXCEPTION;
			} else if (e instanceof IOException) {
				errorCode = HubConstants.IO_EXCEPTION;
			} else if (e instanceof ConnectException) {
				errorCode = HubConstants.CONNECT_EXCEPTION;
			}
		}

		if ("".equals(errorCode)) {
			errorCode = HubConstants.UNKNOWN;
		}

		return errorCode;
	}

	/**
	 * 
	 * @param consumerIdentifier
	 * @param messageServiceRequestID
	 * @param compiledTargetResponse
	 * @return org.w3c.dom.Node
	 * @throws Exception
	 */
	public Node generateResponseFromTargetResponse(String consumerIdentifier,
			long messageServiceRequestID, Document compiledTargetResponse)
			throws Exception {
		XmlOptions xmlOptions = new XmlOptions();
		HashMap<String, String> ns = new HashMap<String, String>();
		ns.put("", "http://caXchange.nci.nih.gov/messaging");
		CaXchangeResponseMessageDocument responseDocument = CaXchangeResponseMessageDocument.Factory
				.newInstance(xmlOptions);

		ResponseMessage responseMessage = responseDocument
				.addNewCaXchangeResponseMessage();
		ResponseMetadata responseMetaData = responseMessage
				.addNewResponseMetadata();
		responseMetaData.setCaXchangeIdentifier(new Long(
				messageServiceRequestID).toString());
		responseMetaData.setExternalIdentifier(consumerIdentifier);
		Response response = responseMessage.addNewResponse();
		response.setResponseStatus(Statuses.SUCCESS);
		buildTargetResponse(compiledTargetResponse.getFirstChild(), response);

		logger.debug(responseDocument.xmlText());
		return responseDocument.getDomNode();
		// return targetResponseMessage.getDomNode();
	}

	/**
	 * 
	 * @param targetResponse
	 * @param response
	 * @return gov.nih.nci.caXchange.messaging.TargetResponseMessage
	 * @throws Exception
	 */
	public TargetResponseMessage buildTargetResponse(Node compiledTargetNode,
			Response response) throws Exception {
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression tsiExp = xpath.compile("targetServiceIdentifier");
		XPathExpression tsoExp = xpath.compile("targetServiceOperation");
		XPathExpression msExp = xpath.compile("targetMessageStatus");
		XPathExpression mpExp = xpath.compile("targetBusinessMessage");
		XPathExpression teExp = xpath.compile("targetError");
		XPathExpression schemaDefExp = xpath
				.compile("/targetResponse/targetBusinessMessage/xmlSchemaDefinition");
		Statuses.Enum responseStatus = Statuses.SUCCESS;
		Node brNode = compiledTargetNode;
		TargetResponseMessage brm = response.addNewTargetResponse();
		brm.setTargetServiceIdentifier((String) tsiExp.evaluate(brNode,
				XPathConstants.STRING));
		brm.setTargetServiceOperation((String) tsoExp.evaluate(brNode,
				XPathConstants.STRING));
		String targetMessageStatus = (String) msExp.evaluate(brNode,
				XPathConstants.STRING);
		if (MessageStatuses.FAULT.toString().equals(targetMessageStatus)) {
			brm.setTargetMessageStatus(MessageStatuses.FAULT);
			responseStatus = Statuses.FAILURE;
		} else if (MessageStatuses.ERROR.toString().equals(targetMessageStatus)) {
			brm.setTargetMessageStatus(MessageStatuses.ERROR);
			responseStatus = Statuses.FAILURE;
		} else if (MessageStatuses.RESPONSE.toString().equals(
				targetMessageStatus)) {
			brm.setTargetMessageStatus(MessageStatuses.RESPONSE);
		}
		Node mpNode = (Node) mpExp.evaluate(brNode, XPathConstants.NODE);
		if (mpNode != null) {
			MessagePayload payload = brm.addNewTargetBusinessMessage();
			// payload.setXmlSchemaDefinition((String)schemaDefExp.evaluate(mpNode,
			// XPathConstants.STRING));
			Node payloadNode = payload.getDomNode();
			Document doc = payloadNode.getOwnerDocument();
			NodeList mpNodes = mpNode.getChildNodes();
			for (int k = 0; k < mpNodes.getLength(); k++) {
				Node mpcNode = mpNodes.item(k);
				if (mpcNode instanceof Element) {
					if (!("xmlSchemaDefinition".equals(mpcNode.getNodeName()))) {
						Node importedNode = doc.importNode(mpcNode, true);
						payloadNode.appendChild(importedNode);
					}
					if (("xmlSchemaDefinition".equals(mpcNode.getNodeName()))) {
						logger.debug("MPC node name:" + mpcNode.getNodeName()
								+ " " + mpcNode.getTextContent());
						payload.setXmlSchemaDefinition(mpcNode.getTextContent()
								.trim());
					}
				}
			}
		}
		Node teNode = (Node) teExp.evaluate(brNode, XPathConstants.NODE);
		if (teNode != null) {
			ErrorDetails targetError = ErrorDetails.Factory.newInstance();
			NodeList teNodes = teNode.getChildNodes();
			for (int k = 0; k < teNodes.getLength(); k++) {
				Node teChildNode = teNodes.item(k);
				if ("errorCode".equals(teChildNode.getNodeName())) {
					targetError.setErrorCode(teChildNode.getTextContent());
				}
				if ("errorDescription".equals(teChildNode.getNodeName())) {
					targetError.setErrorDescription(teChildNode
							.getTextContent());
				}
			}
			brm.setTargetError(targetError);
		}
		response.setResponseStatus(responseStatus);
		return brm;
	}

	/**
	 * This method generates a CaXchangeResponseMessageDocument from the
	 * aggregated Response.
	 * 
	 * @param aggregatedResponse
	 * @return CaXchangeResponseMessageDocument
	 * @throws Exception
	 */
	public Node generateResponseFromAggregatedResponse(
			String consumerIdentifier, String messageID,
			Document aggregatedTargetResponse) throws Exception {
		CaXchangeResponseMessageDocument responseDocument = CaXchangeResponseMessageDocument.Factory
				.newInstance();
		ResponseMessage responseMessage = responseDocument
				.addNewCaXchangeResponseMessage();
		ResponseMetadata responseMetaData = responseMessage
				.addNewResponseMetadata();
		responseMetaData.setCaXchangeIdentifier(messageID);
		responseMetaData.setExternalIdentifier(consumerIdentifier);
		Response response = responseMessage.addNewResponse();
		response = buildResponse(aggregatedTargetResponse, response);

		return responseDocument.getDomNode();
	}

	/**
	 * This method builds a caXchangeResponse from he aggregateResponse.
	 * 
	 * @param aggregatedResponse
	 * @param response
	 * @return response
	 * @throws Exception
	 */
	public Response buildResponse(Node aggregatedResponse, Response response)
			throws Exception {
		XPath xpath = XPathFactory.newInstance().newXPath();
		IntegrationHubNamespaceContext cnc = new IntegrationHubNamespaceContext();
		cnc.addNameSpace("caxchange", "http://caXchange.nci.nih.gov/messaging");
		// Node node = ((DOMSource) aggregatedResponse).getNode();
		XPathExpression expression = xpath
				.compile("/aggregateResponse/targetResponse");
		XPathExpression tsiExp = xpath.compile("targetServiceIdentifier");
		XPathExpression tsoExp = xpath.compile("targetServiceOperation");
		XPathExpression msExp = xpath.compile("targetMessageStatus");
		XPathExpression mpExp = xpath.compile("targetBusinessMessage");
		XPathExpression teExp = xpath.compile("targetError");
		XPathExpression schemaDefExp = xpath
				.compile("/aggregateResponse/targetResponse/targetBusinessMessage/xmlSchemaDefinition");
		NodeList nodes = (NodeList) expression.evaluate(aggregatedResponse,
				XPathConstants.NODESET);
		Statuses.Enum responseStatus = Statuses.SUCCESS;
		for (int i = 0; i < nodes.getLength(); i++) {
			Node brNode = nodes.item(i);
			TargetResponseMessage brm = response.addNewTargetResponse();
			brm.setTargetServiceIdentifier((String) tsiExp.evaluate(brNode,
					XPathConstants.STRING));
			brm.setTargetServiceOperation((String) tsoExp.evaluate(brNode,
					XPathConstants.STRING));
			String targetMessageStatus = (String) msExp.evaluate(brNode,
					XPathConstants.STRING);
			if (MessageStatuses.FAULT.toString().equals(targetMessageStatus)) {
				brm.setTargetMessageStatus(MessageStatuses.FAULT);
				responseStatus = Statuses.FAILURE;
			} else if (MessageStatuses.ERROR.toString().equals(
					targetMessageStatus)) {
				brm.setTargetMessageStatus(MessageStatuses.ERROR);
				responseStatus = Statuses.FAILURE;
			} else if (MessageStatuses.RESPONSE.toString().equals(
					targetMessageStatus)) {
				brm.setTargetMessageStatus(MessageStatuses.RESPONSE);
			}
			Node mpNode = (Node) mpExp.evaluate(brNode, XPathConstants.NODE);
			if (mpNode != null) {
				MessagePayload payload = brm.addNewTargetBusinessMessage();
				// payload.setXmlSchemaDefinition((String)schemaDefExp.evaluate(mpNode,
				// XPathConstants.STRING));
				Node payloadNode = payload.getDomNode();
				Document doc = payloadNode.getOwnerDocument();
				NodeList mpNodes = mpNode.getChildNodes();
				for (int k = 0; k < mpNodes.getLength(); k++) {
					Node mpcNode = mpNodes.item(k);
					if (!("xmlSchemaDefinition".equals(mpcNode.getNodeName()))) {
						Node importedNode = doc.importNode(mpcNode, true);
						payloadNode.appendChild(importedNode);
					}
					if (("xmlSchemaDefinition".equals(mpcNode.getNodeName()))) {
						payload
								.setXmlSchemaDefinition(mpcNode
										.getTextContent());
					}
				}
			}
			Node teNode = (Node) teExp.evaluate(brNode, XPathConstants.NODE);
			if (teNode != null) {
				ErrorDetails targetError = ErrorDetails.Factory.newInstance();
				NodeList teNodes = teNode.getChildNodes();
				for (int k = 0; k < teNodes.getLength(); k++) {
					Node teChildNode = teNodes.item(k);
					if ("errorCode".equals(teChildNode.getNodeName())) {
						targetError.setErrorCode(teChildNode.getTextContent());
					}
					if ("errorDescription".equals(teChildNode.getNodeName())) {
						targetError.setErrorDescription(teChildNode
								.getTextContent());
					}
				}
				brm.setTargetError(targetError);
			}
		}
		response.setResponseStatus(responseStatus);
		return response;
	}

}
