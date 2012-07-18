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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is an integration test class for Adverse event flows.
 * 
 * @author Rohit Gupta
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mirth-deploy-test.xml")
public class AdverseEventIntegrationTest {

    @Value("${transcend.caxchange.service.url}")
    private String transcendCaxchangeServiceUrl;

    private final HttpClient httpclient = new DefaultHttpClient();

    private static final String XMLTEXT = "text/xml";

    private static final String ERRORCODE1053 = "<errorCode>1053</errorCode>";

    /**
     * TestCase for Creating Adverse Event in caAERS
     */
    @Test
    public void createAdverseEvent() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAdverseEventXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Creating Adverse Event in caAERS when Study does not exist in caAERS.
     */
    @Test
    public void createAEStudyNotExist() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAEStudyNotExist());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Creating Adverse Event in caAERS when Participant does not exist in caAERS.
     */
    @Test
    public void createAEParticipantNotExist() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAEParticipantNotExist());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Creating Adverse Event in caAERS when Participant is NOT assigned to given Study.
     */
    @Test
    public void createAEParticipantNotAssignedToStudy() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAEParticipantNotAssignedToStudy());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Creating Adverse Event in caAERS when startDate of an AE is Invalid
     */
    @Test
    public void createAEInvalidStartDateofAE() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAEInvalidAEStartDate());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Creating Adverse Event in caAERS when endDate of an AE is Invalid.
     */
    @Test
    public void createAEInvalidEndDateofAE() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAEInvalidAEEndDate());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Creating Adverse Event in caAERS when startDate on AE is greater than endDate
     */
    @Test
    public void createAEInvalidStartEndDateCombinationofAE() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAEStartDateGreaterThanEndDate());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Creating Adverse Event in caAERS when startDate of the Course is Invalid
     */
    @Test
    public void createAEStartDateOfThisCourse() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAEInvalidStartDateOfThisCourse());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Creating Adverse Event in caAERS when endDate of the Course is Invalid
     */
    @Test
    public void createAEEndDateOfThisCourse() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAEInvalidEndDateOfThisCourse());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Creating Adverse Event in caAERS when startDate is greater than the endDate of the Course
     */
    @Test
    public void createAEStartDateGreaterThanEndDateOfThisCourse() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAEStartDateGreaterEndDateofThisCourse());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Creating Adverse Event in caAERS when OutComeEnumType is Invalid
     */
    @Test
    public void createAEInvalidOutComeEnumType() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAEInvalidOutComeEnumType());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Creating Adverse Event in caAERS when AttributionType is Invalid
     */
    @Test
    public void createAEInvalidAttributionType() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAEInvalidAttributionType());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS
     */
    @Test
    public void updateAdverseEvent() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAdverseEventXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS when Study does not exist in caAERS.
     */
    @Test
    public void updateAEStudyNotExist() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAEStudyNotExist());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS when Participant does not exist in caAERS.
     */
    @Test
    public void updateAEParticipantNotExist() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAEParticipantNotExist());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS when Participant is NOT assigned to given Study.
     */
    @Test
    public void updateAEParticipantNotAssignedToStudy() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAEParticipantNotAssignedToStudy());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS when startDate of an AE is Invalid
     */
    @Test
    public void updateAEInvalidStartDateofAE() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAEInvalidAEStartDate());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS when endDate of an AE is Invalid.
     */
    @Test
    public void updateAEInvalidEndDateofAE() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAEInvalidAEEndDate());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS when startDate on AE is greater than endDate
     */
    @Test
    public void updateAEInvalidStartEndDateCombinationofAE() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAEStartDateGreaterThanEndDate());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS when startDate of the Course is Invalid
     */
    @Test
    public void updateAEStartDateOfThisCourse() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAEInvalidStartDateOfThisCourse());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS when endDate of the Course is Invalid
     */
    @Test
    public void updateAEEndDateOfThisCourse() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAEInvalidEndDateOfThisCourse());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS when startDate is greater than the endDate of the Course
     */
    @Test
    public void updateAEStartDateGreaterThanEndDateOfThisCourse() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAEStartDateGreaterEndDateofThisCourse());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS when OutComeEnumType is Invalid
     */
    @Test
    public void updateAEInvalidOutComeEnumType() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAEInvalidOutComeEnumType());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
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
     * TestCase for Updating Adverse Event in caAERS when AttributionType is Invalid
     */
    @Test
    public void updateAEInvalidAttributionType() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateAEInvalidAttributionType());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();

            String createdXML = null;

            if (entity != null) {
                createdXML = EntityUtils.toString(entity);
                Assert.assertEquals(true, createdXML.contains(ERRORCODE1053));
            }
        } catch (ClientProtocolException e) {
            Assert.fail(e.getMessage());
        } catch (IllegalStateException e) {
            Assert.fail(e.getMessage());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    private String getCreateAdverseEventXMLStr() {
        return getXMLString("CreateAdverseEvent.xml");
    }

    private String getCreateAEStudyNotExist() {
        return getXMLString("CreateAEStudyNotExist.xml");
    }

    private String getCreateAEParticipantNotExist() {
        return getXMLString("CreateAEParticipantNotExist.xml");
    }

    private String getCreateAEParticipantNotAssignedToStudy() {
        return getXMLString("CreateAEParticipantNotAssignedToStudy.xml");
    }

    private String getCreateAEInvalidAEStartDate() {
        return getXMLString("CreateAEInvalidAEStartDate.xml");
    }

    private String getCreateAEInvalidAEEndDate() {
        return getXMLString("CreateAEInvalidAEEndDate.xml");
    }

    private String getCreateAEStartDateGreaterThanEndDate() {
        return getXMLString("CreateAEStartDateGreaterThanEndDate.xml");
    }

    private String getCreateAEInvalidStartDateOfThisCourse() {
        return getXMLString("CreateAEInvalidStartDateOfThisCourse.xml");
    }

    private String getCreateAEInvalidEndDateOfThisCourse() {
        return getXMLString("CreateAEInvalidEndDateOfThisCourse.xml");
    }

    private String getCreateAEStartDateGreaterEndDateofThisCourse() {
        return getXMLString("CreateAEStartDateGreaterEndDateofThisCourse.xml");
    }

    private String getCreateAEInvalidOutComeEnumType() {
        return getXMLString("CreateAEInvalidOutComeEnumType.xml");
    }

    private String getCreateAEInvalidAttributionType() {
        return getXMLString("CreateAEInvalidAttributionType.xml");
    }

    private String getUpdateAdverseEventXMLStr() {
        return getXMLString("UpdateAdverseEvent.xml");
    }

    private String getUpdateAEStudyNotExist() {
        return getXMLString("UpdateAEStudyNotExist.xml");
    }

    private String getUpdateAEParticipantNotExist() {
        return getXMLString("UpdateAEParticipantNotExist.xml");
    }

    private String getUpdateAEParticipantNotAssignedToStudy() {
        return getXMLString("UpdateAEParticipantNotAssignedToStudy.xml");
    }

    private String getUpdateAEInvalidAEStartDate() {
        return getXMLString("UpdateAEInvalidAEStartDate.xml");
    }

    private String getUpdateAEInvalidAEEndDate() {
        return getXMLString("UpdateAEInvalidAEEndDate.xml");
    }

    private String getUpdateAEStartDateGreaterThanEndDate() {
        return getXMLString("UpdateAEStartDateGreaterThanEndDate.xml");
    }

    private String getUpdateAEInvalidStartDateOfThisCourse() {
        return getXMLString("UpdateAEInvalidStartDateOfThisCourse.xml");
    }

    private String getUpdateAEInvalidEndDateOfThisCourse() {
        return getXMLString("UpdateAEInvalidEndDateOfThisCourse.xml");
    }

    private String getUpdateAEStartDateGreaterEndDateofThisCourse() {
        return getXMLString("UpdateAEStartDateGreaterEndDateofThisCourse.xml");
    }

    private String getUpdateAEInvalidOutComeEnumType() {
        return getXMLString("UpdateAEInvalidOutComeEnumType.xml");
    }

    private String getUpdateAEInvalidAttributionType() {
        return getXMLString("UpdateAEInvalidAttributionType.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueParticipantClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads_adverseevent/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            System.err.println("Error while reading contents of file : " + fileName + ". " + e);// NOPMD
        }
        return contents;
    }
}
