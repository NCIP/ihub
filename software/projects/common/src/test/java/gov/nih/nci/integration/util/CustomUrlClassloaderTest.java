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
/**
 * Testclass for Custom Url Classloader
 * @author Vinodh
 *
 */
public class CustomUrlClassloaderTest {

    /**
     * Testcase for emptyCustomFolder
     * @throws MalformedURLException - MalformedURLException
     * @throws ClassNotFoundException - ClassNotFoundException
     */
    @Test
    public void emptyCustomFolder() throws MalformedURLException, ClassNotFoundException {
        CustomUrlClassLoader ccl = new CustomUrlClassLoader("./build/test-classes/custom-dir/");
        Assert.assertTrue(ccl.getURLs().length > 0);

        Assert.assertEquals(CustomUrlClassLoader.class, ccl
                .loadClass("gov.nih.nci.integration.util.CustomUrlClassLoader"));
    }
}
