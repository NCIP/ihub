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
			
			GenericInvocationStrategy targetInvocationStrategy = new gov.nih.nci.ihub.writer.ncies.common.GenericInvocationStrategy();
			targetInvocationStrategy.setServiceUrl(TestConstants.REGISTER_SUBJECT_CAAERS_URL);
			targetInvocationStrategy.setGridClientClassName("gov.nih.nci.ccts.grid.client.RegistrationConsumerClient");
			targetInvocationStrategy.setRequestPayloadClassName("gov.nih.nci.cabig.ccts.domain.Registration");
			targetInvocationStrategy.setReturnTypeNameSpace("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain");
			targetInvocationStrategy.setReturnTypeElement("registration");
						
			Element ihubBusinessMessagePayloadDocument = IntegrationHubUtil.
			stringToDOMDocument(TestConstants.REGISTER_SUBJECT_PAYLOAD).getDocumentElement();
			targetInvocationStrategy.setStrategySpecificVariables("register", 
									"REGISTER_SUBJECT", ihubBusinessMessagePayloadDocument, 
									subject, "caAERS");
			GridInvocationResult gridInvocationResult = targetInvocationStrategy.invokeGridService(false);
			System.out.println("Grid Invocation Result: "+IntegrationHubUtil.xmlToString(gridInvocationResult.getResult()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
