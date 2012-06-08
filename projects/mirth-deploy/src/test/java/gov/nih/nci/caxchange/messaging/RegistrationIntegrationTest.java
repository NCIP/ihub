package gov.nih.nci.caxchange.messaging;

import java.io.IOException;
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
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

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
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

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
     * Testcase for Update Participant
     */
    @Test
    public void sendUpdateRegistrationMessage() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getUpdateMsg());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

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
     * Testcase for sending duplicate message for participant registration
     */
    @Test
    public void sendDuplicateRegistrationMessage() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            final StringEntity reqentity = new StringEntity(getCreateMsg());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                final String output = EntityUtils.toString(entity);
                Assert.assertNotNull(output);

                Assert.assertEquals(
                        true,
                        output.contains("<errorCode>1014</errorCode>")
                                || output.contains("<errorCode>1032</errorCode>"));
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
     * Testcase for sending invalid Study during Participant registration
     */
    @Test
    public void sendInvalidStudyRegistrationMessage() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            String msg = getCreateMsg();
            msg = msg.replaceAll("6482", "CP-01");
            final StringEntity reqentity = new StringEntity(msg);
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                final String output = EntityUtils.toString(entity);
                Assert.assertNotNull(output);
                Assert.assertEquals(
                        true,
                        output.contains("<errorCode>1009</errorCode>")
                                || output.contains("<errorCode>1051</errorCode>"));
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
     * Testcase for sending invalid Institution during Participant Registration
     */
    @Test
    public void sendInvalidInstitutionRegistrationMessage() {
        try {
            final HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            String msg = getCreateMsg();
            msg = msg.replaceAll("DCP", "XYZ");
            final StringEntity reqentity = new StringEntity(msg);
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            final HttpResponse response = httpclient.execute(httppost);
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                final String output = EntityUtils.toString(entity);
                Assert.assertNotNull(output);
                Assert.assertEquals(
                        true,
                        output.contains("<errorCode>1012</errorCode>")
                                || output.contains("<errorCode>1032</errorCode>"));
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
        msg = msg.replaceFirst("Create Registration", "Update Registration");
        msg = msg.replaceAll("Cherry", "updCherry");
        return msg;
    }

    // CHECKSTYLE:OFF
    private String getInvalidCredentialsCreateMsg() {
        String msg = "<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><caXchangeResponseMessage xmlns=\"http://caXchange.nci.nih.gov/messaging\"><responseMetadata><externalIdentifier>322XXXXXX</externalIdentifier><caXchangeIdentifier></caXchangeIdentifier></responseMetadata><response><responseStatus>FAILURE</responseStatus><caXchangeError><errorCode>1015</errorCode><errorDescription>Authentication to iHub failed.</errorDescription></caXchangeError></response></caXchangeResponseMessage></S:Body></S:Envelope>";
        msg = msg.replaceAll("XXXXXX", sdf.format(currDt));
        return msg;
    }

    private String getSuccessCreateMsg() {
        String msg = "<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><caXchangeResponseMessage xmlns=\"http://caXchange.nci.nih.gov/messaging\"><responseMetadata><externalIdentifier>322XXXXXX</externalIdentifier><caXchangeIdentifier></caXchangeIdentifier></responseMetadata><response><responseStatus>SUCCESS</responseStatus><targetResponse><targetServiceIdentifier>iHub</targetServiceIdentifier><targetServiceOperation>Create Registration</targetServiceOperation><targetMessageStatus>RESPONSE</targetMessageStatus><targetBusinessMessage><xmlSchemaDefinition>http://caXchange.nci.nih.gov/messaging</xmlSchemaDefinition></targetBusinessMessage></targetResponse></response></caXchangeResponseMessage></S:Body></S:Envelope>";
        msg = msg.replaceAll("XXXXXX", sdf.format(currDt));
        return msg;
    }

    private String getSuccessUpdateMsg() {
        String msg = "<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><caXchangeResponseMessage xmlns=\"http://caXchange.nci.nih.gov/messaging\"><responseMetadata><externalIdentifier>322XXXXXX</externalIdentifier><caXchangeIdentifier></caXchangeIdentifier></responseMetadata><response><responseStatus>SUCCESS</responseStatus><targetResponse><targetServiceIdentifier>iHub</targetServiceIdentifier><targetServiceOperation>Update Registration</targetServiceOperation><targetMessageStatus>RESPONSE</targetMessageStatus><targetBusinessMessage><xmlSchemaDefinition>http://caXchange.nci.nih.gov/messaging</xmlSchemaDefinition></targetBusinessMessage></targetResponse></response></caXchangeResponseMessage></S:Body></S:Envelope>";
        msg = msg.replaceAll("XXXXXX", sdf.format(currDt));
        return msg;
    }

    private String getMsg() {
        String message = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header/><soapenv:Body><mes:caXchangeRequestMessage xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><!--Optional:--><mes:operationName>Create Registration</mes:operationName><mes:externalIdentifier>322XXXXXX</mes:externalIdentifier><!--Optional:--><mes:caXchangeIdentifier/><!--Optional:--><mes:credentials><!--You have a CHOICE of the next 3 items at this level--><mes:userName>tolvenuser</mes:userName><mes:password>changeme</mes:password></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><ns1trim:trim xmlns:ns1trim=\"urn:tolven-org:trim:4.0\"><ns1trim:extends>patientAct</ns1trim:extends><ns1trim:name>obs/evn/registration</ns1trim:name><ns1trim:description>Registration</ns1trim:description><ns1trim:page>registration.xhtml</ns1trim:page><ns1trim:drilldown>registrationDD.xhtml</ns1trim:drilldown><ns1trim:menu>global:caseReportFormMenu</ns1trim:menu><ns1trim:tolvenEventId accountId=\"47003\" application=\"echr\" id=\"2672224\" path=\"echr:patient-2003014:caseReportForm-2672224\" principal=\"sarah.davis@ucsfmedctr.org\" timestamp=\"20120308213121+0000\"/><ns1trim:reference>http://wikihit.org/wiki/index.php</ns1trim:reference><ns1trim:application name=\"echr\" signatureRequired=\"true\"><ns1trim:instance>echr:patient:caseReportForm</ns1trim:instance><ns1trim:wip>echr:activity:all</ns1trim:wip></ns1trim:application><ns1trim:act classCode=\"OBS\" moodCode=\"EVN\"><ns1trim:label>Registration</ns1trim:label><ns1trim:bind application=\"echr\" phase=\"create\"><ns1trim:placeholder bindAction=\"merge\"><ns1trim:path>echr:patient:caseReportForm</ns1trim:path></ns1trim:placeholder></ns1trim:bind><ns1trim:id><ns1trim:II><ns1trim:root>1.2.1.47003</ns1trim:root><ns1trim:extension>echr:patient-2003014:caseReportForm-2672221</ns1trim:extension></ns1trim:II></ns1trim:id><ns1trim:code><ns1trim:CD><ns1trim:code>OINT</ns1trim:code><ns1trim:codeSystemName>HL7</ns1trim:codeSystemName><ns1trim:codeSystemVersion>3.0</ns1trim:codeSystemVersion></ns1trim:CD></ns1trim:code><ns1trim:statusCode>active</ns1trim:statusCode><ns1trim:title><ns1trim:ST>Registration</ns1trim:ST></ns1trim:title><ns1trim:effectiveTime><ns1trim:label>Date</ns1trim:label><ns1trim:new datatype=\"TS\" function=\"now\"/><ns1trim:TS><ns1trim:value>20120308133121-2200</ns1trim:value></ns1trim:TS></ns1trim:effectiveTime><ns1trim:participation name=\"subject\" typeCode=\"SBJ\"><ns1trim:role classCode=\"PAT\"><ns1trim:bind application=\"echr\"><ns1trim:placeholder><ns1trim:path>echr:patient</ns1trim:path></ns1trim:placeholder></ns1trim:bind><ns1trim:bind application=\"ephr\"><ns1trim:placeholder><ns1trim:path>ephr:patient</ns1trim:path></ns1trim:placeholder></ns1trim:bind><ns1trim:id><ns1trim:II><ns1trim:root>1.2.1.47003</ns1trim:root><ns1trim:extension>echr:patient-2003014</ns1trim:extension></ns1trim:II></ns1trim:id><ns1trim:player classCode=\"PSN\" determinerCode=\"INSTANCE\"><ns1trim:name><ns1trim:label>Patient Name</ns1trim:label><ns1trim:EN><ns1trim:label>Legal Name</ns1trim:label><ns1trim:use>L</ns1trim:use><ns1trim:part><ns1trim:label>First Name</ns1trim:label><ns1trim:type>GIV</ns1trim:type><ns1trim:ST>CherryXXXXXX</ns1trim:ST></ns1trim:part><ns1trim:part><ns1trim:label>Middle Name</ns1trim:label><ns1trim:type>GIV</ns1trim:type><ns1trim:ST/></ns1trim:part><ns1trim:part><ns1trim:label>Last Name</ns1trim:label><ns1trim:type>FAM</ns1trim:type><ns1trim:ST>BlossomXXXXXX</ns1trim:ST></ns1trim:part></ns1trim:EN></ns1trim:name></ns1trim:player></ns1trim:role></ns1trim:participation><ns1trim:participation name=\"dataEnterer\" typeCode=\"ENT\"><ns1trim:role classCode=\"ROL\"><ns1trim:id><ns1trim:II><ns1trim:root>1.2.1.47003</ns1trim:root><ns1trim:extension>sarah.davis@ucsfmedctr.org</ns1trim:extension></ns1trim:II></ns1trim:id></ns1trim:role></ns1trim:participation><ns1trim:relationship direction=\"IN\" enabled=\"true\" name=\"RandomPatientID\" typeCode=\"NAME\"><ns1trim:act classCode=\"OBS\" moodCode=\"EVN\"><ns1trim:bind application=\"echr\" phase=\"create\"><ns1trim:placeholder bindAction=\"create\"><ns1trim:path>echr:patient:trial</ns1trim:path></ns1trim:placeholder></ns1trim:bind><ns1trim:id><ns1trim:II><ns1trim:root>1.2.1.47003</ns1trim:root><ns1trim:extension>echr:patient-2003014:trial-2672222</ns1trim:extension></ns1trim:II></ns1trim:id><ns1trim:title><ns1trim:ST>I-SPY 2 Subject ID</ns1trim:ST></ns1trim:title><ns1trim:observation><ns1trim:value><ns1trim:label>Patient ID</ns1trim:label><ns1trim:ST>488XXXXXX</ns1trim:ST></ns1trim:value><ns1trim:value><ns1trim:label>Study ID</ns1trim:label><ns1trim:ST>6482</ns1trim:ST><!--<ns1trim:ST>I-SPY 2 TRIAL</ns1trim:ST>--></ns1trim:value><ns1trim:value><ns1trim:label>Patient Name</ns1trim:label><ns1trim:ST>2012XXXXXX</ns1trim:ST></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" name=\"patientName\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:title><ns1trim:ST>Patient Name</ns1trim:ST></ns1trim:title><ns1trim:relationship direction=\"IN\" enabled=\"false\" name=\"firstName\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:code><ns1trim:CD><ns1trim:code>184095009</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:code><ns1trim:observation><ns1trim:value><ns1trim:label>First</ns1trim:label><ns1trim:ST>CherryXXXXXX</ns1trim:ST></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" name=\"middleName\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:code><ns1trim:CD><ns1trim:code>405622006</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:code><ns1trim:observation><ns1trim:value><ns1trim:label>Middle</ns1trim:label><ns1trim:ST/></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" enabled=\"false\" name=\"lastName\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:code><ns1trim:CD><ns1trim:code>397678008</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:code><ns1trim:observation><ns1trim:value><ns1trim:label>Last</ns1trim:label><ns1trim:ST>BlossomXXXXXX</ns1trim:ST></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" enabled=\"false\" name=\"initials\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:code><ns1trim:CD><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:code><ns1trim:observation><ns1trim:value><ns1trim:label>Initials</ns1trim:label><ns1trim:ST>CB</ns1trim:ST></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" enabled=\"true\" name=\"otherInfo\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:title><ns1trim:ST>Other Information</ns1trim:ST></ns1trim:title><ns1trim:observation><ns1trim:value><ns1trim:label>Race</ns1trim:label><ns1trim:valueSet>race</ns1trim:valueSet><ns1trim:SETCE><ns1trim:displayName>White</ns1trim:displayName><ns1trim:code>2567335</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>CADSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:SETCE><ns1trim:CD><ns1trim:code>103579009 | race |</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:value><ns1trim:value><ns1trim:label>Ethnicity</ns1trim:label><ns1trim:valueSet>ethnicity</ns1trim:valueSet><ns1trim:CE><ns1trim:displayName>Not Hispanic or Latino</ns1trim:displayName><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion></ns1trim:CE><ns1trim:CD><ns1trim:code>364699009</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>2002440</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:value><ns1trim:value><ns1trim:label>SSN</ns1trim:label><ns1trim:ST>123-XX-XXXX</ns1trim:ST><ns1trim:CD><ns1trim:code>398093005</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:value><ns1trim:value><ns1trim:label>Institution</ns1trim:label><ns1trim:originalText>QU</ns1trim:originalText><ns1trim:ST>DCP</ns1trim:ST></ns1trim:value></ns1trim:observation><ns1trim:relationship direction=\"IN\" enabled=\"false\" name=\"gender\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:code><ns1trim:CD><ns1trim:code>263495000</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:code><ns1trim:title><ns1trim:ST>Male</ns1trim:ST></ns1trim:title><ns1trim:relationship direction=\"IN\" enabled=\"true\" name=\"genderStatus\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"/></ns1trim:relationship></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" name=\"mrn\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:code><ns1trim:CD><ns1trim:code>398225001</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:code><ns1trim:observation><ns1trim:value><ns1trim:label>MRN</ns1trim:label><ns1trim:ST>666XXXXXX</ns1trim:ST></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" name=\"country\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:observation><ns1trim:value><ns1trim:label>Country of birth</ns1trim:label><ns1trim:ST/></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" name=\"address\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:title><ns1trim:ST>Patient Address</ns1trim:ST></ns1trim:title><ns1trim:observation><ns1trim:value><ns1trim:label>Zip</ns1trim:label><ns1trim:ST/></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" name=\"registration\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:title><ns1trim:ST>Registration</ns1trim:ST></ns1trim:title><ns1trim:observation><ns1trim:value><ns1trim:label>Registration Date</ns1trim:label><ns1trim:TS><ns1trim:value>2012-03-07</ns1trim:value></ns1trim:TS></ns1trim:value><ns1trim:value><ns1trim:label>Study Coordinator</ns1trim:label><ns1trim:valueSet>studyCoordinator</ns1trim:valueSet><ns1trim:CD><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" name=\"studyStaff\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:title><ns1trim:ST>Study Staff</ns1trim:ST></ns1trim:title><ns1trim:observation><ns1trim:value><ns1trim:label>Oncologist</ns1trim:label><ns1trim:valueSet>oncologist</ns1trim:valueSet><ns1trim:CD><ns1trim:code>309295000</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:value><ns1trim:value><ns1trim:label>Surgeon</ns1trim:label><ns1trim:valueSet>surgeon</ns1trim:valueSet><ns1trim:CD><ns1trim:code>304292004</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:value></ns1trim:observation><ns1trim:relationship direction=\"IN\" name=\"clinicalCoordinator\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:observation><ns1trim:value><ns1trim:label>Clinical Coordinator</ns1trim:label><ns1trim:valueSet>clinicalCoordinator</ns1trim:valueSet><ns1trim:CD><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:value><ns1trim:value><ns1trim:label>Phone</ns1trim:label><ns1trim:ST/></ns1trim:value><ns1trim:value><ns1trim:label>Fax</ns1trim:label><ns1trim:ST/></ns1trim:value><ns1trim:value><ns1trim:label>Email</ns1trim:label><ns1trim:ST/></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" name=\"radiologyCoordinator\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:observation><ns1trim:value><ns1trim:label>Radiology Coordinator</ns1trim:label><ns1trim:valueSet>radiologyCoordinator</ns1trim:valueSet><ns1trim:CD><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CD></ns1trim:value><ns1trim:value><ns1trim:label>Phone</ns1trim:label><ns1trim:ST/></ns1trim:value><ns1trim:value><ns1trim:label>Fax</ns1trim:label><ns1trim:ST/></ns1trim:value><ns1trim:value><ns1trim:label>Email</ns1trim:label><ns1trim:ST/></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" name=\"legalDates\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:title><ns1trim:ST>Legal / Dates</ns1trim:ST></ns1trim:title><ns1trim:observation><ns1trim:value><ns1trim:label>IRB Approval Date</ns1trim:label><ns1trim:TS><ns1trim:value>20120307</ns1trim:value></ns1trim:TS></ns1trim:value><ns1trim:value><ns1trim:label>Screening Informed Consent Date</ns1trim:label><ns1trim:TS><ns1trim:value>20120307</ns1trim:value></ns1trim:TS></ns1trim:value><ns1trim:value><ns1trim:label>HIPAA Consent Date</ns1trim:label><ns1trim:TS><ns1trim:value>20120307</ns1trim:value></ns1trim:TS></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" name=\"caTissueStatus\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:bind application=\"echr\" phase=\"create\"><ns1trim:placeholder bindAction=\"create\"><ns1trim:path>echr:patient:caseReportForm:caTissueValues</ns1trim:path></ns1trim:placeholder></ns1trim:bind><ns1trim:id><ns1trim:II><ns1trim:root>1.2.1.47003</ns1trim:root><ns1trim:extension>echr:patient-2003014:caseReportForm-2672221:caTissueValues-2672223</ns1trim:extension></ns1trim:II></ns1trim:id><ns1trim:title><ns1trim:ST>caTissueStatus</ns1trim:ST></ns1trim:title><ns1trim:observation><ns1trim:value><ns1trim:INT><ns1trim:label>Participant not created yet</ns1trim:label><ns1trim:value>0</ns1trim:value></ns1trim:INT></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship><ns1trim:relationship direction=\"IN\" name=\"patientDOB\" typeCode=\"NAME\"><ns1trim:act classCode=\"ENTRY\" moodCode=\"EVN\"><ns1trim:title><ns1trim:ST>Patient DOB</ns1trim:ST></ns1trim:title><ns1trim:observation><ns1trim:value><ns1trim:label>Patient DOB: </ns1trim:label><ns1trim:TS><ns1trim:value>1965-11-24</ns1trim:value></ns1trim:TS></ns1trim:value></ns1trim:observation></ns1trim:act></ns1trim:relationship><ns1trim:compute><ns1trim:type>org.tolven.process.RandomIDGenerator</ns1trim:type><ns1trim:property name=\"enabled\"><ns1trim:value xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"xs:boolean\">false</ns1trim:value></ns1trim:property><ns1trim:property name=\"path\"><ns1trim:value xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"xs:string\">echr:patients:trials</ns1trim:value></ns1trim:property></ns1trim:compute><ns1trim:compute><ns1trim:type>org.tolven.process.CRFPrior</ns1trim:type><ns1trim:property name=\"enabled\"><ns1trim:value xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"xs:boolean\">false</ns1trim:value></ns1trim:property><ns1trim:property name=\"action\"><ns1trim:value xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"xs:string\">registration</ns1trim:value></ns1trim:property><ns1trim:property name=\"path\"><ns1trim:value xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"xs:string\">echr:patients:all</ns1trim:value></ns1trim:property></ns1trim:compute><ns1trim:compute><ns1trim:type>org.tolven.process.CRFPrior</ns1trim:type><ns1trim:property name=\"computeEnabled\"><ns1trim:value xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"xs:boolean\">true</ns1trim:value></ns1trim:property><ns1trim:property name=\"action\"><ns1trim:value xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"xs:string\">caTissue</ns1trim:value></ns1trim:property><ns1trim:property name=\"path\"><ns1trim:value xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"xs:string\">echr:patient:caseReportForms:caTissueValueList</ns1trim:value></ns1trim:property></ns1trim:compute></ns1trim:act><ns1trim:valueSet name=\"race\"><ns1trim:CE><ns1trim:displayName>American Indian or Alaska Native</ns1trim:displayName><ns1trim:code>2561140</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Asian</ns1trim:displayName><ns1trim:code>2567338</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Black or African American</ns1trim:displayName><ns1trim:code>2561248</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Native Hawaiian or Other Pacific Islander</ns1trim:displayName><ns1trim:code>20140003 | Hawaiians |</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>2567339</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CE><ns1trim:CE><ns1trim:displayName>White</ns1trim:displayName><ns1trim:code>2567335</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>CADSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:CE></ns1trim:valueSet><ns1trim:valueSet name=\"ethnicity\"><ns1trim:CE><ns1trim:displayName>Hispanic or Latino</ns1trim:displayName><ns1trim:code>414422004</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>2581176</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Not Hispanic or Latino</ns1trim:displayName><ns1trim:code>TBD</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem><ns1trim:codeSystemName>SNOMED CT</ns1trim:codeSystemName><ns1trim:codeSystemVersion>January 2009</ns1trim:codeSystemVersion><ns1trim:translation><ns1trim:code>2567110</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.3.26.2</ns1trim:codeSystem><ns1trim:codeSystemName>caDSR</ns1trim:codeSystemName><ns1trim:codeSystemVersion>2.1</ns1trim:codeSystemVersion></ns1trim:translation></ns1trim:CE></ns1trim:valueSet><ns1trim:valueSet name=\"staff\"><ns1trim:CE><ns1trim:displayName>Staff 1</ns1trim:displayName><ns1trim:code>C0024579</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.56</ns1trim:codeSystem><ns1trim:codeSystemVersion>2007AA</ns1trim:codeSystemVersion></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Staff 2</ns1trim:displayName><ns1trim:code>C0024580</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.56</ns1trim:codeSystem><ns1trim:codeSystemVersion>2007AA</ns1trim:codeSystemVersion></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Staff 3</ns1trim:displayName><ns1trim:code>C0024581</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.56</ns1trim:codeSystem><ns1trim:codeSystemVersion>2007AA</ns1trim:codeSystemVersion></ns1trim:CE></ns1trim:valueSet><ns1trim:valueSet name=\"oncologist\"><ns1trim:CE><ns1trim:displayName>Rugo, Hope </ns1trim:displayName><ns1trim:code>3092950000</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Esserman , Laura </ns1trim:displayName><ns1trim:code>3092950001</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Alvarado, Michael </ns1trim:displayName><ns1trim:code>3092950002</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Ewing, Cheryl </ns1trim:displayName><ns1trim:code>3092950003</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Hwang, Shelley </ns1trim:displayName><ns1trim:code>3092950004</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName> Melisko, Michelle </ns1trim:displayName><ns1trim:code>3092950005</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Chien, Jo </ns1trim:displayName><ns1trim:code>3092950006</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Moasser, Mark  </ns1trim:displayName><ns1trim:code>3092950007</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Munster, Pamela </ns1trim:displayName><ns1trim:code>3092950008</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Park, John </ns1trim:displayName><ns1trim:code>3092950009</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE></ns1trim:valueSet><ns1trim:valueSet name=\"surgeon\"><ns1trim:CE><ns1trim:displayName>Rugo, Hope </ns1trim:displayName><ns1trim:code>3042920040</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Esserman , Laura </ns1trim:displayName><ns1trim:code>3042920041</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Alvarado, Michael </ns1trim:displayName><ns1trim:code>3042920042</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Ewing, Cheryl </ns1trim:displayName><ns1trim:code>3042920043</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Hwang, Shelley </ns1trim:displayName><ns1trim:code>3042920044</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName> Melisko, Michelle </ns1trim:displayName><ns1trim:code>3042920045</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Chien, Jo </ns1trim:displayName><ns1trim:code>3042920046</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Moasser, Mark  </ns1trim:displayName><ns1trim:code>3042920047</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Munster, Pamela </ns1trim:displayName><ns1trim:code>3042920048</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>Park, John </ns1trim:displayName><ns1trim:code>3042920049</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE></ns1trim:valueSet><ns1trim:valueSet name=\"clinicalCoordinator\"><ns1trim:CE><ns1trim:displayName>Lyandres, Julia </ns1trim:displayName><ns1trim:code>TBD0</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName> Watkins, Margarita </ns1trim:displayName><ns1trim:code>TBD1</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>D'Andrea, Carrie </ns1trim:displayName><ns1trim:code>TBD2</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE></ns1trim:valueSet><ns1trim:valueSet name=\"radiologyCoordinator\"><ns1trim:CE><ns1trim:displayName>Lyandres, Julia </ns1trim:displayName><ns1trim:code>TBD0</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName> Watkins, Margarita </ns1trim:displayName><ns1trim:code>TBD1</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>D'Andrea, Carrie </ns1trim:displayName><ns1trim:code>TBD2</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE></ns1trim:valueSet><ns1trim:valueSet name=\"studyCoordinator\"><ns1trim:CE><ns1trim:displayName>Lyandres, Julia </ns1trim:displayName><ns1trim:code>TBD0</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName> Watkins, Margarita </ns1trim:displayName><ns1trim:code>TBD1</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE><ns1trim:CE><ns1trim:displayName>D'Andrea, Carrie </ns1trim:displayName><ns1trim:code>TBD2</ns1trim:code><ns1trim:codeSystem>2.16.840.1.113883.6.5</ns1trim:codeSystem></ns1trim:CE></ns1trim:valueSet></ns1trim:trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";

        message = message.replaceAll("XXXXXX", sdf.format(currDt));
        message = message.replaceAll("XX-XXXX", ssnsdf.format(currDt));
        return message;
    }

    // CHECKSTYLE:ON
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

}
