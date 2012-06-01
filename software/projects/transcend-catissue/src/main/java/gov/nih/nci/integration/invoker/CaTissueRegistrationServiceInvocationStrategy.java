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

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaTissueRegistrationServiceInvocationStrategy implements ServiceInvocationStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueRegistrationServiceInvocationStrategy.class);

    private int retryCount = 0;

    private CaTissueParticipantClient caTissueParticipantClient;

    private XSLTTransformer xsltTransformer;

    Map<String, IntegrationError> msgToErrMap;

    public CaTissueRegistrationServiceInvocationStrategy(int retryCount,
            CaTissueParticipantClient caTissueParticipantClient, XSLTTransformer xsltTransformer) {
        super();
        this.retryCount = retryCount;
        this.caTissueParticipantClient = caTissueParticipantClient;
        this.xsltTransformer = xsltTransformer;

        HashMap<String, IntegrationError> msgToErrMapBase = new LinkedHashMap<String, IntegrationError>();

        msgToErrMapBase.put("Error authenticating user", IntegrationError._1019);

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
            } catch (Exception e) {
            }
            try {
                os.close();
            } catch (Exception e) {
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
        String stackTrace = ExceptionUtils.getFullStackTrace(cause);
        IntegrationException newie = (IntegrationException) exception;
        if (stackTrace.contains("Error authenticating user")) {
            newie = new IntegrationException(IntegrationError._1019, (Object) null);
        }
        result.setInvocationException(newie);
    }

}
