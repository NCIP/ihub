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
import java.util.Calendar;

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
 * This class was generated by Apache CXF 2.4.1 2012-04-12T15:17:59.068-04:00 Generated source version: 2.4.1
 * 
 */

@javax.jws.WebService(serviceName = "CaXchangeRequestService", portName = "soap", 
targetNamespace = "http://caXchange.nci.nih.gov/caxchangerequest", 
endpointInterface = "gov.nih.nci.caxchange.messaging.CaXchangeRequestPortType")
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class CaXchangeRequestService extends AcceptMessage {

    private static final Logger LOG = LoggerFactory.getLogger(CaXchangeRequestService.class.getName());

    private JAXBContext jc = null;

    /**
     * Constructor
     * 
     * @param webServiceMessageReceiver - webServiceMessageReceiver
     */
    public CaXchangeRequestService(WebServiceMessageReceiver webServiceMessageReceiver) {
        super(webServiceMessageReceiver);
    }

    /**
     * This method is used process the message
     * 
     * @param parameters - parameters
     * @return ResponseMessage
     */
    @WebResult(name = "responseMessage", targetNamespace = "http://caXchange.nci.nih.gov/caxchangerequest", 
            partName = "parameters")
    @WebMethod
    public gov.nih.nci.caxchange.messaging.ResponseMessage processRequest(
            @WebParam(partName = "parameters", name = "message", 
            targetNamespace = "http://caXchange.nci.nih.gov/caxchangerequest") Message parameters) {
        LOG.debug("Executing operation processRequest");

        final Long caXchangeId = Calendar.getInstance().getTimeInMillis();
        // setting this value as String, but inside the app is expecting a long value
        parameters.getMetadata().setCaXchangeIdentifier(String.valueOf(caXchangeId));

        final gov.nih.nci.caxchange.messaging.ResponseMessage respMessage = new ResponseMessage();
        final ResponseMetadata rm = new ResponseMetadata();
        rm.setExternalIdentifier(parameters.getMetadata().getExternalIdentifier());
        rm.setCaXchangeIdentifier(parameters.getMetadata().getCaXchangeIdentifier());
        respMessage.setResponseMetadata(rm);

        try {
            final String reqstr = getCaXchangeRequestxml(parameters);
            if (StringUtils.isEmpty(reqstr)) {
                final ErrorDetails errorDetails = new ErrorDetails();
                errorDetails.setErrorCode(String.valueOf(IntegrationError._1050));
                errorDetails.setErrorDescription(IntegrationError._1050.getMessage((Object) null));

                final Response response = new Response();
                response.setCaXchangeError(errorDetails);
                response.setResponseStatus(Statuses.FAILURE);
                respMessage.setResponse(response);
                return respMessage;
            }

            String mcResponse = webServiceMessageReceiver.processData(reqstr);
            
            if (LOG.isDebugEnabled()) {
                LOG.debug("CaXchangeRequestService..MC RESPONSE:" + mcResponse);
            }
            
            if (StringUtils.isEmpty(mcResponse)) {
                throw new JAXBException("No proper response from iHub");
            }

            if ((mcResponse.indexOf("Error") > -1 || mcResponse.indexOf("Exception") > -1
                    || mcResponse.indexOf("ERROR") > -1 || mcResponse.indexOf("error") > -1)) {
                mcResponse = StringUtils.remove(mcResponse, "SUCCESS:");
                mcResponse = StringUtils.remove(mcResponse, "FAILURE:");
                final Response response = new Response();
                response.setCaXchangeError(getErrorDetailsFromCaXchangeError(mcResponse));
                response.setResponseStatus(Statuses.FAILURE);
                respMessage.setResponse(response);
                return respMessage;
            }

            final Response response = new Response();
            response.setResponseStatus(Statuses.SUCCESS);
            response.getTargetResponse().add(prepareTargetResponse(parameters));
            respMessage.setResponse(response);

            return respMessage;
        } catch (JAXBException ex) {        
            return getJAXBExceptionResponseMessage(respMessage, ex);
        }

    }
    
    private ResponseMessage getJAXBExceptionResponseMessage(ResponseMessage respMessage, JAXBException ex) {
        final ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode(String.valueOf(IntegrationError._1000.getErrorCode()));
        errorDetails.setErrorDescription(IntegrationError._1000.getMessage(null) + ex.getMessage());
        final Response response = new Response();
        response.setCaXchangeError(errorDetails);
        response.setResponseStatus(Statuses.FAILURE);
        respMessage.setResponse(response);
        return respMessage;
    }

    private String getCaXchangeRequestxml(final Message parameter) {
        String requestXML = null;

        final QName qname = new QName("http://caXchange.nci.nih.gov/caxchangerequest", "caxchangerequest");
        final JAXBElement<Message> message = new JAXBElement<Message>(qname, Message.class, parameter);
        final StringWriter sw = new StringWriter();
        try {
            getMarshaller().marshal(message, sw);
            requestXML = sw.toString();
        } catch (JAXBException e) {
            LOG.error("Error marshalling CaXchangeRequest!", e);
        }
        return requestXML;
    }

    private ErrorDetails getErrorDetailsFromCaXchangeError(String errorXml) throws JAXBException {
        final StreamSource ss = new StreamSource(new StringReader(errorXml));
        return ((JAXBElement<ErrorDetails>) getUnmarshaller(ErrorDetails.class).unmarshal(ss, ErrorDetails.class))
                .getValue();
    }

    private Marshaller getMarshaller() throws JAXBException {
        if (jc == null) {
            jc = JAXBContext.newInstance(Message.class);
        }
        return jc.createMarshaller();
    }

    private Unmarshaller getUnmarshaller(Class claz) throws JAXBException {
        if (jc == null) {
            jc = JAXBContext.newInstance(claz);
        }
        return jc.createUnmarshaller();
    }

    private TargetResponseMessage prepareTargetResponse(Message parameters) {
        final TargetResponseMessage trm = new TargetResponseMessage();
        final Metadata md = parameters.getMetadata();
        trm.setTargetServiceIdentifier(md.getServiceType());
        trm.setTargetServiceOperation(md.getOperationName().getValue());
        trm.setTargetMessageStatus(MessageStatuses.RESPONSE);
        final MessagePayload mp = new MessagePayload();
        mp.setXmlSchemaDefinition("http://caXchange.nci.nih.gov/messaging");
        trm.setTargetBusinessMessage(mp);
        return trm;
    }

}
