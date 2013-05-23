/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ParticipantIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.StudyIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.AssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.EthnicityType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.GenderType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.RaceType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ReducedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.StudySiteType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.StudyType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType.Assignments;

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
@ContextConfiguration(locations = "classpath:applicationContext-caaers-client.xml")
public class CaAERSParticipantServiceClientTest {
	
	@Autowired
	private CaAERSParticipantServiceWSClient caAERSParticipantServiceClient;
		
	public CaAERSParticipantServiceClientTest() {
		super();
	}
	
	//@Test
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
        ParticipantType.Identifiers ids = new ParticipantType.Identifiers();
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

		CaaersServiceResponse caaersresponse = caAERSParticipantServiceClient.createParticipant(participantXMLStr);
		ServiceResponse response = caaersresponse.getServiceResponse();
		Assert.assertNotNull(response);
		System.out.println(response.getResponsecode());
		System.out.println(response.getMessage());
	}
	
	@Test
	public void createParticipant2() throws JAXBException, IOException {		
		
		String participantXMLStr = getPStr();

		CaaersServiceResponse caaersresponse = caAERSParticipantServiceClient.createParticipant(participantXMLStr);
		ServiceResponse response = caaersresponse.getServiceResponse();
		
		Assert.assertNotNull(response);
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
		//return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns2:participant xmlns:ns2=\"http://webservice.caaers.cabig.nci.nih.gov/participant\"><firstName>fn</firstName><lastName>ln</lastName><birthDate>2012-04-13</birthDate><gender>Male</gender><race>White</race><ethnicity>Not Hispanic or Latino</ethnicity><identifiers><ns2:organizationAssignedIdentifier><type>MRN</type><value>123456</value><primaryIndicator>true</primaryIndicator><ns2:organization><name>QU</name><nciInstituteCode>DCP</nciInstituteCode></ns2:organization></ns2:organizationAssignedIdentifier></identifiers><assignments><ns2:assignment><studySubjectIdentifier>456</studySubjectIdentifier><ns2:studySite><ns2:study><identifiers><identifier><type>Protocol Authority Identifier</type><value>6482</value></identifier></identifiers></ns2:study><ns2:organization><name>QU</name><nciInstituteCode>DCP</nciInstituteCode></ns2:organization></ns2:studySite></ns2:assignment></assignments></ns2:participant>";
		return "<?xml version=\"1.0\"?><caaers:participant xmlns:caaers=\"http://webservice.caaers.cabig.nci.nih.gov/participant\" xmlns:p=\"http://integration.nci.nih.gov/participant\" id=\"1\" version=\"1\"><firstName>Cherry0415</firstName><lastName>Blossom0415</lastName><maidenName/><middleName/><birthDate>1965-11-24</birthDate><gender>Male</gender><race>White</race><ethnicity>Not Hispanic or Latino</ethnicity><identifiers><caaers:organizationAssignedIdentifier id=\"1\" version=\"1\"><type>MRN</type><value>997025</value><primaryIndicator>true</primaryIndicator><caaers:organization id=\"1\" version=\"1\"><name>QU</name><nciInstituteCode>DCP</nciInstituteCode></caaers:organization></caaers:organizationAssignedIdentifier><caaers:systemAssignedIdentifier id=\"1\" version=\"1\"><type>MRN</type><value>997025</value><primaryIndicator>true</primaryIndicator><systemName>MRN</systemName></caaers:systemAssignedIdentifier></identifiers><assignments><caaers:assignment id=\"1\" version=\"1\"><studySubjectIdentifier>48824</studySubjectIdentifier><caaers:studySite id=\"1\" version=\"1\"><caaers:study id=\"1\" version=\"1\"><identifiers><identifier id=\"1\" version=\"1\"><type>Protocol Authority Identifier</type><value>6482</value></identifier></identifiers></caaers:study><caaers:organization id=\"1\" version=\"1\"><name>QU</name><nciInstituteCode>DCP</nciInstituteCode></caaers:organization></caaers:studySite></caaers:assignment></assignments></caaers:participant>";
	}
}
