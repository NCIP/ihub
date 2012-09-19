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
 * This is an integration test class for Specimen client flows.
 * 
 * @author Rohit Gupta
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mirth-deploy-test.xml")
public class SpecimenIntegrationTest {

    @Value("${transcend.caxchange.service.url}")
    private String transcendCaxchangeServiceUrl;

    private final HttpClient httpclient = new DefaultHttpClient();

    private static final String XMLTEXT = "text/xml";

    private static final Logger LOG = LoggerFactory.getLogger(SpecimenIntegrationTest.class);

    /**
     * Testcase for Create Specimen when CollectionProtocol is invalid
     */
    @Test
    public void createSpecimenForInvalidCollectionProtocolSpecimens() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getInsertInvalidCollectionProtocolXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains("<errorCode>1084</errorCode>"));
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
     * Testcase for Create Specimen when TissueSite is invalid
     */
    @Test
    public void createSpecimenForInvalidTissueSite() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getInsertInvalidTissueSiteXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains("<errorCode>1083</errorCode>"));
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
     * Testcase for Create Specimen when SpecimenClass is invalid
     */
    @Test
    public void createInvalidSpecimenClass() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getInsertInvalidSpecimenClassXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains("<responseStatus>FAILURE</responseStatus>"));
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
     * Testcase for Create Specimen
     */
    @Test
    public void createSpecimen() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getInsertSpecimenXMLStr());
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
     * Testcase for Create Specimen when specimen already exists in caTissue
     */
    @Test
    public void createExistingSpecimens() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getInsertExistingSpecimenXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains("<errorCode>1080</errorCode>"));
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
     * Testcase for Update Specimen
     */
    @Test
    public void updateSpecimen() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateSpecimenXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String updatedXML = null;

            if (entity != null) {
                updatedXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, updatedXML.contains("<responseStatus>SUCCESS</responseStatus>"));
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
     * Testcase for Update Specimen when Available quantity is greater than Initial Quantity
     */
    @Test
    public void updateSpecimensInvalidAvailableQtyXMLStr() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateSpecimenInvalidAvailableQtyXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String updatedXML = null;

            if (entity != null) {
                updatedXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, updatedXML.contains("<errorCode>1085</errorCode>"));
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
     * Testcase for Update Specimen when CollectionProtocol is changed during updateSpecimen
     */
    @Test
    public void updateSpecimensCollectionProtocolChangeXMLStr() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateSpecimenCollectionProtocolChangeXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String updatedXML = null;

            if (entity != null) {
                updatedXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, updatedXML.contains("<errorCode>1088</errorCode>"));
            }
        } catch (ClientProtocolException e) {
            Assert.fail(e.getMessage());
        } catch (IllegalStateException e) {
            Assert.fail(e.getMessage());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    private String getInsertInvalidCollectionProtocolXMLStr() {
        return getXMLString("CreateSpecimenInvalidCollectionProtocol_inbound.xml");
    }

    private String getInsertInvalidTissueSiteXMLStr() {
        return getXMLString("CreateSpecimenInvalidTissueSite_inbound.xml");
    }

    private String getInsertInvalidSpecimenClassXMLStr() {
        return getXMLString("CreateSpecimenInvalidSpecimenClass_inbound.xml");
    }

    private String getInsertSpecimenXMLStr() {
        return getXMLString("CreateSpecimen_inbound.xml");
    }

    private String getInsertExistingSpecimenXMLStr() {
        return getXMLString("CreateExistingSpecimen_inbound.xml");
    }

    private String getUpdateSpecimenXMLStr() {
        return getXMLString("UpdateSpecimen_inbound.xml");
    }

    private String getUpdateSpecimenInvalidAvailableQtyXMLStr() {
        return getXMLString("UpdateSpecimenInvalidAvailableQty_inbound.xml");
    }

    private String getUpdateSpecimenCollectionProtocolChangeXMLStr() {
        return getXMLString("UpdateSpecimenCollectionProtocolChange_inbound.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueParticipantClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/specimen/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
