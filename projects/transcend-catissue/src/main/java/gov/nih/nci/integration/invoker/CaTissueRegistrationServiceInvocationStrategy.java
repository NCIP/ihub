package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueParticipantClient;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is Strategy class for RegisterParticipant
 * 
 * @author Vinodh
 */
public class CaTissueRegistrationServiceInvocationStrategy implements ServiceInvocationStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueRegistrationServiceInvocationStrategy.class);

    private int retryCount = 0;

    private CaTissueParticipantClient caTissueParticipantClient;

    private XSLTTransformer xsltTransformer;

    private Map<String, IntegrationError> msgToErrMap;

    /**
     * Constructor
     * 
     * @param retryCount - retryCount
     * @param caTissueParticipantClient - caTissueParticipantClient
     * @param xsltTransformer - xsltTransformer
     */
    public CaTissueRegistrationServiceInvocationStrategy(int retryCount,
            CaTissueParticipantClient caTissueParticipantClient, XSLTTransformer xsltTransformer) {
        super();
        this.retryCount = retryCount;
        this.caTissueParticipantClient = caTissueParticipantClient;
        this.xsltTransformer = xsltTransformer;

        HashMap<String, IntegrationError> msgToErrMapBase = new LinkedHashMap<String, IntegrationError>();

        msgToErrMapBase.put("Error authenticating user", IntegrationError._1019);
        // CHECKSTYLE:OFF
        msgToErrMapBase
                .put("Submission failed since a Participant with the same Participant Protocol ID within this Collection Protocol already exists.",
                        IntegrationError._1031);
        // CHECKSTYLE:ON
        msgToErrMapBase.put(
                "Submission failed since a Participant with the same SOCIAL_SECURITY_NUMBER already exists.",
                IntegrationError._1032);

        msgToErrMap = Collections.synchronizedMap(msgToErrMapBase);
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
    public ServiceInvocationResult invoke(ServiceInvocationMessage msg) {
        ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
        try {
            String participantXMLStr = transformToParticipantXML(msg.getMessage().getRequest());
            serviceInvocationResult = caTissueParticipantClient.registerParticipant(participantXMLStr);
        } catch (IntegrationException e) {
            serviceInvocationResult.setInvocationException(e);
        }
        handleException(serviceInvocationResult);
        return serviceInvocationResult;
    }

    @Override
    public ServiceInvocationResult rollback(ServiceInvocationMessage msg) {
        ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
        try {
            String participantXMLStr = transformToParticipantXML(msg.getMessage().getRequest());
            serviceInvocationResult = caTissueParticipantClient.deleteParticipant(participantXMLStr);
        } catch (IntegrationException e) {
            serviceInvocationResult.setInvocationException(e);
        }
        handleException(serviceInvocationResult);
        return serviceInvocationResult;
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
            LOG.debug("Error transforming to catissue participant XML!", e);
            throw e;
        } finally {
            try {
                is.close();
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                LOG.error("Inside CaTissueRegistrationServiceInvocationStrategy.. Exception while closing InputStream : "
                        + e);
            }
            try {
                os.close();
            } catch (Exception e) {
                // CHECKSTYLE:ON
                LOG.error("CaTissueRegistrationServiceInvocationStrategy.. Exception while closing ByteArrayOutputStream : "
                        + e);
            }
        }
        return participantXMLStr;
    }

    private void handleException(ServiceInvocationResult result) {
        if (!result.isFault()) {
            return;
        }

        Exception exception = result.getInvocationException();
        Throwable cause = exception;
        while (cause instanceof IntegrationException) {
            cause = cause.getCause();
        }
        if (cause == null) {
            return;
        }

        String[] throwableMsgs = getThrowableMsgs(cause);
        IntegrationException newie = (IntegrationException) exception;

        Set<String> keys = msgToErrMap.keySet();

        for (String lkupStr : keys) {
            String msg = getMatchingMsg(lkupStr, throwableMsgs);
            if (msg != null) {
                newie = new IntegrationException(msgToErrMap.get(lkupStr), cause, msg);
                break;
            }
        }

        result.setInvocationException(newie);
    }

    private String[] getThrowableMsgs(Throwable cause) {
        Throwable[] throwables = ExceptionUtils.getThrowables(cause);
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
