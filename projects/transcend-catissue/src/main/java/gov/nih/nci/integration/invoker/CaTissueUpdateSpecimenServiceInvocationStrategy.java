package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueSpecimenClient;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
 * This is Strategy class for Update Specimen
 * 
 * @author Rohit Gupta
 * 
 */
public class CaTissueUpdateSpecimenServiceInvocationStrategy implements ServiceInvocationStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueUpdateSpecimenServiceInvocationStrategy.class);

    private final int retryCount;

    private final CaTissueSpecimenClient caTissueSpecimenClient;

    private final XSLTTransformer xsltTransformer;

    private final Map<String, IntegrationError> msgToErrMap;

    /**
     * Constructor
     * 
     * @param retryCount - retryCount
     * @param caTissueSpecimenClient - caTissueSpecimenClient
     * @param xsltTransformer - xsltTransformer
     */
    public CaTissueUpdateSpecimenServiceInvocationStrategy(int retryCount,
            CaTissueSpecimenClient caTissueSpecimenClient, XSLTTransformer xsltTransformer) {
        super();
        this.retryCount = retryCount;
        this.caTissueSpecimenClient = caTissueSpecimenClient;
        this.xsltTransformer = xsltTransformer;

        final HashMap<String, IntegrationError> msgToErrMapBase = new LinkedHashMap<String, IntegrationError>();

        msgToErrMapBase.put("Specimen with the same LABEL already exists", IntegrationError._1080);
        msgToErrMapBase.put("Specimen Type is invalid", IntegrationError._1081);
        msgToErrMapBase.put("Tissue Side is invalid", IntegrationError._1082);
        msgToErrMapBase.put("Tissue Site is invalid", IntegrationError._1083);
        msgToErrMapBase.put("Specimen Collection Group not found in caTissue", IntegrationError._1084);
        msgToErrMapBase.put("Available Quantity cannot be greater than the Initial Quantity", IntegrationError._1085);
        msgToErrMapBase.put("Pathological Status is invalid", IntegrationError._1086);
        msgToErrMapBase.put("Collection Protocol Event can't be changed while updating the Specimen",
                IntegrationError._1087);
        msgToErrMapBase.put("Collection Protocol can't be changed while updating the Specimen", IntegrationError._1088);
        msgToErrMapBase.put("Specimen Class can't be changed while updating the Specimen", IntegrationError._1089);
        msgToErrMapBase.put("Specimen for given LABEL doesn't exist", IntegrationError._1090);
        msgToErrMapBase.put("Error occurred : Unable to rollback. Please check the logs.", IntegrationError._1093);
        msgToErrMapBase.put("\'Other Text\' should be provided only when a guidance of \'OTHER\' is selected.",
                IntegrationError._1095);
        msgToErrMapBase.put("Only one value for \'Guidance for breast core biopsy\' may be provided.",
                IntegrationError._1096);
        msgToErrMapBase.put("The value for \'Guidance for breast core biopsy\' is invalid.", IntegrationError._1097);

        msgToErrMap = Collections.synchronizedMap(msgToErrMapBase);
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
            final String specimenListXMLStr = transformToSpecimenXML(msg.getMessage().getRequest());

            // call the method to update Specimens
            serviceInvocationResult = caTissueSpecimenClient.updateSpecimens(specimenListXMLStr);

        } catch (IntegrationException e) {
            serviceInvocationResult.setInvocationException(e);
        }

        handleException(serviceInvocationResult);
        return serviceInvocationResult;
    }

    @Override
    public ServiceInvocationResult rollback(ServiceInvocationMessage msg) {
        ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
        final String specimenListXMLStr = msg.getOriginalData();
        // call the method to rollback Specimens
        serviceInvocationResult = caTissueSpecimenClient.rollbackUpdatedSpecimens(specimenListXMLStr);
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
            } catch (IOException e) {
                LOG.error("CaTissueUpdateSpecimenServiceInvocationStrategy.. Exception while closing the stream : " + e);
            }
        }
        return specimenXMLStr;
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
