package gov.nih.nci.integration.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Marshals any object to xml based on its context
 * 
 * @author vinodh.rc@semanticbits.com
 * 
 */
public class ErrorMessageUtilTest {

    /**
     * Testcase to test the reading of error message file with the error message
     */
    @Test
    public void getMessage() {
        ErrorMessagesUtil.init("build/classes/");
        final String message = ErrorMessagesUtil.getMessage("1072");
        Assert.assertNotNull(message);
    }

}
