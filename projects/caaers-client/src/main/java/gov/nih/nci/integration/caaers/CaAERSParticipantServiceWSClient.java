package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import gov.nih.nci.cabig.caaers.integration.schema.participant.AssignmentType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.CreateParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.CreateParticipantResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.DeleteParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.DeleteParticipantResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.GetParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.GetParticipantResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantRef;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantServiceInterface;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.Participants;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ReducedIdentifierType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.UpdateParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.UpdateParticipantResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.StudyType.Identifiers;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WebService client for CaAERS-Participant flow
 * 
 * @author Vinodh
 */
public class CaAERSParticipantServiceWSClient {

    private Unmarshaller unmarshaller = null;
    private ParticipantServiceInterface client;
    private String userName;
    private ClientPasswordCallback clientPasswordCallback;
    private static final Logger LOG = LoggerFactory.getLogger(CaAERSParticipantServiceWSClient.class);

    /**
     * Constructor
     * 
     * @param serviceUrl - URL
     * @param userName - user name
     * @param clientPasswordCallback - clientPasswordCallback
     * @throws IntegrationException - IntegrationException
     */
    public CaAERSParticipantServiceWSClient(String serviceUrl, String userName,
            ClientPasswordCallback clientPasswordCallback) throws IntegrationException {
        super();
        this.userName = userName;
        this.clientPasswordCallback = clientPasswordCallback;

        try {
            getUnmarshaller();
            initClient(serviceUrl);
        } catch (JAXBException e) {
            LOG.error("CaAERSParticipantServiceWSClient..JAXBException while unmarshlling", e);
            throw new IntegrationException(IntegrationError._1054, e.getMessage());// NOPMD
        }
    }

    private void initClient(String serviceUrl) {
        // Manual WSS4JOutInterceptor interceptor process - start
        final ConcurrentHashMap<String, Object> outProps = new ConcurrentHashMap<String, Object>();
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        outProps.put(WSHandlerConstants.USER, userName);
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        outProps.put(WSHandlerConstants.PW_CALLBACK_REF, clientPasswordCallback);

        final WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);

        final JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.getOutInterceptors().add(wssOut);
        factory.setServiceClass(ParticipantServiceInterface.class);
        factory.setAddress(serviceUrl);

        client = (ParticipantServiceInterface) factory.create();

        final Client clientProxy = ClientProxy.getClient(client);

        final HTTPConduit http = (HTTPConduit) clientProxy.getConduit();
        final TLSClientParameters tlsClientParams = new TLSClientParameters();
        tlsClientParams.setDisableCNCheck(true);
        http.setTlsClientParameters(tlsClientParams);
    }

    /**
     * Constructor used for Mocking purpose
     * 
     * @param client - ParticipantServiceInterface
     * @throws IntegrationException - IntegrationException
     */
    public CaAERSParticipantServiceWSClient(ParticipantServiceInterface client) throws IntegrationException {
        try {
            getUnmarshaller();
            this.client = client;
        } catch (JAXBException e) {
            LOG.error("CaAERSParticipantServiceWSClient()..JAXBException while initialising unmarshaller", e);
            throw new IntegrationException(IntegrationError._1054, e.getMessage());// NOPMD
        }
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        if (unmarshaller == null) {
            final JAXBContext jc = JAXBContext.newInstance(ParticipantType.class);
            unmarshaller = jc.createUnmarshaller();
        }
        return unmarshaller;
    }

    /**
     * Unmarshalls a ParticipantType object from an xml string
     * 
     * @param participantXMLStr - the Participant xml string
     * @return ParticipantType object
     * @throws JAXBException - JAXBException
     */
    public ParticipantType parseParticipant(String participantXMLStr) throws JAXBException {
        final JAXBElement<ParticipantType> jaxbEle = (JAXBElement<ParticipantType>) getUnmarshaller().unmarshal(
                new StreamSource(new StringReader(participantXMLStr)), ParticipantType.class);
        return jaxbEle.getValue();
    }

    /**
     * This method has code to call createParticipant() of the webservice
     * 
     * @param participantXMLStr - XMLString containing the register-participant details
     * @return response from the webservice
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * @throws IntegrationException - IntegrationException
     */
    public CaaersServiceResponse createParticipant(String participantXMLStr) throws JAXBException,
            MalformedURLException, SOAPFaultException, IntegrationException {
        final ParticipantType participant = parseParticipant(participantXMLStr);

        final CaaersServiceResponse response = validateAssignment(participant);
        if (response != null) {
            return response;
        }

        final CreateParticipant createParticipant = new CreateParticipant();
        final Participants participants = new Participants();
        participants.getParticipant().add(participant);
        createParticipant.setParticipants(participants);

        CreateParticipantResponse retValue = null;
        try {
            retValue = client.createParticipant(createParticipant);
        } catch (SOAPFaultException e) {
            LOG.error("CaAERSParticipantServiceWSClient..SOAPFaultException while calling createParticipant. ", e);
            throw e;
        }
        return retValue.getCaaersServiceResponse();
    }

    /**
     * This method has code to call deleteParticipant() of the webservice
     * 
     * @param participantXMLStr - XMLString containing the delete-participant details
     * @return response from the webservice
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * @throws IntegrationException - IntegrationException
     */
    public CaaersServiceResponse deleteParticipant(String participantXMLStr) throws JAXBException,
            MalformedURLException, SOAPFaultException, IntegrationException {
        final ParticipantType participant = parseParticipant(participantXMLStr);

        final CaaersServiceResponse response = validateAssignment(participant);
        if (response != null) {
            return response;
        }

        final DeleteParticipant deleteParticipant = new DeleteParticipant();
        final Participants participants = new Participants();
        participants.getParticipant().add(participant);
        deleteParticipant.setParticipants(participants);

        DeleteParticipantResponse retValue = null;
        try {
            retValue = client.deleteParticipant(deleteParticipant);
        } catch (SOAPFaultException e) {
            LOG.error("CaAERSParticipantServiceWSClient..SOAPFaultException while calling deleteParticipant. ", e);
            throw e;
        }
        return retValue.getCaaersServiceResponse();
    }

    /**
     * This method has code to call updateParticipant() of the webservice
     * 
     * @param participantXMLStr - XMLString containing the update-participant details
     * @return response from the webservice
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * @throws IntegrationException - IntegrationException
     */
    public CaaersServiceResponse updateParticipant(String participantXMLStr) throws JAXBException,
            MalformedURLException, SOAPFaultException, IntegrationException {

        // get the existing participant
        final CaaersServiceResponse caaersGetResponse = getParticipant(participantXMLStr);
        // check if responseCode is NOT 0
        if (!"0".equals(caaersGetResponse.getServiceResponse().getResponsecode())) {
            return caaersGetResponse;
        }

        // check if the activity status is "Closed", then don't need to call updateParticipant
        if (participantXMLStr.contains("<activityStatus>Closed</activityStatus>")) {
            return getNonActiveParticipantResponse();
        }

        final ParticipantType participant = parseParticipant(participantXMLStr);

        // check if the StudyId is changed..
        //if (isParticipantStudyChanged(participant, caaersGetResponse)) {
        if (isParticipantStudyChanged()) {
            return getStudyChangedResponse();
        }

        final UpdateParticipant updateParticipant = new UpdateParticipant();
        final Participants participants = new Participants();
        participants.getParticipant().add(participant);
        updateParticipant.setParticipants(participants);

        UpdateParticipantResponse retValue = null;
        try {
            retValue = client.updateParticipant(updateParticipant);
            retValue.getCaaersServiceResponse().getServiceResponse().setResponseData(
                    caaersGetResponse.getServiceResponse().getResponseData());
        } catch (SOAPFaultException e) {
            LOG.error("CaAERSParticipantServiceWSClient..SOAPFaultException while calling updateParticipant. ", e);
            throw e;
        }

        return retValue.getCaaersServiceResponse();
    }

    /**
     * This method has code to call getParticipant() of the webservice
     * 
     * @param participantXMLStr - - XMLString containing the get-participant details
     * @return response from the webservice
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     * @throws SOAPFaultException - SOAPFaultException
     * @throws IntegrationException - IntegrationException
     */
    public CaaersServiceResponse getParticipant(String participantXMLStr) throws JAXBException, MalformedURLException,
            SOAPFaultException, IntegrationException {
        final ParticipantType participant = parseParticipant(participantXMLStr);

        final CaaersServiceResponse response = validateAssignment(participant);
        if (response != null) {
            return response;
        }

        final GetParticipant getParticipant = new GetParticipant();
        final ParticipantRef participantRef = new ParticipantRef();
        final ParticipantRef.ParticipantAssignment assgmntRef = new ParticipantRef.ParticipantAssignment();
        participantRef.setParticipantAssignment(assgmntRef);

        final ParticipantType.Assignments prtcpntAssgmnts = participant.getAssignments();
        final List<AssignmentType> assignments = prtcpntAssgmnts.getAssignment();
        String studySubjectId = null;
        ReducedIdentifierType studyId = null;
        for (AssignmentType assignmentType : assignments) {
            studySubjectId = assignmentType.getStudySubjectIdentifier();
            final Identifiers studyIds = assignmentType.getStudySite().getStudy().getIdentifiers();
            studyId = studyIds.getIdentifier();
        }

        assgmntRef.setStudyIdentifier(studyId);
        assgmntRef.setStudySubjectIdentifier(studySubjectId);

        getParticipant.setParticipantRef(participantRef);

        GetParticipantResponse retValue = null;
        try {
            retValue = client.getParticipant(getParticipant);
        } catch (SOAPFaultException e) {
            LOG.error("CaAERSParticipantServiceWSClient..SOAPFaultException while calling getParticipant. ", e);
            throw e;
        }
        return retValue.getCaaersServiceResponse();
    }

    private CaaersServiceResponse validateAssignment(ParticipantType participant) {
        final ParticipantType.Assignments prtcpntAssgmnts = participant.getAssignments();
        final List<AssignmentType> assignments = prtcpntAssgmnts.getAssignment();
        String studySubjectId = null;
        String studySiteId = null;
        String studyId = null;
        for (AssignmentType assignmentType : assignments) {
            studySubjectId = assignmentType.getStudySubjectIdentifier();
            studySiteId = assignmentType.getStudySite().getOrganization().getNciInstituteCode();
            final Identifiers studyIds = assignmentType.getStudySite().getStudy().getIdentifiers();
            studyId = studyIds.getIdentifier().getValue();
        }

        if (StringUtils.isEmpty(studySubjectId)) {
            return getCaaersServiceResponse("StudySubjectIdentifier cannot be empty", "1", Status.FAILED_TO_PROCESS);
        } else if (StringUtils.isEmpty(studySiteId)) {
            return getCaaersServiceResponse("Site Identifier cannot be empty", "1", Status.FAILED_TO_PROCESS);
        } else if (StringUtils.isEmpty(studyId)) {
            return getCaaersServiceResponse("Study Identifier cannot be empty", "1", Status.FAILED_TO_PROCESS);
        } else {
            return null;
        }
    }

    private CaaersServiceResponse getNonActiveParticipantResponse() {
        return getCaaersServiceResponse("caAERS doesnt support off-study.", "0", Status.PROCESSED);
    }

    /**
     * This method is used to check if the Study is changed during Update Registration
     * 
     * @param participant
     * @param caaersGetResponse
     * @return
     */
    private boolean isParticipantStudyChanged() {

        // TODO :: Update the logic as per WebService implementation. Hardcoded till then.
        final boolean isStudyChanged = false;

        return isStudyChanged;
    }

    private CaaersServiceResponse getStudyChangedResponse() {
        return getCaaersServiceResponse("Study can't be changed while updating the Participant.", "1",
                Status.FAILED_TO_PROCESS);
    }

    private CaaersServiceResponse getCaaersServiceResponse(String msg, String responseCode, Status status) {
        final CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        final ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setMessage(msg);
        serviceResponse.setStatus(status);
        serviceResponse.setResponsecode(responseCode);
        caaersServiceResponse.setServiceResponse(serviceResponse);
        return caaersServiceResponse;
    }
}
