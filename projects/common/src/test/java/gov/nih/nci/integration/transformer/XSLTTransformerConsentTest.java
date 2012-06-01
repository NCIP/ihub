package gov.nih.nci.integration.transformer;

import gov.nih.nci.integration.exception.IntegrationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test Class for XML Transformation Tests for Consent Flow
 * 
 * @author Rohit Gupta
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
public class XSLTTransformerConsentTest {

	@Autowired
	private XSLTTransformer xsltTransformer;

	@Value("${integration.transformer.xsl.baseClassPath}")
	private String baseXSLPath;

	@Value("${catissue.api.consent.xsl}")
	private String catissueConsentXsl;

	@Test
	public void transformIncomingToWrapperXMLTest() throws IntegrationException {
		xsltTransformer.initTransformer(
				"TranscendInboundMsg-to-caCISRequest.xsl", baseXSLPath);
		String trnsfrmdMsg = transformXML(getConsentIncomingRequestMessage());
		Assert.assertNotNull(trnsfrmdMsg);
//		System.out.println(trnsfrmdMsg);
	}

	@Test
	public void transformWrapperToInterimXMLTest() throws IntegrationException {
		xsltTransformer.initTransformer(
				"caCISRequest-to-MsgBroadcasterConsentInboundMsg.xsl",
				baseXSLPath);
		String trnsfrmdMsg = transformXML(getConsentWrapperMessage());
		Assert.assertNotNull(trnsfrmdMsg);
//		System.out.println(trnsfrmdMsg);
	}

	@Test
	public void transformInterimToXMLStringTest() throws IntegrationException {
		xsltTransformer.initTransformer(catissueConsentXsl, baseXSLPath);
		String trnsfrmdMsg = transformXML(getConsentInterimMessage());
		Assert.assertNotNull(trnsfrmdMsg);
//		System.out.println(trnsfrmdMsg);
	}

	private String getConsentIncomingRequestMessage() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><caXchangeRequestMessage xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:ns1=\"http://caXchange.nci.nih.gov/messaging\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:ns2=\"http://schemas.xmlsoap.org/ws/2004/03/addressing\" xmlns:ns4=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential\" xmlns:ns3=\"http://gaards.cagrid.org/cds\"><metadata><serviceType>iHub</serviceType><externalIdentifier>32225879</externalIdentifier><caXchangeIdentifier>89041</caXchangeIdentifier><operationName>REGISTERCONSENT</operationName></metadata><request><businessMessagePayload><trim xmlns=\"urn:tolven-org:trim:4.0\"><consents xmlns=\"http://cacis.nci.nih.gov\"><participant><cdmsSubjectId>66604232</cdmsSubjectId></participant><consentsDetailsList><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen11</cdmsSpecimenId></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol><consentTierResponses><tier><tierId>1</tierId><response>Yes</response></tier><tier><tierId>2</tierId><response>No</response></tier></consentTierResponses></consentDetails><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen12</cdmsSpecimenId></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol><consentTierResponses><tier><tierId>1</tierId><response>Not Specified</response></tier><tier><tierId>2</tierId><response>Withdrawn</response></tier></consentTierResponses></consentDetails></consentsDetailsList></consents></trim></businessMessagePayload></request></caXchangeRequestMessage>";
	}

	private String getConsentWrapperMessage() {
		return "<caCISRequest xmlns=\"http://cacis.nci.nih.gov\"><sourceData><ns2:caxchangerequest xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\"			xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"			xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\"			xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">			<mes:metadata>				<mes:serviceType>iHub</mes:serviceType>				<mes:operationName>REGISTERCONSENT</mes:operationName>				<mes:externalIdentifier>32225879</mes:externalIdentifier>				<mes:caXchangeIdentifier>null</mes:caXchangeIdentifier>				<mes:credentials>					<mes:userName>tolvenuser</mes:userName>					<mes:groupName>nogrid</mes:groupName>					<mes:gridIdentifier>nogrid</mes:gridIdentifier>					<mes:password>changeme</mes:password>					<mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference>				</mes:credentials>			</mes:metadata>			<mes:request>				<mes:businessMessagePayload>					<mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition>					<trim:trim xmlns:trim=\"urn:tolven-org:trim:4.0\" xmlns=\"urn:tolven-org:trim:4.0\"						xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">						<consents:consents xmlns:consents=\"http://cacis.nci.nih.gov\"						xmlns=\"http://cacis.nci.nih.gov\">							<participant>								<cdmsSubjectId>66604232</cdmsSubjectId>							</participant>							<consentsDetailsList>								<consentDetails>									<collectionProtocolEvent>CPL</collectionProtocolEvent>									<specimen>										<cdmsSpecimenId>TolvenTestUser252TissueSpecimen11										</cdmsSpecimenId>									</specimen>									<collectionProtocol>										<title>6482</title>										<shortTitle>6482</shortTitle>									</collectionProtocol>									<consentTierResponses>										<tier>											<tierId>1</tierId>											<response>Yes</response>										</tier>										<tier>											<tierId>2</tierId>											<response>No</response>										</tier>									</consentTierResponses>								</consentDetails>								<consentDetails>									<collectionProtocolEvent>CPL</collectionProtocolEvent>									<specimen>										<cdmsSpecimenId>TolvenTestUser252TissueSpecimen12										</cdmsSpecimenId>									</specimen>									<collectionProtocol>										<title>6482</title>										<shortTitle>6482</shortTitle>									</collectionProtocol>									<consentTierResponses>										<tier>											<tierId>1</tierId>											<response>Not Specified</response>										</tier>										<tier>											<tierId>2</tierId>											<response>Withdrawn</response>										</tier>									</consentTierResponses>								</consentDetails>							</consentsDetailsList>						</consents:consents>					</trim:trim>				</mes:businessMessagePayload>			</mes:request>		</ns2:caxchangerequest>	</sourceData>	<clinicalMetaData patientIdExtension=\"patient_id_1\"		patientIdRoot=\"2.16.840.1.113883.3\" siteIdExtension=\"site_id\"		siteIdRoot=\"2.16.840.1.113883.2\" studyIdExtension=\"study_id\"		studyIdRoot=\"2.16.840.1.113883.1\" /></caCISRequest>";
	}

	private String getConsentInterimMessage() {
		return "<ns2:caxchangerequest xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\"><ns0:request xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\"><ns0:businessMessagePayload><consents xmlns=\"http://cacis.nci.nih.gov\"><participant><cdmsSubjectId>66604232</cdmsSubjectId></participant><consentsDetailsList><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen101</cdmsSpecimenId></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol><consentTierResponses><tier><tierId>1</tierId><response>Yes</response></tier><tier><tierId>2</tierId><response>No</response></tier></consentTierResponses></consentDetails><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen102</cdmsSpecimenId></specimen><collectionProtocol><title>6483</title><shortTitle>6483</shortTitle></collectionProtocol><consentTierResponses><tier><tierId>1</tierId><response>Not Specified</response></tier><tier><tierId>2</tierId><response>Withdrawn</response></tier></consentTierResponses></consentDetails></consentsDetailsList></consents></ns0:businessMessagePayload></ns0:request></ns2:caxchangerequest>";
	}

	private String transformXML(String message) throws IntegrationException {
		String xmlStr = null;
		InputStream is = null;
		ByteArrayOutputStream os = null;

		try {
			os = new ByteArrayOutputStream();
			is = new ByteArrayInputStream(message.getBytes());

			xsltTransformer.transform(null, is, os);

			xmlStr = new String(os.toByteArray());
		} catch (IntegrationException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
			try {
				os.close();
			} catch (Exception e) {
			}
		}
		return xmlStr;
	}

}
