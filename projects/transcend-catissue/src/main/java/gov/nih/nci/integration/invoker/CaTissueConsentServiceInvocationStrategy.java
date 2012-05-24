package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueConsentClient;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Rohit Gupta
 *
 */
public class CaTissueConsentServiceInvocationStrategy implements ServiceInvocationStrategy {

	private static Logger LOG = LoggerFactory.getLogger(CaTissueConsentServiceInvocationStrategy.class);

	private int retryCount = 0;

	private CaTissueConsentClient caTissueConsentClient;

	private XSLTTransformer xsltTransformer;
	
	
	public CaTissueConsentServiceInvocationStrategy(int retryCount,
			CaTissueConsentClient caTissueSpecimenClient,
			XSLTTransformer xsltTransformer) {
		super();
		this.retryCount = retryCount;
		this.caTissueConsentClient = caTissueSpecimenClient;
		this.xsltTransformer = xsltTransformer;
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public StrategyIdentifier getStrategyIdentifier() {
		return StrategyIdentifier.CATISSUE_REGISTER_CONSENT;
		
	}

	@Override
	public ServiceInvocationResult invoke(ServiceInvocationMessage msg) {
		ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
		try {
			// transform the InterimConsentXML into CreateSpecimenXML
			String consentListXMLStr = transformToConsentXML(msg.getMessage().getRequest());	
			
			// call the method to register Consents
			serviceInvocationResult = caTissueConsentClient.registerConsents(consentListXMLStr);
		} catch (IntegrationException e) {
			serviceInvocationResult.setInvocationException(e);
		} 
		return serviceInvocationResult;
	}

	@Override
	public ServiceInvocationResult rollback(ServiceInvocationMessage msg) {
		ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
		
		try {			
			
			System.out.println("Inside Consent Strategy..  Rollback().. msg.getOriginalData() --> " + msg.getOriginalData());
//			String consentListXMLStr = transformToConsentXML(msg.getMessage().getRequest());		
//			System.out.println("Inside Consent Strategy..  Rollback().. consentListXMLStr --> " + consentListXMLStr);
			
			String consentListXMLStr = msg.getOriginalData();
			
			// call the method to rollback Specimens
			serviceInvocationResult = caTissueConsentClient.rollbackConsents(consentListXMLStr);			
		}catch (Exception e) {
			serviceInvocationResult.setInvocationException(e);
		} 
		return serviceInvocationResult;
	}

	/**
	 * This method is used to transform the InterimXML into the SpecimenXML.
	 * @param message
	 * @return
	 * @throws IntegrationException
	 */
	private String transformToConsentXML(String message)throws IntegrationException {
		String specimenXMLStr = null;
		InputStream is = null;
		ByteArrayOutputStream os = null;

		try {
			os = new ByteArrayOutputStream();
			is = new ByteArrayInputStream(message.getBytes());

			xsltTransformer.transform(null, is, os);

			specimenXMLStr = new String(os.toByteArray());
		} catch (IntegrationException e) {
			LOG.error("Error transforming to catissue consent XML!", e);
			throw e;
		} finally {
			try {
				is.close();
				os.close();
			} catch (Exception e) {
			}
		}
		return specimenXMLStr;
	}

}
