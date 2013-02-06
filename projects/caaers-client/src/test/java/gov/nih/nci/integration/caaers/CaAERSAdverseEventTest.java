package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventManagementServiceInterface;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CreateProvisionalAdverseEvents;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CreateProvisionalAdverseEventsResponse;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.DeleteAdverseEvent;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.DeleteAdverseEventResponse;
import gov.nih.nci.integration.exception.IntegrationException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.easymock.classextension.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This client test code only tests the client communication and does code coverage for AdverseEvent.
 * 
 * @author Rohit Gupta
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-caaers-client-test.xml")
public class CaAERSAdverseEventTest {

    private AdverseEventManagementServiceInterface client;
    private CaAERSAdverseEventServiceWSClient caAERSAdverseEventServiceWSClient;
    private static final Logger LOG = LoggerFactory.getLogger(CaAERSAdverseEventTest.class);

    /**
     * To initialize the things
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Before
    public void initialize() throws IntegrationException {
        client = EasyMock.createMock(AdverseEventManagementServiceInterface.class);
        caAERSAdverseEventServiceWSClient = new CaAERSAdverseEventServiceWSClient(client);
    }

    /**
     * TestCase for Creating Adverse Event in caAERS
     */
    @Test
    public void createAdverseEvent() {
        final String adverseEventXMLStr = getAdverseEventXMLStr();
        try {
            final CreateProvisionalAdverseEventsResponse response = new CreateProvisionalAdverseEventsResponse();
            EasyMock.expect(
                    client.createProvisionalAdverseEvents((CreateProvisionalAdverseEvents) org.easymock.EasyMock
                            .anyObject())).andReturn(response);
            EasyMock.replay(client);

            caAERSAdverseEventServiceWSClient.createProvisionalAdverseEvents(adverseEventXMLStr);

        } catch (Exception e) {
            Assert.fail("Exception occured while calling createAdverseEvent. " + e);
        }
    }

    /**
     * TestCase for Updating the Adverse Event in caAERS
     */
    @Test
    public void updateAdverseEvent() {
        try {

            final CreateProvisionalAdverseEventsResponse response = new CreateProvisionalAdverseEventsResponse();
            EasyMock.expect(
                    client.createProvisionalAdverseEvents((CreateProvisionalAdverseEvents) org.easymock.EasyMock
                            .anyObject())).andReturn(response);
            EasyMock.replay(client);

            caAERSAdverseEventServiceWSClient.createProvisionalAdverseEvents(getAdverseEventXMLStr());
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling updateAdverseEvent. " + e);
        }

    }

    /**
     * TestCase for Deleting/Rollbacking the Adverse Event in caAERS
     */
    @Test
    public void deleteAdverseEvent() {
        try {
            final DeleteAdverseEventResponse response = new DeleteAdverseEventResponse();
            EasyMock.expect(client.deleteAdverseEvent((DeleteAdverseEvent) org.easymock.EasyMock.anyObject()))
                    .andReturn(response);
            EasyMock.replay(client);

            caAERSAdverseEventServiceWSClient.deleteAdverseEvent(getAdverseEventXMLStr());
        } catch (JAXBException e) {
            Assert.fail("JAXBException occured while calling deleteAdverseEvent. " + e);
        }
    }

    private String getAdverseEventXMLStr() {
        return getXMLString("AdverseEvent_caaers.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaAERSAdverseEventTest.class.getClassLoader().getResourceAsStream(
                "payloads/adverseevent/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }

}
