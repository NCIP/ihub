/*package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import gov.nih.nci.caXchange.CaxchangeConstants;

import java.util.HashMap;
import java.util.Map;

import javax.jbi.messaging.ExchangeStatus;
import javax.jbi.messaging.InOnly;
import javax.jbi.messaging.MessageExchange;
import javax.jbi.messaging.NormalizedMessage;
import javax.xml.transform.dom.DOMSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

*//**
 * InvokeClinicalConnectorBean is used by caXchange to call external service.
 * 
 * 
 *//*
public class InvokeClinicalConnectorBean extends CaXchangeMessagingBean {
	protected Logger logger = LogManager.getLogger(InvokeClinicalConnectorBean.class);
	

	private String beanName = InvokeClinicalConnectorBean.class.getName();
	private CCCaller ccCaller = null;

	@Override
	public void processMessageExchange(MessageExchange exchange,CaXchangeDataUtil caXchangeDataUtil)
			throws Exception {
		Node targetResponse = null;
		try {
			logger.debug("Processing " + InvokeClinicalConnectorBean.class.getName());
			// The businessPayload below is the message which can be parsed as needed
			Node businessPayload = caXchangeDataUtil.getBusinessPayload();
			GlobusCredential cred=null;
			Subject subject = exchange.getMessage("in").getSecuritySubject();
			Set<GlobusCredential> globusCredentials = new HashSet<GlobusCredential>();
			if (subject != null) {
				globusCredentials = subject.getPrivateCredentials(GlobusCredential.class);
			}

			if(globusCredentials.size()>0){
				cred=globusCredentials.iterator().next();
			}else if(isUseCredentials()){
				throw new GridInvocationException("no credentials found");
			}			
			if (logger.isDebugEnabled()) {
				logger.debug("Input Payload:"
						+ new SourceTransformer().toString(businessPayload));
			}

			// Code to invoke clinicalConnector application
			//logger.debug("Code to invoke clinicalConnector should go here..");
			Node result = null;
			if (ClinicalConnectorConstants.ROLLBACK.equals(caXchangeDataUtil.getTransactionControl())) {
               result = ccCaller.invokeRollbackOperation(businessPayload);
			}else {
				 result = ccCaller.invokeOperation(businessPayload);	
			}
            if (logger.isDebugEnabled()) {
                logger.debug("Response:"+new SourceTransformer().toString(result));
            }
        	final Document response = new SourceTransformer().toDOMDocument(result);
			targetResponse = caXchangeDataUtil
					.buildTargetResponseFromBusinessResponse(beanName,
							"", "", response);

		}
		catch (PayloadParseException pe) {
			logger.error("An error occurred parsing payload for clinicalConnector.", pe);
			targetResponse = caXchangeDataUtil.createTargetErrorResponse(pe, "An error occurred parsing payload for clinicalConnector.");
		}		
		catch (InvokeClinicalConnectorException ie) {
			logger.error("An error occurred invoking clinicalConnector.", ie);
			targetResponse = caXchangeDataUtil.createTargetErrorResponse(ie, "An error occurred invoking clinicalConnector.");
		}				
		catch (Exception e) {
			logger.error("An error occurred invoking clinicalConnector.", e);
			targetResponse = caXchangeDataUtil.createTargetErrorResponse(e,"An error occurred invoking clinicalConnector.");
		}
		if ((caXchangeDataUtil.getTransactionControl()!=null)&&(caXchangeDataUtil.getTransactionControl().equals("ROLLBACK"))) {
			if (exchange instanceof InOnly){
				exchange.setStatus(ExchangeStatus.DONE);
				channel.send(exchange);
				return;
			}
		}
		NormalizedMessage in = exchange.getMessage("in");
		NormalizedMessage out = exchange.createMessage();
		if (logger.isDebugEnabled()) {
		   logger.debug("Target Response:"+new SourceTransformer().toString(targetResponse));
		}
		out.setContent(new DOMSource(targetResponse));
		copyRequestMetadata(out, caXchangeDataUtil);
		copyPropertiesAndAttachments(in, out);
		exchange.setMessage(out, "out");
		channel.send(exchange);
		return;
	}

	protected void copyRequestMetadata(NormalizedMessage out, CaXchangeDataUtil caXchangeDataUtil) throws Exception {
		logger.debug("Copying request metadata...");
		Map<String, String> metadata = new HashMap<String, String>();
		metadata.put(CaxchangeConstants.EXTERNAL_IDENTIFIER, caXchangeDataUtil
				.getExternalIdentifier());
		metadata.put(CaxchangeConstants.CAXCHANGE_IDENTIFIER, caXchangeDataUtil
				.getCaXchangeIdentifier());

		if (out != null) {
			out.setProperty(CaxchangeConstants.REQUEST_METADATA, metadata);
		}
		return;

	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName= beanName;
	}

	public CCCaller getCcCaller() {
		return ccCaller;
	}

	public void setCcCaller(CCCaller ccCaller) {
		this.ccCaller = ccCaller;
	}



}
*/