package gov.nih.nci.integration.transformer;

import gov.nih.nci.integration.exception.IntegrationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
public class XSLTTransformerTest {

    @Autowired
    private XSLTTransformer xsltTransformer;

    @Value("${integration.transformer.xsl.baseClassPath}")
    private String baseXSLPath;

    @Value("${catissue.api.participant.xsl}")
    private String catissueParticipantXsl;

    @Test
    @Before
    public void intialize() throws IntegrationException {
        xsltTransformer.initTransformer(catissueParticipantXsl, baseXSLPath);
        Assert.assertNotNull(xsltTransformer);
    }

    @Test
    public void transformMsgBCMsg() throws IntegrationException {
        xsltTransformer.initTransformer(catissueParticipantXsl, baseXSLPath);

        String trnsfrmdMsg = transformToParticipantXML(getMsgBCMsg());
        Assert.assertNotNull(trnsfrmdMsg);
        // System.out.println(trnsfrmdMsg);
    }

    private String transformToParticipantXML(String message) throws IntegrationException {
        String participantXMLStr = null;
        InputStream is = null;
        ByteArrayOutputStream os = null;

        try {
            os = new ByteArrayOutputStream();
            is = new ByteArrayInputStream(message.getBytes());

            xsltTransformer.transform(null, is, os);

            participantXMLStr = new String(os.toByteArray());
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
        return participantXMLStr;
    }

    private String getMsgBCMsg() {
        return "<xr:caxchangerequest xmlns:xr=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:xs=\"http://www.w3.org/TR/2008/REC-xml-20081126#\"><mes:metadata><mes:serviceType xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">iHub</mes:serviceType><mes:operationName xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">REGISTRATION</mes:operationName><mes:externalIdentifier xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">322050611</mes:externalIdentifier><mes:caXchangeIdentifier xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">1048579</mes:caXchangeIdentifier><mes:credentials xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><xmlSchemaDefinition xmlns=\"http://cacis.nci.nih.gov\">http://integration.nci.nih.gov/participant</xmlSchemaDefinition><p:participant xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" id=\"1\" version=\"1\" xsi:schemaLocation=\"http://integration.nci.nih.gov/participant Participant.xsd \"><p:firstName>Cherry050611</p:firstName><p:lastName>Blossom050611</p:lastName><p:maidenName/><p:middleName/><p:birthDate>1965-11-24</p:birthDate><p:gender>Male</p:gender><p:race>White</p:race><p:ethnicity>Not Hispanic or Latino</p:ethnicity><p:activityStatus>Active</p:activityStatus><p:registrationDate>2012-03-07</p:registrationDate><p:identifiers><p:organizationAssignedIdentifier id=\"1\" version=\"1\"><p:type>MRN</p:type><p:value>666050611</p:value><p:primaryIndicator>true</p:primaryIndicator><p:organization id=\"1\" version=\"1\"><p:name>QU</p:name><p:nciInstituteCode>DCP</p:nciInstituteCode></p:organization></p:organizationAssignedIdentifier><p:organizationAssignedIdentifier id=\"2\" version=\"1\"><p:type>SSN</p:type><p:value>123-05-0611</p:value><p:primaryIndicator>false</p:primaryIndicator><p:organization id=\"1\" version=\"1\"><p:name>SSN</p:name><p:nciInstituteCode>SSN</p:nciInstituteCode></p:organization></p:organizationAssignedIdentifier><p:systemAssignedIdentifier id=\"1\" version=\"1\"><p:type>MRN</p:type><p:value>666050611</p:value><p:primaryIndicator>true</p:primaryIndicator><p:systemName>MRN</p:systemName></p:systemAssignedIdentifier></p:identifiers><p:assignments><p:assignment id=\"1\" version=\"1\"><p:studySubjectIdentifier>488050611</p:studySubjectIdentifier><p:studySite id=\"1\" version=\"1\"><p:study id=\"1\" version=\"1\"><p:identifiers><p:identifier id=\"1\" version=\"1\"><p:type>Protocol Authority Identifier</p:type><p:value>6482</p:value></p:identifier></p:identifiers></p:study><p:organization id=\"1\" version=\"1\"><p:name>QU</p:name><p:nciInstituteCode>DCP</p:nciInstituteCode></p:organization></p:studySite></p:assignment></p:assignments></p:participant></mes:businessMessagePayload></mes:request></xr:caxchangerequest>";
    }
}
