/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.caaers;

import java.io.File;
import java.sql.Date;

import javax.swing.text.DefaultEditorKit.CutAction;

import gov.nih.nci.integration.caaers.invoker.CaAERSServiceInvocationStrategyFactory;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;

import org.junit.Test;

import static org.junit.Assert.*;

public class CaAERSServiceInvocationStrategyFactoryTest {
	
	@Test
	public void createCaAERSServiceInvocationStrategy() {
		File customLibLoc = new File("..\\caaers-client\\caaers-client-lib\\");
		File distLoc = new File("..\\caaers-client\\build\\dist\\");
		System.out.println(customLibLoc.getAbsolutePath());
		ServiceInvocationStrategy sris = CaAERSServiceInvocationStrategyFactory
			.createCaAERSRegistrationServiceInvocationStrategy(
					new String[] { 
							customLibLoc.getAbsolutePath(),
							distLoc.getAbsolutePath()
					}, 
					"classpath*:applicationContext-caaers-client.xml");
		ServiceInvocationStrategy suris = CaAERSServiceInvocationStrategyFactory
		.createCaAERSUpdateRegistrationServiceInvocationStrategy(
				new String[] { 
						customLibLoc.getAbsolutePath(),
						distLoc.getAbsolutePath()
				}, 
				"classpath*:applicationContext-caaers-client.xml");
		System.out.println(sris.getClass().getClassLoader());
		assertNotNull(sris);
		assertNotNull(suris);
		
		ServiceInvocationMessage msg = new ServiceInvocationMessage();
		msg.setStrategyIdentifier(StrategyIdentifier.CAEERS_CREATE_REGISTRATION);
		IHubMessage iHubMessage = new IHubMessage();
		iHubMessage.setRequest(getPStr());
		final Date stTime = new Date(new java.util.Date().getTime());
		iHubMessage.setStartTime(stTime);
		msg.setMessage(iHubMessage);
		
		ServiceInvocationResult res = sris.invoke(msg);
		assertNotNull(res);
		assertTrue(res.isFault());
		assertNotNull(res.getInvocationException());
		System.out.println(res.getResult());
		System.out.println(res.getInvocationException().getCause());
		System.out.println(Thread.currentThread().getContextClassLoader());
	}
	
	private String getPStr() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns1:MessageBroadcasterRequestMessage 	xmlns:ns1=\"http://integration.nci.nih.gov/messaging\" ><ns1:metadata><ns1:serviceType>Registration</ns1:serviceType><ns1:externalIdentifier>myExternalIdentifier</ns1:externalIdentifier></ns1:metadata><ns1:request><ns1:businessMessagePayload><ns1:xmlSchemaDefinition>http://integration.nci.nih.gov/participant</ns1:xmlSchemaDefinition><p:participant xmlns:p=\"http://integration.nci.nih.gov/participant\" id=\"1\" version=\"1\"><p:firstName>Richard</p:firstName><p:lastName>Herd</p:lastName><p:maidenName>maidenName</p:maidenName><p:middleName>Leing</p:middleName><p:birthDate>2001-01-01</p:birthDate><p:gender>Male</p:gender><p:race>Asian</p:race><p:ethnicity>Hispanic or Latino</p:ethnicity><p:identifiers><p:organizationAssignedIdentifier><p:type>MRN</p:type><p:value>poi</p:value><p:primaryIndicator>true</p:primaryIndicator><p:organization><p:name>Mayo Clinic Hospital</p:name><p:nciInstituteCode/></p:organization></p:organizationAssignedIdentifier><p:systemAssignedIdentifier><p:type>MRN</p:type><p:value>ikm</p:value><p:primaryIndicator>false</p:primaryIndicator><p:systemName>Yarois</p:systemName></p:systemAssignedIdentifier></p:identifiers><p:assignments><p:assignment><p:studySubjectIdentifier>001</p:studySubjectIdentifier><p:studySite><p:study><p:identifiers><p:identifier><p:type>Protocol Authority Identifier</p:type><p:value>6482</p:value></p:identifier></p:identifiers></p:study><p:organization><p:name>QU</p:name><p:nciInstituteCode>DCP</p:nciInstituteCode></p:organization></p:studySite></p:assignment></p:assignments></p:participant></ns1:businessMessagePayload></ns1:request></ns1:MessageBroadcasterRequestMessage>";
		//return "<MessageBroadcasterRequestMessage xmlns=\"http://integration.nci.nih.gov/messaging\" xmlns:cacis=\"http://cacis.nci.nih.gov\" xmlns:ns1=\"http://integration.nci.nih.gov/messaging\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:xs=\"http://www.w3.org/TR/2008/REC-xml-20081126#\"><metadata><serviceType>REGISTRATION</serviceType><externalIdentifier>2672224</externalIdentifier></metadata><request><businessMessagePayload><xmlSchemaDefinition>http://integration.nci.nih.gov/participant</xmlSchemaDefinition><p:participant xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" id=\"1\" version=\"1\" xsi:schemaLocation=\"http://integration.nci.nih.gov/participant Participant.xsd \"><p:firstName>Cherry01</p:firstName><p:lastName>Blossom01</p:lastName><p:maidenName/><p:middleName/><p:birthDate>19410502</p:birthDate><p:gender>Male</p:gender><p:race>White</p:race><p:ethnicity>Not Hispanic or Latino</p:ethnicity><p:activityStatus>Active</p:activityStatus><p:identifiers><p:organizationAssignedIdentifier id=\"1\" version=\"1\"><p:type>MRN</p:type><p:value>806005</p:value><p:primaryIndicator>true</p:primaryIndicator><p:organization id=\"1\" version=\"1\"><p:name>University of California San Francisco (UCSF)</p:name><p:nciInstituteCode>In Transit</p:nciInstituteCode></p:organization></p:organizationAssignedIdentifier><p:organizationAssignedIdentifier id=\"1\" version=\"1\"><p:type>SSN</p:type><p:value>123-45-9980</p:value><p:primaryIndicator>false</p:primaryIndicator><p:organization id=\"1\" version=\"1\"><p:name>SSN</p:name><p:nciInstituteCode>SSN</p:nciInstituteCode></p:organization></p:organizationAssignedIdentifier><p:systemAssignedIdentifier id=\"1\" version=\"1\"><p:type>MRN</p:type><p:value>806005</p:value><p:primaryIndicator>true</p:primaryIndicator><p:systemName>MRN</p:systemName></p:systemAssignedIdentifier></p:identifiers><p:assignments><p:assignment id=\"1\" version=\"1\"><p:studySubjectIdentifier>49864</p:studySubjectIdentifier><p:studySite id=\"1\" version=\"1\"><p:study id=\"1\" version=\"1\"><p:identifiers><p:identifier id=\"1\" version=\"1\"><p:type>Study Identifier</p:type><p:value>CP-01</p:value></p:identifier></p:identifiers></p:study><p:organization id=\"1\" version=\"1\"><p:name>University of California San Francisco (UCSF)</p:name><p:nciInstituteCode>In Transit</p:nciInstituteCode></p:organization></p:studySite></p:assignment></p:assignments></p:participant></businessMessagePayload></request></MessageBroadcasterRequestMessage>";
	}
}
