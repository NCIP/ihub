/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.caxchange.messaging;

import gov.nih.nci.integration.catissue.CaTissueParticipantClientIntegrationTest;

import java.io.IOException;
import java.io.InputStream;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * LoadTest class for Create Specimen Flow.
 * 
 * @author Rohit Gupta
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mirth-deploy-test.xml")
public class SpecimenLoadTest {

    @Value("${transcend.caxchange.service.url}")
    private String transcendCaxchangeServiceUrl;

    private Date currDt = new Date();

    private final ExecutorService es = Executors.newFixedThreadPool(10);

    private static final Logger LOG = LoggerFactory.getLogger(SpecimenLoadTest.class);

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
            final StringEntity reqentity = new StringEntity(getInsertInvalidAvailableQuantityXMLStr());
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

    private String getInsertInvalidAvailableQuantityXMLStr() {
        return getXMLString("CreateSpecimenInvalidAvailableQuantity_inbound.xml");
    }

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
