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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
public class XSLTTransformerSpecimenTest {

    @Autowired
    private XSLTTransformer xsltTransformer;

    @Value("${integration.transformer.xsl.baseClassPath}")
    private String baseXSLPath;

    @Value("${catissue.api.specimen.xsl}")
    private String catissueSpecimenXsl;

    @Test
    public void transformSpecimenInterimToXMLStringTest() throws IntegrationException {

        xsltTransformer.initTransformer(catissueSpecimenXsl, baseXSLPath);

        String trnsfrmdMsg = transformXML(getInterimSpecimenXML());

        Assert.assertNotNull(trnsfrmdMsg);

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

    private String getInterimSpecimenXML() {
        return "<ns2:caxchangerequest xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\"><ns0:metadata xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\"><mes:serviceType xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">iHub</mes:serviceType><mes:operationName xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">SPECIMEN</mes:operationName><mes:externalIdentifier xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">32225879</mes:externalIdentifier><mes:caXchangeIdentifier xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">null</mes:caXchangeIdentifier><mes:credentials xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></ns0:metadata><ns0:request xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\"><ns0:businessMessagePayload><specimens xmlns=\"http://cacis.nci.nih.gov\"><participant><activityStatus>Active</activityStatus><cdmsSubjectId>66604232</cdmsSubjectId></participant><specimenDetailsList><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen121</cdmsSpecimenId><barcode>TolvenTestUser252TissueSpecimen121</barcode><activityStatus>Active</activityStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><pathologicalStatus>Malignant</pathologicalStatus><initialQuantity>1</initialQuantity><availableQuantity>1</availableQuantity><specimenCharacteristics><tissueSite>Placenta</tissueSite><tissueSide>Right</tissueSide></specimenCharacteristics></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen122</cdmsSpecimenId><barcode>TolvenTestUser252TissueSpecimen122</barcode><activityStatus>Active</activityStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><pathologicalStatus>Malignant</pathologicalStatus><initialQuantity>1</initialQuantity><availableQuantity>1</availableQuantity><specimenCharacteristics><tissueSite>Placenta</tissueSite><tissueSide>Right</tissueSide></specimenCharacteristics></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimenDetailsList></specimens></ns0:businessMessagePayload></ns0:request></ns2:caxchangerequest>";
    }
}
