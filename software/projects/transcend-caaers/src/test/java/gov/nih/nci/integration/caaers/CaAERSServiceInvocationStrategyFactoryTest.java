package gov.nih.nci.integration.caaers;

import gov.nih.nci.integration.caaers.invoker.CaAERSServiceInvocationStrategyFactory;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for CaAERSServiceInvocationStrategyFactory
 * 
 * @author Vinodh
 * 
 */
public class CaAERSServiceInvocationStrategyFactoryTest {

    private static final Logger LOG = LoggerFactory.getLogger(CaAERSServiceInvocationStrategyFactoryTest.class);

    /**
     * Testcase for createCaAERSServiceInvocationStrategy
     */
    @Test
    public void createCaAERSServiceInvocationStrategy() {
        final File customLibLoc = new File("..\\caaers-client\\caaers-client-lib\\");
        final File distLoc = new File("..\\caaers-client\\build\\dist\\");
        final ServiceInvocationStrategy sris = CaAERSServiceInvocationStrategyFactory
                .createCaAERSRegistrationServiceInvocationStrategy(new String[] { customLibLoc.getAbsolutePath(),
                        distLoc.getAbsolutePath() }, "classpath*:applicationContext-caaers-client-test.xml");
        final ServiceInvocationStrategy suris = CaAERSServiceInvocationStrategyFactory
                .createCaAERSUpdateRegistrationServiceInvocationStrategy(new String[] { customLibLoc.getAbsolutePath(),
                        distLoc.getAbsolutePath() }, "classpath*:applicationContext-caaers-client-test.xml");

        // assertNotNull(sris);
        // assertNotNull(suris);

        final ServiceInvocationMessage msg = new ServiceInvocationMessage();
        msg.setStrategyIdentifier(StrategyIdentifier.CAEERS_CREATE_REGISTRATION);
        final IHubMessage iHubMessage = new IHubMessage();
        iHubMessage.setRequest(getPStr());
        final Date stTime = new Date(new java.util.Date().getTime());
        iHubMessage.setStartTime(stTime);
        msg.setMessage(iHubMessage);

        // final ServiceInvocationResult res = sris.invoke(msg);
        // assertNotNull(res);
        // assertTrue(res.isFault());
        // assertNotNull(res.getInvocationException());
    }

    /**
     * Testcase for createCaAERSAdverseEventServiceInvocationStrategy
     */
    @Test
    public void createCaAERSAdverseEventServiceInvocationStrategy() {
        final File customLibLoc = new File("..\\caaers-client\\caaers-client-lib\\");
        final File distLoc = new File("..\\caaers-client\\build\\dist\\");
        final ServiceInvocationStrategy saeis = CaAERSServiceInvocationStrategyFactory
                .createCaAERSAdverseEventServiceInvocationStrategy(new String[] { customLibLoc.getAbsolutePath(),
                        distLoc.getAbsolutePath() }, "classpath*:applicationContext-caaers-client-test.xml");
        // assertNotNull(saeis);
    }

    /**
     * Testcase for createCaAERSAdverseEventServiceInvocationStrategy
     */
    @Test
    public void createCaAERSAdverseEventServiceInvocationStrategyFailure() {
        final File customLibLoc = new File("..\\caaers-client\\caaers-client-lib\\");
        final File distLoc = new File("..\\caaers-client\\build\\dist\\");
        CaAERSServiceInvocationStrategyFactory.setInitStatus(null);
        final ServiceInvocationStrategy saeis = CaAERSServiceInvocationStrategyFactory
                .createCaAERSAdverseEventServiceInvocationStrategy(new String[] { customLibLoc.getAbsolutePath(),
                        distLoc.getAbsolutePath() }, "classpath*:applicationContext-caaers-client-test.xml");
        // assertNull(saeis);
    }

    private String getPStr() {
        return getXMLString("Registration_MBC.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaAERSServiceInvocationStrategyFactoryTest.class.getClassLoader().getResourceAsStream(
                "payloads/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
