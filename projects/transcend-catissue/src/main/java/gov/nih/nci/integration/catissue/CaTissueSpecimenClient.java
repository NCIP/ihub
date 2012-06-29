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
 * This is the Wrapper client class for Specimen client class.
 * 
 * @author Rohit Gupta
 */
public class CaTissueSpecimenClient {

    private final String clientClassName;
    
    private Class<?> caTissueSpecimenClientClass = null;

    private final String caTissueLibLocation;
    private final String loginName;
    private final String password;

    private final Executor ex = Executors.newCachedThreadPool();

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueSpecimenClient.class);

    /**
     * Constructor
     * 
     * @param caTissueLibLocation - caTissueLibLocation
     * @param loginName - loginName
     * @param password - password
     * @param clientClassName - clientClassName
     * @throws IntegrationException - IntegrationException
     */
    public CaTissueSpecimenClient(String caTissueLibLocation, String loginName, String password, String clientClassName)
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
            caTissueSpecimenClientClass = ccl.loadClass(clientClassName);
        } catch (MalformedURLException e) {
            LOG.error("MalformedURLException  while initializing CaTissueSpecimenClient.", e);
            throw new IntegrationException(IntegrationError._1000, e);
        } catch (ClassNotFoundException e) {
            LOG.error("ClassNotFoundException  while initializing CaTissueSpecimenClient.", e);
            throw new IntegrationException(IntegrationError._1000, e);
        }
    }

    
    /**
     * This method is used to create bio-specimens in caTissue
     * 
     * @param specimenListXMLStr - The XMLString for creating the bio-specimen which may contain multiple specimens.
     * @return ServiceInvocationResult - which may contain things like actualResult, isDataChanged, exception etc
     */
    public ServiceInvocationResult createSpecimens(final String specimenListXMLStr) {
        ServiceInvocationResult result1, result2 = null;

        // method call to check if given specimen already exist in caTissue
        result1 = isSpecimensExist(specimenListXMLStr);

        if (result1.getInvocationException() != null) {
            // We don't want to call the Rollback if a Specimen is already
            // existing. 'isDataChanged' should also be false
            // Also "NO" need to call createSpecimen flow. We are throwing
            // ApplicationException from client code if Specimen is already existing
            return result1;
        }

        // It means that Specimen is not existing, so try to execute below code..
        result2 = createCaTissueSpecimens(specimenListXMLStr);

        if (result2.getInvocationException() != null) {
            // It means that the Specimens were NOT present at the starting when
            // we checked before calling createSpecimen,
            // but then Specimen might be created as we have the list of
            // specimen and exception is for one of them.
            // So we have to call Rollback to make sure that the create specimen are rollbacked
            result2.setDataChanged(true);
        }
        return result2;
    }

    
    /**
     * This method is used to check if the specimen is already existing
     */
    private ServiceInvocationResult isSpecimensExist(final String specimenListXMLStr) {
        CaTissueTask task = null;
        ServiceInvocationResult result = null;
        try {
            task = new CaTissueTask(caTissueSpecimenClientClass, loginName, password, "isSpecimensExist",
                    specimenListXMLStr);
        } catch (IntegrationException e1) {
            LOG.error("Exception  while instantiating CaTissueTask for isSpecimenExist.", e1);
            result = getServiceInvocationResult(IntegrationError._1051, e1);
            return result;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = 
                    new ExecutorCompletionService<ServiceInvocationResult>(ex);
            ecs.submit(task);
            result = ecs.take().get();
            if (!result.isFault()) {
                result.setResult("Successfully called isSpecimensExist !");
            }
        } catch (InterruptedException e) {
            LOG.error("InterruptedException  while calling isSpecimenExist. ", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            LOG.error("ExecutionException  while calling isSpecimenExist. ", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        }

        return result;
    }

    
    /**
     * This method is used to call the createSpecimen of caTissueClient
     */
    private ServiceInvocationResult createCaTissueSpecimens(final String specimenListXMLStr) {
        ServiceInvocationResult result = null;
        CaTissueTask task = null;
        try {
            task = new CaTissueTask(caTissueSpecimenClientClass, loginName, password, "createSpecimens",
                    specimenListXMLStr);
        } catch (IntegrationException e1) {
            LOG.error("IntegrationException while instantiating CaTissueTask for createSpecimens.", e1);
            result = getServiceInvocationResult(IntegrationError._1051, e1);
            return result;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = 
                    new ExecutorCompletionService<ServiceInvocationResult>(ex);
            ecs.submit(task);
            result = ecs.take().get();
            if (!result.isFault()) {
                result.setResult("Successfully created Specimens in CaTissue!");
            }
        } catch (InterruptedException e) {
            LOG.error("InterruptedException  while calling createSpecimens. ", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            LOG.error("ExecutionException  while calling createSpecimens. ", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        }

        return result;
    }

    
    /**
     * This method is used to Rollback the specimen changes for createSpecimen flow
     * 
     * @param specimenListXMLStr - XMLString containing the specimens to be rollback
     * @return ServiceInvocationResult - which may contain things like actualResult, isDataChanged, exception etc
     */
    public ServiceInvocationResult rollbackCreatedSpecimens(final String specimenListXMLStr) {
        ServiceInvocationResult result = null;
        CaTissueTask task = null;
        try {
            task = new CaTissueTask(caTissueSpecimenClientClass, loginName, password, "rollbackCreatedSpecimens",
                    specimenListXMLStr);
        } catch (IntegrationException e1) {
            LOG.error("IntegrationException  while instantiating CaTissueTask for rollbackCreatedSpecimens. ", e1);
            result = getServiceInvocationResult(IntegrationError._1051, e1);
            return result;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = 
                    new ExecutorCompletionService<ServiceInvocationResult>(ex);
            ecs.submit(task);
            result = ecs.take().get();
            if (!result.isFault()) {
                result.setResult("Successfully rollback Specimens from CaTissue.");
            }
        } catch (InterruptedException e) {
            LOG.error("InterruptedException  while calling rollbackCreatedSpecimens. ", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            LOG.error("ExecutionException  while calling rollbackCreatedSpecimens. ", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        }

        return result;
    }

    
    /**
     * This method is used to update bio-specimens in caTissue
     * 
     * @param specimenListXMLStr - The XML string for creating the specimens which may contain multiple specimens.
     * @return ServiceInvocationResult - which may contain things like actualResult, isDataChanged, exception etc
     */
    public ServiceInvocationResult updateSpecimens(final String specimenListXMLStr) {
        ServiceInvocationResult result1, result2, finalResult = null;

        result1 = getExistingSpecimens(specimenListXMLStr);

        if (result1.getInvocationException() != null) {
            // If some exception while fetching the data, don't try for updation.
            return result1;
        }

        // If 'getExistingSpecimens' call is fine then try to 'updateSpecimen'
        result2 = updateCaTissueSpecimens(specimenListXMLStr);

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

    
    private ServiceInvocationResult getExistingSpecimens(final String specimenListXMLStr) {
        CaTissueTask task = null;
        ServiceInvocationResult result = null;
        try {
            task = new CaTissueTask(caTissueSpecimenClientClass, loginName, password, "getExistingSpecimens",
                    specimenListXMLStr);
        } catch (IntegrationException e1) {
            LOG.error("IntegrationException  while instantiating CaTissueTask for getExistingSpecimens. ", e1);
            result = getServiceInvocationResult(IntegrationError._1051, e1);
            return result;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = 
                    new ExecutorCompletionService<ServiceInvocationResult>(ex);
            ecs.submit(task);
            result = ecs.take().get();
            if (!result.isFault()) {
                result.setResult("Successfully fetched Specimens from CaTissue!");
            }
        } catch (InterruptedException e) {
            LOG.error("InterruptedException  while calling getExistingSpecimens. ", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            LOG.error("ExecutionException  while calling getExistingSpecimens. ", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        }

        return result;
    }

    
    private ServiceInvocationResult updateCaTissueSpecimens(final String specimenListXMLStr) {
        CaTissueTask task = null;
        ServiceInvocationResult result = null;
        try {
            task = new CaTissueTask(caTissueSpecimenClientClass, loginName, password, "updateSpecimens",
                    specimenListXMLStr);
        } catch (IntegrationException e1) {
            LOG.error("IntegrationException  while calling updateSpecimens.", e1);
            result = getServiceInvocationResult(IntegrationError._1051, e1);
            return result;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = 
                    new ExecutorCompletionService<ServiceInvocationResult>(ex);
            ecs.submit(task);
            result = ecs.take().get();
            if (!result.isFault()) {
                result.setResult("Successfully Updated Specimens from CaTissue.");
            }
        } catch (InterruptedException e) {
            LOG.error("InterruptedException  while calling updateSpecimens. ", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            LOG.error("ExecutionException  while calling updateSpecimens. ", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        }

        return result;
    }

    
    /**
     * This method is used to Rollback the specimen changes for updateSpecimen flow
     * 
     * @param specimenListXMLStr - XMLString containing the specimens to be rollback
     * @return ServiceInvocationResult - which may contain things like actualResult, isDataChanged, exception etc
     */

    public ServiceInvocationResult rollbackUpdatedSpecimens(final String specimenListXMLStr) {
        ServiceInvocationResult result = null;
        CaTissueTask task = null;
        try {
            task = new CaTissueTask(caTissueSpecimenClientClass, loginName, password, "rollbackUpdatedSpecimens",
                    specimenListXMLStr);
        } catch (IntegrationException e1) {
            LOG.error("IntegrationException  while instantiating CaTissueTask for rollbackUpdatedSpecimens. ", e1);
            result = getServiceInvocationResult(IntegrationError._1051, e1);
            return result;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = 
                    new ExecutorCompletionService<ServiceInvocationResult>(ex);
            ecs.submit(task);
            result = ecs.take().get();
            if (!result.isFault()) {
                result.setResult("Successfully rollback Specimens from CaTissue!");
            }
        } catch (InterruptedException e) {
            LOG.error("InterruptedException  while calling rollbackUpdatedSpecimens. ", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            LOG.error("ExecutionException  while calling rollbackUpdatedSpecimens. ", e);
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
