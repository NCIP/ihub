package gov.nih.nci.caxchange.messaging;

import gov.nih.nci.integration.catissue.CaTissueParticipantClientIntegrationTest;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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
 * LoadTest class for Participant Registration
 * 
 * @author Vinodh
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mirth-deploy-test.xml")
public class RegistrationLoadTest {

    // CHECKSTYLE:OFF
    private final static SimpleDateFormat SDF = new SimpleDateFormat("MMddmm");
    private final static SimpleDateFormat SSNSDF = new SimpleDateFormat("MM-ddmm");
    // CHECKSTYLE:ON

    @Value("${transcend.caxchange.service.url}")
    private String transcendCaxchangeServiceUrl;

    private Date currDt = new Date();

    private final ExecutorService es = Executors.newFixedThreadPool(10);

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationLoadTest.class);

    /**
     * Testcase for sending the messages
     * 
     * @throws InterruptedException - InterruptedException
     */
    @Test
    public void sendRegistrationMessage() throws InterruptedException {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currDt.getTime() + 5000);
        currDt = calendar.getTime();

        final Timer timer = new Timer();
        timer.schedule(getTimerTask(0), currDt);
        timer.schedule(getTimerTask(1), currDt);
        timer.schedule(getTimerTask(2), currDt);
        timer.schedule(getTimerTask(3), currDt);
        timer.schedule(getTimerTask(4), currDt);
        timer.schedule(getTimerTask(5), currDt);
        timer.schedule(getTimerTask(6), currDt);
        timer.schedule(getTimerTask(7), currDt);
        timer.schedule(getTimerTask(8), currDt);
        timer.schedule(getTimerTask(9), currDt);
        timer.schedule(getTimerTask(10), currDt);
        timer.schedule(getTimerTask(11), currDt);
        timer.schedule(getTimerTask(12), currDt);
        timer.schedule(getTimerTask(13), currDt);
        timer.schedule(getTimerTask(14), currDt);
        timer.schedule(getTimerTask(15), currDt);
        timer.schedule(getTimerTask(16), currDt);
        timer.schedule(getTimerTask(17), currDt);
        timer.schedule(getTimerTask(18), currDt);
        timer.schedule(getTimerTask(19), currDt);

        es.awaitTermination(3, TimeUnit.MINUTES);
    }

    private void sendMessage(int sfx) {
        try {
            final HttpClient httpclient = new DefaultHttpClient();
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateMsg(sfx));
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                final String output = EntityUtils.toString(entity);
                Assert.assertNotNull(output);
                Assert.assertEquals(true, output.contains("SUCCESS"));
            }
        } catch (ClientProtocolException e) {
            Assert.fail(e.getMessage());
        } catch (IllegalStateException e) {
            Assert.fail(e.getMessage());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    private String getCreateMsg(int sfx) {
        return getMsg(sfx);
    }

    private String getMsg(int sfx) {
        String message = getXMLString("Participant_inbound.xml");
        message = message.replaceAll("XXXXXX", SDF.format(currDt) + sfx);
        message = message.replaceAll("XX-XXXX", SSNSDF.format(currDt));
        return message;
    }

    private Runnable getRunnable(final int sfx) {
        return new Runnable() {

            @Override
            public void run() {
                sendMessage(sfx);

            }
        };
    }

    private TimerTask getTimerTask(final int sfx) {
        return new TimerTask() {

            @Override
            public void run() {
                es.execute(getRunnable(sfx));
            }
        };
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueParticipantClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/participant/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
