package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueParticipantClientIntegrationTest;
import gov.nih.nci.integration.catissue.CaTissueSpecimenClient;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is the TestClass for CaTissue SpecimenStrategy class.
 * 
 * @author Rohit Gupta
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueSpecimenStrategyTest {

    private CaTissueSpecimenServiceInvocationStrategy caTissueSpecimenStrategy;
    private CaTissueUpdateSpecimenServiceInvocationStrategy caTissueUpdateSpecimenStrategy;
    private XSLTTransformer xsltTransformer;

    private CaTissueSpecimenClient caTissueSpecimenClient;
    private static final int RETRY_COUNT = 1;
    private static final Long REFMSGID = 12345L;

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueSpecimenStrategyTest.class);

    /**
     * To initialize the things
     * 
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    @Test
    @Before
    public void initialize() throws BeansException, MalformedURLException {
        caTissueSpecimenClient = EasyMock.createMock(CaTissueSpecimenClient.class);
        xsltTransformer = EasyMock.createMock(XSLTTransformer.class);
        caTissueSpecimenStrategy = new CaTissueSpecimenServiceInvocationStrategy(RETRY_COUNT, caTissueSpecimenClient,
                xsltTransformer);
        caTissueUpdateSpecimenStrategy = new CaTissueUpdateSpecimenServiceInvocationStrategy(RETRY_COUNT,
                caTissueSpecimenClient, xsltTransformer);
    }

    /**
     * Tests createSpecimens using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void createSpecimens() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime());

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        EasyMock.expect(caTissueSpecimenClient.createSpecimens((String) EasyMock.anyObject())).andReturn(clientResult);
        EasyMock.replay(caTissueSpecimenClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueSpecimenStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueSpecimenStrategy.invoke(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests createSpecimens using the ServiceInvocationStrategy class for the failure scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void createSpecimensInvalidAvailableQuantity() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); 

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        clientResult.setDataChanged(false);
        clientResult.setOriginalData(getSpecimenXMLStr());

        final IntegrationException ie = new IntegrationException(IntegrationError._1085, new Throwable( // NOPMD
                "Available Quantity cannot be greater than the Initial Quantity"),
                "Available Quantity cannot be greater than the Initial Quantity");
        clientResult.setInvocationException(ie);

        EasyMock.expect(caTissueSpecimenClient.createSpecimens((String) EasyMock.anyObject())).andReturn(clientResult);
        EasyMock.replay(caTissueSpecimenClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueSpecimenStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueSpecimenStrategy.invoke(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests rollbackCreatedSpecimens using the ServiceInvocationStrategy class
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void rollbackCreatedSpecimens() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); 

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();

        EasyMock.expect(caTissueSpecimenClient.rollbackCreatedSpecimens((String) EasyMock.anyObject())).andReturn(
                clientResult);
        EasyMock.replay(caTissueSpecimenClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueSpecimenStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueSpecimenStrategy.rollback(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests updateSpecimens using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateSpecimens() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); 

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        EasyMock.expect(caTissueSpecimenClient.updateSpecimens((String) EasyMock.anyObject())).andReturn(clientResult);
        EasyMock.replay(caTissueSpecimenClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueUpdateSpecimenStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueUpdateSpecimenStrategy.invoke(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests updateSpecimens using the ServiceInvocationStrategy class for the failure scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void updateSpecimensFailure() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); 

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        clientResult.setDataChanged(false);
        clientResult.setOriginalData(getSpecimenXMLStr());
        final IntegrationException ie = new IntegrationException(IntegrationError._1085, new Throwable( // NOPMD
                "Available Quantity cannot be greater than the Initial Quantity"),
                "Available Quantity cannot be greater than the Initial Quantity");
        clientResult.setInvocationException(ie);

        EasyMock.expect(caTissueSpecimenClient.updateSpecimens((String) EasyMock.anyObject())).andReturn(clientResult);
        EasyMock.replay(caTissueSpecimenClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueUpdateSpecimenStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueUpdateSpecimenStrategy.invoke(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests rollbackUpdatedSpecimens using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void rollbackUpdatedSpecimens() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); 

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getSpecimenXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        EasyMock.expect(caTissueSpecimenClient.rollbackUpdatedSpecimens((String) EasyMock.anyObject())).andReturn(
                clientResult);
        EasyMock.replay(caTissueSpecimenClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getSpecimenXMLStr(), stTime, caTissueUpdateSpecimenStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueUpdateSpecimenStrategy
                .rollback(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    private ServiceInvocationMessage prepareServiceInvocationMessage(Long referenceMessageId, String message,
            Date startTime, StrategyIdentifier strategyIdentifier) {
        final ServiceInvocationMessage serviceInvocationMessage = new ServiceInvocationMessage();
        serviceInvocationMessage.setStrategyIdentifier(strategyIdentifier);
        final IHubMessage iHubMessage = new IHubMessage();
        iHubMessage.setStartTime(startTime);
        iHubMessage.setEndTime(new Date(new java.util.Date().getTime())); 
        iHubMessage.setRequest(message);
        iHubMessage.setReferenceMessageId(referenceMessageId);
        serviceInvocationMessage.setReferenceMessageId(referenceMessageId);
        serviceInvocationMessage.setMessage(iHubMessage);
        return serviceInvocationMessage;
    }

    private String getSpecimenXMLStr() {
        return getXMLString("CreateSpecimen_catissue.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueParticipantClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }

}
