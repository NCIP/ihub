package gov.nih.nci.integration.util;

import org.junit.Assert;
import org.junit.Test;
/**
 * Testcase for CustomClasspathXmlApplicationContext loader
 * @author Vinodh
 *
 */
public class CustomClasspathXmlApplicationContextIntegrationTest {

    /**
     * Testcase for loadSpringWithCustomClasspath
     */
    @Test
    public void loadSpringWithCustomClasspath() {
        try {
            String[] customLocs = { "build/test-classes/custom-dir/" };
            CustomClasspathXmlApplicationContext ctx = new CustomClasspathXmlApplicationContext(customLocs,
                    "classpath:applicationContext-common-test.xml");

            Assert.assertTrue(ctx.getResource("testloadfile.txt").exists());
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            e.printStackTrace();
            Assert.fail("failed loading config from custom location!");
        }
    }
}
