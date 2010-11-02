package gov.nih.nci.ihub.test;

import static org.junit.Assert.fail;

import javax.security.auth.Subject;

import gov.nih.nci.ihub.writer.ncies.infrastructure.InvokeDelegationServiceBean;

import org.junit.Test;

public class InvokeDelegationServiceTest {

	@Test
	public void testInvokeDelegationService() {
		InvokeDelegationServiceBean delegationServiceBean = new InvokeDelegationServiceBean(
				"/O=caBIG/OU=caGrid/OU=Stage LOA1/OU=Dorian/CN=dev2_ld_user",

				"<ns1:DelegatedCredentialReference xmlns:ns1=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types\">      <ns2:EndpointReference xsi:type=\"ns2:EndpointReferenceType\" xmlns:ns2=\"http://schemas.xmlsoap.org/ws/2004/03/addressing\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">       <ns2:Address xsi:type=\"ns2:AttributedURI\">https://cagrid-cds-stage.nci.nih.gov:8443/wsrf/services/cagrid/DelegatedCredential</ns2:Address>       <ns2:ReferenceProperties xsi:type=\"ns2:ReferencePropertiesType\">        <ns2:DelegatedCredentialKey xmlns:ns2=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential\">         <ns3:delegationId xmlns:ns3=\"http://gaards.cagrid.org/cds\">11624</ns3:delegationId>        </ns2:DelegatedCredentialKey>       </ns2:ReferenceProperties>       <ns2:ReferenceParameters xsi:type=\"ns2:ReferenceParametersType\"/></ns2:EndpointReference></ns1:DelegatedCredentialReference>",

				"C:/projects_svn/caBIGIntegrationHub/common/resources/ihub/ncias-c278-v.nci.nih.gov-cert.pem",

				"C:/projects_svn/caBIGIntegrationHub/common/resources/ihub/ncias-c278-v.nci.nih.gov-key.pem",
				600);
		try {
			Subject subject = delegationServiceBean.invokeDelegationService();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}

}
