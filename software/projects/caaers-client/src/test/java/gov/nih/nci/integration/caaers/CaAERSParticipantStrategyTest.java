package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ResponseDataType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import gov.nih.nci.cabig.caaers.integration.schema.participant.EthnicityType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.GenderType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.Participants;
import gov.nih.nci.cabig.caaers.integration.schema.participant.RaceType;
import gov.nih.nci.integration.caaers.invoker.CaAERSRegistrationServiceInvocationStrategy;
import gov.nih.nci.integration.caaers.invoker.CaAERSUpdateRegistrationServiceInvocationStrategy;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import java.net.MalformedURLException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.SOAPFaultException;

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
 * Test Client for caAERS Strategy Classes. It has test cases for both Register Participant & Update Participant
 * Registration.
 * 
 * @author Rohit Gupta
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-caaers-client-test.xml")
public class CaAERSParticipantStrategyTest {

    private CaAERSRegistrationServiceInvocationStrategy caAERSRegistrationServiceInvocationStrategy;
    private CaAERSUpdateRegistrationServiceInvocationStrategy caAERSUpdateRegistrationServiceInvocationStrategy;
    private CaAERSParticipantServiceWSClient wsClient;
    private XSLTTransformer xsltTransformer;
    private static final int RETRY_COUNT = 1;
    private static final Long REFMSGID = 12345L;
    private static final String SUCCESS = "Success";
    private static final String FAILURE = "Failure";

    /**
     * To initialize the things
     * 
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    @Test
    @Before
    public void initialize() throws BeansException, MalformedURLException {
        wsClient = EasyMock.createMock(CaAERSParticipantServiceWSClient.class);
        xsltTransformer = EasyMock.createMock(XSLTTransformer.class);
        caAERSRegistrationServiceInvocationStrategy = new CaAERSRegistrationServiceInvocationStrategy(xsltTransformer,
                wsClient, RETRY_COUNT);
        caAERSUpdateRegistrationServiceInvocationStrategy = new CaAERSUpdateRegistrationServiceInvocationStrategy(
                xsltTransformer, wsClient, RETRY_COUNT);
    }

    /**
     * Tests registerParticipant using the ServiceInvocationStrategy class for success case
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * 
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void registerParticipantSuccess() throws IntegrationException, SOAPFaultException, MalformedURLException,
            JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getParticipantXMLString();
            }
        }).anyTimes();

        final CaaersServiceResponse caaersServiceResponse = getRegisterParticipantResponse(SUCCESS);
        EasyMock.expect(wsClient.createParticipant((String) EasyMock.anyObject())).andReturn(caaersServiceResponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getParticipantInterimMessage(), stTime,
                caAERSRegistrationServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSRegistrationServiceInvocationStrategy
                .invoke(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests registerParticipant using the ServiceInvocationStrategy class for failure case
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * 
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void registerParticipantFailure() throws IntegrationException, SOAPFaultException, MalformedURLException,
            JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getParticipantXMLString();
            }
        }).anyTimes();

        final CaaersServiceResponse caaersServiceResponse = getRegisterParticipantResponse(FAILURE);
        EasyMock.expect(wsClient.createParticipant((String) EasyMock.anyObject())).andReturn(caaersServiceResponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getParticipantInterimMessage(), stTime,
                caAERSRegistrationServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSRegistrationServiceInvocationStrategy
                .invoke(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests Update Participant Registration using the ServiceInvocationStrategy class for success case
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * @throws DatatypeConfigurationException - DatatypeConfigurationException
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateParticipantRegistrationSuccess() throws IntegrationException, SOAPFaultException,
            MalformedURLException, JAXBException, DatatypeConfigurationException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getParticipantXMLString();
            }
        }).anyTimes();

        final CaaersServiceResponse updateParticipantResponse = getUpdateParticipantResponse(SUCCESS);
        final CaaersServiceResponse getParticipantResponse = getParticipantResponse(SUCCESS);
        EasyMock.expect(wsClient.updateParticipant((String) EasyMock.anyObject())).andReturn(updateParticipantResponse)
                .anyTimes();
        EasyMock.expect(wsClient.getParticipant((String) EasyMock.anyObject())).andReturn(getParticipantResponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getParticipantInterimMessage(), stTime,
                caAERSUpdateRegistrationServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSUpdateRegistrationServiceInvocationStrategy
                .invoke(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests Update Participant Registration using the ServiceInvocationStrategy class for failure case
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * @throws DatatypeConfigurationException - DatatypeConfigurationException
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateParticipantRegistrationFailure() throws IntegrationException, SOAPFaultException,
            MalformedURLException, JAXBException, DatatypeConfigurationException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getParticipantXMLString();
            }
        }).anyTimes();

        final CaaersServiceResponse updateParticipantResponse = getUpdateParticipantResponse(FAILURE);
        final CaaersServiceResponse getParticipantResponse = getParticipantResponse(SUCCESS);
        EasyMock.expect(wsClient.updateParticipant((String) EasyMock.anyObject())).andReturn(updateParticipantResponse)
                .anyTimes();
        EasyMock.expect(wsClient.getParticipant((String) EasyMock.anyObject())).andReturn(getParticipantResponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getParticipantInterimMessage(), stTime,
                caAERSUpdateRegistrationServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSUpdateRegistrationServiceInvocationStrategy
                .invoke(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests Update Participant Registration using the ServiceInvocationStrategy class for a scenario when
     * getParticipant() is failed
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * @throws DatatypeConfigurationException - DatatypeConfigurationException
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateParticipantRegistrationGetParticipantFailure() throws IntegrationException, SOAPFaultException,
            MalformedURLException, JAXBException, DatatypeConfigurationException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getParticipantXMLString();
            }
        }).anyTimes();

        final CaaersServiceResponse updateResponse = getUpdateParticipantResponse(SUCCESS);
        final CaaersServiceResponse getResponse = getParticipantResponse(FAILURE);
        EasyMock.expect(wsClient.updateParticipant((String) EasyMock.anyObject())).andReturn(updateResponse).anyTimes();
        EasyMock.expect(wsClient.getParticipant((String) EasyMock.anyObject())).andReturn(getResponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getParticipantInterimMessage(), stTime,
                caAERSUpdateRegistrationServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSUpdateRegistrationServiceInvocationStrategy
                .invoke(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests rollback() of Register Participant using the ServiceInvocationStrategy class for success case
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * 
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void rollbackRegisterParticipantSuccess() throws IntegrationException, SOAPFaultException,
            MalformedURLException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getParticipantXMLString();
            }
        }).anyTimes();

        final CaaersServiceResponse caaersServiceResponse = getDeleteParticipantResponse(SUCCESS);
        EasyMock.expect(wsClient.deleteParticipant((String) EasyMock.anyObject())).andReturn(caaersServiceResponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getParticipantInterimMessage(), stTime,
                caAERSRegistrationServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSRegistrationServiceInvocationStrategy
                .rollback(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests rollback() of Register Participant using the ServiceInvocationStrategy class for failure case .i.e the
     * rollback() is failed
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * 
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void rollbackRegisterParticipantFailure() throws IntegrationException, SOAPFaultException,
            MalformedURLException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getParticipantXMLString();
            }
        }).anyTimes();

        final CaaersServiceResponse caaersServiceResponse = getDeleteParticipantResponse(FAILURE);
        EasyMock.expect(wsClient.deleteParticipant((String) EasyMock.anyObject())).andReturn(caaersServiceResponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getParticipantInterimMessage(), stTime,
                caAERSRegistrationServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSRegistrationServiceInvocationStrategy
                .rollback(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests rollback() of Update Participant Registration using the ServiceInvocationStrategy class for success case
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * 
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void rollbackUpdateRegisterParticipantSuccess() throws IntegrationException, SOAPFaultException,
            MalformedURLException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getParticipantXMLString();
            }
        }).anyTimes();

        final CaaersServiceResponse caaersServiceResponse = getUpdateParticipantResponse(SUCCESS);
        EasyMock.expect(wsClient.updateParticipant((String) EasyMock.anyObject())).andReturn(caaersServiceResponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getParticipantInterimMessage(), stTime,
                caAERSUpdateRegistrationServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSUpdateRegistrationServiceInvocationStrategy
                .rollback(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests rollback() of Update Participant Registration using the ServiceInvocationStrategy class for failure case
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * 
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void rollbackUpdateRegisterParticipantFailure() throws IntegrationException, SOAPFaultException,
            MalformedURLException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getParticipantXMLString();
            }
        }).anyTimes();

        final CaaersServiceResponse caaersServiceResponse = getUpdateParticipantResponse(FAILURE);
        EasyMock.expect(wsClient.updateParticipant((String) EasyMock.anyObject())).andReturn(caaersServiceResponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getParticipantInterimMessage(), stTime,
                caAERSUpdateRegistrationServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSUpdateRegistrationServiceInvocationStrategy
                .rollback(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    // CHECKSTYLE:OFF
    private String getParticipantInterimMessage() {
        return "<xr:caxchangerequest xmlns:xr=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:xs=\"http://www.w3.org/TR/2008/REC-xml-20081126#\"><mes:metadata><mes:serviceType xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">iHub</mes:serviceType><mes:operationName xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">Create Registration</mes:operationName><mes:externalIdentifier xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">322061501</mes:externalIdentifier><mes:caXchangeIdentifier xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">1340892539847</mes:caXchangeIdentifier><mes:credentials xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><mes:userName>tolvenuser</mes:userName><mes:password>changeme</mes:password></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><xmlSchemaDefinition xmlns=\"http://cacis.nci.nih.gov\">http://integration.nci.nih.gov/participant</xmlSchemaDefinition><p:participant xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" id=\"1\" version=\"1\" xsi:schemaLocation=\"http://integration.nci.nih.gov/participant Participant.xsd \"><p:firstName>Cherry061501</p:firstName><p:lastName>Blossom061501</p:lastName><p:maidenName/><p:middleName/><p:birthDate>1965-11-24</p:birthDate><p:gender>Male</p:gender><p:race>White</p:race><p:ethnicity>Not Hispanic or Latino</p:ethnicity><p:activityStatus>Active</p:activityStatus><p:registrationDate>2012-03-07</p:registrationDate><p:identifiers><p:organizationAssignedIdentifier id=\"1\" version=\"1\"><p:type>MRN</p:type><p:value>666061501</p:value><p:primaryIndicator>true</p:primaryIndicator><p:organization id=\"1\" version=\"1\"><p:name>QU</p:name><p:nciInstituteCode>DCP</p:nciInstituteCode></p:organization></p:organizationAssignedIdentifier><p:organizationAssignedIdentifier id=\"2\" version=\"1\"><p:type>SSN</p:type><p:value>123-06-1501</p:value><p:primaryIndicator>false</p:primaryIndicator><p:organization id=\"1\" version=\"1\"><p:name>SSN</p:name><p:nciInstituteCode>SSN</p:nciInstituteCode></p:organization></p:organizationAssignedIdentifier><p:systemAssignedIdentifier id=\"1\" version=\"1\"><p:type>MRN</p:type><p:value>666061501</p:value><p:primaryIndicator>true</p:primaryIndicator><p:systemName>MRN</p:systemName></p:systemAssignedIdentifier></p:identifiers><p:assignments><p:assignment id=\"1\" version=\"1\"><p:studySubjectIdentifier>488061501</p:studySubjectIdentifier><p:studySite id=\"1\" version=\"1\"><p:study id=\"1\" version=\"1\"><p:identifiers><p:identifier id=\"1\" version=\"1\"><p:type>Protocol Authority Identifier</p:type><p:value>6482</p:value></p:identifier></p:identifiers></p:study><p:organization id=\"1\" version=\"1\"><p:name>QU</p:name><p:nciInstituteCode>DCP</p:nciInstituteCode></p:organization></p:studySite></p:assignment></p:assignments></p:participant></mes:businessMessagePayload></mes:request></xr:caxchangerequest>";
    }

    private String getParticipantXMLString() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <caaers:participant xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:caaers=\"http://schema.integration.caaers.cabig.nci.nih.gov/participant\" id=\"1\" version=\"1\"> <firstName>Cherry061501</firstName> <lastName>Blossom061501</lastName> <maidenName/> <middleName/> <birthDate>1965-11-24</birthDate> <gender>Male</gender> <race>White</race> <ethnicity>Not Hispanic or Latino</ethnicity> <identifiers> <caaers:organizationAssignedIdentifier id=\"1\" version=\"1\"> <type>MRN</type> <value>666061501</value> <primaryIndicator>true</primaryIndicator> <caaers:organization id=\"1\" version=\"1\"> <name>QU</name> <nciInstituteCode>DCP</nciInstituteCode> </caaers:organization> </caaers:organizationAssignedIdentifier> <caaers:systemAssignedIdentifier id=\"1\" version=\"1\"> <type>MRN</type> <value>666061501</value> <primaryIndicator>true</primaryIndicator> <systemName>MRN</systemName> </caaers:systemAssignedIdentifier> </identifiers> <assignments> <caaers:assignment id=\"1\" version=\"1\"> <studySubjectIdentifier>488061501</studySubjectIdentifier> <caaers:studySite id=\"1\" version=\"1\"> <caaers:study id=\"1\" version=\"1\"> <identifiers> <identifier id=\"1\" version=\"1\"> <type>Protocol Authority Identifier</type> <value>6482</value> </identifier> </identifiers> </caaers:study> <caaers:organization id=\"1\" version=\"1\"> <name>QU</name> <nciInstituteCode>DCP</nciInstituteCode> </caaers:organization> </caaers:studySite> </caaers:assignment> </assignments> </caaers:participant>";
    }

    // CHECKSTYLE:ON

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

    private CaaersServiceResponse getRegisterParticipantResponse(String type) {
        final CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        final ServiceResponse serviceResponse = new ServiceResponse();
        if (type.equalsIgnoreCase(SUCCESS)) {
            serviceResponse.setStatus(Status.PROCESSED);
            serviceResponse.setResponsecode("0");
            serviceResponse.setMessage("Participant Cherry062801 Blossom062801  Created in caAERS");
        } else {
            serviceResponse.setStatus(Status.FAILED_TO_PROCESS);
            serviceResponse.setResponsecode("1");
            serviceResponse.setMessage("Participant Cherry061501 Blossom061501  could not be created in caAERS.");
        }
        caaersServiceResponse.setServiceResponse(serviceResponse);
        return caaersServiceResponse;
    }

    private CaaersServiceResponse getUpdateParticipantResponse(String type) {
        final CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        final ServiceResponse serviceResponse = new ServiceResponse();
        if (type.equalsIgnoreCase(SUCCESS)) {
            serviceResponse.setStatus(Status.PROCESSED);
            serviceResponse.setResponsecode("0");
        } else {
            serviceResponse.setStatus(Status.FAILED_TO_PROCESS);
            serviceResponse.setResponsecode("1");
            serviceResponse.setMessage("Participant Cherry061501 Blossom061501  could not be updated in caAERS.");
        }
        caaersServiceResponse.setServiceResponse(serviceResponse);
        return caaersServiceResponse;
    }

    private CaaersServiceResponse getDeleteParticipantResponse(String type) {
        final CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        final ServiceResponse serviceResponse = new ServiceResponse();
        if (type.equalsIgnoreCase(SUCCESS)) {
            serviceResponse.setStatus(Status.PROCESSED);
            serviceResponse.setResponsecode("0");
        } else {
            serviceResponse.setStatus(Status.FAILED_TO_PROCESS);
            serviceResponse.setResponsecode("1");
            serviceResponse.setMessage("Participant Cherry061501 Blossom061501  could not be delete from caAERS.");
        }
        caaersServiceResponse.setServiceResponse(serviceResponse);
        return caaersServiceResponse;
    }

    private CaaersServiceResponse getParticipantResponse(String type) throws DatatypeConfigurationException {
        final CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        final ServiceResponse serviceResponse = new ServiceResponse();
        if (type.equalsIgnoreCase(SUCCESS)) {
            serviceResponse.setStatus(Status.PROCESSED);
            serviceResponse.setResponsecode("0");
            final ResponseDataType resDataType = getParticipantResponseData();
            serviceResponse.setResponseData(resDataType);
        } else {
            serviceResponse.setStatus(Status.FAILED_TO_PROCESS);
            serviceResponse.setResponsecode("1");
            serviceResponse.setMessage("Could not retrieved Partcipant from caAERS.");
        }
        caaersServiceResponse.setServiceResponse(serviceResponse);
        return caaersServiceResponse;
    }

    private ResponseDataType getParticipantResponseData() throws DatatypeConfigurationException {
        final ResponseDataType resDataType = new ResponseDataType();
        final Participants participants = new Participants();
        final ParticipantType participant = new ParticipantType();
        participant.setFirstName("updCherry060801");
        participant.setLastName("Blossom060801");
        final GregorianCalendar gcal3 = new GregorianCalendar(1960, 06, 15);
        final XMLGregorianCalendar xgcal3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal3);
        participant.setBirthDate(xgcal3);
        participant.setGender(GenderType.MALE);
        participant.setRace(RaceType.BLACK_OR_AFRICAN_AMERICAN);
        participant.setEthnicity(EthnicityType.NOT_HISPANIC_OR_LATINO);

        participants.getParticipant().add(participant);
        resDataType.setAny(participants);
        return resDataType;
    }
}
