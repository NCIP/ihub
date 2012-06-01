package gov.nih.nci.integration.exception;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class IntegrationExceptionTest {

	@Test
	public void msgFormat() {
		Throwable t = new Throwable("test throwable");
		IntegrationException ie = new IntegrationException(
				IntegrationError._1013, t, "P-id", "CP-id");

		assertEquals(
				"Insufficient privileges to deregister participant P-id to study CP-id.",
				ie.getMessage());
	}
}
