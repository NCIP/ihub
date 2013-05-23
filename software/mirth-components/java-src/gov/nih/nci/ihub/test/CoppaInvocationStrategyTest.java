/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.test;

import static org.junit.Assert.*;

import javax.security.auth.Subject;

import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.core.CoppaInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;
import gov.nih.nci.ihub.writer.ncies.infrastructure.InvokeDelegationServiceBean;

import org.junit.Test;

public class CoppaInvocationStrategyTest {

	@Test
	public void testInvokeGridServiceStringStringElementSubject() {

		InvokeDelegationServiceBean delegationServiceBean = new InvokeDelegationServiceBean(
				TestConstants.HOST_IDENTITY,
				TestConstants.DELEGATION_CREDENTIAL_REFERENCE_XML,
				TestConstants.CERT_FILE_LOCATION,
				TestConstants.KEY_FILE_LOCATION,
				TestConstants.CREDENTIAL_EXPIRATION_SECONDS);
		try {
			Subject subject = delegationServiceBean.invokeDelegationService();
			System.out.println("SUBJECT: " + subject.toString());
			CoppaInvocationStrategy coppaInvocationStrategy = new CoppaInvocationStrategy();
			coppaInvocationStrategy
					.setServiceUrl(TestConstants.IDENTIFIED_PERSON_URL);
			coppaInvocationStrategy
					.setGridClientClassName(TestConstants.IDENTIFIED_PERSON_CLIENT_CLASS_NAME);
			coppaInvocationStrategy.init();
			coppaInvocationStrategy
					.setStrategySpecificVariables("getByPlayerIds",
							"IDENTIFIED_PERSON",
							IntegrationHubUtil.stringToDOMDocument(
									TestConstants.IDENTIFIED_PERSON_PAYLOAD)
									.getDocumentElement(), subject,
							"IDENTIFIED_PERSON");
			GridInvocationResult coppaResponse = coppaInvocationStrategy
					.invokeGridService(false);

			System.out
					.println("COPPA Response: "
							+ IntegrationHubUtil.xmlToString(coppaResponse
									.getResult()));

			// fail("Not yet implemented");
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
