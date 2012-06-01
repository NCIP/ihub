package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is the TestClass for registerConsent flow (from Wrappper Client).
 * 
 * @author Rohit Gupta
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueConsentClientIntegrationTest {

	@Autowired
	private CaTissueConsentClient caTissueConsentClient;

	@Test
	public void registerConsents() {
		ServiceInvocationResult svc = caTissueConsentClient
				.registerConsents(getRegisterConsentXMLStr());

		if (svc.isDataChanged() && svc.getInvocationException() != null) {
			caTissueConsentClient.rollbackConsents(svc.getOriginalData()
					.toString());
		}

	}

	private String getRegisterConsentXMLStr() {
		return "<?xml version=\"1.0\" ?><consents><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen111</specimenLabel><consentTierStatus><consentTier><id>1</id></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><id>2</id></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></consentDetails><consentDetails><collectionProtocolEvent>CPL2</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen102</specimenLabel><consentTierStatus><consentTier><id>1</id></consentTier><status>Not Specified</status></consentTierStatus><consentTierStatus><consentTier><id>2</id></consentTier><status>Withdrawn</status></consentTierStatus></consentData><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></consentDetails></consents>";
	}

}
