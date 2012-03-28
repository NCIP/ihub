package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueParticipantClient;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.transformer.XSLTTransformer;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.transform.TransformerException;

import org.apache.axis.utils.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaTissueServiceInvocationStrategy implements
		ServiceInvocationStrategy {

	private static Logger LOG = LoggerFactory
			.getLogger(CaTissueServiceInvocationStrategy.class);

	private int retryCount = 0;

	private CaTissueParticipantClient caTissueParticipantClient;

	private XSLTTransformer xsltTransformer;
	
	
	public CaTissueServiceInvocationStrategy(int retryCount,
			CaTissueParticipantClient caTissueParticipantClient,
			XSLTTransformer xsltTransformer) {
		super();
		this.retryCount = retryCount;
		this.caTissueParticipantClient = caTissueParticipantClient;
		this.xsltTransformer = xsltTransformer;
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public StrategyIdentifier getStrategyIdentifier() {
		return StrategyIdentifier.CATISSUE_CREATE_REGISTRATION;
	}

	@Override
	public ServiceInvocationResult invoke(String arg0) {
		ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
		try {
			String participantXMLStr = transformToParticipantXML(arg0);
			caTissueParticipantClient.registerParticipant(participantXMLStr);
		} catch (TransformerException e) {
			serviceInvocationResult.setInvocationException(e);
		} catch (ApplicationException e) {
			serviceInvocationResult.setInvocationException(e);
		}
		serviceInvocationResult.setResult("Participant registered successfully in CaTissue!");
		return serviceInvocationResult;
	}

	@Override
	public ServiceInvocationResult rollback(String arg0) {
		ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
		try {
			String participantXMLStr = transformToParticipantXML(arg0);
			caTissueParticipantClient.deleteParticipant(participantXMLStr);
		} catch (TransformerException e) {
			serviceInvocationResult.setInvocationException(e);
		} catch (ApplicationException e) {
			serviceInvocationResult.setInvocationException(e);
		}
		serviceInvocationResult.setResult("Participant rollback successful in CaTissue!");
		return serviceInvocationResult;
	}

	private String transformToParticipantXML(String message)
			throws TransformerException {
		String participantXMLStr = null;
		InputStream is = null;
		ByteArrayOutputStream os = null;

		try {
			os = new ByteArrayOutputStream();
			is = new ByteArrayInputStream(message.getBytes());

			xsltTransformer.transform(null, is, os);

			participantXMLStr = new String(os.toByteArray());
		} catch (TransformerException e) {
			LOG.debug("Error transforming to catissue participant XML!", e);
			throw e;
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
			try {
				os.close();
			} catch (Exception e) {
			}
		}
		return participantXMLStr;
	}

}
