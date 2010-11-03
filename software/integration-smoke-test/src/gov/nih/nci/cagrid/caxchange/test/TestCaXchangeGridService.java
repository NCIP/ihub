package gov.nih.nci.cagrid.caxchange.test;


import gov.nih.nci.cagrid.caxchange.client.CaXchangeRequestProcessorClient;
import gov.nih.nci.cagrid.caxchange.context.client.CaXchangeResponseServiceClient;
import gov.nih.nci.cagrid.caxchange.context.stubs.types.CaXchangeResponseServiceReference;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.caxchange.Credentials;
import gov.nih.nci.caxchange.Message;
import gov.nih.nci.caxchange.MessagePayload;
import gov.nih.nci.caxchange.Metadata;
import gov.nih.nci.caxchange.Request;
import gov.nih.nci.caxchange.ResponseMessage;
import gov.nih.nci.caxchange.Statuses;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.axis.message.MessageElement;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI;
import org.globus.gsi.GlobusCredential;
import org.jasig.cas.authentication.Authentication;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;

/**
 * Test the caXchange. This class contains methods to send test messages of all the
 * message types accepted by caXchange.
 *
 * @author Harsh Marwaha
 *
 */
public class TestCaXchangeGridService extends TestCase {

    CaXchangeRequestProcessorClient client;
    GlobusCredential userCredentials=null;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    Properties loginProperties = null;
    String externalIdentifier = "myExternalIdentifier";
    String userName=null;
    String password=null;
    String delegatedReference = null;

    Message message = null;

    static String url=System.getProperty("caxchange.url");

    public TestCaXchangeGridService() {
    }

    public TestCaXchangeGridService(String name) {
        super(name);
    }

    public InputStream getResourceInputStream(String fileName) throws Exception {
    	ClassPathResource cpr = new ClassPathResource(fileName);
    	if (!cpr.exists()) {
    		throw new Exception(fileName+" does not exist.");
    	}
    	try {
    	  InputStream inputStream = cpr.getInputStream();
    	  return inputStream;
    	}catch(IOException e) {
    		throw new Exception("Error loading file "+fileName);
    	}
    }

    public void setUp() {
      try {
      	dbf.setNamespaceAware(true);
       loginProperties = new Properties();
       InputStream is = getResourceInputStream("/resources/login.properties");
       loginProperties.load(is);
       userName= loginProperties.getProperty("userName");
       password = loginProperties.getProperty("password");
       message = new Message();
       Metadata metadata = new Metadata();
       metadata.setExternalIdentifier(externalIdentifier);
       Credentials creds = new Credentials();
       creds.setUserName(userName);
       creds.setPassword(password);
       metadata.setCredentials(creds);
       message.setMetadata(metadata);
       Request request = new Request();
       message.setRequest(request);
	   org.cagrid.gaards.websso.authentication.CaGridAuthenticationManager authManager = new org.cagrid.gaards.websso.authentication.CaGridAuthenticationManager();
	   UsernamePasswordCredentials credentials = new UsernamePasswordCredentials();
	   credentials.setUsername(userName.trim());
	   credentials.setPassword(password.trim());
	   Authentication auth = authManager.authenticate(credentials);
	   delegatedReference =  authManager.getSerializedDelegationEpr();
	   creds.setDelegatedCredentialReference(delegatedReference);
   	   client = new CaXchangeRequestProcessorClient(url, authManager.getCredentials());
   	   userCredentials = authManager.getCredentials();
      }
      catch(Exception e) {
          System.out.println("Error creating the client");
          e.printStackTrace();
          throw new RuntimeException(e);
      }
    }
    /**
     * Invoke the service with the input request message
     *
     * @throws Exception
     */
    public ResponseMessage invokeService() throws Exception {
		String synchronousProcessing = System.getProperty("synchronous");
		ResponseMessage getResponse = null;
		StringWriter sw = new StringWriter();
		Utils.serializeObject(message, new QName("http://caXchange.nci.nih.gov/messaging","caXchangeRequestMessage"), sw);
		System.out.print(sw);
		if ("true".equals(synchronousProcessing)) {
			System.out.println("Invoking the service synchronously.");
			getResponse = client.processRequestSynchronously(message);
		} else {
			CaXchangeResponseServiceReference crsr = client
					.processRequestAsynchronously(message);
			System.out.println("Message has been send.");
			EndpointReferenceType endPointReference = crsr
					.getEndpointReference();
			CaXchangeResponseServiceClient responsClient = new CaXchangeResponseServiceClient(
					endPointReference, userCredentials);
			boolean gotResponse = false;
			while (!gotResponse) {
				try {
					getResponse = responsClient.getResponse();
					gotResponse = true;
				} catch (Exception e) {
					System.out.println("caXchange response not ready.");
				}
			}
		}
		StringWriter stringWriter = new StringWriter();
		Utils.serializeObject(getResponse, new QName(
				"http://caXchange.nci.nih.gov/messaging",
				"caXchangeResponseMessage"), stringWriter);
		System.out.println(stringWriter.toString());
		return getResponse;

	}




    /**
	 * Test messagess of type LAB_BASED_AE.
	 *  .
	 */
    public void testAENotification() {
      try {
        InputStream testMessage = TestCaXchangeGridService.class.getClassLoader().getResourceAsStream("labbasedae.xml");
        if (testMessage == null) {
            throw new RuntimeException("Test message does not exist.");
        }
        message.getMetadata().setServiceType("LAB_BASED_AE");
        MessagePayload messagePayload = new MessagePayload();
        URI uri = new URI();
        uri.setPath("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain");
        messagePayload.setXmlSchemaDefinition(uri);
        DocumentBuilder db =dbf.newDocumentBuilder();
        Document payload = db.parse(testMessage);
        MessageElement messageElement = new MessageElement(payload.getDocumentElement());
        messagePayload.set_any(new MessageElement[]{messageElement});
        message.getRequest().setBusinessMessagePayload(messagePayload);
        ResponseMessage responseMessage = invokeService();
        assertNotNull(responseMessage);
        //Since lab based AE is currently an unhandled message type it should return a response of failure
        /*
        assertEquals(responseMessage.getResponse().getResponseStatus(),Statuses.FAILURE);
        assertEquals(responseMessage.getResponse().getCaXchangeError().getErrorCode(),"401");
        */
        assertNotNull(responseMessage);
        assertEquals(responseMessage.getResponse().getResponseStatus(),Statuses.SUCCESS);
      }
      catch(Exception e) {
          System.out.println("Error sending message .");
          throw new RuntimeException(e);
      }

    }


    /**
     * Test messagess of type LOAD_LAB_TO_CDMS.
     *
     * .
     */
    public void testLoadLab() {
      try {
        InputStream testMessage = TestCaXchangeGridService.class.getClassLoader().getResourceAsStream("loadlabcdms.xml");
        if (testMessage == null) {
            throw new RuntimeException("Test message does not exist.");
        }
        message.getMetadata().setServiceType("LOAD_LAB_TO_CDMS");
        MessagePayload messagePayload = new MessagePayload();
        URI uri = new URI();
        uri.setPath("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain");
        messagePayload.setXmlSchemaDefinition(uri);
        DocumentBuilder db =dbf.newDocumentBuilder();
        Document payload = db.parse(testMessage);
        MessageElement messageElement = new MessageElement(payload.getDocumentElement());
        messagePayload.set_any(new MessageElement[]{messageElement});
        message.getRequest().setBusinessMessagePayload(messagePayload);
        StringWriter sw = new StringWriter();
        //Utils.serializeObject(message, new QName("http://caXchange.nci.nih.gov/messaging","caXchangeRequestMessage"), sw);
        //System.out.println(sw);
        ResponseMessage responseMessage = invokeService();
        assertNotNull(responseMessage);
        assertEquals(responseMessage.getResponse().getResponseStatus(),Statuses.SUCCESS);
      }
      catch(Exception e) {
          System.out.println("Error sending message .");
          e.printStackTrace();
          throw new RuntimeException(e);
      }

    }

    /**
     * Test messagess of type CT_LAB_DATA .
     *
     * .
     */
    public void testCtLabData() {
      try {
        InputStream testMessage = TestCaXchangeGridService.class.getClassLoader().getResourceAsStream("ctlabdata.xml");
        if (testMessage == null) {
            throw new RuntimeException("Test message does not exist.");
        }
        message.getMetadata().setServiceType("CT_LAB_DATA");
        MessagePayload messagePayload = new MessagePayload();
        URI uri = new URI();
        uri.setPath("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain");
        messagePayload.setXmlSchemaDefinition(uri);
        DocumentBuilder db =dbf.newDocumentBuilder();
        Document payload = db.parse(testMessage);
        MessageElement messageElement = new MessageElement(payload.getDocumentElement());
        messagePayload.set_any(new MessageElement[]{messageElement});
        message.getRequest().setBusinessMessagePayload(messagePayload);
        ResponseMessage responseMessage = invokeService();
        assertNotNull(responseMessage);
        assertEquals(responseMessage.getResponse().getResponseStatus(),Statuses.SUCCESS);
      }
      catch(Exception e) {
          System.out.println("Error sending message .");
          throw new RuntimeException(e);
      }

    }

    /**
     * Test messagess of type SCHEDULE_MODIFICATION .
     *
     * .
     */
    public void testScheduleModification() {
      try {
        InputStream testMessage = TestCaXchangeGridService.class.getClassLoader().getResourceAsStream("schedulemodification.xml");
        if (testMessage == null) {
            throw new RuntimeException("Test message does not exist.");
        }
        message.getMetadata().setServiceType("SCHEDULE_MODIFICATION");
        MessagePayload messagePayload = new MessagePayload();
        URI uri = new URI();
        uri.setPath("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain");
        messagePayload.setXmlSchemaDefinition(uri);
        DocumentBuilder db =dbf.newDocumentBuilder();
        Document payload = db.parse(testMessage);
        MessageElement messageElement = new MessageElement(payload.getDocumentElement());
        messagePayload.set_any(new MessageElement[]{messageElement});
        message.getRequest().setBusinessMessagePayload(messagePayload);
        ResponseMessage responseMessage = invokeService();
        assertNotNull(responseMessage);
        assertEquals(responseMessage.getResponse().getResponseStatus(),Statuses.SUCCESS);
      }
      catch(Exception e) {
          System.out.println("Error sending message .");
          throw new RuntimeException(e);
      }

    }

    public void testAuthentication() {
    	try {
    		org.cagrid.gaards.websso.authentication.CaGridAuthenticationManager authManager = new org.cagrid.gaards.websso.authentication.CaGridAuthenticationManager();
    		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials();
    		credentials.setUsername("cctsdemo1@nci.nih.gov");
    		credentials.setPassword("!Ccts1");
    		Authentication auth = authManager.authenticate(credentials);
    		assertNotNull(auth.getPrincipal().getId());
    	}catch(Exception e){
    		System.out.println("Error authenticating .");
            throw new RuntimeException(e);
    	}
    }

    public static Test suite() {
       TestSuite suite = new TestSuite();

      suite.addTest(new TestCaXchangeGridService("testStudyCreation"));
      suite.addTest(new TestCaXchangeGridService("testRegisterSubject"));
      // suite.addTest(new TestCaXchangeGridService("testAuthentication"));
       suite.addTest(new TestCaXchangeGridService("testScheduleModification"));
       suite.addTest(new TestCaXchangeGridService("testCtLabData"));
       suite.addTest(new TestCaXchangeGridService("testLoadLab"));
       suite.addTest(new TestCaXchangeGridService("testAENotification"));


       return suite;
    }

    public static void main(String[] args) {
         url = System.getProperty("caxchange.url");
         junit.textui.TestRunner.run(suite());
     }




}
