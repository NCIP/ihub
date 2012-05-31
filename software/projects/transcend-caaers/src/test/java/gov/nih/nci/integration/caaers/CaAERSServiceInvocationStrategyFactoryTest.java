package gov.nih.nci.integration.caaers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.nih.nci.integration.caaers.invoker.CaAERSServiceInvocationStrategyFactory;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;

import java.io.File;
import java.sql.Date;

import org.junit.Test;

public class CaAERSServiceInvocationStrategyFactoryTest {
	
	@Test
	public void createCaAERSServiceInvocationStrategy() {
		File customLibLoc = new File("..\\caaers-client\\build\\caaers-client-lib\\");
		File distLoc = new File("..\\caaers-client\\build\\dist\\");
		ServiceInvocationStrategy sris = CaAERSServiceInvocationStrategyFactory
			.createCaAERSRegistrationServiceInvocationStrategy(
					new String[] { 
							customLibLoc.getAbsolutePath(),
							distLoc.getAbsolutePath()
					}, 
					"classpath*:applicationContext-caaers-client-test.xml");
		ServiceInvocationStrategy suris = CaAERSServiceInvocationStrategyFactory
		.createCaAERSUpdateRegistrationServiceInvocationStrategy(
				new String[] { 
						customLibLoc.getAbsolutePath(),
						distLoc.getAbsolutePath()
				}, 
				"classpath*:applicationContext-caaers-client-test.xml");
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
	}
	
	private String getPStr() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns1:MessageBroadcasterRequestMessage 	xmlns:ns1=\"http://integration.nci.nih.gov/messaging\" ><ns1:metadata><ns1:serviceType>Registration</ns1:serviceType><ns1:externalIdentifier>myExternalIdentifier</ns1:externalIdentifier></ns1:metadata><ns1:request><ns1:businessMessagePayload><ns1:xmlSchemaDefinition>http://integration.nci.nih.gov/participant</ns1:xmlSchemaDefinition><p:participant xmlns:p=\"http://integration.nci.nih.gov/participant\" id=\"1\" version=\"1\"><p:firstName>Richard</p:firstName><p:lastName>Herd</p:lastName><p:maidenName>maidenName</p:maidenName><p:middleName>Leing</p:middleName><p:birthDate>2001-01-01</p:birthDate><p:gender>Male</p:gender><p:race>Asian</p:race><p:ethnicity>Hispanic or Latino</p:ethnicity><p:identifiers><p:organizationAssignedIdentifier><p:type>MRN</p:type><p:value>poi</p:value><p:primaryIndicator>true</p:primaryIndicator><p:organization><p:name>Mayo Clinic Hospital</p:name><p:nciInstituteCode/></p:organization></p:organizationAssignedIdentifier><p:systemAssignedIdentifier><p:type>MRN</p:type><p:value>ikm</p:value><p:primaryIndicator>false</p:primaryIndicator><p:systemName>Yarois</p:systemName></p:systemAssignedIdentifier></p:identifiers><p:assignments><p:assignment><p:studySubjectIdentifier>001</p:studySubjectIdentifier><p:studySite><p:study><p:identifiers><p:identifier><p:type>Protocol Authority Identifier</p:type><p:value>6482</p:value></p:identifier></p:identifiers></p:study><p:organization><p:name>QU</p:name><p:nciInstituteCode>DCP</p:nciInstituteCode></p:organization></p:studySite></p:assignment></p:assignments></p:participant></ns1:businessMessagePayload></ns1:request></ns1:MessageBroadcasterRequestMessage>";		
	}
}
