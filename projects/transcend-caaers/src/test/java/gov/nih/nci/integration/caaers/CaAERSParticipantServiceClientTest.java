package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.webservice.AssignmentType;
import gov.nih.nci.cabig.caaers.webservice.EthnicityType;
import gov.nih.nci.cabig.caaers.webservice.GenderType;
import gov.nih.nci.cabig.caaers.webservice.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.webservice.OrganizationType;
import gov.nih.nci.cabig.caaers.webservice.ParticipantIdentifierType;
import gov.nih.nci.cabig.caaers.webservice.ParticipantType;
import gov.nih.nci.cabig.caaers.webservice.RaceType;
import gov.nih.nci.cabig.caaers.webservice.ReducedIdentifierType;
import gov.nih.nci.cabig.caaers.webservice.Response;
import gov.nih.nci.cabig.caaers.webservice.StudyIdentifierType;
import gov.nih.nci.cabig.caaers.webservice.StudySiteType;
import gov.nih.nci.cabig.caaers.webservice.StudyType;
import gov.nih.nci.cabig.caaers.webservice.ParticipantType.Assignments;
import gov.nih.nci.cabig.caaers.webservice.ParticipantType.Identifiers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.cxf.helpers.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author chandrasekaravr
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-transcend-caaers.xml")
public class CaAERSParticipantServiceClientTest {
	
	@Autowired
	private CaAERSParticipantServiceWSClient caAERSParticipantServiceClient;
		
	public CaAERSParticipantServiceClientTest() {
		super();
	}
	
	@Test
	public void marshalParticipantType() throws JAXBException, DatatypeConfigurationException {
		ParticipantType pt = new ParticipantType();
		pt.setFirstName("fn");
		pt.setLastName("ln");
		pt.setEthnicity(EthnicityType.NOT_HISPANIC_OR_LATINO);
		pt.setGender(GenderType.MALE);
		DatatypeFactory df =  DatatypeFactory.newInstance();
		GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(new Date().getTime());
        pt.setBirthDate(df.newXMLGregorianCalendar(gc));
        pt.setRace(RaceType.WHITE);
        
        OrganizationAssignedIdentifierType orgId = new OrganizationAssignedIdentifierType();
        orgId.setType(ParticipantIdentifierType.MRN);
        orgId.setValue("123456");
        orgId.setPrimaryIndicator(true);
        OrganizationType ot = new OrganizationType();
        ot.setName("UCSF)");
        ot.setNciInstituteCode("UCSF");
        orgId.setOrganization(ot);
        Identifiers ids = new Identifiers();
        ids.getOrganizationAssignedIdentifier().add(orgId);
        pt.setIdentifiers(ids);
        
        AssignmentType at = new AssignmentType();
        at.setStudySubjectIdentifier("456");
        StudySiteType sst= new StudySiteType();
        OrganizationType ot2 = new OrganizationType();
        ot2.setName("UCSF)");
        ot2.setNciInstituteCode("UCSF");
        sst.setOrganization(ot2);
        StudyType st = new StudyType();
        ReducedIdentifierType rit = new ReducedIdentifierType();
        rit.setType(StudyIdentifierType.SITE_IDENTIFIER);
        rit.setValue("1.2.3.4.5");
        StudyType.Identifiers sids = new StudyType.Identifiers();
        sids.setIdentifier(rit);
        st.setIdentifiers(sids);
        sst.setStudy(st);
        at.setStudySite(sst);
        
        Assignments ass = new Assignments();
        ass.getAssignment().add(at);
        pt.setAssignments(ass);
		
		QName qname = new QName("http://webservice.caaers.cabig.nci.nih.gov/participant", "participant");
		JAXBElement<ParticipantType> ptJaxbEle = new JAXBElement<ParticipantType>(qname, ParticipantType.class, pt);
		StringWriter sw = new StringWriter();
		getMarshaller().marshal(ptJaxbEle, sw);
		System.out.println(sw.toString());
		Assert.assertNotNull(sw.toString());
	}


	//@Test
	public void createParticipant() throws JAXBException, IOException {		
		
		String participantXMLStr = IOUtils.toString(new FileReader(new File("C:\\vin\\SUITE-iHub\\tmp\\sample\\smpl_caaers_participant.xml")));

		Response response = caAERSParticipantServiceClient.createParticipant(participantXMLStr);
		
		Assert.assertNotNull(response);
		System.out.println(response.getDescription());
		System.out.println(response.getResponsecode());
		System.out.println(response.getMessage());
	}
	
	@Test
	public void createParticipant2() throws JAXBException, IOException {		
		
		String participantXMLStr = getPStr();

		Response response = caAERSParticipantServiceClient.createParticipant(participantXMLStr);
		
		Assert.assertNotNull(response);
		System.out.println(response.getDescription());
		System.out.println(response.getResponsecode());
		System.out.println(response.getMessage());
	}
	
	private Marshaller getMarshaller() throws JAXBException {		
		JAXBContext jc = JAXBContext.newInstance(ParticipantType.class);		
		return jc.createMarshaller();		
	}
	
	private String getPStr() {
		return "<?xml version=\"1.0\"?><participant xmlns:p=\"http://integration.nci.nih.gov/participant\" id=\"1\" version=\"1\"><firstName>Cherry</firstName><lastName>Blossom</lastName><maidenName/><middleName/><birthDate>19410502</birthDate><gender>Male</gender><race>White</race><ethnicity>Not Hispanic or Latino</ethnicity><identifiers><organizationAssignedIdentifier id=\"1\" version=\"1\"><type>MRN</type><value>996005</value><primaryIndicator>true</primaryIndicator><organization id=\"1\" version=\"1\"><name>University of California San Francisco (UCSF)</name><nciInstituteCode>UCSF</nciInstituteCode></organization></organizationAssignedIdentifier><organizationAssignedIdentifier id=\"1\" version=\"1\"><type>SSN</type><value>123-45-9994</value><primaryIndicator>false</primaryIndicator><organization id=\"1\" version=\"1\"><name>SSN</name><nciInstituteCode>SSN</nciInstituteCode></organization></organizationAssignedIdentifier><systemAssignedIdentifier id=\"1\" version=\"1\"><type>MRN</type><value>996005</value><primaryIndicator>true</primaryIndicator><systemName>MRN</systemName></systemAssignedIdentifier></identifiers><assignments><assignment id=\"1\" version=\"1\"><studySubjectIdentifier>49864</studySubjectIdentifier><studySite id=\"1\" version=\"1\"><study id=\"1\" version=\"1\"><identifiers><identifier id=\"1\" version=\"1\"><type>Study Identifier</type><value>CP-01</value></identifier></identifiers></study><organization id=\"1\" version=\"1\"><name>University of California San Francisco (UCSF)</name><nciInstituteCode>UCSF</nciInstituteCode></organization></studySite></assignment></assignments></participant>";
	}
}
