/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueConsentClient;
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
 * This is Strategy class for RegisterConsent
 * 
 * @author Rohit Gupta
 */
public class CaTissueConsentServiceInvocationStrategy implements ServiceInvocationStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueConsentServiceInvocationStrategy.class);

    private final int retryCount;

    private final CaTissueConsentClient caTissueConsentClient;

    private final XSLTTransformer xsltTransformer;

    private final Map<String, IntegrationError> msgToErrMap;

    /**
     * Constructor
     * 
     * @param retryCount - retryCount
     * @param caTissueSpecimenClient -caTissueSpecimenClient
     * @param xsltTransformer - xsltTransformer
     */
    public CaTissueConsentServiceInvocationStrategy(int retryCount, CaTissueConsentClient caTissueSpecimenClient,
            XSLTTransformer xsltTransformer) {
        super();
        this.retryCount = retryCount;
        this.caTissueConsentClient = caTissueSpecimenClient;
        this.xsltTransformer = xsltTransformer;

        final HashMap<String, IntegrationError> msgToErrMapBase = new LinkedHashMap<String, IntegrationError>();
        msgToErrMapBase.put("Specimen for given LABEL doesn't exist", IntegrationError._1090);
        msgToErrMapBase.put("Collection Protocol was not found in caTissue", IntegrationError._1091);
        msgToErrMapBase.put("ConsentTier Statement was not found for given CollectionProtocol in caTissue",
                IntegrationError._1092);
        msgToErrMapBase.put("Error occurred : Unable to rollback. Please check the logs.", IntegrationError._1093);
        msgToErrMapBase.put("Exception occurred while XSL transformation.", IntegrationError._1099);

        msgToErrMap = Collections.synchronizedMap(msgToErrMapBase);
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
            final String consentListXMLStr = transformToConsentXML(msg.getMessage().getRequest());

            // call the method to register Consents
            serviceInvocationResult = caTissueConsentClient.registerConsents(consentListXMLStr);
        } catch (IntegrationException e) {
            serviceInvocationResult.setInvocationException(e);
        }

        handleException(serviceInvocationResult);
        return serviceInvocationResult;
    }

    @Override
    public ServiceInvocationResult rollback(ServiceInvocationMessage msg) {
        ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
        final String consentListXMLStr = msg.getOriginalData();
        // call the method to rollback Specimens
        serviceInvocationResult = caTissueConsentClient.rollbackConsents(consentListXMLStr);
        return serviceInvocationResult;
    }

    /**
     * This method is used to transform the InterimXML into the SpecimenXML.
     * 
     * @param message
     * @return
     * @throws IntegrationException
     */
    private String transformToConsentXML(String message) throws IntegrationException {
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
            } catch (IOException e) {
                LOG.error("Inside CaTissueConsentServiceInvocationStrategy.. Error while closing the stream : " + e);
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
