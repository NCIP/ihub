package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.CCTransformException;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.CCTransformer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.w3c.dom.Node;

/**
 * CCCaller has common operations for all callers.
 * 
 * @author John Chen
 */
public abstract class CCCaller {

	protected String ccSystemName = "CC";
	protected CCTransformer ccTransformer = null;
	protected String clientClassName = "";
	protected static Logger logger = LogManager.getLogger(CCCaller.class);

	public String getCcSystemName() {
		return ccSystemName;
	}

	public void setCcSystemName(String ccSystemName) {
		this.ccSystemName = ccSystemName;
	}

	public CCTransformer getCcTransformer() {
		return ccTransformer;
	}

	public void setCcTransformer(CCTransformer ccTransformer) {
		this.ccTransformer = ccTransformer;
	}

	public CCCaller() {
	}

	public String getSystemName() {
		return ccSystemName;
	}

	public void setSystemName(String ccSystemName) {
		this.ccSystemName = ccSystemName;
	}

	public abstract Node invokeOperation(Node node)
			throws InvokeClinicalConnectorException, CCTransformException;

	public abstract Node invokeRollbackOperation(Node node)
			throws InvokeClinicalConnectorException, CCTransformException;

	public String getXMLString(Node businessPayload)
			throws InvokeClinicalConnectorException {
		try {
			return (new SourceTransformer()).toString(businessPayload);
		} catch (Exception e) {
			logger.error("Error transforming business payload to string.", e);
			throw new InvokeClinicalConnectorException(
					"Error transforming business payload to string.", e);
		}
	}
}