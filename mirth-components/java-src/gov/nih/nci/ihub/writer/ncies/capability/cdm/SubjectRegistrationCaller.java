package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.BL;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.II;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.Message;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.RegisterSubjectRequest;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.RegisterSubjectResponse;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.CCTransformException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.apache.servicemix.jbi.jaxp.StringSource;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * A caller of subject registration.
 * 
 * @author John Chen
 */
public class SubjectRegistrationCaller extends CCCaller {

	protected Logger logger = LogManager
			.getLogger(SubjectRegistrationCaller.class);
	protected SubjectRegistrationClient subjectRegistrationClient = null;

	public SubjectRegistrationCaller() {
	}

	public Node invokeOperation(Node node)
			throws InvokeClinicalConnectorException, CCTransformException {
		String xml = getXMLString(node);
		RegisterSubjectRequest request = (RegisterSubjectRequest) ccTransformer
				.convert2Request(xml);

		SubjectRegistrationClient client = getSubjectRegistrationClient();

		RegisterSubjectResponse response = client.registerSubject(request);

		BL responseIndicator = response.getIndicator();
		if (responseIndicator == null) {
			throw new InvokeClinicalConnectorException(
					"Indicator must be set in response.");
		}

		Node result = node;
		if (responseIndicator.getValue()) {
			// success case
			II pid = response.getPatientIdentifier();
			String pidName = "";
			String pidExtension = "";
			if (pid != null) {
				pidName = pid.getIdentifierName();
				pidExtension = pid.getExtension();
			}
			logger.debug("received patient identifier: " + pidName + " - "
					+ pidExtension);

			// insert patient position number into the node
			try {
				result = insertPatientPosition(xml, pidExtension);
			} catch (Exception e) {
				logger.error("Error inserting patient position", e);
				throw new InvokeClinicalConnectorException(
						"Error inserting patient position", e);
			}
		} else {
			// failure case
			Message msg = response.getMessage();
			if (msg != null) {
				int code = msg.getCode();
				String reason = msg.getReason();
				String errorMessage = "Error in registerSubject: " + code
						+ " - " + reason + ".";
				throw new InvokeClinicalConnectorException(errorMessage);
			} else {
				throw new InvokeClinicalConnectorException(
						"Error message must be set in response.");
			}
		}

		return result;
	}

	public Node invokeRollbackOperation(Node node)
			throws InvokeClinicalConnectorException, CCTransformException {
		String xml = getXMLString(node);
		RegisterSubjectRequest request = (RegisterSubjectRequest) ccTransformer
				.convert2RollbackRequest(xml);

		SubjectRegistrationClient client = getSubjectRegistrationClient();
		RegisterSubjectResponse response = client
				.rollbackRegisterSubject(request);

		BL responseIndicator = response.getIndicator();
		if (responseIndicator == null) {
			throw new InvokeClinicalConnectorException(
					"Indicator must be set in response.");
		}

		Node result = node;
		if (!responseIndicator.getValue()) {
			// failure case
			Message msg = response.getMessage();
			if (msg != null) {
				int code = msg.getCode();
				String reason = msg.getReason();
				String errorMessage = "Error in registerSubject: " + code
						+ " - " + reason + ".";
				throw new InvokeClinicalConnectorException(errorMessage);
			} else {
				throw new InvokeClinicalConnectorException(
						"Error message must be set in response.");
			}
		}

		return result;
	}

	private Node insertPatientPosition(String xml, String position)
			throws Exception {
		Document output = new SourceTransformer()
				.toDOMDocument(new StringSource(xml));
		Element root = output.getDocumentElement();
		String namespace = root.getNamespaceURI();
		String prefix = root.getPrefix();

		Element ppNode = output.createElementNS(namespace, "identifier");

		Element positionValue = output.createElementNS(namespace, "value");
		positionValue.setTextContent(position);

		Element systemName = output.createElementNS(namespace, "systemName");
		systemName.setTextContent(super.getSystemName());

		Element type = output.createElementNS(namespace, "type");
		type.setTextContent("Patient Position");

		Attr attr = output.createAttributeNS(namespace, "type");
		attr.setValue("SystemAssignedIdentifierType");
		attr.setPrefix("xsi");

		ppNode.appendChild(positionValue);
		ppNode.appendChild(systemName);
		ppNode.appendChild(type);
		ppNode.setAttribute("xmlns:xsi",
				"http://www.w3.org/2001/XMLSchema-instance");
		ppNode.setAttributeNode(attr);
		root.appendChild(ppNode);

		if (prefix != null && !"".equals(prefix.trim())) {
			ppNode.setPrefix(prefix);
			positionValue.setPrefix(prefix);
			systemName.setPrefix(prefix);
			type.setPrefix(prefix);
			attr.setValue(prefix + ":" + "SystemAssignedIdentifierType");
		}

		return output;
	}

	public SubjectRegistrationClient getSubjectRegistrationClient() {
		return subjectRegistrationClient;
	}

	public void setSubjectRegistrationClient(
			SubjectRegistrationClient subjectRegistrationClient) {
		this.subjectRegistrationClient = subjectRegistrationClient;
	}

}