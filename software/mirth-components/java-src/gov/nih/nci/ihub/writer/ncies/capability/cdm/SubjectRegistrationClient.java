package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.CCBusinessFaultMessage;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.CCDataValidationFaultMessage;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.CCSystemFaultMessage;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.CCSystemFault;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.CCBusinessFault;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.CCDataValidationFault;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.RegisterSubjectRequest;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.RegisterSubjectResponse;

import javax.xml.soap.SOAPException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Stub;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * SubjectRegistrationClient implements all operations in SubjectRegistration
 * profile.
 * 
 * @author John Chen
 * 
 */
public class SubjectRegistrationClient extends CCClient {

	Logger logger = LogManager.getLogger(SubjectRegistrationClient.class);

	/**
	 * constructor
	 */
	public SubjectRegistrationClient() {
		super();
	}

	/**
	 * constructor
	 * 
	 * @param ccURL
	 * @param ccUsername
	 * @param ccPassword
	 */
	public SubjectRegistrationClient(String ccURL, String ccUsername,
			String ccPassword) {
		super(ccURL, ccUsername, ccPassword);
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
			logger.error("Error handling register subject system fault.", e);
			throw new InvokeClinicalConnectorException(
					"Error handling register subject system fault.", e);
		}
	}

	public String convertToString(CCBusinessFaultMessage businessFault)
			throws InvokeClinicalConnectorException {
		try {
			CCBusinessFault cbf = businessFault.getFaultMessage();
			OMElement omElement = cbf.getOMElement(CCBusinessFault.MY_QNAME,
					OMAbstractFactory.getOMFactory());
			return omElement.toString();
		} catch (Exception e) {
			logger.error("Error handling register subject  business fault.", e);
			throw new InvokeClinicalConnectorException(
					"Error handling register subject business fault.", e);
		}
	}

	public String convertToString(CCDataValidationFaultMessage businessFault)
			throws InvokeClinicalConnectorException {
		try {
			CCDataValidationFault cbf = businessFault.getFaultMessage();
			OMElement omElement = cbf.getOMElement(
					CCDataValidationFault.MY_QNAME, OMAbstractFactory
							.getOMFactory());
			return omElement.toString();
		} catch (Exception e) {
			logger.error(
					"Error handling register subject  data validation fault.",
					e);
			throw new InvokeClinicalConnectorException(
					"Error handling register subject  data validation  fault.",
					e);
		}
	}

	/**
	 * registerSubject
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public RegisterSubjectResponse registerSubject(
			RegisterSubjectRequest request)
			throws InvokeClinicalConnectorException {
		RegisterSubjectResponse response = null;

		try {
			Stub stub = getStub();
			/*
			 * Code to provide a custom header for Medidata if
			 * (isUseMedidataFormatter()) { Options overrideOptions = new
			 * Options(); overrideOptions
			 * .setProperty(Constants.Configuration.MESSAGE_TYPE,
			 * "RegisterSubject");
			 * stub._getServiceClient().setOverrideOptions(overrideOptions); }
			 */
			/*
			 * Proxy to charles for debugging
			 * HttpTransportProperties.ProxyProperties pp = new
			 * HttpTransportProperties.ProxyProperties();
			 * pp.setProxyName("localhost"); pp.setProxyPort(8888);
			 * stub._getServiceClient
			 * ().getOptions().setProperty(HTTPConstants.PROXY,pp);
			 */
			response = ((SubjectRegistrationServiceStub) stub)
					.registerSubject(request);
		} catch (CCDataValidationFaultMessage dvFault) {
			throw new InvokeClinicalConnectorException(convertToString(dvFault));
		} catch (CCBusinessFaultMessage businessFault) {
			throw new InvokeClinicalConnectorException(
					convertToString(businessFault));
		} catch (CCSystemFaultMessage systemFault) {
			throw new InvokeClinicalConnectorException(
					convertToString(systemFault));
		} catch (Exception ex) {
			throw new InvokeClinicalConnectorException(
					"Error in registerSubject: " + ex.getMessage(), ex);
		}

		return response;
	}

	/**
	 * rollbackRegisterSubject
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public RegisterSubjectResponse rollbackRegisterSubject(
			RegisterSubjectRequest request)
			throws InvokeClinicalConnectorException {
		RegisterSubjectResponse response = null;

		try {
			Stub stub = getStub();
			/*
			 * Proxy to charles for debugging
			 * HttpTransportProperties.ProxyProperties pp = new
			 * HttpTransportProperties.ProxyProperties();
			 * pp.setProxyName("localhost"); pp.setProxyPort(8888);
			 * stub._getServiceClient
			 * ().getOptions().setProperty(HTTPConstants.PROXY,pp);
			 */
			response = ((SubjectRegistrationServiceStub) stub)
					.rollbackRegisterSubject(request);
		} catch (CCDataValidationFaultMessage dvFault) {
			throw new InvokeClinicalConnectorException(convertToString(dvFault));
		} catch (CCBusinessFaultMessage businessFault) {
			throw new InvokeClinicalConnectorException(
					convertToString(businessFault));
		} catch (CCSystemFaultMessage systemFault) {
			throw new InvokeClinicalConnectorException(
					convertToString(systemFault));
		} catch (Exception ex) {
			throw new InvokeClinicalConnectorException(
					"Error in rollbackRegisterSubject: " + ex.getMessage(), ex);
		}

		return response;
	}

	/**
	 * Creates a stub.
	 */
	@Override
	public Stub getStub() throws AxisFault, SOAPException {
		/*
		 * Code to provide a custom header for Medidata ConfigurationContext cc
		 * = null; try { cc =
		 * ConfigurationContextFactory.createDefaultConfigurationContext(); if
		 * (isUseMedidataFormatter()) { AxisConfiguration axisConfiguration =
		 * cc.getAxisConfiguration();
		 * axisConfiguration.addMessageFormatter("RegisterSubject", new
		 * RegisterSubjectMedidataMessageFormatter()); } }catch(Exception e){
		 * logger.warn(
		 * "Error creating the ConfigurationContext. Proceeding with a null configuration context."
		 * , e); }
		 */
		SubjectRegistrationServiceStub stub = new SubjectRegistrationServiceStub(
				getCCURL());
		/*
		 * if (cc != null) { stub = new
		 * SubjectRegistrationServiceStub(cc,getCCURL()); } else { stub = new
		 * SubjectRegistrationServiceStub(getCCURL()); }
		 */
		configStub(stub);
		return stub;
	}

}
