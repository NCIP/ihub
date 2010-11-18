package gov.nih.nci.ihub.test;

import static org.junit.Assert.*;
import gov.nih.nci.ihub.writer.ncies.core.CoppaInvocationStrategy;

import org.junit.Test;

public class CoppaInvocationStrategyTest {

	@Test
	public void testInvokeGridServiceStringStringElementSubject() {
		CoppaInvocationStrategy coppaInvocationStrategy = new CoppaInvocationStrategy();
		coppaInvocationStrategy.setServiceUrl("https://ctms-services-po-3-1-integration.nci.nih.gov:1521/wsrf/services/cagrid/Person");
		coppaInvocationStrategy.setGridClientClassName("gov.nih.nci.coppa.services.entities.person.client.PersonClient");
		coppaInvocationStrategy.init();
		//coppaPersonStrategy.invokeGridService();
		//fail("Not yet implemented");
	}

}
