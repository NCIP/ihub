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
import java.io.StringReader;
import java.io.StringWriter;
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

import org.apache.cxf.helpers.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
				
		JAXBElement<ParticipantType> jaxbEle = (JAXBElement<ParticipantType>)getUnMarshaller().unmarshal(
				new StreamSource(new StringReader(sw.toString())), ParticipantType.class);
		Assert.assertNotNull(jaxbEle);
		Assert.assertNotNull(jaxbEle.getValue());
		
        jaxbEle = (JAXBElement<ParticipantType>) getUnMarshaller().unmarshal(
				new StreamSource(new StringReader(getPStr())), ParticipantType.class);
		ParticipantType ptn = jaxbEle.getValue();
		Assert.assertNotNull(jaxbEle);
		Assert.assertNotNull(jaxbEle.getValue());
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
	
	//@Test
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
	
	private Unmarshaller getUnMarshaller() throws JAXBException {		
		JAXBContext jc = JAXBContext.newInstance(ParticipantType.class);		
		return jc.createUnmarshaller();		
	}
	
	private String getPStr() {
		return "<caaers:participant xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:caaers=\"http://webservice.caaers.cabig.nci.nih.gov/participant\" id=\"1\" version=\"1\"><firstName>Richard</firstName><lastName>Herd</lastName><maidenName>maidenName</maidenName><middleName>Leing</middleName><birthDate>2001-01-01</birthDate><gender>Male</gender><race>Asian</race><ethnicity>Hispanic or Latino</ethnicity><identifiers><caaers:organizationAssignedIdentifier id=\"1\" version=\"1\"><type>MRN</type><value>poi</value><primaryIndicator>true</primaryIndicator><caaers:organization id=\"1\" version=\"1\"><name>Mayo Clinic Hospital</name><nciInstituteCode/></caaers:organization></caaers:organizationAssignedIdentifier><caaers:systemAssignedIdentifier id=\"1\" version=\"1\"><type>MRN</type><value>ikm</value><primaryIndicator>false</primaryIndicator><systemName>Yarois</systemName></caaers:systemAssignedIdentifier></identifiers><assignments><caaers:assignment id=\"1\" version=\"1\"><studySubjectIdentifier>001</studySubjectIdentifier><caaers:studySite id=\"1\" version=\"1\"><caaers:study id=\"1\" version=\"1\"><identifiers><identifier><type>Protocol Authority Identifier</type><value>6482</value></identifier></identifiers></caaers:study><caaers:organization id=\"1\" version=\"1\"><name>QU</name><nciInstituteCode>DCP</nciInstituteCode></caaers:organization></caaers:studySite></caaers:assignment></assignments></caaers:participant>";
	}
}
