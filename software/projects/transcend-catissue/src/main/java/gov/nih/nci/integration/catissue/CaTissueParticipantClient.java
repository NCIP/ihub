/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.util.CustomUrlClassLoader;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the Wrapper client class for RegisterParticipant client class.
 * 
 * @author chandrasekaravr
 */
public class CaTissueParticipantClient {

    private final String clientClassName;

    private Class<?> caTissueClientClass = null;
    private static final Logger LOG = LoggerFactory.getLogger(CaTissueParticipantClient.class);

    private final String caTissueLibLocation;
    private final String loginName;
    private final String password;

    private final Executor ex = Executors.newCachedThreadPool();

    /**
     * Constructor
     * 
     * @param caTissueLibLocation - caTissueLibLocation
     * @param loginName - loginName
     * @param password - password
     * @param clientClassName - clientClassName
     * @throws IntegrationException - IntegrationException
     */
    public CaTissueParticipantClient(String caTissueLibLocation, String loginName, String password,
            String clientClassName) throws IntegrationException {
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
            caTissueClientClass = ccl.loadClass(clientClassName);
            // CHECKSTYLE:OFF
        } catch (Exception e) { // NOPMD
            LOG.error("Exception occured while initializing CaTissueParticipantClient.", e);
            throw new IntegrationException(IntegrationError._1052, e);
        }
    }

    /**
     * This method is used to call the 'registerParticipantFromXML' of the client
     * 
     * @param participantXMLStr - Participant information in the form of XMLString
     * @return ServiceInvocationResult
     */
    public ServiceInvocationResult registerParticipant(final String participantXMLStr) {
        ServiceInvocationResult result = null;
        CaTissueTask task = null;
        try {
            task = new CaTissueTask(caTissueClientClass, loginName, password, "registerParticipantFromXML",
                    participantXMLStr);
        } catch (IntegrationException e1) {
            LOG.error("IntegrationException occured while calling registerParticipantFromXML.", e1);
            result = getServiceInvocationResult(IntegrationError._1051, e1);
            return result;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = new ExecutorCompletionService<ServiceInvocationResult>(
                    ex);
            ecs.submit(task);
            result = ecs.take().get();
            if (!result.isFault()) {
                result.setResult("Successfully registered participant in CaTissue!");
            }
        } catch (InterruptedException e) {
            LOG.error("InterruptedException occured while calling registerParticipantFromXML.", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            LOG.error("ExecutionException occured while calling registerParticipantFromXML.", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        }

        return result;
    }

    /**
     * This method is used to call the 'updateParticipantRegistrationFromXML' of the client
     * 
     * @param participantXMLStr - Participant information in the form of XMLString
     * @return ServiceInvocationResult
     */
    public ServiceInvocationResult updateRegistrationParticipant(final String participantXMLStr) {
        ServiceInvocationResult result = null;
        CaTissueTask task = null;
        try {
            task = new CaTissueTask(caTissueClientClass, loginName, password, "updateParticipantRegistrationFromXML",
                    participantXMLStr);
        } catch (IntegrationException e1) {
            LOG.error("IntegrationException occured while calling updateParticipantRegistrationFromXML.", e1);
            result = getServiceInvocationResult(IntegrationError._1051, e1);
            return result;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = new ExecutorCompletionService<ServiceInvocationResult>(
                    ex);
            ecs.submit(task);
            result = ecs.take().get();
            if (!result.isFault()) {
                result.setResult("Successfully updated participant registration in CaTissue!");
            }
        } catch (InterruptedException e) {
            LOG.error("InterruptedException occured while calling updateParticipantRegistrationFromXML.", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            LOG.error("ExecutionException occured while calling updateParticipantRegistrationFromXML.", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        }

        return result;
    }

    /**
     * This method is used to call the 'deleteParticipantFromXML' of the client
     * 
     * @param participantXMLStr - Participant information in the form of XMLString
     * @return ServiceInvocationResult
     */
    public ServiceInvocationResult deleteParticipant(final String participantXMLStr) {
        ServiceInvocationResult result = null;
        CaTissueTask task = null;
        try {
            task = new CaTissueTask(caTissueClientClass, loginName, password, "deleteParticipantFromXML",
                    participantXMLStr);
        } catch (IntegrationException e1) {
            LOG.error("IntegrationException occured while calling deleteParticipantFromXML.", e1);
            result = getServiceInvocationResult(IntegrationError._1051, e1);
            return result;
        }

        try {
            final ExecutorCompletionService<ServiceInvocationResult> ecs = new ExecutorCompletionService<ServiceInvocationResult>(
                    ex);
            ecs.submit(task);
            result = ecs.take().get();
            if (!result.isFault()) {
                result.setResult("Successfully rollabck participant from CaTissue!");
            }
        } catch (InterruptedException e) {
            LOG.error("IntegrationException occured while calling deleteParticipantFromXML.", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        } catch (ExecutionException e) {
            LOG.error("IntegrationException occured while calling deleteParticipantFromXML.", e);
            result = getServiceInvocationResult(IntegrationError._1051, e);
        }

        return result;
    }

    private ServiceInvocationResult getServiceInvocationResult(IntegrationError error, Exception e) {
        final ServiceInvocationResult result = new ServiceInvocationResult();
        if (e instanceof IntegrationException) {
            result.setInvocationException(e);
        } else {
            result.setInvocationException(new IntegrationException(error, e, e.getMessage()));
        }
        return result;
    }

}
