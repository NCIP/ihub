
package gov.nih.nci.caxchange.transcend;

import gov.nih.nci.caxchange.messaging.ErrorDetails;
import gov.nih.nci.caxchange.messaging.Message;
import gov.nih.nci.caxchange.messaging.MessagePayload;
import gov.nih.nci.caxchange.messaging.MessageStatuses;
import gov.nih.nci.caxchange.messaging.Metadata;
import gov.nih.nci.caxchange.messaging.Response;
import gov.nih.nci.caxchange.messaging.ResponseMessage;
import gov.nih.nci.caxchange.messaging.ResponseMetadata;
import gov.nih.nci.caxchange.messaging.Statuses;
import gov.nih.nci.caxchange.messaging.TargetResponseMessage;
import gov.nih.nci.integration.exception.IntegrationError;

import java.io.StringReader;
import java.io.StringWriter;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mirth.connect.connectors.ws.AcceptMessage;
import com.mirth.connect.connectors.ws.WebServiceMessageReceiver;
/**
 * This class was generated by Apache CXF 2.4.1
 * 2012-04-12T15:17:59.068-04:00
 * Generated source version: 2.4.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "CaXchangeRequestService",
                      portName = "soap",
                      targetNamespace = "http://caXchange.nci.nih.gov/caxchangerequest",
                      endpointInterface = "gov.nih.nci.caxchange.messaging.CaXchangeRequestPortType")
                      
public class CaXchangeRequestService  extends AcceptMessage{

    private static final Logger LOG = LoggerFactory.getLogger(CaXchangeRequestService.class.getName());
    
    private String customLibDir = "custom-lib/";
    
    private JAXBContext jc = null; 
    
    public CaXchangeRequestService(WebServiceMessageReceiver webServiceMessageReceiver) {
        super(webServiceMessageReceiver);
    }

   
    @WebResult(name = "responseMessage", targetNamespace = "http://caXchange.nci.nih.gov/caxchangerequest", partName = "parameters")
    @WebMethod
    public gov.nih.nci.caxchange.messaging.ResponseMessage processRequest(
            @WebParam(partName = "parameters", name = "message", 
                    targetNamespace = "http://caXchange.nci.nih.gov/caxchangerequest") Message parameters)
    	{    	
        LOG.info("Executing operation processRequest");
        System.out.println("Message : " + parameters.getRequest().getBusinessMessagePayload().getXmlSchemaDefinition().toString());
        
        gov.nih.nci.caxchange.messaging.ResponseMessage respMessage = new ResponseMessage();
        ResponseMetadata rm = new ResponseMetadata();
        rm.setExternalIdentifier(parameters.getMetadata().getExternalIdentifier());
        //TODO : need to find a way to get this identifier from MC or set this id from here
        rm.setCaXchangeIdentifier(parameters.getMetadata().getCaXchangeIdentifier());
        respMessage.setResponseMetadata(rm);
        
        try {
        
        	String reqstr = getCaXchangeRequestxml(parameters);
            
       	 if (StringUtils.isEmpty(reqstr)) {
       		 ErrorDetails errorDetails = new ErrorDetails();
       		 errorDetails.setErrorCode("Empty_Value");
       		 errorDetails.setErrorDescription("RequestXML is empty...");
       		 
       		 Response response = new Response();
       		 response.setCaXchangeError(errorDetails);
             response.setResponseStatus(Statuses.FAILURE);
             
             respMessage.setResponse(response);
       		 return respMessage;
              
            }

            String mcResponse = webServiceMessageReceiver.processData(reqstr);
            
            System.out.println("Inside CaXchangeRequestService... mcResponse is :: " +mcResponse);

            LOG.info("MC RESPONSE:" + mcResponse);
            
            if(StringUtils.isEmpty(mcResponse)) {
            	throw new Exception("No proper response from iHub");
            }
            
            if ( (mcResponse.indexOf("Error") > -1 || mcResponse.indexOf("Exception") > -1
                            || mcResponse.indexOf("ERROR") > -1 || mcResponse.indexOf("error") > -1)) {
                mcResponse = StringUtils.remove(mcResponse, "SUCCESS:");
                mcResponse = StringUtils.remove(mcResponse, "FAILURE:");
                          		 
          		Response response = new Response();
          		response.setCaXchangeError(getErrorDetailsFromCaXchangeError(mcResponse));
                response.setResponseStatus(Statuses.FAILURE);
                
                respMessage.setResponse(response);
          		return respMessage;               
            }
            
            Response response = new Response();
            response.setResponseStatus(Statuses.SUCCESS);
            response.getTargetResponse().add(prepareTargetResponse(parameters));
            respMessage.setResponse(response);            
            
            return respMessage;
        } catch (java.lang.Exception ex) {        	
        	ErrorDetails errorDetails = new ErrorDetails();        	
      		errorDetails.setErrorCode(String.valueOf(IntegrationError._1000.getErrorCode()));
      		errorDetails.setErrorDescription(IntegrationError._1000.getMessage(null) + ex.getMessage());
      		
        	Response response = new Response();
        	response.setCaXchangeError(errorDetails);
            response.setResponseStatus(Statuses.FAILURE);
            
            respMessage.setResponse(response);         
            return respMessage;
        }
    }
    
    private String getCaXchangeRequestxml(final Message parameter) {
        try {
        	String requestXML= "";       
        	QName qname = new QName("http://caXchange.nci.nih.gov/caxchangerequest", "caxchangerequest");
        	JAXBElement<Message> message = new JAXBElement<Message>(qname, Message.class, parameter);            
        	StringWriter sw = new StringWriter();        	
        	getMarshaller().marshal( message,sw);        	
        	requestXML = sw.toString(); 
            
            return requestXML;
        } catch (Exception ex) {
        	LOG.error("Error marshalling CaXchangeRequest!", ex);                	 
            return null;
        } 
    }
    
    private ErrorDetails getErrorDetailsFromCaXchangeError(String errorXml) throws JAXBException {
    	StreamSource ss = new StreamSource(new StringReader(errorXml));
    	return ((JAXBElement<ErrorDetails>)getUnmarshaller(ErrorDetails.class)
    				.unmarshal(ss, ErrorDetails.class)).getValue();
    }
    
    private Marshaller getMarshaller() throws JAXBException {
		if(jc == null) {
    		jc = JAXBContext.newInstance(Message.class);
    	}	
		return jc.createMarshaller();		
	}
    
    private Unmarshaller getUnmarshaller(Class claz) throws JAXBException {		
    	if(jc == null) {
    		jc = JAXBContext.newInstance(claz);
    	}		
		return jc.createUnmarshaller();		
	}
    
    private TargetResponseMessage prepareTargetResponse(Message parameters) {
    	TargetResponseMessage trm = new TargetResponseMessage();
    	Metadata md = parameters.getMetadata();
    	trm.setTargetServiceIdentifier(md.getServiceType());
    	trm.setTargetServiceOperation(md.getOperationName().getValue());
    	trm.setTargetMessageStatus(MessageStatuses.RESPONSE);
    	MessagePayload mp = new MessagePayload();
    	mp.setXmlSchemaDefinition("http://caXchange.nci.nih.gov/messaging");
    	trm.setTargetBusinessMessage(mp);
    	return trm;
    }
        
}
