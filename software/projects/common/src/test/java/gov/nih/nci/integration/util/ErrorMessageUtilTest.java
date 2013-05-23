/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
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
