package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventManagementServiceInterface;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventsInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CreateOrUpdateAdverseEvent;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CreateOrUpdateAdverseEventResponse;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.DeleteAdverseEvent;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.DeleteAdverseEventResponse;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.io.StringReader;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

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
 * WebService client for caAERS-Adverse Event Management Service. This class provide method to create, update and
 * delete/rollback the AE.
 * 
 * @author Rohit Gupta
 */
public class CaAERSAdverseEventServiceWSClient {

    private Unmarshaller unmarshaller = null;
    private AdverseEventManagementServiceInterface client;
    private final String userName;
    private final ClientPasswordCallback clientPasswordCallback;
    private static final Logger LOG = LoggerFactory.getLogger(CaAERSAdverseEventServiceWSClient.class);

    /**
     * Constructor
     * 
     * @param serviceUrl - URL
     * @param userName - user name
     * @param clientPasswordCallback - clientPasswordCallback
     * @throws IntegrationException - IntegrationException
     */
    public CaAERSAdverseEventServiceWSClient(String serviceUrl, String userName,
            ClientPasswordCallback clientPasswordCallback) throws IntegrationException {
        super();
        this.userName = userName;
        this.clientPasswordCallback = clientPasswordCallback;

        try {
            getUnmarshaller();
            initClient(serviceUrl);
        } catch (JAXBException e) {
            LOG.error("CaAERSAdverseEventServiceWSClient..JAXBException while unmarshlling", e);
            throw new IntegrationException(IntegrationError._1054, e);
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
        factory.setServiceClass(AdverseEventManagementServiceInterface.class);
        factory.setAddress(serviceUrl);

        client = (AdverseEventManagementServiceInterface) factory.create();
        final Client clientProxy = ClientProxy.getClient(client);
        final HTTPConduit http = (HTTPConduit) clientProxy.getConduit();
        final TLSClientParameters tlsClientParams = new TLSClientParameters();
        tlsClientParams.setDisableCNCheck(true);
        http.setTlsClientParameters(tlsClientParams);
    }

    /**
     * This method is used to create Adverse Event in caAERS
     * 
     * @param adverseEventXMLStr - XMLString containing the list of adverse event to be created
     * @return CreateAdverseEventResponse - response from the webservice call
     * @throws JAXBException - JAXBException
     */
    public CreateOrUpdateAdverseEventResponse createAdverseEvent(String adverseEventXMLStr) throws JAXBException {

        // parse the incoming XML String
        final AdverseEventsInputMessage inputMessage = parseAdverseEventXMLStr(adverseEventXMLStr);

        final CreateOrUpdateAdverseEvent adverseEvent = new CreateOrUpdateAdverseEvent();
        adverseEvent.setAdverseEventsInputMessage(inputMessage);

        CreateOrUpdateAdverseEventResponse serviceResponse = null;

        serviceResponse = client.createOrUpdateAdverseEvent(adverseEvent);

        return serviceResponse;
    }

    /**
     * This method is used to create Adverse Event in caAERS
     * 
     * @param adverseEventXMLStr - XMLString containing the list of adverse event to be created
     * @return CreateOrUpdateAdverseEventResponse - response from the webservice call
     * @throws JAXBException - JAXBException
     */
    public CreateOrUpdateAdverseEventResponse updateAdverseEvent(String adverseEventXMLStr) throws JAXBException {

        // parse the incoming XML String
        final AdverseEventsInputMessage inputMessage = parseAdverseEventXMLStr(adverseEventXMLStr);

        final CreateOrUpdateAdverseEvent adverseEvent = new CreateOrUpdateAdverseEvent();
        adverseEvent.setAdverseEventsInputMessage(inputMessage);

        CreateOrUpdateAdverseEventResponse serviceResponse = null;

        serviceResponse = client.createOrUpdateAdverseEvent(adverseEvent);

        return serviceResponse;
    }

    /**
     * This method is used to delete Adverse Event in caAERS
     * 
     * @param adverseEventXMLStr - XMLString containing the list of adverse event to be deleted
     * @return DeleteAdverseEventResponse
     * @throws JAXBException - JAXBException
     */
    public DeleteAdverseEventResponse deleteAdverseEvent(String adverseEventXMLStr) throws JAXBException {

        // parse the incoming XML String
        final AdverseEventsInputMessage inputMessage = parseAdverseEventXMLStr(adverseEventXMLStr);

        final DeleteAdverseEvent adverseEvent = new DeleteAdverseEvent();
        adverseEvent.setAdverseEventsInputMessage(inputMessage);

        DeleteAdverseEventResponse serviceResponse = null;

        serviceResponse = client.deleteAdverseEvent(adverseEvent);

        return serviceResponse;
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        if (unmarshaller == null) {
            final JAXBContext jc = JAXBContext.newInstance(AdverseEventsInputMessage.class);
            unmarshaller = jc.createUnmarshaller();
        }
        return unmarshaller;
    }

    private AdverseEventsInputMessage parseAdverseEventXMLStr(String adverseEventXMLStr) throws JAXBException {

        final JAXBElement<AdverseEventsInputMessage> jaxbEle = (JAXBElement<AdverseEventsInputMessage>) getUnmarshaller()
                .unmarshal(new StreamSource(new StringReader(adverseEventXMLStr)), AdverseEventsInputMessage.class);
        return jaxbEle.getValue();
    }

}
