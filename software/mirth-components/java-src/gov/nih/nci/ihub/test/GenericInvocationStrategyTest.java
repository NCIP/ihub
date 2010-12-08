package gov.nih.nci.ihub.test;

import static org.junit.Assert.*;
import gov.nih.nci.ihub.util.HubConstants;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.dc.DataCaptureTransformer;
import gov.nih.nci.ihub.writer.ncies.common.GenericInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.infrastructure.InvokeDelegationServiceBean;

import javax.security.auth.Subject;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

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
			
			GenericInvocationStrategy loadLabToCDMSStrategy = new GenericInvocationStrategy();
			loadLabToCDMSStrategy.setServiceType("LOAD_LAB_TO_CDMS");
			loadLabToCDMSStrategy.setServiceUrl(TestConstants.LOAD_LAB_TO_CDMS_C3D_UTL);
			loadLabToCDMSStrategy.setGridClientClassName("gov.nih.nci.clinicalconnector.client.ClinicalConnectorClient");
			loadLabToCDMSStrategy.setRequestPayloadClassName("clinicalconnector.nci.nih.gov.LoadLabsRequest");
			loadLabToCDMSStrategy.setReturnTypeNameSpace("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain.loadlabs");
			loadLabToCDMSStrategy.setReturnTypeElement("LoadLabsResponse");
			loadLabToCDMSStrategy.setOperationName("loadLabs");
			
			DataCaptureTransformer dataCaptureTransformer = new DataCaptureTransformer();
			String loadLabConvertedString = dataCaptureTransformer.convert2RequestString(TestConstants.LOAD_LAB_TO_CDMS_PAYLOAD);			
			System.out.println("LoadLab Converted String: "+loadLabConvertedString);
			String loadLabBusinessString = IntegrationHubUtil.convertPayloadIntoBusinessPayload(loadLabConvertedString);
			
			Element labBasedAEPayloadDocument = IntegrationHubUtil.
			stringToDOMDocument(loadLabBusinessString).getDocumentElement();
			
			loadLabToCDMSStrategy.setSubject(subject);
			loadLabToCDMSStrategy.setServiceProviderName("C3D");
			loadLabToCDMSStrategy.setPayload(labBasedAEPayloadDocument);
			
			GridInvocationResult studyCreationResponse = loadLabToCDMSStrategy.invokeGridService(false);
			System.out.println(" Response: "+IntegrationHubUtil.xmlToString(studyCreationResponse.getResult()));
			
			/*
			GenericInvocationStrategy labBasedAEStrategy = new GenericInvocationStrategy();
			labBasedAEStrategy.setServiceType("LAB_BASED_AE");
			labBasedAEStrategy.setServiceUrl(TestConstants.LAB_BASED_AE_CAAERS_URL);
			labBasedAEStrategy.setGridClientClassName("gov.nih.nci.ccts.grid.client.LabConsumerServiceClient");
			labBasedAEStrategy.setRequestPayloadClassName("gov.nih.nci.cabig.ccts.domain.loadlabs.LoadLabsRequest");
			labBasedAEStrategy.setReturnTypeNameSpace("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain.loadlabs");
			labBasedAEStrategy.setReturnTypeElement("Acknowledgement");
			labBasedAEStrategy.setOperationName("loadLabs");
			
			Element labBasedAEPayloadDocument = IntegrationHubUtil.
			stringToDOMDocument(TestConstants.LAB_BASED_AE_PAYYLOAD).getDocumentElement();
			
			labBasedAEStrategy.setSubject(subject);
			labBasedAEStrategy.setServiceProviderName("caAERS");
			labBasedAEStrategy.setPayload(labBasedAEPayloadDocument);
			
			GridInvocationResult studyCreationResponse = labBasedAEStrategy.invokeGridService(false);
			System.out.println(" Response: "+IntegrationHubUtil.xmlToString(studyCreationResponse.getResult()));
			*/
			
			/*
			GenericInvocationStrategy adverseEventStrategy = new GenericInvocationStrategy();
			adverseEventStrategy.setServiceType("SCHEDULE_MODIFICATION");
			adverseEventStrategy.setServiceUrl(TestConstants.SCHEDULE_MODIFICATION_PSC_URL);
			adverseEventStrategy.setGridClientClassName("gov.nih.nci.cabig.ctms.grid.ae.client.AdverseEventConsumerClient");
			adverseEventStrategy.setRequestPayloadClassName("gov.nih.nci.cabig.ccts.ae.domain.AENotificationType");
			adverseEventStrategy.setReturnTypeNameSpace("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.ae.domain");
			adverseEventStrategy.setReturnTypeElement("");
			adverseEventStrategy.setOperationName("register");
			
			Element scheduleModificationPayloadDocument = IntegrationHubUtil.
			stringToDOMDocument(TestConstants.SCHEDULE_MODIFICATION_PAYLOAD).getDocumentElement();
			
			adverseEventStrategy.setSubject(subject);
			adverseEventStrategy.setServiceProviderName("PSC");
			adverseEventStrategy.setPayload(scheduleModificationPayloadDocument);
			
			GridInvocationResult studyCreationResponse = adverseEventStrategy.invokeGridService(false);
			System.out.println(" Response: "+IntegrationHubUtil.xmlToString(studyCreationResponse.getResult()));
			*/
						
			/*
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
			*/
			
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
