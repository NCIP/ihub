package gov.nih.nci.integration.catissue.client;

import static org.junit.Assert.*;

import org.junit.Test;

public class CaTissueSpecimenIntegrationTest {	
	
//	@Test
	public void createInvalidCollectionProtocolSpecimens(){		
		CaTissueSpecimenClient caTissueSpecimenClient ;	
		String existXML = null;
		String createdXML="CREATED";
		try {
			caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");	
			existXML= caTissueSpecimenClient.isSpecimensExist(getInsertInvalidCollectionProtocolXMLStr());
			caTissueSpecimenClient.createSpecimens(getInsertInvalidCollectionProtocolXMLStr());
		} catch (Exception e) {
			existXML = null;
			createdXML=null;
		}
		assertNull(existXML);	
		assertNull(createdXML);	
	}
	
	
//	@Test
	public void createInvalidSpecimenClass(){		
		CaTissueSpecimenClient caTissueSpecimenClient ;	
		String existXML = null;
		String createdXML="CREATED";
		try {
			caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");	
			existXML= caTissueSpecimenClient.isSpecimensExist(getInsertInvalidSpecimenClassXMLStr());
			caTissueSpecimenClient.createSpecimens(getInsertInvalidSpecimenClassXMLStr());
		} catch (Exception e) {
			existXML = null;
			createdXML=null;
		}
		assertNull(existXML);	
		assertNull(createdXML);	
	}
	
	
//	@Test
	public void createInvalidAvailableQuantity(){		
		CaTissueSpecimenClient caTissueSpecimenClient ;	
		String existXML = null;
		String createdXML="CREATED";
		try {
			caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");	
			existXML= caTissueSpecimenClient.isSpecimensExist(getInsertInvalidAvailableQuantityXMLStr());
			caTissueSpecimenClient.createSpecimens(getInsertInvalidAvailableQuantityXMLStr());
		} catch (Exception e) {
			existXML = null;
			createdXML=null;
		}
		assertNull(existXML);	
		assertNull(createdXML);	
	}
	
//	@Test
	public void createInvalidSpecimenType(){		
		CaTissueSpecimenClient caTissueSpecimenClient ;	
		String existXML = null;
		String createdXML="CREATED";
		try {
			caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");	
			existXML= caTissueSpecimenClient.isSpecimensExist(getInsertInvalidSpecimenTypeXMLStr());
			caTissueSpecimenClient.createSpecimens(getInsertInvalidSpecimenTypeXMLStr());
		} catch (Exception e) {
			existXML = null;
			createdXML=null;
		}
		assertNull(existXML);	
		assertNull(createdXML);	
	}
	
	
//	@Test
	public void createInvalidTissueSide(){		
		CaTissueSpecimenClient caTissueSpecimenClient ;	
		String existXML = null;
		String createdXML="CREATED";
		try {
			caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");	
			existXML= caTissueSpecimenClient.isSpecimensExist(getInsertInvalidTissueSideXMLStr());
			caTissueSpecimenClient.createSpecimens(getInsertInvalidTissueSideXMLStr());
		} catch (Exception e) {
			existXML = null;
			createdXML=null;
		}
		assertNull(existXML);	
		assertNull(createdXML);	
	}
	
	
//	@Test
	public void createInvalidTissueSite(){		
		CaTissueSpecimenClient caTissueSpecimenClient ;	
		String existXML = null;
		String createdXML="CREATED";
		try {
			caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");	
			existXML= caTissueSpecimenClient.isSpecimensExist(getInsertInvalidTissueSiteXMLStr());
			caTissueSpecimenClient.createSpecimens(getInsertInvalidTissueSiteXMLStr());
		} catch (Exception e) {
			existXML = null;
			createdXML=null;
		}
		assertNull(existXML);	
		assertNull(createdXML);	
	}
	
	
	@Test
	public void createInvalidPathologicalStatus(){		
		CaTissueSpecimenClient caTissueSpecimenClient ;	
		String existXML = null;
		String createdXML="CREATED";
		try {
			caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");	
			existXML= caTissueSpecimenClient.isSpecimensExist(getInsertInvalidPathologicalStatusXMLStr());
			caTissueSpecimenClient.createSpecimens(getInsertInvalidPathologicalStatusXMLStr());
		} catch (Exception e) {
			existXML = null;
			createdXML=null;
		}
		assertNull(existXML);	
		assertNull(createdXML);	
	}
	
//	@Test
	public void createSpecimens(){		
		CaTissueSpecimenClient caTissueSpecimenClient;
		String existXML = null;
		String createdXML="CREATED";
		try {
			caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");
			existXML= caTissueSpecimenClient.isSpecimensExist(getInsertExistingSpecimenXMLStr());
			caTissueSpecimenClient.createSpecimens(getInsertSpecimenXMLStr());
		} catch (Exception e) {
			existXML = null;
			createdXML=null;
		}	
		
		assertNull(existXML);	
		assertNotNull(createdXML); 	
	}

	
//	@Test
	public void createExistingSpecimens(){		
		CaTissueSpecimenClient caTissueSpecimenClient ;	
		String existXML = null;
		String createdXML="CREATED";
		try {
			caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");	
			existXML= caTissueSpecimenClient.isSpecimensExist(getInsertExistingSpecimenXMLStr());
			caTissueSpecimenClient.createSpecimens(getInsertExistingSpecimenXMLStr());
		} catch (Exception e) {
			existXML = null;
			createdXML=null;
		}
		assertNull(existXML);	
		assertNull(createdXML);	
	}
	
//	@Test
	public void updateSpecimens() throws Exception{		
		CaTissueSpecimenClient caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");			
		String retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenXMLStr());		
		
	}
	
//	@Test
	public void updateSpecimensInvalidCollectionProtocol(){		
		CaTissueSpecimenClient caTissueSpecimenClient;
		String retXML = null;
		try {
			caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");
			retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenInvalidAvailableQtyXMLStr());			
		} catch (Exception e) {
		}		
		assertNull(retXML);		
	}
	
//	@Test
	public void updateSpecimensSpecimenClassChange(){		
		CaTissueSpecimenClient caTissueSpecimenClient;
		String retXML = null;
		try {
			caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");
			retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenClassChangeXMLStr());			
		} catch (Exception e) {
		}		
		assertNull(retXML);		
	}
	
	
	
//	@Test
	public void rollbackSpecimens() throws Exception{		
		CaTissueSpecimenClient caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");		
		caTissueSpecimenClient.rollbackCreatedSpecimens(getRollbackSpecimenXMLStr());	
		
	}
	

	
	private String getInsertSpecimenXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";		
	}
	
	private String getInsertExistingSpecimenXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen173</barcode><label>TolvenTestUser252TissueSpecimen173</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen174</barcode><label>TolvenTestUser252TissueSpecimen174</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";		
	}
	
	private String getInsertInvalidCollectionProtocolXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen175</barcode><label>TolvenTestUser252TissueSpecimen175</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6486</title><shortTitle>6486</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen176</barcode><label>TolvenTestUser252TissueSpecimen176</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6486</title><shortTitle>6486</shortTitle></collectionProtocol></specimenDetail></specimens>";		
	}
	
	private String getInsertInvalidSpecimenClassXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"Tissue1234Specimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue1234</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";		
	}
	
	private String getInsertInvalidAvailableQuantityXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>40.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";		
	}
	
	private String getInsertInvalidSpecimenTypeXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue123</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";		
	}
	
	private String getInsertInvalidTissueSideXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right123</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";		
	}
	
	private String getInsertInvalidTissueSiteXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta123</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";		
	}
	
	private String getInsertInvalidPathologicalStatusXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant123</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";		
	}
	
	
	private String getUpdateSpecimenXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL1</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>5.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen163</barcode><label>TolvenTestUser252TissueSpecimen163</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>1.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen160</barcode><label>TolvenTestUser252TissueSpecimen160</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";	
	}
	
	private String getUpdateSpecimenInvalidAvailableQtyXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>400.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";	
	}
	
	private String getUpdateSpecimenClassChangeXMLStr() {				
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";	
	}
	
	private String getRollbackSpecimenXMLStr() {			
		return"<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL1</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>5.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen163</barcode><label>TolvenTestUser252TissueSpecimen163</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>1.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen160</barcode><label>TolvenTestUser252TissueSpecimen160</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";	
	}
	
	

}
