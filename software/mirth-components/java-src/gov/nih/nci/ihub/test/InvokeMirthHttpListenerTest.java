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
		
		String coppaCredential = "<ns1:DelegatedCredentialReference xmlns:ns1=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types\">" +
		"<ns2:EndpointReference xsi:type=\"ns2:EndpointReferenceType\" xmlns:ns2=\"http://schemas.xmlsoap.org/ws/2004/03/addressing\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
		"<ns2:Address xsi:type=\"ns2:AttributedURI\">https://cagrid-cds-stage.nci.nih.gov:8443/wsrf/services/cagrid/DelegatedCredential</ns2:Address>" +
		"<ns2:ReferenceProperties xsi:type=\"ns2:ReferencePropertiesType\">" +
		"<ns2:DelegatedCredentialKey xmlns:ns2=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential\">" +
		"<ns3:delegationId xmlns:ns3=\"http://gaards.cagrid.org/cds\">12022</ns3:delegationId>" +
		"</ns2:DelegatedCredentialKey>" +
		"</ns2:ReferenceProperties>" +
		"<ns2:ReferenceParameters xsi:type=\"ns2:ReferenceParametersType\"/>" +
		"</ns2:EndpointReference>" +
		"</ns1:DelegatedCredentialReference>";
		method.addParameter("coppa_delegated_credential_ref", coppaCredential);
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