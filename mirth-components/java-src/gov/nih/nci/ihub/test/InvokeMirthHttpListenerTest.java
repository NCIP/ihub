package gov.nih.nci.ihub.test;

import static org.junit.Assert.fail;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InvokeMirthHttpListenerTest {

	@Test
	public void testInvokeMirthHttpListener() {

		HttpClient client = new HttpClient();
		//client.getParams().setParameter("http.useragent", "Test Client");

		BufferedReader br = null;

		PostMethod method = new PostMethod("http://localhost:8195");
		
		String synchronousMsg = "<ns1:caXchangeRequestMessage xmlns:ns1=\"http://caXchange.nci.nih.gov/messaging\">               <ns1:metadata>                  <ns1:transactionControl xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>                  <ns1:serviceType>PERSON</ns1:serviceType>                  <ns1:operationName>search</ns1:operationName><ns1:caXchangeIdentifier>d1f6f8b0-eb57-11df-b0a6-857449edcfa9</ns1:caXchangeIdentifier><ns1:externalIdentifier>myExternalIdentifier</ns1:externalIdentifier>                  <ns1:credentials>                     <ns1:userName>dev2_sm_reg</ns1:userName>                     <ns1:password>D3v2@nc1.gov</ns1:password>                     <ns1:delegatedCredentialReference>&lt;ns1:DelegatedCredentialReference xmlns:ns1=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types\"&gt; &lt;ns2:EndpointReference xsi:type=\"ns2:EndpointReferenceType\" xmlns:ns2=\"http://schemas.xmlsoap.org/ws/2004/03/addressing\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"&gt;  &lt;ns2:Address xsi:type=\"ns2:AttributedURI\"&gt;https://cagrid-cds-stage.nci.nih.gov:8443/wsrf/services/cagrid/DelegatedCredential&lt;/ns2:Address&gt;  &lt;ns2:ReferenceProperties xsi:type=\"ns2:ReferencePropertiesType\"&gt;   &lt;ns2:DelegatedCredentialKey xmlns:ns2=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential\"&gt;    &lt;ns3:delegationId xmlns:ns3=\"http://gaards.cagrid.org/cds\"&gt;"+TestConstants.DELEGATED_CREDENTIAL_REF_NUMBER+"&lt;/ns3:delegationId&gt;   &lt;/ns2:DelegatedCredentialKey&gt;  &lt;/ns2:ReferenceProperties&gt;  &lt;ns2:ReferenceParameters xsi:type=\"ns2:ReferenceParametersType\"/&gt; &lt;/ns2:EndpointReference&gt;&lt;/ns1:DelegatedCredentialReference&gt;</ns1:delegatedCredentialReference>                  </ns1:credentials>               </ns1:metadata>               <ns1:request>                  <ns1:businessMessagePayload>                     <ns1:xmlSchemaDefinition>http://po.coppa.nci.nih.gov</ns1:xmlSchemaDefinition>                     <ns1:Person xmlns:ns1=\"http://po.coppa.nci.nih.gov\" xmlns:ns2=\"uri:iso.org:21090\">                        <ns1:name>                           <ns2:part type=\"FAM\" value=\"smith\"/>                           <ns2:part type=\"GIV\" value=\"Adriana\"/>                        </ns1:name>                     </ns1:Person>                  </ns1:businessMessagePayload>               </ns1:request>            </ns1:caXchangeRequestMessage>";
		
		method.addParameter("synchronous_msg", synchronousMsg);
		//method.addParameter("synchronous_msg", "<request><metadata><uid>66b20b26-dc51-11df-9972-ddc780619c0d</uid><sourceformat>XML</sourceformat><targetformat>HL7V3</targetformat></metadata><payload><patient><assigning_authority>NCI</assigning_authority><given_name>John</given_name><family_name>Doe</family_name><birth_date>1965-09-16</birth_date><gender>Male</gender></patient></payload></request>");
		
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
		method.addParameter("coppa_user_credential", coppaCredential);
		method.addRequestHeader("mirth.http.user", "tomcatuser");
		method.addRequestHeader("mirth.http.password", "changeme");

		try {
			int returnCode = client.executeMethod(method);

			if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
				System.err
						.println("The Post method is not implemented by this URI");
				// still consume the response body
				method.getResponseBodyAsString();
			} else {
				br = new BufferedReader(new InputStreamReader(method
						.getResponseBodyAsStream()));
				String readLine;
				while (((readLine = br.readLine()) != null)) {
					System.out.println(readLine);
				}
			}
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			method.releaseConnection();
			if (br != null)
				try {
					br.close();
				} catch (Exception fe) {
				}
		}

	}
}