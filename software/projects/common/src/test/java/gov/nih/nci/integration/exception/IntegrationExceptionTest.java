/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.exception;

import static junit.framework.Assert.*;

import org.junit.Test;

public class IntegrationExceptionTest {
	
	@Test
	public void msgFormat() {
		Throwable t = new Throwable("test throwable");
		IntegrationException ie = 
			new IntegrationException(IntegrationError._1010, t, "P-id", "CP-id" );
		
		assertEquals("Participant P-id is not registered to study CP-id.", ie.getMessage());
	}
}
