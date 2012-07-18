package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CreateOrUpdateAdverseEventResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.integration.caaers.invoker.CaAERSAdverseEventServiceInvocationStrategy;
import gov.nih.nci.integration.caaers.invoker.CaAERSUpdateAdverseEventServiceInvocationStrategy;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Date;

import javax.xml.bind.JAXBException;

import org.easymock.IAnswer;
import org.easymock.classextension.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test Client for caAERS 'Adverse Event' Strategy Classes. It has test cases for both Create & Update Adverse Event.
 * 
 * @author Rohit Gupta
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-caaers-client-test.xml")
public class CaAERSAdverseEventStrategyTest {

    private CaAERSAdverseEventServiceInvocationStrategy caAERSAdverseEventServiceInvocationStrategy;
    private CaAERSUpdateAdverseEventServiceInvocationStrategy caAERSUpdateAdverseEventServiceInvocationStrategy;
    private CaAERSAdverseEventServiceWSClient wsClient;
    private XSLTTransformer xsltTransformer;
    private static final int RETRY_COUNT = 1;
    private static final Long REFMSGID = 12345L;
    private static final String SUCCESS = "Success";
    private static final String FAILURE = "Failure";

    /**
     * To initialize the things
     * 
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    @Test
    @Before
    public void initialize() throws BeansException, MalformedURLException {
        wsClient = EasyMock.createMock(CaAERSAdverseEventServiceWSClient.class);
        xsltTransformer = EasyMock.createMock(XSLTTransformer.class);
        caAERSAdverseEventServiceInvocationStrategy = new CaAERSAdverseEventServiceInvocationStrategy(xsltTransformer,
                wsClient, RETRY_COUNT);
        caAERSUpdateAdverseEventServiceInvocationStrategy = new CaAERSUpdateAdverseEventServiceInvocationStrategy(
                xsltTransformer, wsClient, RETRY_COUNT);
    }

    /**
     * Tests createAdverseEvent using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void createAdverseEventSuccess() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        }).anyTimes();

        final CreateOrUpdateAdverseEventResponse caaersresponse = getWSResponse(SUCCESS);
        EasyMock.expect(wsClient.createAdverseEvent((String) EasyMock.anyObject())).andReturn(caaersresponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime, caAERSAdverseEventServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSAdverseEventServiceInvocationStrategy
                .invoke(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests createAdverseEvent using the ServiceInvocationStrategy class for the failure scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void createAdverseEventFailure() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        });

        final CreateOrUpdateAdverseEventResponse caaersresponse = getWSResponse(FAILURE);
        EasyMock.expect(wsClient.createAdverseEvent((String) EasyMock.anyObject())).andReturn(caaersresponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime, caAERSAdverseEventServiceInvocationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult result = caAERSAdverseEventServiceInvocationStrategy
                .invoke(serviceInvocationMessage);
        Assert.assertNotNull(result);
    }

    /**
     * Tests Rollback for createAdverseEvent using the ServiceInvocationStrategy class
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void createAERollback() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        });

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime, caAERSAdverseEventServiceInvocationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult result = caAERSAdverseEventServiceInvocationStrategy
                .rollback(serviceInvocationMessage);
        Assert.assertNotNull(result);
    }

    /**
     * Tests updateAdverseEvent using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateAdverseEventSuccess() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        }).anyTimes();

        final CreateOrUpdateAdverseEventResponse caaersresponse = getWSResponse(SUCCESS);
        EasyMock.expect(wsClient.updateAdverseEvent((String) EasyMock.anyObject())).andReturn(caaersresponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime,
                caAERSUpdateAdverseEventServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSUpdateAdverseEventServiceInvocationStrategy
                .invoke(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests updateAdverseEvent using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateAdverseEventFailure() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        }).anyTimes();

        final CreateOrUpdateAdverseEventResponse caaersresponse = getWSResponse(FAILURE);
        EasyMock.expect(wsClient.updateAdverseEvent((String) EasyMock.anyObject())).andReturn(caaersresponse);
        EasyMock.replay(wsClient);

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime,
                caAERSUpdateAdverseEventServiceInvocationStrategy.getStrategyIdentifier());

        final ServiceInvocationResult result = caAERSUpdateAdverseEventServiceInvocationStrategy
                .invoke(serviceInvocationMessage);

        Assert.assertNotNull(result);
    }

    /**
     * Tests Rollback for createAdverseEvent using the ServiceInvocationStrategy class
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateAERollback() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                // return the value to be returned by the method (null for void)
                return getAEXMLString();
            }
        });

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getAEInterimMessage(), stTime,
                caAERSUpdateAdverseEventServiceInvocationStrategy.getStrategyIdentifier());
        final ServiceInvocationResult result = caAERSUpdateAdverseEventServiceInvocationStrategy
                .rollback(serviceInvocationMessage);
        Assert.assertNotNull(result);
    }

    private String getAEInterimMessage() {
        return getXMLString("AEInterimXML.xml");
    }

    private String getAEXMLString() {
        return getXMLString("AdverseEvent.xml");
    }

    private ServiceInvocationMessage prepareServiceInvocationMessage(Long referenceMessageId, String message,
            Date startTime, StrategyIdentifier strategyIdentifier) {
        final ServiceInvocationMessage serviceInvocationMessage = new ServiceInvocationMessage();
        serviceInvocationMessage.setStrategyIdentifier(strategyIdentifier);
        final IHubMessage iHubMessage = new IHubMessage();
        iHubMessage.setStartTime(startTime);
        iHubMessage.setEndTime(new Date(new java.util.Date().getTime())); // NOPMD
        iHubMessage.setRequest(message);
        iHubMessage.setReferenceMessageId(referenceMessageId);
        serviceInvocationMessage.setReferenceMessageId(referenceMessageId);
        serviceInvocationMessage.setMessage(iHubMessage);
        return serviceInvocationMessage;
    }

    private CreateOrUpdateAdverseEventResponse getWSResponse(String type) {
        final CreateOrUpdateAdverseEventResponse caaersresponse = new CreateOrUpdateAdverseEventResponse();
        final CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        final ServiceResponse serviceResponse = new ServiceResponse();
        if (type.equalsIgnoreCase(SUCCESS)) {
            serviceResponse.setResponsecode("0");
        } else {
            serviceResponse.setResponsecode("1");
            serviceResponse.setMessage("incoming data is not proper.");
        }
        caaersServiceResponse.setServiceResponse(serviceResponse);
        caaersresponse.setCaaersServiceResponse(caaersServiceResponse);
        return caaersresponse;
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaAERSAdverseEventServiceClientTest.class.getClassLoader().getResourceAsStream(
                "payloads_adverseevent/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            System.err.println("Error while reading contents of file : " + fileName + ". " + e);// NOPMD
        }
        return contents;
    }
}
