package gov.nih.nci.ihub.test;

import static org.junit.Assert.*;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.common.GenericInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.infrastructure.InvokeDelegationServiceBean;

import javax.security.auth.Subject;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GenericInvocationStrategyTest {

	@Test
	public void testInvokeGridService() {
		InvokeDelegationServiceBean delegationServiceBean = new InvokeDelegationServiceBean(
				TestConstants.HOST_IDENTITY,
				TestConstants.DELEGATION_CREDENTIAL_REFERENCE_XML,
				TestConstants.CERT_FILE_LOCATION,
				TestConstants.KEY_FILE_LOCATION,
				TestConstants.CREDENTIAL_EXPIRATION_SECONDS);
		try {
			Subject subject = delegationServiceBean.invokeDelegationService();
			System.out.println("SUBJECT: "+subject.toString());
			
			
			GenericInvocationStrategy studyCreationStrategy = new gov.nih.nci.ihub.writer.ncies.common.GenericInvocationStrategy();
			studyCreationStrategy.setServiceType("STUDY_CREATION");
			studyCreationStrategy.setServiceUrl(TestConstants.STUDY_CREATION_CAAERS_URL);
			studyCreationStrategy.setGridClientClassName("gov.nih.nci.ccts.grid.studyconsumer.client.StudyConsumerClient");
			studyCreationStrategy.setRequestPayloadClassName("gov.nih.nci.cabig.ccts.domain.Study");
			studyCreationStrategy.setReturnTypeNameSpace("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain");
			studyCreationStrategy.setReturnTypeElement("study");	
			studyCreationStrategy.setOperationName("createStudy");
						
			Element studyCreationPayloadDocument = IntegrationHubUtil.
			stringToDOMDocument(TestConstants.STUDY_CREATION_PAYLOAD).getDocumentElement();
			
			studyCreationStrategy.setSubject(subject);
			studyCreationStrategy.setServiceProviderName("caAERS");
			studyCreationStrategy.setPayload(studyCreationPayloadDocument);
			
			GridInvocationResult studyCreationResponse = studyCreationStrategy.invokeGridService(false);
			System.out.println("Study Creation Response: "+IntegrationHubUtil.xmlToString(studyCreationResponse.getResult()));
			
			/*
			GenericInvocationStrategy registrationInvocationStrategy = new gov.nih.nci.ihub.writer.ncies.common.GenericInvocationStrategy();
			registrationInvocationStrategy.setServiceType("REGISTER_SUBJECT");
			registrationInvocationStrategy.setServiceUrl(TestConstants.REGISTER_SUBJECT_CAAERS_URL);
			registrationInvocationStrategy.setGridClientClassName("gov.nih.nci.ccts.grid.client.RegistrationConsumerClient");
			registrationInvocationStrategy.setRequestPayloadClassName("gov.nih.nci.cabig.ccts.domain.Registration");
			registrationInvocationStrategy.setReturnTypeNameSpace("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain");
			registrationInvocationStrategy.setReturnTypeElement("registration");	
			registrationInvocationStrategy.setOperationName("register");
						
			Element subjectRegistrationPayloadDocument = IntegrationHubUtil.
			stringToDOMDocument(TestConstants.REGISTER_SUBJECT_PAYLOAD).getDocumentElement();
			
			registrationInvocationStrategy.setSubject(subject);
			registrationInvocationStrategy.setServiceProviderName("caAERS");
			registrationInvocationStrategy.setPayload(subjectRegistrationPayloadDocument);
			
			GridInvocationResult subjectRegistrationResponse = registrationInvocationStrategy.invokeGridService(false);
			System.out.println("Subject Registration Response: "+IntegrationHubUtil.xmlToString(subjectRegistrationResponse.getResult()));
			*/
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
