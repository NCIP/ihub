/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.test;

import static org.junit.Assert.fail;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.ihub.writer.ncies.infrastructure.InvokeDelegationServiceBean;

import java.io.ByteArrayInputStream;

import javax.security.auth.Subject;

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
			
			ByteArrayInputStream bStream = new ByteArrayInputStream(subject.toString().getBytes());
			SAMLAssertion samlAssertion = new SAMLAssertion(bStream);
			System.out.println("SAML Assertion: "+samlAssertion.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
