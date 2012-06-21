package gov.nih.nci.caxchange.messaging;

import java.io.IOException;

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
            final StringEntity reqentity = new StringEntity(getCreateAEInvalidArrtibutionType());
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
            final StringEntity reqentity = new StringEntity(getUpdateAEInvalidArrtibutionType());
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

    // CHECKSTYLE:OFF
    private String getCreateAdverseEventXMLStr() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getCreateAEStudyNotExist() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>12345</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getCreateAEParticipantNotExist() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-001</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getCreateAEParticipantNotAssignedToStudy() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7211</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getCreateAEInvalidAEStartDate() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-47-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getCreateAEInvalidAEEndDate() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-67-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getCreateAEStartDateGreaterThanEndDate() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-19-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getCreateAEInvalidStartDateOfThisCourse() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-57-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getCreateAEInvalidEndDateOfThisCourse() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-87-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getCreateAEStartDateGreaterEndDateofThisCourse() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-22-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getCreateAEInvalidOutComeEnumType() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING_123</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getCreateAEInvalidArrtibutionType() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE_123</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAdverseEventXMLStr() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAEStudyNotExist() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>12345</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAEParticipantNotExist() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-001</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAEParticipantNotAssignedToStudy() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7211</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAEInvalidAEStartDate() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-47-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAEInvalidAEEndDate() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-67-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAEStartDateGreaterThanEndDate() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-19-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAEInvalidStartDateOfThisCourse() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-57-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAEInvalidEndDateOfThisCourse() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-87-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAEStartDateGreaterEndDateofThisCourse() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-22-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAEInvalidOutComeEnumType() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING_123</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    private String getUpdateAEInvalidArrtibutionType() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header /><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Update Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier /><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>10001367</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE_123</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>10014004</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    // CHECKSTYLE:ON
}
