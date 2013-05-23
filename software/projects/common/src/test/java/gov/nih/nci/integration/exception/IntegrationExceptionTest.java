/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.exception;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
/**
 * 
 * @author Vinodh
 *
 */
public class IntegrationExceptionTest {

    /**
     * msgFormat
     */
    @Test
    public void msgFormat() {
        Throwable t = new Throwable("test throwable");// NOPMD
        IntegrationException ie = new IntegrationException(IntegrationError._1013, t, "P-id", "CP-id");
        assertEquals("Insufficient privileges to deregister participant P-id to study CP-id.", ie.getMessage());
    }
    
    /**
     * msgFormat
     */
    @Test
    public void msgTemplate() {
        Throwable t = new Throwable("test throwable");// NOPMD
        IntegrationException ie = new IntegrationException(IntegrationError._1013, t, null);
        assertEquals("Insufficient privileges to deregister participant %1$s to study %2$s.", ie.getMessage());
    }
}
