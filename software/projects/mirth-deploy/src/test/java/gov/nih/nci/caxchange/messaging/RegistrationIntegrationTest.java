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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
 * This is the TestClass for Participant Registration flow.
 * 
 * @author Vinodh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mirth-deploy-test.xml")
public class RegistrationIntegrationTest {

    private final Locale locale = Locale.US;
    private final SimpleDateFormat sdf = new SimpleDateFormat("MMddmm", locale);
    private final SimpleDateFormat ssnsdf = new SimpleDateFormat("MM-ddmm", locale);

    @Value("${transcend.caxchange.service.url}")
    private String transcendCaxchangeServiceUrl;

    private final Date currDt = new Date();

    private final HttpClient httpclient = new DefaultHttpClient();

    private static final String XMLTEXT = "text/xml";

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationIntegrationTest.class);

    /**
     * Testcase for sending invalid credential message
     */
    @Test
    public void sendInvalidIHubCredentialsMessage() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            String msg = getCreateMsg();
            msg = msg.replaceAll("<mes:userName>tolvenuser</mes:userName>",
                    "<mes:userName>tolvenuser-invalid</mes:userName>");
            final StringEntity reqentity = new StringEntity(msg);
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                final String output = EntityUtils.toString(entity);
                Assert.assertNotNull(output);
                Assert.assertEquals(getInvalidCredentialsCreateMsg(), removeCaXchangeIdentifier(output));
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
     * Testcase for Participant Registration
     */
    @Test
    public void sendRegistrationMessage() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateMsg());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                final String output = EntityUtils.toString(entity);
                Assert.assertNotNull(output);
                Assert.assertEquals(getSuccessCreateMsg(), removeCaXchangeIdentifier(output));
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
     * Testcase for sending invalid Study (Study Not Present) during Participant registration
     */
    @Test
    public void sendInvalidStudyRegistrationMessage() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            String msg = getCreateMsg();
            msg = msg.replaceAll("ISPY2", "ISPY2_123");
            msg = msg.replaceAll("1823467", "1823467_123456");

            final StringEntity reqentity = new StringEntity(msg);
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                final String output = EntityUtils.toString(entity);
                Assert.assertNotNull(output);
                Assert.assertEquals(
                        true,
                        output.contains("<errorCode>1009</errorCode>"));
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
     * Testcase for Update Participant
     */
    @Test
    public void sendUpdateRegistrationMessage() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateMsg());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                final String output = EntityUtils.toString(entity);
                Assert.assertNotNull(output);
                Assert.assertEquals(getSuccessUpdateMsg(), removeCaXchangeIdentifier(output));
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
     * Testcase for Update Participant
     */
    @Test
    public void sendUpdateRegistrationToDifferentStudyMessage() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            String messageStr = getUpdateMsg();
            messageStr = messageStr.replace("6482", "7216");
            final StringEntity reqentity = new StringEntity(getUpdateMsg());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, XMLTEXT);

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                final String output = EntityUtils.toString(entity);
                Assert.assertNotNull(output);
                Assert.assertEquals(getSuccessUpdateMsg(), removeCaXchangeIdentifier(output));
            }
        } catch (ClientProtocolException e) {
            Assert.fail(e.getMessage());
        } catch (IllegalStateException e) {
            Assert.fail(e.getMessage());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    private String getCreateMsg() {
        return getMsg();
    }

    private String getUpdateMsg() {
        String msg = getMsg();
        msg = msg.replaceFirst("Create Participant Registration", "Update Participant Registration");
        msg = msg.replaceAll("FirstName", "updFirstName");
        return msg;
    }

    private String getInvalidCredentialsCreateMsg() {
        String msg = getXMLString("InvalidCredential.xml");
        msg = msg.replaceAll("XXXXXX", sdf.format(currDt));
        return msg;
    }

    private String getSuccessCreateMsg() {
        String msg = getXMLString("SuccessCreateMsg.xml");
        msg = msg.replaceAll("XXXXXX", sdf.format(currDt));
        return msg;
    }

    private String getSuccessUpdateMsg() {
        String msg = getXMLString("SuccessUpdateMsg.xml");
        msg = msg.replaceAll("XXXXXX", sdf.format(currDt));
        return msg;
    }

    private String getMsg() {
        String message = getXMLString("Participant_inbound.xml");
        message = message.replaceAll("XXXXXX", sdf.format(currDt));
        message = message.replaceAll("XX-XXXX", ssnsdf.format(currDt));
        return message;
    }

    private String removeCaXchangeIdentifier(String output) {
        int startTagEnd = output.indexOf("<caXchangeIdentifier>");
        if (startTagEnd < 0) {
            return "";
        }
        startTagEnd = startTagEnd + 21;
        final int endTagStart = output.indexOf("</caXchangeIdentifier>");
        if (endTagStart < 0) {
            return "";
        }
        return output.substring(0, startTagEnd) + output.substring(endTagStart);
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
