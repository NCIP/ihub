package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.util.CustomUrlClassLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the Wrapper client class for RegisterConsent client class.
 * 
 * @author Rohit Gupta
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class CaTissueConsentClient {

    private static final String CLIENT_CLASSNAME = "gov.nih.nci.integration.catissue.client.CaTissueConsentClient";
    private Class<?> caTissueConsentClientClass = null;

    private final String caTissueLibLocation;
    private final String loginName;
    private final String password;

    private final Executor ex = Executors.newCachedThreadPool();
    private static final Logger LOG = LoggerFactory.getLogger(CaTissueConsentClient.class);

    /**
     * Constructor
     * 
     * @param caTissueLibLocation - caTissueLibLocation
     * @param loginName - loginName
     * @param password - password
     * @throws IntegrationException - IntegrationException
     */
    public CaTissueConsentClient(String caTissueLibLocation, String loginName, String password)
            throws IntegrationException {
        super();
        this.caTissueLibLocation = caTissueLibLocation;
        this.loginName = loginName;
        this.password = password;

        init();
    }

    private void init() throws IntegrationException {
        try {
            final File libFile = new File(caTissueLibLocation);

            // creating the custom classloader that bypasses the systemclassloader
            final CustomUrlClassLoader ccl = new CustomUrlClassLoader(ClassLoader.getSystemClassLoader().getParent(),
                    libFile.getAbsolutePath());
            caTissueConsentClientClass = ccl.loadClass(CLIENT_CLASSNAME);
        } catch (MalformedURLException e) {
            LOG.error("MalformedURLException occured while initializing CaTissueConsentClient.", e);
            throw new IntegrationException(IntegrationError._1000, e);
        } catch (ClassNotFoundException e) {
            LOG.error("ClassNotFoundException occured while initializing CaTissueConsentClient.", e);
            throw new IntegrationException(IntegrationError._1000, e);
        }

    }

    /**
     * This method is used Register the Consents
     * 
     * @param consentListXMLStr - XMLString containing the list of consents for which registration has to be done
     * @return ServiceInvocationResult - which may contain things like actualResult, isDataChanged, exception etc
     */
    public ServiceInvocationResult registerConsents(final String consentListXMLStr) {
        ServiceInvocationResult result1, result2, finalResult = null;
        CaTissueTask task1, task2 = null;

        // First get the existing Specimen/Consent data - which will be needed
        // incase of Rollback
        try {
            task1 = new CaTissueTask(caTissueConsentClientClass, loginName, password, "getExistingConsents",
                    consentListXMLStr);
        } catch (IntegrationException e1) {
            result1 = getServiceInvocationResult(IntegrationError._1051, e1);
            return result1;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = 
                    new ExecutorCompletionService<ServiceInvocationResult>(ex);
            ecs.submit(task1);

            result1 = ecs.take().get();

            if (!result1.isFault()) {
                result1.setResult("Successfully Fetched Consent in CaTissue!");
            }
        } catch (InterruptedException e) {
            result1 = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            result1 = getServiceInvocationResult(IntegrationError._1051, e);
        }

        if (result1.getInvocationException() != null) {
            // return if some issue while fetching the data itself.
            return result1;
        }

        // now perform registerConsent operation
        try {
            task2 = new CaTissueTask(caTissueConsentClientClass, loginName, password, "registerConsents",
                    consentListXMLStr);
        } catch (IntegrationException e1) {
            result2 = getServiceInvocationResult(IntegrationError._1051, e1);
            return result2;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = 
                    new ExecutorCompletionService<ServiceInvocationResult>(ex);
            ecs.submit(task2);
            result2 = ecs.take().get();
            if (!result2.isFault()) {
                result2.setResult("Successfully Registered Consent in CaTissue!");
            }
        } catch (InterruptedException e) {
            result2 = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            result2 = getServiceInvocationResult(IntegrationError._1051, e);
        }

        // Merge result1 & result2 to get the final result
        if (result2.getInvocationException() != null) {
            finalResult = new ServiceInvocationResult();
            finalResult.setDataChanged(true);
            finalResult.setOriginalData(result1.getOriginalData());
            finalResult.setInvocationException(result2.getInvocationException());
        } else {
            finalResult = result2;
        }

        return finalResult;
    }

    /***
     * This method is used to Rollback the Consents
     * 
     * @param consentListXMLStr - XMLString containing the specimens/consents to be rollback
     * @return ServiceInvocationResult - which may contain things like actualResult, isDataChanged, exception etc
     */
    public ServiceInvocationResult rollbackConsents(final String consentListXMLStr) {
        ServiceInvocationResult result = null;
        CaTissueTask task = null;
        try {
            task = new CaTissueTask(caTissueConsentClientClass, loginName, password, "rollbackConsentRegistration",
                    consentListXMLStr);
        } catch (IntegrationException e1) {
            LOG.error("IntegrationException occured while instantiating CaTissueTask for rollbackConsentRegistration.",
                    e1);
            result = getServiceInvocationResult(IntegrationError._1051, e1);
            return result;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = 
                    new ExecutorCompletionService<ServiceInvocationResult>(ex);
            ecs.submit(task);
            result = ecs.take().get();
            if (!result.isFault()) {
                result.setResult("Successfully rollback Consents from CaTissue!");
            }
        } catch (InterruptedException e) {
            result = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            result = getServiceInvocationResult(IntegrationError._1051, e);
        }

        return result;
    }

    private ServiceInvocationResult getServiceInvocationResult(IntegrationError error, Exception e) {
        final ServiceInvocationResult result = new ServiceInvocationResult();
        result.setInvocationException(new IntegrationException(error, e));
        return result;
    }

}
