/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.util;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;

public class CustomUrlClassloaderTest {

	@Test
	public void emptyCustomFolder() throws MalformedURLException, ClassNotFoundException {
		CustomUrlClassLoader ccl = new CustomUrlClassLoader("common-lib/");
		
		Assert.assertArrayEquals(new String[0], ccl.getURLs());
		
		Assert.assertEquals(CustomUrlClassLoader.class, ccl.loadClass("gov.nih.nci.integration.util.CustomUrlClassLoader"));
	}
}
