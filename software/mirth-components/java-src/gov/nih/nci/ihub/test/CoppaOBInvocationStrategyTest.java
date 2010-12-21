package gov.nih.nci.ihub.test;

import static org.junit.Assert.fail;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.common.GenerateResponseBean;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.core.CoppaInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.core.CoppaOBInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.core.CoppaPBInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;
import gov.nih.nci.ihub.writer.ncies.infrastructure.InvokeDelegationServiceBean;

import javax.security.auth.Subject;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CoppaOBInvocationStrategyTest {

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
			System.out.println("SUBJECT: " + subject.toString());

			CoppaOBInvocationStrategy coppaOBInvocationStrategy = new CoppaOBInvocationStrategy();
			coppaOBInvocationStrategy.setServiceUrl(TestConstants.IDENTIFIED_ORGANIZATION_URL);
			coppaOBInvocationStrategy
			.setGridClientClassName("gov.nih.nci.coppa.services.structuralroles.identifiedorganization.client.IdentifiedOrganizationClient");
			
			CoppaInvocationStrategy identifiedOrganizationInvocationStrategy = new CoppaInvocationStrategy();
			identifiedOrganizationInvocationStrategy.setServiceUrl(TestConstants.IDENTIFIED_ORGANIZATION_URL);
			identifiedOrganizationInvocationStrategy
			.setGridClientClassName("gov.nih.nci.coppa.services.structuralroles.identifiedorganization.client.IdentifiedOrganizationClient");
			
			CoppaInvocationStrategy organizationInvocationStrategy = new CoppaInvocationStrategy();
			organizationInvocationStrategy.setServiceUrl(TestConstants.ORGANIZATION_URL);
			organizationInvocationStrategy.setGridClientClassName("gov.nih.nci.coppa.services.entities.organization.client.OrganizationClient");
			
			coppaOBInvocationStrategy.setIdentifiedOrganizationInvocationStrategy(identifiedOrganizationInvocationStrategy);
			coppaOBInvocationStrategy.setOrganizationInvocationStrategy(organizationInvocationStrategy);
			
			CoppaOBInvocationStrategy channelCoppaOBInvocationStrategy = new CoppaOBInvocationStrategy();
			channelCoppaOBInvocationStrategy.copyCommonContents(coppaOBInvocationStrategy);
			channelCoppaOBInvocationStrategy.init();			
			
			channelCoppaOBInvocationStrategy.setStrategySpecificVariables("something", "ORGANIZATION_BUSINESS_SERVICE", IntegrationHubUtil.stringToDOMDocument(TestConstants.ORGANIZATION_BUSINESS_SERVICE_PAYLOAD).getDocumentElement(), subject, "ORGANIZATION_BUSINESS_SERVICE");
			
			GridInvocationResult organizationBusinessServiceInvocationResult = channelCoppaOBInvocationStrategy.invokeGridService(false);
			
			System.out.println("Organization Business Service Response: "
					+ IntegrationHubUtil
							.xmlToString(organizationBusinessServiceInvocationResult
									.getResult()));
			
			GenerateResponseBean generateResponseBean = new GenerateResponseBean();
			Document gridInvocationResultDocument = generateResponseBean.createOutputDocument("ORGANIZATION_BUSINESS_SERVICE", "something",
					"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain", organizationBusinessServiceInvocationResult);
			
			String successResponseXML = IntegrationHubUtil.xmlToString(gridInvocationResultDocument);			
			System.out.println(" Response: "+successResponseXML);
			
		} catch (GridInvocationException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
