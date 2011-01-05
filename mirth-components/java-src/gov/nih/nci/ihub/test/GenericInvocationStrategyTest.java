package gov.nih.nci.ihub.test;

import static org.junit.Assert.*;
import gov.nih.nci.ihub.util.HubConstants;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.C3DDataCaptureInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.C3DRegistrationInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.dc.DataCaptureTransformer;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.sr.SubjectRegistrationTransformer;
import gov.nih.nci.ihub.writer.ncies.common.GenerateResponseBean;
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
			
			/*
			C3DRegistrationInvocationStrategy registrationC3DStrategy = new C3DRegistrationInvocationStrategy();
			registrationC3DStrategy.setServiceType("REGISTER_SUBJECT");
			registrationC3DStrategy.setServiceUrl(TestConstants.REGISTER_SUBJECT_C3D_URL);
			registrationC3DStrategy.setGridClientClassName("gov.nih.nci.clinicalconnector.client.ClinicalConnectorClient");
			registrationC3DStrategy.setRequestPayloadClassName("clinicalconnector.nci.nih.gov.RegisterSubjectRequest");
			registrationC3DStrategy.setReturnTypeNameSpace("http://gov.nci.nih.clinicalconnector/ClinicalConnector");
			registrationC3DStrategy.setReturnTypeElement("RegisterSubjectResponse");
			registrationC3DStrategy.setOperationName("registerSubject");
			
			Element labBasedAEPayloadDocument = IntegrationHubUtil.
			stringToDOMDocument(TestConstants.REGISTER_SUBJECT_PAYLOAD).getDocumentElement();
			
			registrationC3DStrategy.setSubject(subject);
			registrationC3DStrategy.setServiceProviderName("C3D");
			registrationC3DStrategy.setPayload(labBasedAEPayloadDocument);
			
			GridInvocationResult registerSubjectResponse = registrationC3DStrategy.invokeGridService(false);			
			System.out.println(" C3D Transformed Response: "+IntegrationHubUtil.xmlToString(registerSubjectResponse.getResult()));
			
			GenerateResponseBean generateResponseBean = new GenerateResponseBean();
			Document gridInvocationResultDocument = generateResponseBean.createOutputDocument("C3D", "",
					"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain", registerSubjectResponse);
			
			String successResponseXML = IntegrationHubUtil.xmlToString(gridInvocationResultDocument);			
			System.out.println(" Response: "+successResponseXML);
			*/
			
			/*
			GenericInvocationStrategy ctLabDataStrategy = new GenericInvocationStrategy();
			ctLabDataStrategy.setServiceType("CT_LAB_DATA");
			ctLabDataStrategy.setServiceUrl(TestConstants.CT_LAB_DATA_CTOM_UTL);
			ctLabDataStrategy.setGridClientClassName("gov.nih.nci.cagrid.labviewer.client.LabLoaderClient");
			ctLabDataStrategy.setRequestPayloadClassName("java.lang.String");
			ctLabDataStrategy.setReturnTypeNameSpace("http://labviewer.cagrid.nci.nih.gov/LabLoader/types");
			ctLabDataStrategy.setReturnTypeElement("LoadLabResponse");
			ctLabDataStrategy.setOperationName("loadLab");
			
			Element labBasedAEPayloadDocument = IntegrationHubUtil.
			stringToDOMDocument(TestConstants.CT_LAB_DATA_PAYLOAD).getDocumentElement();
			
			ctLabDataStrategy.setSubject(subject);
			ctLabDataStrategy.setServiceProviderName("CTOM");
			ctLabDataStrategy.setPayload(labBasedAEPayloadDocument);
			
			GridInvocationResult studyCreationResponse = ctLabDataStrategy.invokeGridService(false);
			System.out.println(" Response: "+IntegrationHubUtil.xmlToString(studyCreationResponse.getResult()));
			*/
			
			/*
			C3DDataCaptureInvocationStrategy loadLabToCDMSStrategy = new C3DDataCaptureInvocationStrategy();
			loadLabToCDMSStrategy.setServiceType("LOAD_LAB_TO_CDMS");
			loadLabToCDMSStrategy.setServiceUrl(TestConstants.LOAD_LAB_TO_CDMS_C3D_UTL);
			loadLabToCDMSStrategy.setGridClientClassName("gov.nih.nci.clinicalconnector.client.ClinicalConnectorClient");
			loadLabToCDMSStrategy.setRequestPayloadClassName("clinicalconnector.nci.nih.gov.LoadLabsRequest");
			loadLabToCDMSStrategy.setReturnTypeNameSpace("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain.loadlabs");
			loadLabToCDMSStrategy.setReturnTypeElement("LoadLabsResponse");
			loadLabToCDMSStrategy.setOperationName("loadLabs");
			
//			DataCaptureTransformer dataCaptureTransformer = new DataCaptureTransformer();
//			String loadLabConvertedString = dataCaptureTransformer.convert2RequestString(TestConstants.LOAD_LAB_TO_CDMS_PAYLOAD);			
//			System.out.println("LoadLab Converted String: "+loadLabConvertedString);
//			String loadLabBusinessString = IntegrationHubUtil.convertPayloadIntoBusinessPayload(loadLabConvertedString);
			
			Element loadLabToCDMSPayloadDocument = IntegrationHubUtil.
			stringToDOMDocument(TestConstants.LOAD_LAB_TO_CDMS_PAYLOAD).getDocumentElement();
			
			loadLabToCDMSStrategy.setSubject(subject);
			loadLabToCDMSStrategy.setServiceProviderName("C3D");
			loadLabToCDMSStrategy.setPayload(loadLabToCDMSPayloadDocument);
			
			GridInvocationResult studyCreationResponse = loadLabToCDMSStrategy.invokeGridService(false);
			System.out.println(" Response: "+IntegrationHubUtil.xmlToString(studyCreationResponse.getResult()));
			*/
			
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
			
			GenerateResponseBean generateResponseBean = new GenerateResponseBean();
			
			Document gridInvocationResultDocument = generateResponseBean.createOutputDocument("C3D", "",
					"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain", subjectRegistrationResponse);
			
			System.out.println("Response Document Type: "+subjectRegistrationResponse.getResult().getNodeType());
			System.out.println("Subject Registration Response: "+IntegrationHubUtil.xmlToString(subjectRegistrationResponse.getResult()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
