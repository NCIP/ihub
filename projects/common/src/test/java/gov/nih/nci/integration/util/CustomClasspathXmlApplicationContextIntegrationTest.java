package gov.nih.nci.integration.util;

import org.junit.Assert;
import org.junit.Test;

public class CustomClasspathXmlApplicationContextIntegrationTest {

	@Test
	public void loadSpringWithCustomClasspath() {
		try {
			String[] customLocs = { "build/test-classes/custom-dir/" };
			CustomClasspathXmlApplicationContext ctx = new CustomClasspathXmlApplicationContext(
					customLocs, "classpath:applicationContext-common-test.xml");

			Assert.assertTrue(ctx.getResource("testloadfile.txt").exists());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed loading config from custom location!");
		}
	}
}
