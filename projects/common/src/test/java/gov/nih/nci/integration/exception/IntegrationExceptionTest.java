package gov.nih.nci.integration.exception;

import static junit.framework.Assert.*;

import org.junit.Test;

public class IntegrationExceptionTest {
	
	@Test
	public void msgFormat() {
		Throwable t = new Throwable("test throwable");
		IntegrationException ie = 
			new IntegrationException(IntegrationError._1012, t, "P-id", "CP-id" );
		
		assertEquals("Insufficient privileges to register participant P-id to caAERS CP-id.", ie.getMessage());
	}
}
