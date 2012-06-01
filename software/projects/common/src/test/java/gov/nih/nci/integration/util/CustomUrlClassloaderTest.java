package gov.nih.nci.integration.util;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;

public class CustomUrlClassloaderTest {

    @Test
    public void emptyCustomFolder() throws MalformedURLException, ClassNotFoundException {
        CustomUrlClassLoader ccl = new CustomUrlClassLoader("common-lib/");
        Assert.assertTrue(ccl.getURLs().length > 0);

        Assert.assertEquals(CustomUrlClassLoader.class, ccl
                .loadClass("gov.nih.nci.integration.util.CustomUrlClassLoader"));
    }
}
