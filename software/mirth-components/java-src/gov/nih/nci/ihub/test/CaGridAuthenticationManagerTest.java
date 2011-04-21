package gov.nih.nci.ihub.test;

import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.FileWriter;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.ihub.writer.ncies.exception.AuthenticationConfigurationException;
import gov.nih.nci.ihub.writer.ncies.exception.AuthenticationErrorException;
import gov.nih.nci.ihub.writer.ncies.infrastructure.CaGridAuthenticationManager;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;

public class CaGridAuthenticationManagerTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CaGridAuthenticationManagerTest.class);

	@Test
	public void testGetSubjectUsingCaGridCredentials() {
		BasicConfigurator.configure();

		CaGridAuthenticationManager caGridAuthenticationManager = new CaGridAuthenticationManager(
				"dev1@nci",
				"D3v1@NC1.gov",
				// "duke_admin",
				// "D3v1@NC1.gov",
//				 "coppagridtest",
//				 "Coppa#12345",
				"https://cagrid-dorian-stage.nci.nih.gov:8443/wsrf/services/cagrid/Dorian",
				"https://cagrid-dorian-stage.nci.nih.gov:8443/wsrf/services/cagrid/Dorian",
				"https://cagrid-cds-stage.nci.nih.gov:8443/wsrf/services/cagrid/CredentialDelegationService",
				12,
				"/O=caBIG/OU=caGrid/OU=Stage LOA1/OU=Services/CN=ncias-c278-v.nci.nih.gov");

		/*
		 * CaGridAuthenticationManager caGridAuthenticationManager = new
		 * CaGridAuthenticationManager( "ccts1amz@ekagra", "Ccts@mz123!",
		 * "https://dorian.training.cagrid.org:8443/wsrf/services/cagrid/Dorian"
		 * ,
		 * "https://dorian.training.cagrid.org:8443/wsrf/services/cagrid/Dorian"
		 * ,
		 * "https://cds.training.cagrid.org:8443/wsrf/services/cagrid/CredentialDelegationService"
		 * , 12,
		 * "/O=caBIG/OU=caGrid/OU=Stage LOA1/OU=Services/CN=ncias-c278-v.nci.nih.gov"
		 * );
		 */

		try {
			System.out.println("Delegated Credential Reference: "
					+ caGridAuthenticationManager
							.getDelegatedCredentialReference());

//			System.out.println("Security Subject: "
//					+ caGridAuthenticationManager.getSubject());

			/*
			 * SAMLAssertion assertion =
			 * caGridAuthenticationManager.authenticate(); try{ // Create file
			 * FileWriter fstream = new FileWriter("C://SAML_Token.xml");
			 * BufferedWriter out = new BufferedWriter(fstream);
			 * out.write(assertion.toString()); //Close the output stream
			 * out.close(); }catch (Exception e){//Catch exception if any
			 * System.err.println("Error: " + e.getMessage()); }
			 * System.out.println("SAML Token "+assertion.toString());
			 */

		} catch (AuthenticationErrorException e) {
			fail(e.getMessage());
		} catch (AuthenticationConfigurationException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
