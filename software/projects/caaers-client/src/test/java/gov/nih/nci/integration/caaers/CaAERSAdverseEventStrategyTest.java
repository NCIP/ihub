package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CreateOrUpdateAdverseEventResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.integration.caaers.invoker.CaAERSAdverseEventServiceInvocationStrategy;
import gov.nih.nci.integration.caaers.invoker.CaAERSUpdateAdverseEventServiceInvocationStrategy;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
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
 * Test Client for caAERS 'Adverse Event' Strategy Classes. 
 * It has test cases for both Create & Update Adverse Event.
 * 
 * @author Rohit Gupta
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-caaers-client-test.xml")
public class CaAERSAdverseEventStrategyTest {

    private CaAERSAdverseEventServiceInvocationStrategy caAERSAdverseEventServiceInvocationStrategy;
    private CaAERSUpdateAdverseEventServiceInvocationStrategy caAERSUpdateAdverseEventServiceInvocationStrategy;
    private CaAERSAdverseEventServiceWSClient wsClient;
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
        wsClient = EasyMock.createMock(CaAERSAdverseEventServiceWSClient.class);
        xsltTransformer = EasyMock.createMock(XSLTTransformer.class);
        caAERSAdverseEventServiceInvocationStrategy = new CaAERSAdverseEventServiceInvocationStrategy(xsltTransformer,
                wsClient, RETRY_COUNT);
        caAERSUpdateAdverseEventServiceInvocationStrategy = new CaAERSUpdateAdverseEventServiceInvocationStrategy(
                xsltTransformer, wsClient, RETRY_COUNT);
    }

    /**
     * Tests createAdverseEvent using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void createAdverseEventSuccess() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        }).anyTimes();

        final CreateOrUpdateAdverseEventResponse caaersresponse = getWSResponse(SUCCESS);
        EasyMock.expect(wsClient.createAdverseEvent((String) EasyMock.anyObject())).andReturn(caaersresponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime, caAERSAdverseEventServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSAdverseEventServiceInvocationStrategy
                .invoke(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests createAdverseEvent using the ServiceInvocationStrategy class for the failure scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void createAdverseEventFailure() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        });

        final CreateOrUpdateAdverseEventResponse caaersresponse = getWSResponse(FAILURE);
        EasyMock.expect(wsClient.createAdverseEvent((String) EasyMock.anyObject())).andReturn(caaersresponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime, caAERSAdverseEventServiceInvocationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult result = caAERSAdverseEventServiceInvocationStrategy
                .invoke(serviceInvocationMessage);
        Assert.assertNotNull(result);
    }

    /**
     * Tests Rollback for createAdverseEvent using the ServiceInvocationStrategy class
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void createAERollback() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        });

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime, caAERSAdverseEventServiceInvocationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult result = caAERSAdverseEventServiceInvocationStrategy
                .rollback(serviceInvocationMessage);
        Assert.assertNotNull(result);
    }

    /**
     * Tests updateAdverseEvent using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateAdverseEventSuccess() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        }).anyTimes();

        final CreateOrUpdateAdverseEventResponse caaersresponse = getWSResponse(SUCCESS);
        EasyMock.expect(wsClient.updateAdverseEvent((String) EasyMock.anyObject())).andReturn(caaersresponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime,
                caAERSUpdateAdverseEventServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSUpdateAdverseEventServiceInvocationStrategy
                .invoke(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests updateAdverseEvent using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateAdverseEventFailure() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        }).anyTimes();

        final CreateOrUpdateAdverseEventResponse caaersresponse = getWSResponse(FAILURE);
        EasyMock.expect(wsClient.updateAdverseEvent((String) EasyMock.anyObject())).andReturn(caaersresponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime,
                caAERSUpdateAdverseEventServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSUpdateAdverseEventServiceInvocationStrategy
                .invoke(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests Rollback for createAdverseEvent using the ServiceInvocationStrategy class
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateAERollback() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        });

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime,
                caAERSUpdateAdverseEventServiceInvocationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult result = caAERSUpdateAdverseEventServiceInvocationStrategy
                .rollback(serviceInvocationMessage);
        Assert.assertNotNull(result);
    }

    // CHECKSTYLE:OFF
    private String getAEInterimMessage() {
        return "<ns2:caxchangerequest xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\"><ns0:metadata xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\"><mes:serviceType xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">iHub</mes:serviceType><mes:operationName xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">Create Adverse Event</mes:operationName><mes:externalIdentifier xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">32225879</mes:externalIdentifier><mes:caXchangeIdentifier xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">1340651815262</mes:caXchangeIdentifier><mes:credentials xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></ns0:metadata><adverseeventinput xmlns=\"http://cacis.nci.nih.gov\"><criteria><participantIdentifier>PM-113</participantIdentifier><studyIdentifier>7216</studyIdentifier><course><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse><treatmentType>Treatment</treatmentType><treatmentAssignmentCode>TAC</treatmentAssignmentCode></course></criteria><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><ctepCode>Adrenal insufficiency</ctepCode><grade>3</grade><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><expected>true</expected><attributionSummary>POSSIBLE</attributionSummary><outcome><outComeEnumType>LIFE_THREATENING</outComeEnumType></outcome><outcome><outComeEnumType>HOSPITALIZATION</outComeEnumType></outcome></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><ctepCode>Aspartateamino transferase increased</ctepCode><grade>4</grade><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><expected>true</expected><attributionSummary>DEFINITE</attributionSummary><outcome><outComeEnumType>CONGENITAL_ANOMALY</outComeEnumType></outcome><outcome><outComeEnumType>OTHER_SERIOUS</outComeEnumType></outcome></adverseEvent></adverseEventsList></adverseeventinput></ns2:caxchangerequest>";
    }

    private String getAEXMLString() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns22:adverseevent xmlns:ns22=\"http://schema.integration.caaers.cabig.nci.nih.gov/adverseevent\" xmlns:ns33=\"http://schema.integration.caaers.cabig.nci.nih.gov/common\" xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\" xmlns:a=\"http://cacis.nci.nih.gov\" xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\"><ns22:adverseEvents><ns22:adverseEvent><verbatim>Event1 Verbatim</verbatim><grade>3</grade><expected>true</expected><attributionSummary>POSSIBLE</attributionSummary><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><ctepCode>Adrenal insufficiency</ctepCode><outcome><outComeEnumType>LIFE_THREATENING</outComeEnumType></outcome><outcome><outComeEnumType>HOSPITALIZATION</outComeEnumType></outcome></ns22:adverseEvent><ns22:adverseEvent><verbatim>Event2 Verbatim</verbatim><grade>4</grade><expected>true</expected><attributionSummary>DEFINITE</attributionSummary><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><ctepCode>Aspartateamino transferase increased</ctepCode><outcome><outComeEnumType>CONGENITAL_ANOMALY</outComeEnumType></outcome><outcome><outComeEnumType>OTHER_SERIOUS</outComeEnumType></outcome></ns22:adverseEvent></ns22:adverseEvents><ns22:criteria><participantIdentifier>PM-113</participantIdentifier><studyIdentifier>7216</studyIdentifier><course><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse><treatmentType>Treatment</treatmentType><treatmentAssignmentCode>TAC</treatmentAssignmentCode></course></ns22:criteria></ns22:adverseevent>";
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

    private CreateOrUpdateAdverseEventResponse getWSResponse(String type) {
        final CreateOrUpdateAdverseEventResponse caaersresponse = new CreateOrUpdateAdverseEventResponse();
        final CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        final ServiceResponse serviceResponse = new ServiceResponse();
        if (type.equalsIgnoreCase(SUCCESS)) {
            serviceResponse.setResponsecode("0");
        } else {
            serviceResponse.setResponsecode("1");
            serviceResponse.setMessage("incoming data is not proper.");
        }
        caaersServiceResponse.setServiceResponse(serviceResponse);
        caaersresponse.setCaaersServiceResponse(caaersServiceResponse);
        return caaersresponse;
    }
}
