package gov.nih.nci.integration.util;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;

public class CustomUrlClassloaderTest {

	@Test
	public void emptyCustomFolder() throws MalformedURLException, ClassNotFoundException {
		CustomUrlClassLoader ccl = new CustomUrlClassLoader("common-lib/");
		Assert.assertEquals(1, ccl.getURLs().length);
		
		Assert.assertEquals(CustomUrlClassLoader.class, ccl.loadClass("gov.nih.nci.integration.util.CustomUrlClassLoader"));
	}
}
