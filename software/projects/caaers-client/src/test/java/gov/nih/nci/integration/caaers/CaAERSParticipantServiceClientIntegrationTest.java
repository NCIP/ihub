package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ParticipantIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.common.StudyIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.AssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.EthnicityType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.GenderType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType.Assignments;
import gov.nih.nci.cabig.caaers.integration.schema.participant.RaceType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ReducedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.StudySiteType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.StudyType;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
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
 * This client test code only tests the client communication and does code coverage. So, if there is proper service, it
 * will fail with SOAPFaultException because of schema validation. If not will fail with IntegrationException because of
 * Connection Refused.
 * 
 * @author chandrasekaravr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-caaers-client-test.xml")
public class CaAERSParticipantServiceClientIntegrationTest {

    @Autowired
    private CaAERSParticipantServiceWSClient caAERSParticipantServiceClient;
    private static final Logger LOG = LoggerFactory.getLogger(CaAERSParticipantServiceClientIntegrationTest.class);

    /**
     * Testcase for marshlling participant type
     * 
     * @throws JAXBException - JAXBException
     * @throws DatatypeConfigurationException - DatatypeConfigurationException
     */
    @Test
    public void marshalParticipantType() throws JAXBException, DatatypeConfigurationException {
        final ParticipantType pt = new ParticipantType();
        pt.setFirstName("fn");
        pt.setLastName("ln");
        pt.setEthnicity(EthnicityType.NOT_HISPANIC_OR_LATINO);
        pt.setGender(GenderType.MALE);
        final DatatypeFactory df = DatatypeFactory.newInstance();
        final GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        pt.setBirthDate(df.newXMLGregorianCalendar(gc));
        pt.setRace(RaceType.WHITE);

        final OrganizationAssignedIdentifierType orgId = new OrganizationAssignedIdentifierType();
        orgId.setType(ParticipantIdentifierType.MRN);
        orgId.setValue("123456");
        orgId.setPrimaryIndicator(true);
        final OrganizationType ot = new OrganizationType();
        ot.setName("UCSF)");
        ot.setNciInstituteCode("UCSF");
        orgId.setOrganization(ot);
        final ParticipantType.Identifiers ids = new ParticipantType.Identifiers();
        ids.getOrganizationAssignedIdentifier().add(orgId);
        pt.setIdentifiers(ids);

        final AssignmentType at = new AssignmentType();
        at.setStudySubjectIdentifier("456");
        final StudySiteType sst = new StudySiteType();
        final OrganizationType ot2 = new OrganizationType();
        ot2.setName("UCSF)");
        ot2.setNciInstituteCode("UCSF");
        sst.setOrganization(ot2);
        final StudyType st = new StudyType();
        final ReducedIdentifierType rit = new ReducedIdentifierType();
        rit.setType(StudyIdentifierType.SITE_IDENTIFIER);
        rit.setValue("1.2.3.4.5");
        final StudyType.Identifiers sids = new StudyType.Identifiers();
        sids.setIdentifier(rit);
        st.setIdentifiers(sids);
        sst.setStudy(st);
        at.setStudySite(sst);

        final Assignments ass = new Assignments();
        ass.getAssignment().add(at);
        pt.setAssignments(ass);

        final QName qname = new QName("http://webservice.caaers.cabig.nci.nih.gov/participant", "participant");
        final JAXBElement<ParticipantType> ptJaxbEle = new JAXBElement<ParticipantType>(qname, ParticipantType.class,
                pt);
        final StringWriter sw = new StringWriter();
        getMarshaller().marshal(ptJaxbEle, sw);
        Assert.assertNotNull(sw.toString());

        JAXBElement<ParticipantType> jaxbEle = (JAXBElement<ParticipantType>) getUnMarshaller().unmarshal(
                new StreamSource(new StringReader(sw.toString())), ParticipantType.class);
        Assert.assertNotNull(jaxbEle);
        Assert.assertNotNull(jaxbEle.getValue());

        jaxbEle = (JAXBElement<ParticipantType>) getUnMarshaller().unmarshal(
                new StreamSource(new StringReader(getPStr())), ParticipantType.class);
        Assert.assertNotNull(jaxbEle);
        final ParticipantType ptn = jaxbEle.getValue();
        Assert.assertNotNull(ptn);
    }

    /**
     * Testcase for createParticipant
     * 
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     */
    @Test
    public void createParticipant() throws MalformedURLException, JAXBException {
        final String participantXMLStr = getPStr();
        try {
            caAERSParticipantServiceClient.createParticipant(participantXMLStr);
        } catch (SOAPFaultException e) {
            Assert.assertEquals(getXMLString("ParticipantSOAPFaultExceptionMsg.txt"), e.getMessage());
        } catch (IntegrationException e) {
            Assert.assertEquals(IntegrationError._1053.getErrorCode(), e.getErrorCode());
        }
    }

    /**
     * Testcase for getParticipant
     * 
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     */
    @Test
    public void getParticipant() throws MalformedURLException, JAXBException {
        final String participantXMLStr = getPStr();
        try {
            caAERSParticipantServiceClient.getParticipant(participantXMLStr);
        } catch (SOAPFaultException e) {
            Assert.assertEquals(getXMLString("ParticipantSOAPFaultExceptionMsg.txt"), e.getMessage());
        } catch (IntegrationException e) {
            Assert.assertEquals(IntegrationError._1053.getErrorCode(), e.getErrorCode());
        }
    }

    /**
     * Testcase for updateParticipant
     * 
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     */
    @Test
    public void updateParticipant() throws MalformedURLException, JAXBException {
        final String participantXMLStr = getPStr();
        try {
            caAERSParticipantServiceClient.updateParticipant(participantXMLStr);
        } catch (SOAPFaultException e) {
            Assert.assertEquals(getXMLString("ParticipantSOAPFaultExceptionMsg.txt"), e.getMessage());
        } catch (IntegrationException e) {
            Assert.assertEquals(IntegrationError._1053.getErrorCode(), e.getErrorCode());
        }
    }

    /**
     * Testcase for updateParticipant for Off Study
     * 
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     */
    @Test
    public void updateParticipantOffStudy() throws MalformedURLException, JAXBException {
        final String participantXMLStr = getParticipantOffStudyString();
        try {
            caAERSParticipantServiceClient.updateParticipant(participantXMLStr);
        } catch (SOAPFaultException e) {
            Assert.assertEquals(getXMLString("ParticipantSOAPFaultExceptionMsg.txt"), e.getMessage());
        } catch (IntegrationException e) {
            Assert.assertEquals(IntegrationError._1053.getErrorCode(), e.getErrorCode());
        }
    }

    /**
     * Testcase for deleteParticipant
     * 
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     */
    @Test
    public void deleteParticipant() throws MalformedURLException, JAXBException {
        final String participantXMLStr = getPStr();
        try {
            caAERSParticipantServiceClient.deleteParticipant(participantXMLStr);
        } catch (SOAPFaultException e) {
            Assert.assertEquals(getXMLString("ParticipantSOAPFaultExceptionMsg.txt"), e.getMessage());
        } catch (IntegrationException e) {
            Assert.assertEquals(IntegrationError._1053.getErrorCode(), e.getErrorCode());
        }
    }

    private Marshaller getMarshaller() throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance(ParticipantType.class);
        return jc.createMarshaller();
    }

    private Unmarshaller getUnMarshaller() throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance(ParticipantType.class);
        return jc.createUnmarshaller();
    }

    private String getPStr() {
        return getXMLString("Participant_caaers.xml");
    }

    private String getParticipantOffStudyString() {
        return getXMLString("Participant_OffStudy_caaers.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaAERSAdverseEventServiceClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/participant/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
