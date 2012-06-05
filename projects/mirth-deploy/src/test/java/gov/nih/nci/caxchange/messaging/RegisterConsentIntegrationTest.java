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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mirth-deploy-test.xml")
public class RegisterConsentIntegrationTest {

    @Value("${transcend.caxchange.service.url}")
    private String transcendCaxchangeServiceUrl;

    private HttpClient httpclient = new DefaultHttpClient();
       
    @Test
    public void registerConsents() {
        try {
            HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            StringEntity reqentity = new StringEntity(getRegisterConsentXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            
            String createdXML = null;
            
            if (entity != null) {
                createdXML = EntityUtils.toString(entity);               
                System.out.println("createdXML --> " +createdXML); //NOPMD
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
    
    
    @Test
    public void registerConsentsSpecimenNotExist() {
        try {
            HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            StringEntity reqentity = new StringEntity(getRegisterConsentSpecimenNotExistXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            
            String createdXML = null;
            
            if (entity != null) {
                createdXML = EntityUtils.toString(entity);               
                System.out.println("createdXML --> " +createdXML); //NOPMD
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
    
    
    @Test
    public void registerConsentsCollectionProtocolNotExist() {
        try {
            HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            StringEntity reqentity = new StringEntity(getRegisterConsentCollectionProtocolNotExistXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            
            String createdXML = null;
            
            if (entity != null) {
                createdXML = EntityUtils.toString(entity);               
                System.out.println("createdXML --> " +createdXML); //NOPMD
                Assert.assertEquals(true, createdXML.contains("<errorCode>1091</errorCode>"));
            }
        } catch (ClientProtocolException e) {
            Assert.fail(e.getMessage());
        } catch (IllegalStateException e) {
            Assert.fail(e.getMessage());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }
    
  
    
    @Test
    public void registerConsentsStatementNotExist() {
        try {
            HttpPost httppost = new HttpPost(transcendCaxchangeServiceUrl);
            StringEntity reqentity = new StringEntity(getRegisterConsentStatementNotExistXMLStr());
            httppost.setEntity(reqentity);
            httppost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            
            String createdXML = null;
            
            if (entity != null) {
                createdXML = EntityUtils.toString(entity);               
                System.out.println("createdXML --> " +createdXML); //NOPMD
                Assert.assertEquals(true, createdXML.contains("<errorCode>1092</errorCode>"));
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
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header/><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Register Consent</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier/><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><consents xmlns=\"http://cacis.nci.nih.gov\"><participant><cdmsSubjectId>66604232</cdmsSubjectId></participant><consentsDetailsList><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen12</cdmsSpecimenId></specimen><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol><consentTierResponses><tier><statement>This is a statement</statement><response>Yes</response></tier><tier><statement>This is a second statement.</statement><response>No</response></tier></consentTierResponses></consentDetails><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen155</cdmsSpecimenId></specimen><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol><consentTierResponses><tier><statement>This is a statement</statement><response>Not Specified</response></tier><tier><statement>This is a second statement.</statement><response>Withdrawn</response></tier></consentTierResponses></consentDetails></consentsDetailsList></consents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }
    
    
    private String getRegisterConsentSpecimenNotExistXMLStr() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header/><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Register Consent</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier/><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><consents xmlns=\"http://cacis.nci.nih.gov\"><participant><cdmsSubjectId>66604232</cdmsSubjectId></participant><consentsDetailsList><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen2221</cdmsSpecimenId></specimen><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol><consentTierResponses><tier><statement>This is a statement</statement><response>Yes</response></tier><tier><statement>This is a second statement.</statement><response>No</response></tier></consentTierResponses></consentDetails><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen155</cdmsSpecimenId></specimen><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol><consentTierResponses><tier><statement>This is a statement</statement><response>Not Specified</response></tier><tier><statement>This is a second statement.</statement><response>Withdrawn</response></tier></consentTierResponses></consentDetails></consentsDetailsList></consents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }
   
    private String getRegisterConsentCollectionProtocolNotExistXMLStr() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header/><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Register Consent</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier/><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><consents xmlns=\"http://cacis.nci.nih.gov\"><participant><cdmsSubjectId>66604232</cdmsSubjectId></participant><consentsDetailsList><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen12</cdmsSpecimenId></specimen><collectionProtocol><title>Tolven Tissue Protocol123</title><shortTitle>ttp123</shortTitle></collectionProtocol><consentTierResponses><tier><statement>This is a statement</statement><response>Yes</response></tier><tier><statement>This is a second statement.</statement><response>No</response></tier></consentTierResponses></consentDetails><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen155</cdmsSpecimenId></specimen><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol><consentTierResponses><tier><statement>This is a statement</statement><response>Not Specified</response></tier><tier><statement>This is a second statement.</statement><response>Withdrawn</response></tier></consentTierResponses></consentDetails></consentsDetailsList></consents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }
    
    
    private String getRegisterConsentStatementNotExistXMLStr() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\"><soapenv:Header/><soapenv:Body><mes:caXchangeRequestMessage><mes:metadata><mes:transactionControl>?</mes:transactionControl><mes:serviceType>iHub</mes:serviceType><mes:operationName>Register Consent</mes:operationName><mes:externalIdentifier>32225879</mes:externalIdentifier><mes:caXchangeIdentifier/><mes:credentials><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><mes:xmlSchemaDefinition>urn:tolven-org:trim:4.0</mes:xmlSchemaDefinition><trim xmlns=\"urn:tolven-org:trim:4.0\"><consents xmlns=\"http://cacis.nci.nih.gov\"><participant><cdmsSubjectId>66604232</cdmsSubjectId></participant><consentsDetailsList><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen12</cdmsSpecimenId></specimen><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol><consentTierResponses><tier><statement>This is 123 a statement</statement><response>Yes</response></tier><tier><statement>This is a second statement.</statement><response>No</response></tier></consentTierResponses></consentDetails><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen><cdmsSpecimenId>TolvenTestUser252TissueSpecimen155</cdmsSpecimenId></specimen><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol><consentTierResponses><tier><statement>This is a statement</statement><response>Not Specified</response></tier><tier><statement>This is a second statement.</statement><response>Withdrawn</response></tier></consentTierResponses></consentDetails></consentsDetailsList></consents></trim></mes:businessMessagePayload></mes:request></mes:caXchangeRequestMessage></soapenv:Body></soapenv:Envelope>";
    }
    
}
