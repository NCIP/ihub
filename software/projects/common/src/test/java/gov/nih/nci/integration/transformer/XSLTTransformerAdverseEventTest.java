package gov.nih.nci.integration.transformer;

import gov.nih.nci.integration.exception.IntegrationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test Class for XML Transformation Tests for Adverse Event Flow
 * 
 * @author Rohit Gupta
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
public class XSLTTransformerAdverseEventTest {

    @Autowired
    private XSLTTransformer xsltTransformer;

    @Value("${integration.transformer.xsl.baseClassPath}")
    private String baseXSLPath;

    @Value("${caaers.adverseevent.xsl}")
    private String caaersAdverseEventXsl;

    /**
     * 
     */
    @Test
    public void createAdverseEvent() {
        System.out.println("Hi..");// NOPMD
    }

    /**
     * Testcase for transforming Interim XML to XMLString
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void transformInterimToXMLStringTest() throws IntegrationException {
        System.out.println("Hi");// NOPMD
        xsltTransformer.initTransformer(caaersAdverseEventXsl, baseXSLPath);
        String trnsfrmdMsg = transformXML(getAEInterimMessage());
        Assert.assertNotNull(trnsfrmdMsg);
    }

    // CHECKSTYLE:OFF

    private String getAEInterimMessage() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>Adrenal insufficiency</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>Aspartateamino transferase increased</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents>";
    }

    // CHECKSTYLE:ON

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
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("XMLString --> " + xmlStr); // NOPMD
        return xmlStr;
    }

}
