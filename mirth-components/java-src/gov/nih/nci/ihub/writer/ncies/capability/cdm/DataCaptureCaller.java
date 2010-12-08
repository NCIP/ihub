package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.BL;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.LoadLabsRequest;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.LoadLabsResponse;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.Message;
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
 * A caller of data capture.
 * 
 * @author John Chen
 */
public class DataCaptureCaller extends CCCaller {

	protected Logger logger = LogManager.getLogger(DataCaptureCaller.class);
	protected DataCaptureClient dataCaptureClient = null;

	public DataCaptureCaller() {
		super();
	}

	public DataCaptureClient getDataCaptureClient() {
		return dataCaptureClient;
	}

	public void setDataCaptureClient(DataCaptureClient dataCaptureClient) {
		this.dataCaptureClient = dataCaptureClient;
	}

	public Node invokeOperation(Node node)
			throws InvokeClinicalConnectorException, CCTransformException {
		String xml = getXMLString(node);

		LoadLabsRequest request = (LoadLabsRequest) ccTransformer
				.convert2Request(xml);

		DataCaptureClient client = getDataCaptureClient();
		LoadLabsResponse response = client.loadLabs(request);

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
				String errorMessage = "Error in load labs: " + code + " - "
						+ reason + ".";
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
		LoadLabsRequest request = (LoadLabsRequest) ccTransformer
				.convert2RollbackRequest(xml);

		DataCaptureClient client = getDataCaptureClient();
		LoadLabsResponse response = client.rollbackLoadLabs(request);

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
				String errorMessage = "Error in load labs: " + code + " - "
						+ reason + ".";
				throw new InvokeClinicalConnectorException(errorMessage);
			} else {
				throw new InvokeClinicalConnectorException(
						"Error message must be set in response.");
			}
		}

		return result;
	}

	public Node insertPatientPosition(String xml, String position)
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

		Attr attr = output.createAttributeNS(namespace, "type");
		attr.setValue("SystemAssignedIdentifierType");

		ppNode.appendChild(positionValue);
		ppNode.appendChild(systemName);
		ppNode.setAttributeNode(attr);
		root.appendChild(ppNode);

		if (prefix != null && !"".equals(prefix.trim())) {
			ppNode.setPrefix(prefix);
			positionValue.setPrefix(prefix);
			systemName.setPrefix(prefix);
			attr.setValue(prefix + ":" + "SystemAssignedIdentifierType");
		}

		return output;
	}

}