package gov.nih.nci.ihub.test;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.ihub.writer.ncies.exception.AuthenticationConfigurationException;
import gov.nih.nci.ihub.writer.ncies.exception.AuthenticationErrorException;
import gov.nih.nci.ihub.writer.ncies.infrastructure.GetSubjectUsingCaGridCredentialsBean;

import org.globus.gsi.GlobusCredential;
import org.globus.gsi.GlobusCredentialException;
import org.junit.Test;

public class GetSubjectUsingCaGridCredentialsTest {

	@Test
	public void testGetSubjectUsingCaGridCredentials() {		 
		GetSubjectUsingCaGridCredentialsBean subjectUsingCaGridCredentialsBean = new GetSubjectUsingCaGridCredentialsBean(
				"coppagridtest",
				"Coppa#12345",
				"https://cagrid-dorian-stage.nci.nih.gov:8443/wsrf/services/cagrid/Dorian",
				"https://cagrid-dorian-stage.nci.nih.gov:8443/wsrf/services/cagrid/Dorian",
				12);
		try {
			SAMLAssertion saAssertion = subjectUsingCaGridCredentialsBean
					.authenticate(
							"https://cagrid-dorian-stage.nci.nih.gov:8443/wsrf/services/cagrid/Dorian",
							"coppagridtest", "Coppa#12345");

			System.out.println("SAML Assertion successfully obtained");
			// Obtained the GlobusCredential for the Authenticated User
			GlobusCredential userCredential = subjectUsingCaGridCredentialsBean
					.obtainProxy(saAssertion);			
			System.out.println("Identity: " + userCredential.getIdentity());
			
			
			
			/*ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			userCredential.save(arrayOutputStream);
			System.out.println("Byte Array Contents: "
					+ arrayOutputStream.toString());			
			String coppaCredential = "-----BEGIN CERTIFICATE-----\n"+
			"MIIDPTCCAiWgAwIBAgIGASwsDWO2MA0GCSqGSIb3DQEBBQUAMGgxDjAMBgNVBAoT\n"+
			"BWNhQklHMQ8wDQYDVQQLEwZjYUdyaWQxEzARBgNVBAsTClN0YWdlIExPQTExMDAu\n"+
			"BgNVBAMTJ2NhR3JpZCBTdGFnZSBMT0ExIENlcnRpZmljYXRlIEF1dGhvcml0eTAe\n"+
			"Fw0xMDExMDgxNTE0MTNaFw0xMDExMDkwMzE0MjNaMF8xDjAMBgNVBAoTBWNhQklH\n"+
			"MQ8wDQYDVQQLEwZjYUdyaWQxEzARBgNVBAsTClN0YWdlIExPQTExDzANBgNVBAsT\n"+
			"BkRvcmlhbjEWMBQGA1UEAxMNY29wcGFncmlkdGVzdDCBnzANBgkqhkiG9w0BAQEF\n"+
			"AAOBjQAwgYkCgYEAu8+faSk5w8Uu5pxusKDXw25rTIq2F7YPZTvGQY5f5kvlbDdP\n"+
			"GZGHwnMF9nTY+F7wx2mG4poNUKiN0q1AYckI8GO2e+2SjISVKqHi80vblkYe/en0\n"+
			"fcYVYNWeoKaGQEx0Ra/32q7KjaXXTdC1ECAihxBJ/NH4EZ6e4zaxf5UmFnMCAwEA\n"+
			"AaN6MHgwDAYDVR0TAQH/BAIwADAOBgNVHQ8BAf8EBAMCBPAwHQYDVR0OBBYEFGlc\n"+
			"pFuW2kbVrU3CBXsVeZiwvZgLMB8GA1UdIwQYMBaAFKNUEKgFN6of0E6hNwbGH9gZ\n"+
			"XseyMBgGA1UdIAQRMA8wDQYLYIZIAYb5WwMaAwIwDQYJKoZIhvcNAQEFBQADggEB\n"+
			"ADhHQCeabWFEbGRxteE/Ukv6+DIuolsQhDs5PPbl5iHiEEYHw/yHvZc5x46r9v2W\n"+
			"4sUT5GppJYBFmbwGfTbbx2+Qqrq27S4IEWrQNu7FJXdGSghH4AkgrMibCvFyxf0n\n"+
			"3OAa/JML/VKbeq28ZKddV72CwcNFQQxC5iIj9zAcLUVkTCRxvsP6+v/6P2XKaU9A\n"+
			"+SXb2yCXn2rP3KZ6JMiGE6D5aIk6JVLYitnVz9LCbG8ly0hj+5zc4Sp7dIf3Nz3n\n"+
			"wimDzVOMp6eEEakRIDXqMX5vPZB0X/32PXn2Rzn6jJ/dPExEzr++BR/EY/egwRyK\n"+
			"hZAcNdgTus00/4/hSxp3GE4=\n"+
			"-----END CERTIFICATE-----\n"+
			"-----BEGIN RSA PRIVATE KEY-----\n"+
			"MIICXgIBAAKBgQC7z59pKTnDxS7mnG6woNfDbmtMirYXtg9lO8ZBjl/mS+VsN08Z\n"+
			"kYfCcwX2dNj4XvDHaYbimg1QqI3SrUBhyQjwY7Z77ZKMhJUqoeLzS9uWRh796fR9\n"+
			"xhVg1Z6gpoZATHRFr/farsqNpddN0LUQICKHEEn80fgRnp7jNrF/lSYWcwIDAQAB\n"+
			"AoGBAJ82xccaodOq57WsS9IEqKUOiHvc4716I9cIM2wDzJypHpb81FuymcpoRFfI\n"+
			"et8jbS8/8I9NVZhZK9G7+eZrzNy5hSnngx0atO0BVoTHfySCSnfTEWZxGLFq7alF\n"+
			"UVnS8cmyqXQq90pX25ueJOPjS7YVW4JS10WfYEfL3BKV1u7xAkEA2+WpQ32/4jEG\n"+
			"eYBfkPzWg0iokpXFf0R5uSQTe2LzubFeXgxHHyziEhdBhAUp8J3mXE/vUsAcDtCA\n"+
			"YSFUn/glHwJBANqlX8CEO0JYmsn0fwp3LycPwVTuctD/pq/QCyjb2GjLmaPCNCsf\n"+
			"UYICqSjKPxQRESpMM7ZgM5t7mNPnqvZ2cC0CQQCj/odZmjK8kitt+dtL2gRxLILr\n"+
			"r173Jy5QcSNGZem2lxz1rtpr2aFQNJ/bwa4hkOD3/3VQlY1XTxAvYBgopXupAkEA\n"+
			"nByhzmpbiVaMRpAcuRM6BpDYQFT0g8dVc4h+v1ChJsngTC1YZEW7Q7G0qBwnh6Nb\n"+
			"9zBrZ9cuYeCQ0RRDXDBajQJAE4loW2Ee+NeoCnwWAlCJ4aLpK+Qzw+GIVhJK35UY\n"+
			"0sLhJ7Yg+ZlrnRon7XU4xeIMh/OHmcKgn6klrxea60UYKA==\n"+
			"-----END RSA PRIVATE KEY-----";
			GlobusCredential reconstructedUserCredential = new GlobusCredential(
					new ByteArrayInputStream(arrayOutputStream.toString()
							.getBytes()));
			GlobusCredential reconstructedUserCredential = new GlobusCredential(
					new ByteArrayInputStream(coppaCredential
							.getBytes()));
			System.out.println("Re-Constructed Identity: "
					+ reconstructedUserCredential.getIdentity());
			
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (GlobusCredentialException e) {
			fail(e.getMessage());
*/			
		} catch (AuthenticationErrorException e) {
			fail(e.getMessage());
		} catch (AuthenticationConfigurationException e) {
			fail(e.getMessage());
		}
	}

}
