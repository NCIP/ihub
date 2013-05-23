/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.test;

import static org.junit.Assert.*;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.common.GridMessageImpl;

import org.junit.Test;
import org.w3c.dom.Document;

public class GridMessageImplTest {

	String ihubMessage = "<ns1:caXchangeRequestMessage xmlns:ns1=\"http://caXchange.nci.nih.gov/messaging\">               <ns1:metadata>                  <ns1:transactionControl xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>                  <ns1:serviceType>PERSON</ns1:serviceType>                  <ns1:operationName>search</ns1:operationName><ns1:caXchangeIdentifier>d1f6f8b0-eb57-11df-b0a6-857449edcfa9</ns1:caXchangeIdentifier><ns1:externalIdentifier>myExternalIdentifier</ns1:externalIdentifier>                  <ns1:credentials>                     <ns1:userName>dev2_sm_reg</ns1:userName>                     <ns1:password>D3v2@nc1.gov</ns1:password>                     <ns1:delegatedCredentialReference>&lt;ns1:DelegatedCredentialReference xmlns:ns1=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types\"&gt; &lt;ns2:EndpointReference xsi:type=\"ns2:EndpointReferenceType\" xmlns:ns2=\"http://schemas.xmlsoap.org/ws/2004/03/addressing\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"&gt;  &lt;ns2:Address xsi:type=\"ns2:AttributedURI\"&gt;https://cagrid-cds-stage.nci.nih.gov:8443/wsrf/services/cagrid/DelegatedCredential&lt;/ns2:Address&gt;  &lt;ns2:ReferenceProperties xsi:type=\"ns2:ReferencePropertiesType\"&gt;   &lt;ns2:DelegatedCredentialKey xmlns:ns2=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential\"&gt;    &lt;ns3:delegationId xmlns:ns3=\"http://gaards.cagrid.org/cds\"&gt;"+TestConstants.DELEGATED_CREDENTIAL_REF_NUMBER+"&lt;/ns3:delegationId&gt;   &lt;/ns2:DelegatedCredentialKey&gt;  &lt;/ns2:ReferenceProperties&gt;  &lt;ns2:ReferenceParameters xsi:type=\"ns2:ReferenceParametersType\"/&gt; &lt;/ns2:EndpointReference&gt;&lt;/ns1:DelegatedCredentialReference&gt;</ns1:delegatedCredentialReference>                  </ns1:credentials>               </ns1:metadata>               <ns1:request>                  <ns1:businessMessagePayload>                     <ns1:xmlSchemaDefinition>http://po.coppa.nci.nih.gov</ns1:xmlSchemaDefinition>                     <ns1:Person xmlns:ns1=\"http://po.coppa.nci.nih.gov\" xmlns:ns2=\"uri:iso.org:21090\">                        <ns1:name>                           <ns2:part type=\"FAM\" value=\"smith\"/>                           <ns2:part type=\"GIV\" value=\"Adriana\"/>                        </ns1:name>                     </ns1:Person>                  </ns1:businessMessagePayload>               </ns1:request>            </ns1:caXchangeRequestMessage>";
	
	String ihubPayload = "<ns1:businessMessagePayload xmlns:ns1=\"http://caXchange.nci.nih.gov/messaging\">                     <ns1:xmlSchemaDefinition>http://po.coppa.nci.nih.gov</ns1:xmlSchemaDefinition>                     <ns1:Person xmlns:ns1=\"http://po.coppa.nci.nih.gov\" xmlns:ns2=\"uri:iso.org:21090\">                        <ns1:name>                           <ns2:part type=\"FAM\" value=\"smith\"/>                           <ns2:part type=\"GIV\" value=\"Adriana\"/>                        </ns1:name>                     </ns1:Person>                  </ns1:businessMessagePayload>";
	@Test
	public void testGetMetaData() {
		Document ihubMessageDocument = IntegrationHubUtil.stringToDOMDocument(ihubMessage);
		GridMessageImpl gridMessageImpl = new GridMessageImpl(ihubMessageDocument);		
		System.out.println("Metadata: "+gridMessageImpl.getMetaData());
		System.out.println("Payloads size: "+gridMessageImpl.getPayloads(IntegrationHubUtil.stringToDOMDocument(ihubPayload).getDocumentElement()).size());
		fail("Not yet implemented");
	}

	@Test
	public void testGetPayload() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPayloads() {
		fail("Not yet implemented");
	}

}
