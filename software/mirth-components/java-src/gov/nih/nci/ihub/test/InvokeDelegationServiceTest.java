package gov.nih.nci.ihub.test;

import static org.junit.Assert.fail;

import javax.security.auth.Subject;

import gov.nih.nci.ihub.writer.ncies.infrastructure.InvokeDelegationServiceBean;

import org.junit.Test;

public class InvokeDelegationServiceTest {

	@Test
	public void testInvokeDelegationService() {
		InvokeDelegationServiceBean delegationServiceBean = new InvokeDelegationServiceBean(
				TestConstants.HOST_IDENTITY,
				TestConstants.DELEGATION_CREDENTIAL_REFERENCE_XML,
				TestConstants.CERT_FILE_LOCATION,
				TestConstants.KEY_FILE_LOCATION,
				TestConstants.CREDENTIAL_EXPIRATION_SECONDS);
		try {
			Subject subject = delegationServiceBean.invokeDelegationService();
			System.out.println("SUBJECT: "+subject.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
