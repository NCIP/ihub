package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueSpecimenClient;
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
 * 
 * @author Rohit Gupta
 * 
 */
public class CaTissueSpecimenServiceInvocationStrategy implements ServiceInvocationStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueSpecimenServiceInvocationStrategy.class);

    private int retryCount = 0;

    private CaTissueSpecimenClient caTissueSpecimenClient;

    private XSLTTransformer xsltTransformer;

    Map<String, IntegrationError> msgToErrMap;

    public CaTissueSpecimenServiceInvocationStrategy(int retryCount, CaTissueSpecimenClient caTissueSpecimenClient,
            XSLTTransformer xsltTransformer) {
        super();
        this.retryCount = retryCount;
        this.caTissueSpecimenClient = caTissueSpecimenClient;
        this.xsltTransformer = xsltTransformer;

        HashMap<String, IntegrationError> msgToErrMapBase = new LinkedHashMap<String, IntegrationError>();

        msgToErrMapBase.put("Specimen with the same LABEL already exists", IntegrationError._1080);
        msgToErrMapBase.put("Specimen Type is invalid", IntegrationError._1081);
        msgToErrMapBase.put("Tissue Side is invalid", IntegrationError._1082);
        msgToErrMapBase.put("Tissue Site is invalid", IntegrationError._1083);
        msgToErrMapBase.put("Specimen Collection Group not found in caTissue", IntegrationError._1084);
        msgToErrMapBase.put("Available Quantity cannot be greater than the Initial Quantity", IntegrationError._1085);
        msgToErrMapBase.put("Pathological Status is invalid", IntegrationError._1086);

        msgToErrMap = Collections.synchronizedMap(msgToErrMapBase);
    }

    @Override
    public int getRetryCount() {
        return retryCount;
    }

    @Override
    public StrategyIdentifier getStrategyIdentifier() {
        return StrategyIdentifier.CATISSUE_CREATE_SPECIMEN;

    }

    @Override
    public ServiceInvocationResult invoke(ServiceInvocationMessage msg) {
        ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
        try {
            // transform the InterimCreateSpecimenXML into CreateSpecimenXML
            String specimenListXMLStr = transformToSpecimenXML(msg.getMessage().getRequest());

            // call the method to create Specimens
            serviceInvocationResult = caTissueSpecimenClient.createSpecimens(specimenListXMLStr);
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

            String specimenListXMLStr = transformToSpecimenXML(msg.getMessage().getRequest());

            // call the method to rollback Specimens
            serviceInvocationResult = caTissueSpecimenClient.rollbackCreatedSpecimens(specimenListXMLStr);

        } catch (IntegrationException e) {
            serviceInvocationResult.setInvocationException(e);
        } catch (Exception e) {
            serviceInvocationResult.setInvocationException(e);
        }
        return serviceInvocationResult;
    }

    /**
     * This method is used to transform the InterimXML into the SpecimenXML.
     * 
     * @param message
     * @return
     * @throws IntegrationException
     */
    private String transformToSpecimenXML(String message) throws IntegrationException {
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