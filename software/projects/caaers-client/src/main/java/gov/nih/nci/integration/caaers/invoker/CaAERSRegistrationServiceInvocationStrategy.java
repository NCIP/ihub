package gov.nih.nci.integration.caaers.invoker;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.integration.caaers.CaAERSParticipantServiceWSClient;
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
import java.net.MalformedURLException;
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
 * This is Strategy class for Participant Registration for caAERS
 * 
 * @author chandrasekaravr
 * 
 */
public class CaAERSRegistrationServiceInvocationStrategy implements ServiceInvocationStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(CaAERSRegistrationServiceInvocationStrategy.class);

    private final CaAERSParticipantServiceWSClient client;

    private final int retryCount;

    private final XSLTTransformer xsltTransformer;

    private final Map<String, IntegrationError> msgToErrMap;

    /**
     * Constructor
     * 
     * @param xsltTransformer - XSLTTransformer
     * @param client - CaAERSParticipantServiceWSClient
     * @param retryCount - retryCount
     */
    public CaAERSRegistrationServiceInvocationStrategy(XSLTTransformer xsltTransformer,
            CaAERSParticipantServiceWSClient client, int retryCount) {
        super();
        this.xsltTransformer = xsltTransformer;
        this.client = client;
        this.retryCount = retryCount;

        final HashMap<String, IntegrationError> msgToErrMapBase = new LinkedHashMap<String, IntegrationError>();

        msgToErrMapBase.put("User is not authorized on this Study", IntegrationError._1009);
        msgToErrMapBase.put("Invalid Username/Password", IntegrationError._1011);
        msgToErrMapBase.put("User is not authorized on this Organization", IntegrationError._1012);
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
        return StrategyIdentifier.CAEERS_CREATE_REGISTRATION;
    }

    @Override
    public ServiceInvocationResult invoke(ServiceInvocationMessage msg) {
        final ServiceInvocationResult result = new ServiceInvocationResult();
        IntegrationException ie = null;
        try {
            final String participantXMLStr = transformToParticipantXML(msg.getMessage().getRequest());

            final CaaersServiceResponse caaersresponse = client.createParticipant(participantXMLStr);
            final ServiceResponse response = caaersresponse.getServiceResponse();
            if ("0".equals(response.getResponsecode())) {
                result.setResult(response.getResponsecode() + " : " + response.getMessage());
                result.setDataChanged(true);
                // there is no original data
            } else {
                handleErrorResponse(response, result);
            }
        } catch (SOAPFaultException e) {
            LOG.error("CaAERSRegistrationStrategy. SOAPFaultException while calling createParticipant.", e);
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (MalformedURLException e) {
            LOG.error("CaAERSRegistrationStrategy. MalformedURLException while calling createParticipant.", e);
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (JAXBException e) {
            LOG.error("CaAERSRegistrationStrategy. JAXBException while calling createParticipant.", e);
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (WebServiceException e) {
            LOG.error("CaAERSRegistrationStrategy. WebServiceException while calling createParticipant.", e);
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (IntegrationException e) {
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
        IntegrationException ie = null;
        try {
            final String participantXMLStr = transformToParticipantXML(msg.getMessage().getRequest());
            final CaaersServiceResponse caaersresponse = client.deleteParticipant(participantXMLStr);
            final ServiceResponse response = caaersresponse.getServiceResponse();
            if ("0".equals(response.getResponsecode())) {
                result.setResult(response.getResponsecode() + " : " + response.getMessage());
            } else {
                handleErrorResponse(response, result);
            }
        } catch (SOAPFaultException e) {
            LOG.error("CaAERSRegistrationStrategy. SOAPFaultException while rollback of createParticipant.", e);
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (MalformedURLException e) {
            LOG.error("CaAERSRegistrationStrategy. MalformedURLException while rollback of createParticipant.", e);
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (JAXBException e) {
            LOG.error("CaAERSRegistrationStrategy. JAXBException while rollback of createParticipant.", e);
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (WebServiceException e) {
            LOG.error("CaAERSRegistrationStrategy. WebServiceException while rollback of createParticipant.", e);
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (IntegrationException e) {
            ie = e;
        }
        if (!result.isFault()) {
            result.setInvocationException(ie);
        }
        handleException(result);
        return result;
    }

    private String transformToParticipantXML(String message) throws IntegrationException {
        String participantXMLStr = null;
        InputStream is = null;
        ByteArrayOutputStream os = null;

        try {
            os = new ByteArrayOutputStream();
            is = new ByteArrayInputStream(message.getBytes());

            xsltTransformer.transform(null, is, os);

            participantXMLStr = new String(os.toByteArray());
        } catch (IntegrationException e) {
            LOG.error("CaAERSRegistrationStrategy.IntegrationException occurred while transformToParticipantXML. ", e);
            throw e;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LOG.error("Inside CaAERSRegistrationStrategy.Exception occurred while closing InputStream. ", e);
            }
            try {
                os.close();
            } catch (IOException e) {
                LOG.error("CaAERSRegistrationStrategy.Exception occurred while closing OutputStream. ", e);
            }
        }
        return participantXMLStr;
    }

    private void handleErrorResponse(ServiceResponse response, ServiceInvocationResult result) {
        final List<WsError> wserrors = response.getWsError();
        WsError error = null;
        IntegrationException ie = null;
        
        if (wserrors == null || wserrors.isEmpty()) {
            ie = new IntegrationException(IntegrationError._1053, new Throwable(response.getMessage()), response // NOPMD
                    .getMessage());
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
