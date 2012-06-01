package gov.nih.nci.integration.caaers.invoker;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.Participants;
import gov.nih.nci.integration.caaers.CaAERSParticipantServiceWSClient;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author chandrasekaravr
 * 
 */
public class CaAERSUpdateRegistrationServiceInvocationStrategy implements ServiceInvocationStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(CaAERSUpdateRegistrationServiceInvocationStrategy.class);

    private static QName QNAME = new QName(// NOPMD
            "http://schema.integration.caaers.cabig.nci.nih.gov/participant", "participant");;

    private CaAERSParticipantServiceWSClient client;

    private int retryCount;

    private XSLTTransformer xsltTransformer;

    private Marshaller marshaller = null;

    Map<String, IntegrationError> msgToErrMap;

    public CaAERSUpdateRegistrationServiceInvocationStrategy(XSLTTransformer xsltTransformer,
            CaAERSParticipantServiceWSClient client, int retryCount) {
        super();
        this.xsltTransformer = xsltTransformer;
        this.client = client;
        this.retryCount = retryCount;

        HashMap<String, IntegrationError> msgToErrMapBase = new LinkedHashMap<String, IntegrationError>();

        msgToErrMapBase.put("User is not authorized on this Study", IntegrationError._1009);
        msgToErrMapBase.put("Unable to retrieve participant", IntegrationError._1010);
        msgToErrMapBase.put("Invalid Username/Password", IntegrationError._1011);
        msgToErrMapBase.put("User is not authorized on this Organization", IntegrationError._1012);
        msgToErrMapBase.put("could not be created in caAERS", IntegrationError._1014);
        msgToErrMapBase.put("Could not send Message", IntegrationError._1020);

        msgToErrMap = Collections.synchronizedMap(msgToErrMapBase);
    }

    @Override
    public int getRetryCount() {
        return retryCount;
    }

    @Override
    public StrategyIdentifier getStrategyIdentifier() {
        return StrategyIdentifier.CAEERS_UPDATE_REGISTRATION;
    }

    @Override
    public ServiceInvocationResult invoke(ServiceInvocationMessage msg) {
        ServiceInvocationResult result = new ServiceInvocationResult();
        IntegrationException ie = null;
        try {

            String participantXMLStr = transformToParticipantXML(msg.getMessage().getRequest());

            CaaersServiceResponse caaersGetResponse = client.getParticipant(participantXMLStr);
            ServiceResponse response = caaersGetResponse.getServiceResponse();
            ParticipantType existingPrtcpnt = null;
            if ("0".equals(response.getResponsecode())) {
                result.setResult(response.getResponsecode() + " : " + response.getMessage());
                Participants prtcpnts = (Participants) response.getResponseData().getAny();
                existingPrtcpnt = prtcpnts.getParticipant().get(0);
            } else {
                handleErrorResponse(response, result);
                handleException(result);
                return result;
            }
            String originalData = marshalParticipantType(existingPrtcpnt);

            CaaersServiceResponse caaersresponse = client.updateParticipant(participantXMLStr);
            response = caaersresponse.getServiceResponse();
            if ("0".equals(response.getResponsecode())) {
                result.setResult(response.getResponsecode() + " : " + response.getMessage());
                result.setDataChanged(true);
                result.setOriginalData(originalData);
            } else {
                handleErrorResponse(response, result);
            }
        } catch (SOAPFaultException e) {
            e.printStackTrace();
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (WebServiceException e) {
            e.printStackTrace();
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (IntegrationException e) {
            ie = e;
        }
        if (!result.isFault()) {
            result.setInvocationException(ie);
        }
        handleException(result);
        return result;
    }

    @Override
    public ServiceInvocationResult rollback(ServiceInvocationMessage msg) {
        ServiceInvocationResult result = new ServiceInvocationResult();
        IntegrationException ie = null;
        try {
            String participantXMLStr = msg.getOriginalData();
            CaaersServiceResponse caaersresponse = client.updateParticipant(participantXMLStr);
            ServiceResponse response = caaersresponse.getServiceResponse();
            if ("0".equals(response.getResponsecode())) {
                result.setResult(response.getResponsecode() + " : " + response.getMessage());
            } else {
                handleErrorResponse(response, result);
            }
        } catch (SOAPFaultException e) {
            e.printStackTrace();
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (WebServiceException e) {
            e.printStackTrace();
            ie = new IntegrationException(IntegrationError._1053, e, e.getMessage());
        } catch (IntegrationException e) {
            ie = e;
        }
        if (!result.isFault()) {
            result.setInvocationException(ie);
        }
        handleException(result);
        return result;
    }

    private String transformToParticipantXML(String message) throws IntegrationException {
        String participantXMLStr = null;
        InputStream is = null;
        ByteArrayOutputStream os = null;

        try {
            os = new ByteArrayOutputStream();
            is = new ByteArrayInputStream(message.getBytes());

            xsltTransformer.transform(null, is, os);

            participantXMLStr = new String(os.toByteArray());
        } catch (IntegrationException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                is.close();
            } catch (Exception e) {
            }
            try {
                os.close();
            } catch (Exception e) {
            }
        }
        return participantXMLStr;
    }

    private String marshalParticipantType(ParticipantType participantType) throws IntegrationException {
        StringWriter sw = new StringWriter();

        try {
            JAXBElement<ParticipantType> jaxbEle = new JAXBElement<ParticipantType>(QNAME, ParticipantType.class, null,
                    participantType);
            PrintWriter pw = new PrintWriter(sw);
            getMarshaller().marshal(jaxbEle, pw);
        } catch (JAXBException e) {
            IntegrationException ie = new IntegrationException(IntegrationError._1053, e,
                    "Unable to serialize retrieved Participant");
            throw ie;
        }
        return sw.toString();
    }

    private Marshaller getMarshaller() throws JAXBException {
        if (marshaller == null) {
            JAXBContext jc = JAXBContext.newInstance(ParticipantType.class);
            marshaller = jc.createMarshaller();
        }
        return marshaller;
    }

    private void handleErrorResponse(ServiceResponse response, ServiceInvocationResult result) {
        List<WsError> wserrors = response.getWsError();
        WsError error = null;
        IntegrationException ie = null;
        if (wserrors != null && !wserrors.isEmpty()) {
            error = wserrors.get(0);
            ie = new IntegrationException(IntegrationError._1053, new Throwable(error.getException()), error
                    .getErrorDesc());// NOPMD
        } else {
            ie = new IntegrationException(IntegrationError._1053, new Throwable(response.getMessage()), response
                    .getMessage());// NOPMD
        }
        result.setInvocationException(ie);
    }

    private void handleException(ServiceInvocationResult result) {
        if (!result.isFault()) {
            return;
        }

        Exception exception = result.getInvocationException();
        Throwable cause = exception;
        while (cause instanceof IntegrationException) {
            cause = cause.getCause();
        }
        if (cause == null) {
            return;
        }
        String[] throwableMsgs = getThrowableMsgs(cause);
        IntegrationException newie = (IntegrationException) exception;

        Set<String> keys = msgToErrMap.keySet();

        for (String lkupStr : keys) {
            String msg = getMatchingMsg(lkupStr, throwableMsgs);
            if (msg != null) {
                newie = new IntegrationException(msgToErrMap.get(lkupStr), cause, msg);
                break;
            }
        }

        result.setInvocationException(newie);
    }

    private String[] getThrowableMsgs(Throwable cause) {
        Throwable[] throwables = ExceptionUtils.getThrowables(cause);
        String[] msgs = new String[throwables.length];
        for (int i = 0; i < throwables.length; i++) {
            msgs[i] = throwables[i].getMessage();
        }
        return msgs;
    }

    private String getMatchingMsg(String lookupStr, String[] msgs) {
        for (String string : msgs) {
            if (string.contains(lookupStr)) {
                return string;
            }
        }
        return null;
    }

}
