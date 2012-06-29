package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueParticipantClient;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;

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
        final IntegrationException ie = new IntegrationException(IntegrationError._1034,
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
        final IntegrationException ie = new IntegrationException(IntegrationError._1034,
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

    // CHECKSTYLE:OFF
    private String getSpecimenXMLStr() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><catissue:participant xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:catissue=\"http://domain.catissuecore.wustl.edu/participant\"><catissue:activityStatus>Active</catissue:activityStatus><catissue:birthDate>19410502</catissue:birthDate><catissue:ethnicity>Not Hispanic or Latino</catissue:ethnicity><catissue:firstName>Cherry</catissue:firstName><catissue:gender>Male Gender</catissue:gender><catissue:lastName>Blossom</catissue:lastName><catissue:socialSecurityNumber>123-45-9991</catissue:socialSecurityNumber><catissue:vitalStatus>Alive</catissue:vitalStatus><catissue:collectionProtocolRegistrationCollection class=\"set\"><catissue:collectionProtocolRegistration><catissue:activityStatus>Active</catissue:activityStatus><catissue:consentSignatureDate>2012-04-08</catissue:consentSignatureDate><catissue:protocolParticipantIdentifier/><catissue:registrationDate>2012-04-08</catissue:registrationDate><catissue:specimenCollectionGroupCollection class=\"set\"/><catissue:collectionProtocol><catissue:title>CP-01</catissue:title><catissue:collectionProtocolEventCollection class=\"linked-hash-set\"/><catissue:childCollectionProtocolCollection class=\"linked-hash-set\"/><catissue:studyFormContextCollection class=\"set\"/><catissue:collectionProtocolRegistrationCollection class=\"set\"/><catissue:siteCollection class=\"set\"/><catissue:clinicalDiagnosisCollection class=\"linked-hash-set\"/><catissue:distributionProtocolCollection class=\"linked-hash-set\"/><catissue:coordinatorCollection class=\"linked-hash-set\"/><catissue:assignedProtocolUserCollection class=\"set\"/><catissue:gridGrouperPrivileges/></catissue:collectionProtocol><catissue:participant reference=\"../../..\"/><catissue:isToInsertAnticipatorySCGs>true</catissue:isToInsertAnticipatorySCGs></catissue:collectionProtocolRegistration></catissue:collectionProtocolRegistrationCollection><catissue:raceCollection class=\"set\"/><catissue:participantMedicalIdentifierCollection class=\"linked-hash-set\"/><catissue:participantRecordEntryCollection class=\"set\"/></catissue:participant>";
    }

}
