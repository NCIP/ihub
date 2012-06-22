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

    // CHECKSTYLE:OFF
    private String getInsertInvalidAvailableQuantityXMLStr() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header/><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Create Biospecimen</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier/><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><extends>patientAct</extends><name>obs/evn/randomization</name><description>Randomization Form</description><author>Tolven</author><page>randomization.xhtml</page><drilldown>randomizationDD.xhtml</drilldown><menu>global:caseReportFormMenu</menu><tolvenEventId timestamp=\"20090707102504+0000\" status=\"active\" principal=\"v\" application=\"echr\" path=\"echr:patient-1021093:caseReportForm-1030048\" id=\"1030048\" accountId=\"165000\"/><reference/><application signatureRequired=\"true\" name=\"echr\"><instance>echr:patient:caseReportForm</instance><wip>echr:activity:all</wip></application><transitions path=\"trim.act.statusCodeValue\"><transition to=\"active\" name=\"create\"><label language=\"en\">Active</label></transition><transition to=\"completed\" from=\"active\" name=\"complete\"><label language=\"en\">Completed</label></transition><transition to=\"active\" from=\"active\" name=\"reviseActive\"><label language=\"en\">Revise</label></transition><transition to=\"nullified\" from=\"active\" name=\"eie\"><label language=\"en\">Entered in Error</label></transition><transition to=\"completed\" from=\"completed\" name=\"reviseCompleted\"><label language=\"en\">Revise</label></transition></transitions><act moodCode=\"EVN\" classCode=\"OBS\"><bind phase=\"create\" application=\"echr\"><placeholder bindAction=\"create\"><path>echr:patient:caseReportForm</path></placeholder></bind><id><II><root>1.2.1.165000</root><extension>echr:patient-1021093:caseReportForm-1030047</extension></II></id><code><CD><code>C0017506</code><codeSystemName>UMLS</codeSystemName><codeSystemVersion>2007AA</codeSystemVersion></CD></code><statusCode>active</statusCode><title><ST>Randomization Form</ST></title><effectiveTime><label>Date of Therapy:</label><new function=\"now\" datatype=\"TS\"/><TS><value>20090707155504+0530</value></TS></effectiveTime><participation typeCode=\"SBJ\" name=\"subject\"><role classCode=\"PAT\"><bind application=\"echr\"><placeholder><path>echr:patient</path></placeholder></bind><bind application=\"ephr\"><placeholder><path>ephr:patient</path></placeholder></bind><id><II><root>1.2.1.165000</root><extension>echr:patient-1021093</extension></II></id><player determinerCode=\"INSTANCE\" classCode=\"PSN\"><name><label>Patient Name</label><EN><label>Legal Name</label><use>L</use><part><label>First Name</label><type>GIV</type><ST>Willie</ST></part><part><label>Middle Name</label><type>GIV</type><ST>Ruth</ST></part><part><label>Last Name</label><type>FAM</type><ST>Zipse</ST></part></EN></name></player></role></participation><participation typeCode=\"ENT\" name=\"dataEnterer\"><role classCode=\"ROL\"><id><II><root>1.2.1.165000</root><extension>v</extension></II></id></role></participation><relationship typeCode=\"APND\" direction=\"IN\" name=\"treatmentConsent\"><act moodCode=\"APT\" classCode=\"ACCM\"><effectiveTime><IVL_TS><center><TS><value>20090706</value></TS></center></IVL_TS></effectiveTime><observation><value><CE><displayName>Yes</displayName><code>C0183108</code><codeSystem>2.16.840.1.113883.6.56</codeSystem><codeSystemVersion>2007AA</codeSystemVersion></CE></value><value><label>Other</label><ST/></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"activityStatus\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>activityStatus</label><ST>Active</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"cdmsSubjectId\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>cdmsSubjectId</label><ST>66604232</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"specimenList\"><act moodCode=\"APT\" classCode=\"ACCM\"><relationship typeCode=\"APND\" direction=\"IN\" name=\"specimen\"><act moodCode=\"APT\" classCode=\"ACCM\"><relationship typeCode=\"APND\" direction=\"IN\" name=\"collectionProtocolEvent\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>collectionProtocolEvent</label><ST>CPL</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"cdmsSpecimenId\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>cdmsSpecimenId</label><ST>TestUser0012</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"barCode\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>barCode</label><ST>TestUser0012</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"activityStatus\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation>                                          <value><label>activityStatus</label><ST>Active</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"collectionProtocol\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>title</label><ST>6482</ST></value><value><label>shortTitle</label><ST>6482</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"specimenClass\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>specimenClass</label><ST>Tissue</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"specimenType\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>specimenType</label><ST>Fixed Tissue</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"pathologicalStatus\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>pathologicalStatus</label><ST>Malignant</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"initialQuantity\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>initialQuantity</label><ST>1</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"availableQuantity\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>availableQuantity</label><ST>10</ST></value></observation></act></relationship><relationship typeCode=\"APND\" direction=\"IN\" name=\"specimenCharacteristics\"><act moodCode=\"APT\" classCode=\"ACCM\"><observation><value><label>tissueSite</label><ST>Placenta</ST></value><value><label>tissueSide</label><ST>Right</ST></value></observation></act></relationship></act></relationship></act></relationship></act><valueSet name=\"treatmentConsentBooleanValues\"><CE><displayName>Yes</displayName><code>C0183108</code><codeSystem>2.16.840.1.113883.6.56</codeSystem><codeSystemVersion>2007AA</codeSystemVersion></CE><CE><displayName>No</displayName><code>C0183107</code><codeSystem>2.16.840.1.113883.6.56</codeSystem><codeSystemVersion>2007AA</codeSystemVersion></CE></valueSet><valueSet name=\"treatmentConsentNoReasons\"><CE><displayName>Decided not to have neoadjuvant chemotherapy</displayName><code>C0183107</code><codeSystem>2.16.840.1.113883.6.56</codeSystem><codeSystemVersion>2007AA</codeSystemVersion></CE><CE><displayName>Decided not to be treated with a novel agent</displayName><code>C0183108</code><codeSystem>2.16.840.1.113883.6.56</codeSystem><codeSystemVersion>2007AA</codeSystemVersion></CE><CE><displayName>Patient found to be ineligible for study</displayName><code>C0183109</code><codeSystem>2.16.840.1.113883.6.56</codeSystem><codeSystemVersion>2007AA</codeSystemVersion></CE><CE><displayName>Other</displayName><code>C0183110</code><codeSystem>2.16.840.1.113883.6.56</codeSystem><codeSystemVersion>2007AA</codeSystemVersion></CE></valueSet></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
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
