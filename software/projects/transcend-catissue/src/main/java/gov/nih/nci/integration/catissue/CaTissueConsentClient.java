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
public class CaTissueConsentClient {

    private final String clientClassName;
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
     * @param clientClassName - clientClassName
     * @throws IntegrationException - IntegrationException
     */
    public CaTissueConsentClient(String caTissueLibLocation, String loginName, String password, String clientClassName)
            throws IntegrationException {
        super();
        this.caTissueLibLocation = caTissueLibLocation;
        this.loginName = loginName;
        this.password = password;
        this.clientClassName = clientClassName;
        init();
    }

    
    private void init() throws IntegrationException {
        try {
            final File libFile = new File(caTissueLibLocation);

            // creating the custom classloader that bypasses the systemclassloader
            final CustomUrlClassLoader ccl = new CustomUrlClassLoader(ClassLoader.getSystemClassLoader().getParent(),
                    libFile.getAbsolutePath());
            caTissueConsentClientClass = ccl.loadClass(clientClassName);
        } catch (MalformedURLException e) {
            LOG.error("MalformedURLException  while initializing CaTissueConsentClient.", e);
            throw new IntegrationException(IntegrationError._1000, e);
        } catch (ClassNotFoundException e) {
            LOG.error("ClassNotFoundException  while initializing CaTissueConsentClient.", e);
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

        // First get the existing Specimen/Consent data - incase of Rollback
        result1 = getExistingConsents(consentListXMLStr);

        if (result1.getInvocationException() != null) {
            // return if some exception while fetching the data itself.
            return result1;
        }

        // If 'getExistingConsents' call is fine then call 'registerConsent'
        result2 = registerCaTissueConsents(consentListXMLStr);

        // Merge result1 & result2 to get the final result
        if (result2.getInvocationException() == null) {
            finalResult = result2;
        } else {
            finalResult = new ServiceInvocationResult();
            finalResult.setDataChanged(true);
            finalResult.setOriginalData(result1.getOriginalData());
            finalResult.setInvocationException(result2.getInvocationException());
        }

        return finalResult;
    }

    
    /**
     * This method is used to call getExistingConsents() of CaTissueClient
     */
    private ServiceInvocationResult getExistingConsents(final String consentListXMLStr) {
        CaTissueTask task1 = null;
        ServiceInvocationResult result1 = null;
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

        return result1;
    }

    
    /**
     * This method is used to call registerConsents() of CaTissueClient
     */
    private ServiceInvocationResult registerCaTissueConsents(final String consentListXMLStr) {
        CaTissueTask task = null;
        ServiceInvocationResult result = null;
        try {
            task = new CaTissueTask(caTissueConsentClientClass, loginName, password, "registerConsents",
                    consentListXMLStr);
        } catch (IntegrationException e1) {
            result = getServiceInvocationResult(IntegrationError._1051, e1);
            return result;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = 
                    new ExecutorCompletionService<ServiceInvocationResult>(ex);
            ecs.submit(task);
            result = ecs.take().get();
            if (!result.isFault()) {
                result.setResult("Successfully Registered Consent in CaTissue!");
            }
        } catch (InterruptedException e) {
            result = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            result = getServiceInvocationResult(IntegrationError._1051, e);
        }

        return result;
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
            LOG.error("IntegrationException  while instantiating CaTissueTask for rollbackConsentRegistration.", e1);
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
