package gov.nih.nci.integration.caaers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEvents;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventsInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CourseType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CreateOrUpdateAdverseEventResponse;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.Criteria;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.DeleteAdverseEventResponse;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.OutComeEnumType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.OutcomeType;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.soap.SOAPFaultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test Class for CaAERSAdverseEventServiceWSClient
 * 
 * @author Rohit Gupta
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-caaers-client-test.xml")
public class CaAERSAdverseEventServiceClientTest {

    @Autowired
    private CaAERSAdverseEventServiceWSClient caAERSAdverseEventServiceWSClient;
    private static final Logger LOG = LoggerFactory.getLogger(CaAERSAdverseEventServiceClientTest.class);

    /**
     * TestCase for Creating Adverse Event in caAERS
     */
    // @Test
    public void createAdverseEvent() {
        final String adverseEventXMLStr = getAdverseEventXMLStr();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .createAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for CreateAdverseEvent.", Long.valueOf(0),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling createAdverseEvent. " + e);
        }
    }

    /**
     * TestCase for Creating Adverse Event in caAERS when Study does not exist in caAERS.
     */
    @Test
    public void createAEStudyNotExist() {
        final String adverseEventXMLStr = getAEStudyNotExist();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .createAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for CreateAEStudyNotExist.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling createAEStudyNotExist. " + e);
        }
    }

    /**
     * TestCase for Creating Adverse Event in caAERS when Participant does not exist in caAERS.
     */
    // @Test
    public void createAEParticipantNotExist() {
        final String adverseEventXMLStr = getAEParticipantNotExist();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .createAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for CreateParticipantNotExist.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling createAEParticipantNotExist. " + e);
        }
    }

    /**
     * TestCase for Creating Adverse Event in caAERS when Participant is NOT assigned to given Study.
     */
    // @Test
    public void createAEParticipantNotAssignedToStudy() {
        final String adverseEventXMLStr = getAEParticipantNotAssignedToStudy();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .createAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for CreateAEParticipantNotAssignedToStudy.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling createAEParticipantNotAssignedToStudy. " + e);
        }
    }

    /**
     * TestCase for Creating Adverse Event in caAERS when startDate of an AE is Invalid
     */
    // @Test
    public void createAEInvalidStartDateofAE() {
        final String adverseEventXMLStr = getAEInvalidAEStartDate();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .createAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for CreateAEInvalidStartDateofAE.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling createAEInvalidStartDateofAE. " + e);
        }
    }

    /**
     * TestCase for Creating Adverse Event in caAERS when endDate of an AE is Invalid. The endDate is Blank in this case
     */
    // @Test
    public void createAEInvalidEndDateofAE() {
        final String adverseEventXMLStr = getAEInvalidAEEndDate();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .createAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for CreateAEInvalidEndDateofAE.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling createAEInvalidEndDateofAE. " + e);
        }
    }

    /**
     * TestCase for Creating Adverse Event in caAERS when startDate on AE is greater than endDate
     */
    // @Test
    public void createAEInvalidStartEndDateCombinationofAE() {
        final String adverseEventXMLStr = getAEStartDateGreaterThanEndDate();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .createAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for CreateAEInvalidStartEndDateCombinationofAE.",
                    Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling createAEInvalidStartEndDateCombinationofAE. " + e);
        }
    }

    /**
     * TestCase for Creating Adverse Event in caAERS when startDate of the Course is Invalid
     */
    // @Test
    public void createAEStartDateOfThisCourse() {
        final String adverseEventXMLStr = getAEInvalidStartDateOfThisCourse();
        CreateOrUpdateAdverseEventResponse response = null;
        try {
            response = caAERSAdverseEventServiceWSClient.createAdverseEvent(adverseEventXMLStr);

        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling createAEStartDateOfThisCourse. " + e);
        } catch (SOAPFaultException e) {
            LOG.debug("SOAPFaultException occured while calling createAEStartDateOfThisCourse. ", e);
        }
        assertNull(response);
    }

    /**
     * TestCase for Creating Adverse Event in caAERS when endDate of the Course is Invalid
     */
    // @Test
    public void createAEEndDateOfThisCourse() {
        final String adverseEventXMLStr = getAEInvalidEndDateOfThisCourse();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .createAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for CreateAEEndDateOfThisCourse.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling createAEEndDateOfThisCourse. " + e);
        }
    }

    /**
     * TestCase for Creating Adverse Event in caAERS when startDate is greater than the endDate of the Course
     */
    // @Test
    public void createAEStartDateGreaterThanEndDateOfThisCourse() {
        final String adverseEventXMLStr = getAEStartDateGreaterEndDateofThisCourse();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .createAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for CreateAEStartDateGreaterThanEndDateOfThisCourse.",
                    Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling createAEStartDateGreaterThanEndDateOfThisCourse. " + e);
        }
    }

    /**
     * TestCase for Creating Adverse Event in caAERS when OutComeEnumType is Invalid
     */
    // @Test
    public void createAEInvalidOutComeEnumType() {
        final String adverseEventXMLStr = getAEInvalidOutComeEnumType();
        CreateOrUpdateAdverseEventResponse response = null;
        try {
            response = caAERSAdverseEventServiceWSClient.createAdverseEvent(adverseEventXMLStr);

        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling createAEInvalidOutComeEnumType. " + e);
        } catch (SOAPFaultException e) {
            LOG.debug("SOAPFaultException occured while calling createAEInvalidOutComeEnumType. ", e);
        }

        assertNull(response);
    }

    /**
     * TestCase for Creating Adverse Event in caAERS when AttributionType is Invalid
     */
    // @Test
    public void createAEInvalidAttributionType() {
        final String adverseEventXMLStr = getAEInvalidArrtibutionType();
        CreateOrUpdateAdverseEventResponse response = null;
        try {
            response = caAERSAdverseEventServiceWSClient.createAdverseEvent(adverseEventXMLStr);

        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling CreateAEInvalidAttributionType. " + e);
        } catch (SOAPFaultException e) {
            LOG.debug("SOAPFaultException occured while calling CreateAEInvalidAttributionType. ", e);
        }

        assertNull(response);
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS
     */
    // @Test
    public void updateAdverseEvent() {

        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .updateAdverseEvent(getAdverseEventXMLStr());
            assertEquals("Response code is NOT proper for updateAdverseEvent.", Long.valueOf(0),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAdverseEvent. " + e);
        }

    }

    /**
     * TestCase for Updating the Adverse Event in caAERS when Study does not exist in caAERS.
     */
    @Test
    public void updateAEStudyNotExist() {
        final String adverseEventXMLStr = getAEStudyNotExist();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .updateAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for UpdateAEStudyNotExist.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAEStudyNotExist. " + e);
        }
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS when Participant does not exist in caAERS.
     */
    // @Test
    public void updateAEParticipantNotExist() {
        final String adverseEventXMLStr = getAEParticipantNotExist();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .updateAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for UpdateAEParticipantNotExist.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAEParticipantNotExist. " + e);
        }
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS when Participant is NOT assigned to given Study.
     */
    // @Test
    public void updateAEParticipantNotAssignedToStudy() {
        final String adverseEventXMLStr = getAEParticipantNotAssignedToStudy();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .updateAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for UpdateAEParticipantNotAssignedToStudy.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAEParticipantNotAssignedToStudy. " + e);
        }
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS when startDate of an AE is Invalid
     */
    // @Test
    public void updateAEInvalidStartDateofAE() {
        final String adverseEventXMLStr = getAEInvalidAEStartDate();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .updateAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for UpdateAEInvalidStartDateofAE.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAEInvalidStartDateofAE. " + e);
        }
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS when endDate of an AE is Invalid.
     */
    // @Test
    public void updateAEInvalidEndDateofAE() {
        final String adverseEventXMLStr = getAEInvalidAEEndDate();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .updateAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for UpdateAEInvalidEndDateofAE.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAEInvalidEndDateofAE. " + e);
        }
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS when startDate on AE is greater than endDate
     */
    // @Test
    public void updateAEInvalidStartEndDateCombinationofAE() {
        final String adverseEventXMLStr = getAEStartDateGreaterThanEndDate();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .updateAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for UpdateAEInvalidStartEndDateCombinationofAE.",
                    Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAEInvalidStartEndDateCombinationofAE. " + e);
        }
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS when startDate of the Course is Invalid
     */
    // @Test
    public void updateAEStartDateOfThisCourse() {
        final String adverseEventXMLStr = getAEInvalidStartDateOfThisCourse();
        CreateOrUpdateAdverseEventResponse response = null;
        try {
            response = caAERSAdverseEventServiceWSClient.updateAdverseEvent(adverseEventXMLStr);
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAEStartDateOfThisCourse. " + e);
        } catch (SOAPFaultException e) {
            LOG.debug("SOAPFaultException occured while calling updateAEStartDateOfThisCourse. ", e);
        }
        assertNull(response);
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS when endDate of the Course is Invalid
     */
    // @Test
    public void updateAEEndDateOfThisCourse() {
        final String adverseEventXMLStr = getAEInvalidEndDateOfThisCourse();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .updateAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for UpdateAEEndDateOfThisCourse.", Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAEEndDateOfThisCourse. " + e);
        }
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS when startDate is greater than the endDate of the Course
     */
    // @Test
    public void updateAEStartDateGreaterThanEndDateOfThisCourse() {
        final String adverseEventXMLStr = getAEStartDateGreaterEndDateofThisCourse();
        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .updateAdverseEvent(adverseEventXMLStr);
            assertEquals("Response code is NOT proper for updateAEStartDateGreaterThanEndDateOfThisCourse.",
                    Long.valueOf(1),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAEStartDateGreaterThanEndDateOfThisCourse. " + e);
        }
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS when OutComeEnumType is Invalid
     */
    // @Test
    public void updateAEInvalidOutComeEnumType() {
        final String adverseEventXMLStr = getAEInvalidOutComeEnumType();
        CreateOrUpdateAdverseEventResponse response = null;
        try {
            response = caAERSAdverseEventServiceWSClient.updateAdverseEvent(adverseEventXMLStr);
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAEInvalidOutComeEnumType. " + e);
        } catch (SOAPFaultException e) {
            LOG.debug("SOAPFaultException occured while calling updateAEInvalidOutComeEnumType. ", e);
        }

        assertNull(response);
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS when AttributionType is Invalid
     */
    // @Test
    public void updateAEInvalidAttributionType() {
        final String adverseEventXMLStr = getAEInvalidArrtibutionType();
        CreateOrUpdateAdverseEventResponse response = null;
        try {
            response = caAERSAdverseEventServiceWSClient.updateAdverseEvent(adverseEventXMLStr);
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAEInvalidAttributionType. " + e);
        } catch (SOAPFaultException e) {
            LOG.debug("SOAPFaultException occured while calling updateAEInvalidAttributionType. ", e);
        }
        assertNull(response);
    }

    /**
     * TestCase for Deleting/Rollbacking the Adverse Event in caAERS
     */
    @Test
    public void deleteAdverseEvent() {
        try {
            final DeleteAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .deleteAdverseEvent(getAdverseEventXMLStr());
            assertEquals("Response code is NOT proper for deleteAdverseEvent.", Long.valueOf(0),
                    Long.valueOf(response.getCaaersServiceResponse().getServiceResponse().getResponsecode()));
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling deleteAdverseEvent. " + e);
        }

    }

    private Marshaller getMarshaller() throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance(AdverseEventsInputMessage.class);
        return jc.createMarshaller();
    }

    private String getAdverseEventXMLStr() {
        return getXMLString("AdverseEvent.xml");
    }

    private String getAEStudyNotExist() {
        return getXMLString("AEStudyNotExist.xml");
    }

    private String getAEParticipantNotExist() {
        return getXMLString("AEParticipantNotExist.xml");
    }

    private String getAEParticipantNotAssignedToStudy() {
        return getXMLString("AEParticipantNotAssignedToStudy.xml");
    }

    private String getAEInvalidAEStartDate() {
        return getXMLString("AEInvalidAEStartDate.xml");
    }

    private String getAEInvalidAEEndDate() {
        return getXMLString("AEInvalidAEEndDate.xml");
    }

    private String getAEStartDateGreaterThanEndDate() {
        return getXMLString("AEStartDateGreaterThanEndDate.xml");
    }

    private String getAEInvalidStartDateOfThisCourse() {
        return getXMLString("AEInvalidStartDateOfThisCourse.xml");
    }

    private String getAEInvalidEndDateOfThisCourse() {
        return getXMLString("AEInvalidEndDateOfThisCourse.xml");
    }

    private String getAEStartDateGreaterEndDateofThisCourse() {
        return getXMLString("AEStartDateGreaterEndDateofThisCourse.xml");
    }

    private String getAEInvalidOutComeEnumType() {
        return getXMLString("AEInvalidOutComeEnumType.xml");
    }

    private String getAEInvalidArrtibutionType() {
        return getXMLString("AEInvalidAttributionType.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaAERSAdverseEventServiceClientTest.class.getClassLoader().getResourceAsStream(
                "payloads_adverseevent/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }

    private String getAdverseEventsInputMessageString() {
        StringWriter sw = null;
        try {
            final AdverseEventsInputMessage inputMessage = new AdverseEventsInputMessage();
            final AdverseEvents adverseEvents = new AdverseEvents();
            final Criteria criteria = new Criteria();

            final AdverseEventType event1 = getAdverseEvent1();
            adverseEvents.getAdverseEvent().add(event1);
            final AdverseEventType event2 = getAdverseEvent2();
            adverseEvents.getAdverseEvent().add(event2);

            criteria.setParticipantIdentifier("PM-113");
            criteria.setStudyIdentifier("7216");
            final CourseType course = getCourseType();
            criteria.setCourse(course);

            inputMessage.setAdverseEvents(adverseEvents);
            inputMessage.setCriteria(criteria);

            final QName qname = new QName("http://schema.integration.caaers.cabig.nci.nih.gov/adverseevent",
                    "adverseevent");
            final JAXBElement<AdverseEventsInputMessage> ptJaxbEle = new JAXBElement<AdverseEventsInputMessage>(qname,
                    AdverseEventsInputMessage.class, inputMessage);
            sw = new StringWriter();
            getMarshaller().marshal(ptJaxbEle, sw);

            System.out.println("StringXML-->" + sw.toString()); // NOPMD
        } catch (DatatypeConfigurationException de) {
            LOG.debug("DatatypeConfigurationExceptionwhile populating the test data.", de);
        } catch (JAXBException je) {
            LOG.debug("JAXBException while populating the test data.", je);
        }

        return sw.toString();
    }

    private CourseType getCourseType() throws DatatypeConfigurationException {
        final GregorianCalendar gcal2 = new GregorianCalendar(2012, 06, 12);
        final XMLGregorianCalendar xgcal2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal2);
        final GregorianCalendar gcal3 = new GregorianCalendar(2012, 06, 15);
        final XMLGregorianCalendar xgcal3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal3);

        final CourseType course = new CourseType();
        course.setStartDateOfThisCourse(xgcal2);
        course.setEndDateOfThisCourse(xgcal3);
        course.setTreatmentType("Treatment");
        course.setTreatmentAssignmentCode("TAC");
        return course;
    }

    private AdverseEventType getAdverseEvent1() throws DatatypeConfigurationException {
        final GregorianCalendar gcal0 = new GregorianCalendar(2012, 06, 10);
        final XMLGregorianCalendar xgcal0 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal0);
        final GregorianCalendar gcal1 = new GregorianCalendar(2012, 06, 11);
        final XMLGregorianCalendar xgcal1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal1);
        final AdverseEventType event1 = new AdverseEventType();
        event1.setVerbatim("Event1 Verbatim");
        event1.setCtepCode("10001367");// code for 'Adrenal insufficiency'
        event1.setGrade(3);
        event1.setStartDate(xgcal0);
        event1.setEndDate(xgcal1);
        event1.setExpected(true);
        event1.setAttributionSummary(AttributionType.POSSIBLE);
        final OutcomeType outcomeType1 = new OutcomeType();
        outcomeType1.setOutComeEnumType(OutComeEnumType.LIFE_THREATENING);
        final OutcomeType outcomeType2 = new OutcomeType();
        outcomeType2.setOutComeEnumType(OutComeEnumType.HOSPITALIZATION);
        event1.getOutcome().add(outcomeType1);
        event1.getOutcome().add(outcomeType2);
        return event1;
    }

    private AdverseEventType getAdverseEvent2() throws DatatypeConfigurationException {
        final GregorianCalendar gcal0 = new GregorianCalendar(2012, 06, 10);
        final XMLGregorianCalendar xgcal0 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal0);
        final GregorianCalendar gcal1 = new GregorianCalendar(2012, 06, 11);
        final XMLGregorianCalendar xgcal1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal1);
        final AdverseEventType event2 = new AdverseEventType();
        event2.setVerbatim("Event2 Verbatim");
        event2.setCtepCode("10014004");// code for 'Ear disorder'
        event2.setGrade(4);
        event2.setStartDate(xgcal0);
        event2.setEndDate(xgcal1);
        event2.setExpected(true);
        event2.setAttributionSummary(AttributionType.DEFINITE);
        final OutcomeType outcomeType3 = new OutcomeType();
        outcomeType3.setOutComeEnumType(OutComeEnumType.CONGENITAL_ANOMALY);
        final OutcomeType outcomeType4 = new OutcomeType();
        outcomeType4.setOutComeEnumType(OutComeEnumType.OTHER_SERIOUS);
        event2.getOutcome().add(outcomeType3);
        event2.getOutcome().add(outcomeType4);
        return event2;
    }

    /*
     * private Unmarshaller getUnMarshaller() throws JAXBException { final JAXBContext jc =
     * JAXBContext.newInstance(AdverseEventsInputMessage.class); return jc.createUnmarshaller(); }
     */
}
