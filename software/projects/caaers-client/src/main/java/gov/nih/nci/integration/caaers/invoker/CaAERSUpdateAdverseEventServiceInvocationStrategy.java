package gov.nih.nci.integration.caaers.invoker;

import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CreateOrUpdateAdverseEventResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.integration.caaers.CaAERSAdverseEventServiceWSClient;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is Strategy class for Update Adverse Event for caAERS
 * 
 * @author Rohit Gupta
 * 
 */
public class CaAERSUpdateAdverseEventServiceInvocationStrategy implements ServiceInvocationStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(CaAERSUpdateAdverseEventServiceInvocationStrategy.class);

    private final CaAERSAdverseEventServiceWSClient client;

    private final int retryCount;

    private final XSLTTransformer xsltTransformer;

    private final Map<String, IntegrationError> msgToErrMap;

    /**
     * Constructor
     * 
     * @param xsltTransformer - XSLTTransformer
     * @param client - CaAERSAdverseEventServiceWSClient
     * @param retryCount - retryCount
     */
    public CaAERSUpdateAdverseEventServiceInvocationStrategy(XSLTTransformer xsltTransformer,
            CaAERSAdverseEventServiceWSClient client, int retryCount) {
        super();
        this.xsltTransformer = xsltTransformer;
        this.client = client;
        this.retryCount = retryCount;

        final HashMap<String, IntegrationError> msgToErrMapBase = new LinkedHashMap<String, IntegrationError>();
        msgToErrMapBase.put("Invalid Username/Password", IntegrationError._1011);
        msgToErrMapBase.put("could not be created in caAERS", IntegrationError._1014);
        msgToErrMapBase.put("Could not send Message", IntegrationError._1020);

        msgToErrMap = Collections.synchronizedMap(msgToErrMapBase);
    }

    @Override
    public int getRetryCount() {
        return retryCount;
    }

    @Override
    public StrategyIdentifier getStrategyIdentifier() {
        return StrategyIdentifier.CAEERS_UPDATE_AE;
    }

    @Override
    public ServiceInvocationResult invoke(ServiceInvocationMessage msg) {
        final ServiceInvocationResult result = new ServiceInvocationResult();
        IntegrationException ie = null;
        try {
            final String adverseEventXMLStr = transformToAdverseEventXML(msg.getMessage().getRequest());

            final CreateOrUpdateAdverseEventResponse caaersresponse = client.updateAdverseEvent(adverseEventXMLStr);
            final ServiceResponse response = caaersresponse.getCaaersServiceResponse().getServiceResponse();

            if ("FAILED_TO_PROCESS".equalsIgnoreCase(response.getStatus().name())) {
                handleErrorResponse(response, result);
            } else {
                result.setResult(response.getResponsecode() + " : " + response.getMessage());
                result.setDataChanged(true);
            }
        } catch (SOAPFaultException e) {
            LOG.error("SOAPFaultException while calling updateAdverseEvent.", e);
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (JAXBException e) {
            LOG.error("JAXBException while calling updateAdverseEvent.", e);
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (WebServiceException e) {
            LOG.error("WebServiceException while calling updateAdverseEvent.", e);
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (IntegrationException e) {
            LOG.error("IntegrationException while calling updateAdverseEvent.", e);
            ie = e;
        }
        if (!result.isFault()) {
            result.setInvocationException(ie);
        }
        handleException(result);
        return result;
    }

    @Override
    public ServiceInvocationResult rollback(ServiceInvocationMessage msg) {
        final ServiceInvocationResult result = new ServiceInvocationResult();
        /*
         * IntegrationException ie = null;
         * 
         * // As per the latest discussion, we are NOT calling the delete method. try { final String adverseEventXMLStr
         * = transformToAdverseEventXML(msg.getMessage().getRequest());
         * 
         * final DeleteAdverseEventResponse caaersresponse = client.deleteAdverseEvent(adverseEventXMLStr); final
         * ServiceResponse response = caaersresponse.getCaaersServiceResponse().getServiceResponse(); if
         * ("0".equals(response.getResponsecode())) { result.setResult(response.getResponsecode() + " : " +
         * response.getMessage()); } else { handleErrorResponse(response, result); }
         * 
         * } catch (SOAPFaultException e) { LOG.error("SOAPFaultException while rollback of updateAdverseEvent.", e); ie
         * = new IntegrationException(IntegrationError._1053, e, e.getMessage()); } catch (WebServiceException e) {
         * LOG.error("WebServiceException while rollback of updateAdverseEvent.", e); ie = new
         * IntegrationException(IntegrationError._1053, e, e.getMessage()); } catch (IntegrationException e) {
         * LOG.error("IntegrationException while rollback of updateAdverseEvent.", e); ie = e; } if (!result.isFault())
         * { result.setInvocationException(ie); } handleException(result);
         */
        return result;
    }

    private String transformToAdverseEventXML(String message) throws IntegrationException {
        String adverseEventXMLStr = null;
        InputStream is = null;
        ByteArrayOutputStream os = null;

        try {
            os = new ByteArrayOutputStream();
            is = new ByteArrayInputStream(message.getBytes());

            xsltTransformer.transform(null, is, os);

            adverseEventXMLStr = new String(os.toByteArray());
        } catch (IntegrationException e) {
            LOG.error("IntegrationException occurred while transformToAdverseEventXML. ", e);
            throw e;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LOG.error("IOException occurred while closing InputStream. ", e);
            }
            try {
                os.close();
            } catch (IOException e) {
                LOG.error("IOException occurred while closing OutputStream. ", e);
            }
        }
        return adverseEventXMLStr;
    }

    private void handleErrorResponse(ServiceResponse response, ServiceInvocationResult result) {
        final List<WsError> wserrors = response.getWsError();
        WsError error = null;
        IntegrationException ie = null;

        if (wserrors == null || wserrors.isEmpty()) {
            ie = new IntegrationException(IntegrationError._1053, new Throwable(response.getMessage()), // NOPMD
                    response.getMessage());
        } else {
            error = wserrors.get(0);
            ie = new IntegrationException(IntegrationError._1053, new Throwable(error.getException()), error // NOPMD
                    .getErrorDesc());
        }

        result.setInvocationException(ie);
    }

    private void handleException(ServiceInvocationResult result) {
        if (!result.isFault()) {
            return;
        }

        final Exception exception = result.getInvocationException();
        Throwable cause = exception;
        while (cause instanceof IntegrationException) {
            cause = cause.getCause();
        }
        if (cause == null) {
            return;
        }

        final String[] throwableMsgs = getThrowableMsgs(cause);
        IntegrationException newie = (IntegrationException) exception;

        final Set<String> keys = msgToErrMap.keySet();

        for (String lkupStr : keys) {
            final String msg = getMatchingMsg(lkupStr, throwableMsgs);
            if (msg != null) {
                newie = new IntegrationException(msgToErrMap.get(lkupStr), cause, msg);
                break;
            }
        }

        result.setInvocationException(newie);
    }

    private String[] getThrowableMsgs(Throwable cause) {
        final Throwable[] throwables = ExceptionUtils.getThrowables(cause);
        String[] msgs = new String[throwables.length];
        for (int i = 0; i < throwables.length; i++) {
            msgs[i] = throwables[i].getMessage();
        }
        return msgs;
    }

    private String getMatchingMsg(String lookupStr, String[] msgs) {
        for (String string : msgs) {
            if (string.contains(lookupStr)) {
                return string;
            }
        }
        return null;
    }

}
