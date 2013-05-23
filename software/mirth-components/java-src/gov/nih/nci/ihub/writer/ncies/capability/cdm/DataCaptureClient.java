/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.CCLoadLabsFaultMessage;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.CCSystemFaultMessage;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.CCLoadLabsFault;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.CCSystemFault;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.LoadLabsRequest;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.LoadLabsResponse;

import javax.xml.soap.SOAPException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Stub;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * DataCaptureClient implements all operations in DataCapture profile.
 * 
 * @author John Chen
 * 
 */
public class DataCaptureClient extends CCClient {
	protected static Logger logger = LogManager
			.getLogger(DataCaptureClient.class);

	/**
	 * constructor
	 */
	public DataCaptureClient() {
		super();
	}

	/**
	 * constructor
	 * 
	 * @param ccURL
	 * @param ccUsername
	 * @param ccPassword
	 */
	public DataCaptureClient(String ccURL, String ccUsername, String ccPassword, long ccTimeout) {
		super(ccURL, ccUsername, ccPassword, ccTimeout);
	}

	public String convertToString(CCLoadLabsFaultMessage loadLabsFault)
			throws InvokeClinicalConnectorException {
		try {
			CCLoadLabsFault cllf = loadLabsFault.getFaultMessage();
			OMElement omElement = cllf.getOMElement(CCLoadLabsFault.MY_QNAME,
					OMAbstractFactory.getOMFactory());
			return omElement.toString();
		} catch (Exception e) {
			logger.error("Error handling load labs fault.", e);
			throw new InvokeClinicalConnectorException(
					"Error handling load labs fault.", e);
		}
	}

	public String convertToString(CCSystemFaultMessage systemFault)
			throws InvokeClinicalConnectorException {
		try {
			gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.CCSystemFault csf = systemFault
					.getFaultMessage();
			OMElement omElement = csf.getOMElement(CCSystemFault.MY_QNAME,
					OMAbstractFactory.getOMFactory());
			return omElement.toString();
		} catch (Exception e) {
			logger.error("Error handling System fault.", e);
			throw new InvokeClinicalConnectorException(
					"Error handling system fault.", e);
		}
	}

	/**
	 * loadLabs
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public LoadLabsResponse loadLabs(LoadLabsRequest request)
			throws InvokeClinicalConnectorException {
		LoadLabsResponse response = null;

		try {
			Stub stub = getStub();
			response = ((DataCaptureServiceStub) stub).loadLabs(request);
		} catch (CCLoadLabsFaultMessage businessFault) {
			throw new InvokeClinicalConnectorException(
					convertToString(businessFault));
		} catch (CCSystemFaultMessage systemFault) {
			throw new InvokeClinicalConnectorException(
					convertToString(systemFault));
		} catch (Exception ex) {
			throw new InvokeClinicalConnectorException("Error in loadLabs: "
					+ ex.getMessage(), ex);
		}

		return response;
	}

	/**
	 * rollbackLoadLabs
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public LoadLabsResponse rollbackLoadLabs(LoadLabsRequest request)
			throws InvokeClinicalConnectorException {
		LoadLabsResponse response = null;

		try {
			Stub stub = getStub();
			response = ((DataCaptureServiceStub) stub)
					.rollbackLoadLabs(request);
		} catch (CCLoadLabsFaultMessage businessFault) {
			throw new InvokeClinicalConnectorException(
					convertToString(businessFault));
		} catch (CCSystemFaultMessage systemFault) {
			throw new InvokeClinicalConnectorException(
					convertToString(systemFault));
		} catch (Exception ex) {
			throw new InvokeClinicalConnectorException(
					"Error in rollbackLoadLabs: " + ex.getMessage(), ex);
		}

		return response;
	}

	/**
	 * Creates a stub.
	 */
	@Override
	public Stub getStub() throws AxisFault, SOAPException {
		DataCaptureServiceStub stub = new DataCaptureServiceStub(getCCURL());
		configStub(stub);
		return stub;
	}
}
