package gov.nih.nci.caxchange.messaging;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
 * LoadTest class for Adverse Event
 * 
 * @author Rohit Gupta
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mirth-deploy-test.xml")
public class AdverseEventLoadTest {

    @Value("${transcend.caxchange.service.url}")
    private String transcendCaxchangeServiceUrl;

    private Date currDt = new Date();

    private final ExecutorService es = Executors.newFixedThreadPool(10);

    /**
     * Testcase for sending the messages
     * 
     * @throws InterruptedException - InterruptedException
     */
    @Test
    public void sendAdverseEventMessage() throws InterruptedException {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currDt.getTime() + 5000);
        currDt = calendar.getTime();

        final Timer timer = new Timer();
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);
        timer.schedule(getTimerTask(), currDt);

        es.awaitTermination(3, TimeUnit.MINUTES);
    }

    private void sendMessage() {
        try {
            final HttpClient httpclient = new DefaultHttpClient();
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateAdverseEventXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                final String output = EntityUtils.toString(entity);
                Assert.assertNotNull(output);
                Assert.assertEquals(true, output.contains("FAILURE"));
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
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header/><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Adverse Event</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier/><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><adverseeventinput><criteria><participantIdentifier>PM-113</participantIdentifier><studyIdentifier>7216</studyIdentifier><course><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse><treatmentType>Treatment</treatmentType><treatmentAssignmentCode>TAC</treatmentAssignmentCode></course></criteria><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><ctepCode>Adrenal insufficiency</ctepCode><grade>3</grade><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><expected>true</expected><attributionSummary>POSSIBLE</attributionSummary><outcome><outComeEnumType>LIFE_THREATENING</outComeEnumType></outcome><outcome><outComeEnumType>HOSPITALIZATION</outComeEnumType></outcome></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><ctepCode>Aspartateamino transferase increased</ctepCode><grade>4</grade><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><expected>true</expected><attributionSummary>DEFINITE</attributionSummary><outcome><outComeEnumType>CONGENITAL_ANOMALY</outComeEnumType></outcome><outcome><outComeEnumType>OTHER_SERIOUS</outComeEnumType></outcome></adverseEvent></adverseEventsList></adverseeventinput></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }

    // CHECKSTYLE:ON

    private Runnable getRunnable() {
        return new Runnable() {

            @Override
            public void run() {
                sendMessage();
            }
        };
    }

    private TimerTask getTimerTask() {
        return new TimerTask() {

            @Override
            public void run() {
                es.execute(getRunnable());
            }
        };
    }
}
