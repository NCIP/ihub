package gov.nih.nci.caxchange.messaging;

import gov.nih.nci.integration.catissue.CaTissueParticipantClientIntegrationTest;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is the TestClass for registerConsent flow.
 * 
 * @author Rohit Gupta
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mirth-deploy-test.xml")
public class RegisterConsentIntegrationTest {

    @Value("${transcend.caxchange.service.url}")
    private String transcendCaxchangeServiceUrl;

    private final HttpClient httpclient = new DefaultHttpClient();

    private static final String XMLTEXT = "text/xml";

    private static final Logger LOG = LoggerFactory.getLogger(RegisterConsentIntegrationTest.class);

    /**
     * Testcase for registerConsents flow
     */
    @Test
    public void registerConsents() {
        try {
            // Create the Specimen for which Consent has to be registered.
            createSpecimen();

            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getRegisterConsentXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains("<responseStatus>SUCCESS</responseStatus>"));
            }
        } catch (ClientProtocolException e) {
            Assert.fail(e.getMessage());
        } catch (IllegalStateException e) {
            Assert.fail(e.getMessage());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Testcase for registerConsents when Specimen doesn't exist
     */
    @Test
    public void registerConsentsSpecimenNotExist() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getRegisterConsentSpecimenNotExistXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains("<errorCode>1090</errorCode>"));
            }
        } catch (ClientProtocolException e) {
            Assert.fail(e.getMessage());
        } catch (IllegalStateException e) {
            Assert.fail(e.getMessage());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    private void createSpecimen() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            String xmlString = getInsertSpecimenXMLStr();
            xmlString = xmlString.replaceAll("102", "102_Consent");
            final StringEntity reqentity = new StringEntity(xmlString);
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains("<responseStatus>SUCCESS</responseStatus>"));
            }
        } catch (ClientProtocolException e) {
            Assert.fail(e.getMessage());
        } catch (IllegalStateException e) {
            Assert.fail(e.getMessage());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    private String getRegisterConsentXMLStr() {
        return getXMLString("RegisterConsent_inbound.xml");
    }

    private String getRegisterConsentSpecimenNotExistXMLStr() {
        return getXMLString("RegisterConsentSpecimenNotExist_inbound.xml");
    }

    private String getInsertSpecimenXMLStr() {
        return getXMLString("CreateSpecimen_inbound.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueParticipantClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/consent/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
