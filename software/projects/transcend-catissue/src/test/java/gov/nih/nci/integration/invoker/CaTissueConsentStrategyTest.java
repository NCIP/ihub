package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueConsentClient;
import gov.nih.nci.integration.catissue.CaTissueParticipantClientIntegrationTest;
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
 * This is the TestClass for ConsentStrategy class.
 * 
 * @author Rohit Gupta
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueConsentStrategyTest {

    private CaTissueConsentServiceInvocationStrategy caTissueConsentStrategy; // NOPMD
    private XSLTTransformer xsltTransformer;
    private CaTissueConsentClient caTissueConsentClient;
    private static final int RETRY_COUNT = 1;
    private static final Long REFMSGID = 12345L;

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueConsentStrategyTest.class);

    /**
     * To initialize the things
     * 
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    @Test
    @Before
    public void initialize() throws BeansException, MalformedURLException {
        caTissueConsentClient = EasyMock.createMock(CaTissueConsentClient.class);
        xsltTransformer = EasyMock.createMock(XSLTTransformer.class);
        caTissueConsentStrategy = new CaTissueConsentServiceInvocationStrategy(RETRY_COUNT, caTissueConsentClient,
                xsltTransformer);
    }

    /**
     * Tests registerConsents using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void registerConsents() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getRegisterConsentXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        EasyMock.expect(caTissueConsentClient.registerConsents((String) EasyMock.anyObject())).andReturn(clientResult);
        EasyMock.replay(caTissueConsentClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getRegisterConsentXMLStr(), stTime, caTissueConsentStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueConsentStrategy.invoke(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests registerConsents using the ServiceInvocationStrategy class for the failure scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void registerConsentsSpecimenNotExist() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getRegisterConsentXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        clientResult.setDataChanged(false);

        final IntegrationException ie = new IntegrationException(IntegrationError._1090, new Throwable( // NOPMD
                "Specimen for given LABEL doesn't exist"), "Specimen for given LABEL doesn't exist");
        clientResult.setInvocationException(ie);

        EasyMock.expect(caTissueConsentClient.registerConsents((String) EasyMock.anyObject())).andReturn(clientResult);
        EasyMock.replay(caTissueConsentClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getRegisterConsentXMLStr(), stTime, caTissueConsentStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueConsentStrategy.invoke(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
    }

    /**
     * Tests rollbackConsents using the ServiceInvocationStrategy class for the success scenario
     * 
     * @throws IntegrationException - IntegrationException
     * @throws JAXBException - JAXBException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void rollbackConsents() throws IntegrationException, JAXBException {
        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        xsltTransformer.transform(null, null, null);
        EasyMock.expectLastCall().andAnswer(new IAnswer() {

            public Object answer() {
                return getRegisterConsentXMLStr();
            }
        }).anyTimes();

        final ServiceInvocationResult clientResult = new ServiceInvocationResult();
        EasyMock.expect(caTissueConsentClient.rollbackConsents((String) EasyMock.anyObject())).andReturn(clientResult);
        EasyMock.replay(caTissueConsentClient);
        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(REFMSGID,
                getRegisterConsentXMLStr(), stTime, caTissueConsentStrategy.getStrategyIdentifier());
        final ServiceInvocationResult strategyResult = caTissueConsentStrategy.rollback(serviceInvocationMessage);
        Assert.assertNotNull(strategyResult);
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

    private String getRegisterConsentXMLStr() {
        return getXMLString("RegisterConsent.xml");
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
