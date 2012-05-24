package gov.nih.nci.integration.catissue.client;

import org.junit.Test;

public class CaTissueSpecimenIntegrationTest {

		
//	@Test
	public void createSpecimens() throws Exception{		
		CaTissueSpecimenClient caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");	
		caTissueSpecimenClient.createSpecimens(getInsertSpecimenXMLStr());
	
	}

	@Test
	public void updateSpecimens() throws Exception{		
		CaTissueSpecimenClient caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");			
		String retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenXMLStr());		
		System.out.println(retXML);	
	}
	
//	@Test
	public void rollbackSpecimens() throws Exception{		
		CaTissueSpecimenClient caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");		
		caTissueSpecimenClient.rollbackCreatedSpecimens(getRollbackSpecimenXMLStr());	
		
	}
	
	

	
	private String getInsertSpecimenXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen133</barcode><label>TolvenTestUser252TissueSpecimen133</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen134</barcode><label>TolvenTestUser252TissueSpecimen134</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";		
		
	}
	
	private String getUpdateSpecimenXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL1</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>5.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen133</barcode><label>TolvenTestUser252TissueSpecimen133</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>1.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen134</barcode><label>TolvenTestUser252TissueSpecimen134</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
	}
	
	private String getRollbackSpecimenXMLStr() {			
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>5.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen133</barcode><label>TolvenTestUser252TissueSpecimen133</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen134</barcode><label>TolvenTestUser252TissueSpecimen134</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";	
	}
	
	

}
