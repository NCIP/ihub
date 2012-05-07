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
