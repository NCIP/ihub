package gov.nih.nci.integration.catissue.client;

import org.junit.Test;

/**
 * This is the TestClass for registerConsent flow.
 * @author Rohit Gupta
 */

public class CaTissueConsentIntegrationTest {


	@Test
	public void registerConsents() throws Exception{			
		CaTissueConsentClient caTissueConsentClient = new CaTissueConsentClient("admin@admin.com", "Rohit123");	
		caTissueConsentClient.registerConsents(getRegisterConsentXMLStr());		
	}

//	@Test
	public void rollbackConsents() throws Exception{			
		CaTissueConsentClient caTissueConsentClient = new CaTissueConsentClient("admin@admin.com", "Rohit123");	
		String existingConsentString=caTissueConsentClient.getExistingConsents(getRegisterConsentXMLStr());
		caTissueConsentClient.rollbackConsentRegistration(existingConsentString);
	}
	
	private String getRegisterConsentXMLStr() {			
		
		return "<?xml version=\"1.0\" ?><consents><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen101</specimenLabel><consentTierStatus><consentTier><statement>This is a statement</statement></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol></consentDetails></consents>";
	}	

}