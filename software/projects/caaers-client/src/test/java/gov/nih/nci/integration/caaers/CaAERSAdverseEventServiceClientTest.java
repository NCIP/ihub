package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEvents;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventsInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AttributionType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CourseType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CreateAdverseEventResponse;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CreateOrUpdateAdverseEventResponse;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.Criteria;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.DeleteAdverseEventResponse;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.OutComeEnumType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.OutcomeType;
import gov.nih.nci.integration.exception.IntegrationException;

import java.io.StringWriter;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.soap.SOAPFaultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    /**
     * @throws IntegrationException - IntegrationException
     * @throws DatatypeConfigurationException - DatatypeConfigurationException
     * @throws JAXBException - JAXBException
     * 
     */
    @Test
    public void createAdverseEvent() throws IntegrationException, DatatypeConfigurationException, JAXBException {

        final String adverseEventXMLStr = getAdverseEventsInputMessageString();

        // final String adverseEventXMLStr = getAdverseEventXMLStr();

        try {
            final CreateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .createAdverseEvent(adverseEventXMLStr);

            System.out.println("Response Status--> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getStatus());
            System.out.println("Response Message --> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getMessage());
            System.out.println("Response Code --> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getResponsecode());
            System.out.println("Response Data --> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getResponseData());

        } catch (SOAPFaultException e) {
            System.err.println(e); // NOPMD
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            Assert.fail("Expected either SOAPFaultException or IntegrationException only!" + e);
            // CHECKSTYLE:ON
        }

    }

    /**
     * 
     * @throws IntegrationException - IntegrationException
     * @throws DatatypeConfigurationException - DatatypeConfigurationException
     * @throws JAXBException - JAXBException
     */
    // @Test
    public void updateAdverseEvent() throws IntegrationException, DatatypeConfigurationException, JAXBException {

        try {
            final CreateOrUpdateAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .updateAdverseEvent(getAdverseEventXMLStr());

            System.out.println("Response Status--> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getStatus());
            System.out.println("Response Message --> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getMessage());
            System.out.println("Response Code --> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getResponsecode());
            System.out.println("Response Data --> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getResponseData());

        } catch (SOAPFaultException e) {
            System.err.println(e); // NOPMD
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            Assert.fail("Expected either SOAPFaultException or IntegrationException only!" + e);
            // CHECKSTYLE:ON
        }

    }

    /**
     * 
     * @throws IntegrationException - IntegrationException
     * @throws DatatypeConfigurationException - DatatypeConfigurationException
     * @throws JAXBException - JAXBException
     */
    // @Test
    public void deleteAdverseEvent() throws IntegrationException, DatatypeConfigurationException, JAXBException {

        try {
            final DeleteAdverseEventResponse response = caAERSAdverseEventServiceWSClient
                    .deleteAdverseEvent(getAdverseEventXMLStr());

            System.out.println("Response Status--> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getStatus());
            System.out.println("Response Message --> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getMessage());
            System.out.println("Response Code --> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getResponsecode());
            System.out.println("Response Data --> " // NOPMD
                    + response.getCaaersServiceResponse().getServiceResponse().getResponseData());

        } catch (SOAPFaultException e) {
            System.err.println(e); // NOPMD
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            Assert.fail("Expected either SOAPFaultException or IntegrationException only!" + e);
            // CHECKSTYLE:ON
        }

    }

    private Marshaller getMarshaller() throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance(AdverseEventsInputMessage.class);
        return jc.createMarshaller();
    }

    private Unmarshaller getUnMarshaller() throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance(AdverseEventsInputMessage.class);
        return jc.createUnmarshaller();
    }

    private String getAdverseEventsInputMessageString() throws DatatypeConfigurationException, JAXBException {
        final GregorianCalendar gcal0 = new GregorianCalendar(2012, 06, 10);
        final XMLGregorianCalendar xgcal0 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal0);
        final GregorianCalendar gcal1 = new GregorianCalendar(2012, 06, 11);
        final XMLGregorianCalendar xgcal1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal1);
        final GregorianCalendar gcal2 = new GregorianCalendar(2012, 06, 12);
        final XMLGregorianCalendar xgcal2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal2);
        final GregorianCalendar gcal3 = new GregorianCalendar(2012, 06, 15);
        final XMLGregorianCalendar xgcal3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal3);

        final AdverseEventsInputMessage inputMessage = new AdverseEventsInputMessage();
        final AdverseEvents adverseEvents = new AdverseEvents();
        final Criteria criteria = new Criteria();

        final AdverseEventType event1 = new AdverseEventType();
        event1.setVerbatim("Event1 Verbatim");
        event1.setCtepCode("Adrenal insufficiency");
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

        adverseEvents.getAdverseEvent().add(event1);

        final AdverseEventType event2 = new AdverseEventType();
        event2.setVerbatim("Event2 Verbatim");
        event2.setCtepCode("Aspartateamino transferase increased");
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

        adverseEvents.getAdverseEvent().add(event2);

        criteria.setParticipantIdentifier("PM-113");
        criteria.setStudyIdentifier("7216");
        final CourseType course = new CourseType();
        course.setStartDateOfThisCourse(xgcal2);
        course.setEndDateOfThisCourse(xgcal3);
        course.setTreatmentType("Treatment");
        course.setTreatmentAssignmentCode("TAC");

        criteria.setCourse(course);

        inputMessage.setAdverseEvents(adverseEvents);
        inputMessage.setCriteria(criteria);

        final QName qname = new QName("http://schema.integration.caaers.cabig.nci.nih.gov/adverseevent", "adverseevent");
        final JAXBElement<AdverseEventsInputMessage> ptJaxbEle = new JAXBElement<AdverseEventsInputMessage>(qname,
                AdverseEventsInputMessage.class, inputMessage);
        final StringWriter sw = new StringWriter();
        getMarshaller().marshal(ptJaxbEle, sw);
        System.out.println("StringXML-->" + sw.toString()); // NOPMD

        return sw.toString();
    }

    // CHECKSTYLE:OFF
    private String getAdverseEventXMLStr() {

        // return
        // "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns2:adverseevent xmlns:ns2=\"http://schema.integration.caaers.cabig.nci.nih.gov/adverseevent\" xmlns:ns3=\"http://schema.integration.caaers.cabig.nci.nih.gov/common\"><ns2:adverseEvents><ns2:adverseEvent><verbatim>Event1 Verbatim</verbatim><grade>3</grade><expected>true</expected><attributionSummary>POSSIBLE</attributionSummary><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><ctepCode>Adrenal insufficiency</ctepCode><outcome><outComeEnumType>LIFE_THREATENING</outComeEnumType></outcome><outcome><outComeEnumType>HOSPITALIZATION</outComeEnumType></outcome></ns2:adverseEvent><ns2:adverseEvent><verbatim>Event2 Verbatim</verbatim><grade>4</grade><expected>true</expected><attributionSummary>DEFINITE</attributionSummary><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><ctepCode>Aspartateamino transferase increased</ctepCode><outcome><outComeEnumType>CONGENITAL_ANOMALY</outComeEnumType></outcome><outcome><outComeEnumType>OTHER_SERIOUS</outComeEnumType></outcome></ns2:adverseEvent></ns2:adverseEvents><ns2:criteria><participantIdentifier>PM-113</participantIdentifier><studyIdentifier>7216</studyIdentifier><course><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse><treatmentType>Treatment</treatmentType><treatmentAssignmentCode>TAC</treatmentAssignmentCode></course></ns2:criteria></ns2:adverseevent>";

        return "<?xml version=\"1.0\"?><ns2:adverseevent xmlns:ns2=\"http://schema.integration.caaers.cabig.nci.nih.gov/adverseevent\" xmlns:ns3=\"http://schema.integration.caaers.cabig.nci.nih.gov/common\"><ns2:adverseEvents><ns2:adverseEvent><verbatim>Event1 Verbatim</verbatim><grade>3</grade><expected>true</expected><attributionSummary>POSSIBLE</attributionSummary><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><ctepCode>Adrenal insufficiency</ctepCode><outcome><outComeEnumType>LIFE_THREATENING</outComeEnumType></outcome><outcome><outComeEnumType>HOSPITALIZATION</outComeEnumType></outcome></ns2:adverseEvent><ns2:adverseEvent><verbatim>Event2 Verbatim</verbatim><grade>4</grade><expected>true</expected><attributionSummary>DEFINITE</attributionSummary><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><ctepCode>Aspartateamino transferase increased</ctepCode><outcome><outComeEnumType>CONGENITAL_ANOMALY</outComeEnumType></outcome><outcome><outComeEnumType>OTHER_SERIOUS</outComeEnumType></outcome></ns2:adverseEvent></ns2:adverseEvents><ns2:criteria><participantIdentifier>PM-113</participantIdentifier><studyIdentifier>7216</studyIdentifier><course><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse><treatmentType>Treatment</treatmentType><treatmentAssignmentCode>TAC</treatmentAssignmentCode></course></ns2:criteria></ns2:adverseevent>";
    }
    // CHECKSTYLE:ON
}
