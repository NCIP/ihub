package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueSpecimenClient;
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
public class CaTissueUpdateSpecimenServiceInvocationStrategy implements ServiceInvocationStrategy {

	private static Logger LOG = LoggerFactory.getLogger(CaTissueUpdateSpecimenServiceInvocationStrategy.class);

	private int retryCount = 0;

	private CaTissueSpecimenClient caTissueSpecimenClient;

	private XSLTTransformer xsltTransformer;
	
	
	public CaTissueUpdateSpecimenServiceInvocationStrategy(int retryCount,
			CaTissueSpecimenClient caTissueSpecimenClient,
			XSLTTransformer xsltTransformer) {
		super();
		this.retryCount = retryCount;
		this.caTissueSpecimenClient = caTissueSpecimenClient;
		this.xsltTransformer = xsltTransformer;
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public StrategyIdentifier getStrategyIdentifier() {
		return StrategyIdentifier.CATISSUE_UPDATE_SPECIMEN;		
				
	}

	@Override
	public ServiceInvocationResult invoke(ServiceInvocationMessage msg) {
		ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
		try {
			// transform the InterimCreateSpecimenXML into SpecimenXML
			String specimenListXMLStr = transformToSpecimenXML(msg.getMessage().getRequest());	
			
			// call the method to update Specimens
			serviceInvocationResult = caTissueSpecimenClient.updateSpecimens(specimenListXMLStr);

		} catch (IntegrationException e) {
			serviceInvocationResult.setInvocationException(e);
		} catch (Exception e) {
			serviceInvocationResult.setInvocationException(e);
		}
		return serviceInvocationResult;
	}

	@Override
	public ServiceInvocationResult rollback(ServiceInvocationMessage msg) {
		ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
		try {			
			String specimenListXMLStr = msg.getOriginalData();			
				
			// call the method to rollback Specimens
			serviceInvocationResult = caTissueSpecimenClient.rollbackUpdatedSpecimens(specimenListXMLStr);			
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
	private String transformToSpecimenXML(String message)throws IntegrationException {
		String specimenXMLStr = null;
		InputStream is = null;
		ByteArrayOutputStream os = null;

		try {
			os = new ByteArrayOutputStream();
			is = new ByteArrayInputStream(message.getBytes());

			xsltTransformer.transform(null, is, os);

			specimenXMLStr = new String(os.toByteArray());
		} catch (IntegrationException e) {
			LOG.error("Error transforming to catissue specimen XML!", e);
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
