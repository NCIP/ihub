package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueParticipantClient;
import gov.nih.nci.integration.catissue.CaTissueParticipantClientIntegrationTest;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Date;

import javax.xml.bind.JAXBException;

import org.easymock.IAnswer;
import org.easymock.classextension.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is the TestClass for CaTissue SpecimenStrategy class.
 * 
 * @author Rohit Gupta
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueParticipantStrategyTest {

    private CaTissueRegistrationServiceInvocationStrategy caTissueRegistrationStrategy;
    private CaTissueUpdateRegistrationServiceInvocationStrategy caTissueUpdateRegistrationStrategy;
    private XSLTTransformer xsltTransformer;
    private CaTissueParticipantClient caTissueParticipantClient;
    private static final int RETRY_COUNT = 1;
    private static final Long REFMSGID = 12345L;

    /**
     * To initialize the things
     * 
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    @Test
    @Before
    public void initialize() throws BeansException, MalformedURLException {
        caTissueParticipantClient = EasyMock.createMock(CaTissueParticipantClient.class);
        xsltTransformer = EasyMock.createMock(XSLTTransformer.class);
        caTissueRegistrationStrategy = new CaTissueRegistrationServiceInvocationStrategy(RETRY_COUNT,
                caTissueParticipantClient, xsltTransformer);
        caTissueUpdateRegistrationStrategy = new CaTissueUpdateRegistrationServiceInvocationStrategy(RETRY_COUNT,
                caTissueParticipantClient, xsltTransformer);
    }

    /**
     * Tests registerParticipant using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void registerParticipant() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        EasyMock.expect(caTissueParticipantClient.registerParticipant((String) EasyMock.anyObject())).andReturn(
                clientResult);
        EasyMock.replay(caTissueParticipantClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueRegistrationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueRegistrationStrategy.invoke(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests registerParticipant using the ServiceInvocationStrategy class for the failure scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void registerParticipantFailure() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        clientResult.setDataChanged(false);
        clientResult.setOriginalData(getSpecimenXMLStr());

        final IntegrationException ie = new IntegrationException(IntegrationError._1034, new Throwable( // NOPMD
                "Participant does not contain the unique identifier SSN"),
                "Participant does not contain the unique identifier SSN");
        clientResult.setInvocationException(ie);

        EasyMock.expect(caTissueParticipantClient.registerParticipant((String) EasyMock.anyObject())).andReturn(
                clientResult);
        EasyMock.replay(caTissueParticipantClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueRegistrationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueRegistrationStrategy.invoke(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests rollbackRegisterParticipant using the ServiceInvocationStrategy class
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void rollbackRegisterParticipant() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        EasyMock.expect(caTissueParticipantClient.deleteParticipant((String) EasyMock.anyObject())).andReturn(
                clientResult);
        EasyMock.replay(caTissueParticipantClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueRegistrationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueRegistrationStrategy.rollback(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests updateRegistrationParticipant using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateParticipantRegistration() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        EasyMock.expect(caTissueParticipantClient.updateRegistrationParticipant((String) EasyMock.anyObject()))
                .andReturn(clientResult);
        EasyMock.replay(caTissueParticipantClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueUpdateRegistrationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueUpdateRegistrationStrategy
                .invoke(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests updateRegistrationParticipant using the ServiceInvocationStrategy class for the failure scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateParticipantRegistrationFailure() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        clientResult.setDataChanged(false);
        clientResult.setOriginalData(getSpecimenXMLStr());
        final IntegrationException ie = new IntegrationException(IntegrationError._1034, new Throwable( // NOPMD
                "Participant does not contain the unique identifier SSN"),
                "Participant does not contain the unique identifier SSN");
        clientResult.setInvocationException(ie);

        EasyMock.expect(caTissueParticipantClient.updateRegistrationParticipant((String) EasyMock.anyObject()))
                .andReturn(clientResult);
        EasyMock.replay(caTissueParticipantClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueUpdateRegistrationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueUpdateRegistrationStrategy
                .invoke(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests rollbakUpdateParticipantRegistration using the ServiceInvocationStrategy
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void rollbakUpdateParticipantRegistration() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        EasyMock.expect(caTissueParticipantClient.updateRegistrationParticipant((String) EasyMock.anyObject()))
                .andReturn(clientResult);
        EasyMock.replay(caTissueParticipantClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueUpdateRegistrationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueUpdateRegistrationStrategy
                .rollback(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    private ServiceInvocationMessage prepareServiceInvocationMessage(Long referenceMessageId, String message,
            Date startTime, StrategyIdentifier strategyIdentifier) {
        final ServiceInvocationMessage serviceInvocationMessage = new ServiceInvocationMessage();
        serviceInvocationMessage.setStrategyIdentifier(strategyIdentifier);
        final IHubMessage iHubMessage = new IHubMessage();
        iHubMessage.setStartTime(startTime);
        iHubMessage.setEndTime(new Date(new java.util.Date().getTime())); // NOPMD
        iHubMessage.setRequest(message);
        iHubMessage.setReferenceMessageId(referenceMessageId);
        serviceInvocationMessage.setReferenceMessageId(referenceMessageId);
        serviceInvocationMessage.setMessage(iHubMessage);
        return serviceInvocationMessage;
    }

    private String getSpecimenXMLStr() {
        return getXMLString("Participant.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueParticipantClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            System.err.println("Error while reading contents of file : " + fileName + ". " + e);// NOPMD
        }
        return contents;
    }

}
