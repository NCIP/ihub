package gov.nih.nci.integration.catissue.client;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.DisposalEventParameters;
import edu.wustl.catissuecore.domain.FluidSpecimen;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCharacteristics;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.domain.TissueSpecimen;
import edu.wustl.catissuecore.domain.User;
import gov.nih.nci.integration.catissue.domain.SpecimenDetail;
import gov.nih.nci.integration.catissue.domain.Specimens;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * This is the client class for Specimen Flow. It provide operation like CreateSpecimen, UpdateSpecimen, RollbackSpecimen
 * @author Rohit Gupta
 */

public class CaTissueSpecimenClient {

	private static Logger LOG = LoggerFactory.getLogger(CaTissueSpecimenClient.class);
	private final CaTissueAPIClientWithRegularAuthentication caTissueAPIClient;	
	private XStream xStream = new XStream(new StaxDriver());
	
	private static final String ACTIVITY_STATUS_DISABLED = "Disabled";
	
	
	public CaTissueSpecimenClient(String loginName, String password) throws Exception
	{
		super();		 
		Thread.currentThread().setContextClassLoader(CaTissueSpecimenClient.class.getClassLoader());		
		this.caTissueAPIClient = new CaTissueAPIClientWithRegularAuthentication(loginName, password);
		
		xStream.alias("specimens", Specimens.class);
		xStream.alias("participant", Participant.class);
		xStream.alias("specimenDetail", SpecimenDetail.class);
		xStream.alias("specimen", Specimen.class);
		xStream.alias("TissueSpecimen", TissueSpecimen.class);
		xStream.alias("FluidSpecimen", FluidSpecimen.class);
		xStream.alias("collectionProtocol", CollectionProtocol.class);		
		xStream.addImplicitCollection(Specimens.class, "specimenDetailList");
	}
	
	/**
	 * This method is used to check if Specimen(s) already exist in caTissue
	 * @throws ApplicationException - Throws exception if Specimen already exist
	 */
	public String isSpecimensExist(String specimenListXMLStr) throws ApplicationException{
		
		LOG.debug("Inside isSpecimensExist...The Incoming XML for isSpecimensExist() is --> " + specimenListXMLStr);
//		System.out.println("Inside isSpecimensExist...The Incoming XML for isSpecimensExist() is --> " + specimenListXMLStr);
		
		// Parse the incoming XML String. The returned object will contain data from the incoming specimens XML
		Specimens specimens = parseSpecimenListXML(specimenListXMLStr);

		return isSpecimensAlreadyExist(specimens);
	}
	
	
	/**
	 * This method is used to fetch the Specimen(s) details for given specimen XMLString
	 * @throws ApplicationException
	 */
	public String getExistingSpecimens(String specimenListXMLStr) throws ApplicationException{
		
		LOG.debug("Inside getExistingSpecimens...The Incoming XML for getExistingSpecimens() is --> " + specimenListXMLStr);
//		System.out.println("Inside getExistingSpecimens...The Incoming XML for getExistingSpecimens() is --> " + specimenListXMLStr);
		
		// Parse the incoming XML String. The returned object will contain data from the incoming consents XML
		Specimens incomingSpecimens = parseSpecimenListXML(specimenListXMLStr);

		// Fetch the existing Consents
		Specimens exitingSpecimens = fetchExistingSpecimens(incomingSpecimens);
		
		return xStream.toXML(exitingSpecimens);
	}
	
	
	
	/**
	 * Creates specimens in caTissue
	 * @param specimenListXMLStr -- The XML string for creating the bio-specimen which may contain multiple specimens.
	 */
	public String createSpecimens(String specimenListXMLStr) throws ApplicationException{
		
		LOG.debug("Inside CaTissueSpecimenClient...The Incoming XML for createSpecimens() is --> " + specimenListXMLStr);		
//		System.out.println("Inside CaTissueSpecimenClient...The Incoming XML for createSpecimens() is --> " + specimenListXMLStr);
		
		// Parse the incoming XML String. The returned object will contain data from the incoming specimens XML
		Specimens specimens = parseSpecimenListXML(specimenListXMLStr);
		
		// perform the actual logic to create the Specimens.. Also do the rollback, if required
		performCreateSpecimens(specimens);		
	
		//Returning NULL here as we don't need the returned values at the moment.
		// We can return the list of Created Specimen, if required.
		return null;
	}
	
	
	/**
	 * Updates specimens in caTissue
	 * @param specimenListXMLStr -- The XML string for creating the specimens which may contain multiple specimens.
	 */
	public String updateSpecimens(String specimenListXMLStr) throws ApplicationException{
		
		LOG.debug("Inside CaTissueSpecimenClient... updateSpecimens()..The Incoming XML is --> " + specimenListXMLStr);
//		System.out.println("Inside CaTissueSpecimenClient... updateSpecimens()..The Incoming XML is --> " + specimenListXMLStr);
		
		// This object contain data from the incoming specimens xml
		Specimens specimens = parseSpecimenListXML(specimenListXMLStr);
		
		// perform the actual logic to Updating the Specimens.. 	
		List<Specimen> existingSpecimenList = performUpdateSpecimens(specimens);
		
		// Copy the exiting Specimen and return in the form of XML
		return xStream.toXML(copyFromExistingSpecimen(existingSpecimenList));
		
	}
	
	
	/**
	 * This method is used to Rollback the specimen changes for createSpecimen flow
	 */
	public String rollbackCreatedSpecimens(String specimenListXMLStr) throws ApplicationException{
		
		LOG.debug("Inside CaTissueSpecimenClient... rollbackCreatedSpecimens()..The Incoming XML is --> " + specimenListXMLStr);		
//		System.out.println("Inside CaTissueSpecimenClient Impl Class... rollbackSpecimens()..The Incoming XML is --> " + specimenListXMLStr);
		
		// This object contain data from the incoming specimens xml
		Specimens specimens = parseSpecimenListXML(specimenListXMLStr);
		
		// write a method which will rollback the things...		
		performRollbackCreatedSpecimens(specimens);

		return null;
	}
	
	
	
	/**
	 * This method is used to Rollback the specimen changes for updateSpecimen flow
	 * @throws ApplicationException
	 */
	public String rollbackUpdatedSpecimens(String specimenListXMLStr) throws ApplicationException{
		
		LOG.debug("Inside CaTissueSpecimenClient... rollbackUpdatedSpecimens()..The Incoming XML is --> " + specimenListXMLStr);		
//		System.out.println("Inside CaTissueSpecimenClient Impl Class... rollbackUpdatedSpecimens()..The Incoming XML is --> " + specimenListXMLStr);
		
		// This object contain data from the incoming specimens xml
		Specimens specimens = parseSpecimenListXML(specimenListXMLStr);
		
		// write a method which will rollback the things...		
		performRollbackUpdatedSpecimens(specimens);

		return null;
	}
	
	

	
	/**
	 * This method is used to parse the incoming XML string and populate the 'Specimens' object
	 * @param specimenListXMLStr
	 * @return
	 */
	private Specimens parseSpecimenListXML(String specimenListXMLStr) {		
		Specimens specimens = (Specimens) xStream.fromXML(new StringReader(specimenListXMLStr));					
		return specimens;
	}
	
	
	private String isSpecimensAlreadyExist(Specimens specimens) throws ApplicationException{
		List<SpecimenDetail> specimenDetailList = specimens.getSpecimenDetailList();
		Iterator<SpecimenDetail> specimenDetailItr = specimenDetailList.iterator();
		while(specimenDetailItr.hasNext()){
			SpecimenDetail specimenDetail = specimenDetailItr.next();
			String specimenLabel = specimenDetail.getSpecimen().getLabel();
			Specimen existingSpecimen= getExistingSpecimen(specimenLabel);
			if(existingSpecimen!=null){
				throw new ApplicationException( "Submission failed since a Specimen with the same LABEL already exists.");
			}
			
		}		
		return null;
	}
	
	
	private Specimens fetchExistingSpecimens(Specimens incomingSpecimens ) throws ApplicationException{
		List<Specimen> existingSpecimenList = new ArrayList<Specimen>();
		List<SpecimenDetail> specimenDetailList = incomingSpecimens.getSpecimenDetailList();
		Iterator<SpecimenDetail> specimenDetailItr = specimenDetailList.iterator();
		while(specimenDetailItr.hasNext()){
			SpecimenDetail specimenDetail = specimenDetailItr.next();
			Specimen existingSpecimen= getExistingSpecimen(specimenDetail.getSpecimen().getLabel());
			// check if the request data is correct by doing validation checks
			if(! isUpdateSpecimenRequestDataValid(specimenDetail, existingSpecimen)){
				throw new ApplicationException("UpdateSpecimen Request Failed for Label"+ specimenDetail.getSpecimen().getLabel() +" and exception is COLLECTION_PROTOCOL or COLLECTION_PROTOCOL_EVENT NOT MATCHING with Existing Specimen");
			}
			
			existingSpecimenList.add(existingSpecimen);
		}
	
		return copyFromExistingSpecimen(existingSpecimenList);
	}
	
	
	/**
	 * This method has the code/logic to call the createSpecimen.
	 * @param specimens to be created
	 */
	private void performCreateSpecimens(Specimens specimens) throws ApplicationException{	
			
		List<SpecimenDetail> specimenDetailList = specimens.getSpecimenDetailList();
		Iterator<SpecimenDetail> specimenDetailItr = null;
		Specimen specimen = null;
		
		for(specimenDetailItr = specimenDetailList.iterator(); specimenDetailItr.hasNext();)
		{
			SpecimenDetail specimenDetail = null;
			specimenDetail = (SpecimenDetail)specimenDetailItr.next();	
			CollectionProtocol cp = specimenDetail.getCollectionProtocol();			
			specimen= specimenDetail.getSpecimen();				
			boolean scgFound = false;
			List<SpecimenCollectionGroup> scgList = getSpecimenCollectionGroupList(specimenDetail);
			
			if ((scgList != null) && (scgList.size() > 0)) {				
				for (SpecimenCollectionGroup scg : scgList) {					
					CollectionProtocol cpObj = scg.getCollectionProtocolRegistration().getCollectionProtocol();					
					if (cpObj.getTitle().equals(cp.getTitle())) {
						scgFound= true; // Participant has a SpecimenCollectionGroup under the given protocol
						specimen.setSpecimenCollectionGroup(scg);	
						break;
					}
				}
			}
						
			if(scgFound== false){
				// throw exception
				LOG.error("Specimen Collection Group was found in caTissue for Label " + specimen.getLabel());
				throw new ApplicationException( "Specimen_Collection_Group_Not_Found");
			}
			
			try{
				// method call to createSpecimen
				createSpecimen(specimen);
			}catch(Exception e){
				throw new ApplicationException("CreateSpecimen Failed for Label"+ specimen.getLabel() +" and exception is " +e.getCause());				
			}						
		}		
	}
	
		
	private Specimen createSpecimen(Specimen specimen) throws ApplicationException{
		return caTissueAPIClient.insert(specimen);
	}

	
	/**
	 * This method has the code/logic to Update the Specimens.
	 * @throws Exception
	 */
	private List<Specimen> performUpdateSpecimens(Specimens specimens) throws ApplicationException {
		
		List<Specimen> existSpecimenList = new ArrayList<Specimen>();
		
		List<SpecimenDetail> specimenDetailList = specimens.getSpecimenDetailList();
		Iterator<SpecimenDetail> specimenDetailItr = null;
		SpecimenDetail specimenDetail = null;
		
		try{
			for(specimenDetailItr = specimenDetailList.iterator(); specimenDetailItr.hasNext();)
			{			
				specimenDetail = specimenDetailItr.next();
				Specimen incomingSpecimen = specimenDetail.getSpecimen();
				
				// Get the corresponding existing Specimen using the Label 
				Specimen existingSpecimen = getExistingSpecimen(incomingSpecimen.getLabel());					
				incomingSpecimen.setId(existingSpecimen.getId());					
				incomingSpecimen.setSpecimenCollectionGroup(existingSpecimen.getSpecimenCollectionGroup());//Specimen Collection Group is required.				
				incomingSpecimen.setLineage(existingSpecimen.getLineage());//Lineage should not be changed while updating the specimen		
				incomingSpecimen.getSpecimenCharacteristics().setId(existingSpecimen.getSpecimenCharacteristics().getId());	// The given object has a null identifier: SpecimenCharacteristics	
				
				updateSpecimen(incomingSpecimen);			
				
				// setting the existing Specimen, which will be required in case of rollback
				existSpecimenList.add(existingSpecimen);
			}
		}catch(Exception e){			
			throw new ApplicationException("UpdateSpecimen Failed for Label"+ specimenDetail.getSpecimen().getLabel() +" and exception is " +e.getCause());	
		}
		
		
		return existSpecimenList;
	}
	
	
	private boolean isUpdateSpecimenRequestDataValid(SpecimenDetail inSpecimenDetail, Specimen existingSpecimen){
		boolean hasValidData = true;
		String inCPE= inSpecimenDetail.getCollectionProtocolEvent();
		String existCPE= existingSpecimen.getSpecimenCollectionGroup().getCollectionProtocolEvent().getCollectionPointLabel();
		String inCP = inSpecimenDetail.getCollectionProtocol().getTitle();
		String existCP = existingSpecimen.getSpecimenCollectionGroup().getCollectionProtocolEvent().getCollectionProtocol().getTitle();
		
		if( ! inCPE.equalsIgnoreCase(existCPE) || !inCP.equalsIgnoreCase(existCP)){
			hasValidData = false;
		}		
		
		return hasValidData;
	}
	
	private void performRollbackUpdatedSpecimens(Specimens specimens){
//		System.out.println("Inside Specimen Client Impl... Rollback...");
		
		List<SpecimenDetail> specimenDetailList = specimens.getSpecimenDetailList();
		Iterator<SpecimenDetail> specimenDetailItr = specimenDetailList.iterator();
		
		while(specimenDetailItr.hasNext()){
			SpecimenDetail specimenDetail = specimenDetailItr.next();
			Specimen existingSpecimen = specimenDetail.getSpecimen();
			try {
				updateSpecimen(existingSpecimen);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	/**
	 * This method is used to get a specimen on the basis of the Label
	 * @param label
	 * @return
	 * @throws ApplicationException
	 */
	private Specimen getExistingSpecimen(String label) throws ApplicationException{		
		Specimen specimen = new Specimen();
		specimen.setLabel(label);// set the cdmsSpecimenId		
		// get the specimen, corresponding to the cdmsSpecimenId 
		specimen = caTissueAPIClient.searchById(Specimen.class, specimen);		
		return specimen;		
	}
	
	
	private Specimen updateSpecimen(Specimen specimen) throws ApplicationException{
		return caTissueAPIClient.update(specimen);
	}
	
	
	
	private Specimens copyFromExistingSpecimen(List<Specimen> existingSpecimenList){		

		Specimens existingSpecimens = new Specimens();
		Iterator<Specimen> specimenItr = null;				
		for(specimenItr = existingSpecimenList.iterator(); specimenItr.hasNext();)
		{	
			Specimen existingSpecimen =specimenItr.next(); 
			Specimen specimen = null;	
			
			if("Tissue".equalsIgnoreCase(existingSpecimen.getSpecimenClass())){
				specimen= new TissueSpecimen();
			}else if("Fluid".equalsIgnoreCase(existingSpecimen.getSpecimenClass())){
				specimen = new FluidSpecimen();
			}
					
			specimen.setId(existingSpecimen.getId());
			specimen.setInitialQuantity(existingSpecimen.getInitialQuantity());
			specimen.setPathologicalStatus(existingSpecimen.getPathologicalStatus());
			specimen.setSpecimenClass(existingSpecimen.getSpecimenClass().trim());
			specimen.setSpecimenType(existingSpecimen.getSpecimenType());			
			specimen.setActivityStatus(existingSpecimen.getActivityStatus());
			specimen.setAvailableQuantity(existingSpecimen.getAvailableQuantity());
			specimen.setBarcode(existingSpecimen.getBarcode());
			specimen.setLabel(existingSpecimen.getLabel());
			
			SpecimenCollectionGroup specimenCollectionGroup = new SpecimenCollectionGroup();			
			specimenCollectionGroup.setId(existingSpecimen.getSpecimenCollectionGroup().getId());
			specimen.setSpecimenCollectionGroup(specimenCollectionGroup);

			SpecimenCharacteristics chars = new SpecimenCharacteristics();
			chars.setTissueSide(existingSpecimen.getSpecimenCharacteristics().getTissueSide());
			chars.setTissueSite(existingSpecimen.getSpecimenCharacteristics().getTissueSite());
			specimen.setSpecimenCharacteristics(chars);
			specimen.getSpecimenCharacteristics().setId(existingSpecimen.getSpecimenCharacteristics().getId());	
			
			specimen.setLineage(existingSpecimen.getLineage());			
			
			specimen.setIsAvailable(existingSpecimen.getIsAvailable());
			specimen.setCollectionStatus(existingSpecimen.getCollectionStatus());
			
			SpecimenDetail specimenDetail = new SpecimenDetail();
			specimenDetail.setSpecimen(specimen);
			
			existingSpecimens.add(specimenDetail);
			
		}
		
		return existingSpecimens;
	}
	
	private void performRollbackCreatedSpecimens(Specimens specimens) throws ApplicationException{		
		List<SpecimenDetail> specimenDetailList = specimens.getSpecimenDetailList();
		Iterator<SpecimenDetail> specimenDetailItr = specimenDetailList.iterator();
		while(specimenDetailItr.hasNext()){
			SpecimenDetail specimenDetail = specimenDetailItr.next();
			String specimenLabel = specimenDetail.getSpecimen().getLabel();
			Specimen existingSpecimen;
			try {
				existingSpecimen = getExistingSpecimen(specimenLabel);
			} catch (ApplicationException e) {
				return;
			}
			
			softDeleteSpecimen(existingSpecimen);
		}
	}
	
	
	private void softDeleteSpecimen(Specimen existingSpecimen) throws ApplicationException{
		// First change the Label of the Specimen to some dummy value.. like "DELETED_Label_+Timestamp" 
        Specimen updatedSpecimen = updateSpecimenLabel(existingSpecimen);
        
        // Then set the Specimen to Disabled
		DisposalEventParameters disposalEventParameters = new  DisposalEventParameters();	        
		updatedSpecimen.setActivityStatus(ACTIVITY_STATUS_DISABLED);      
        disposalEventParameters.setSpecimen(updatedSpecimen);
        disposalEventParameters.setActivityStatus(ACTIVITY_STATUS_DISABLED);
        disposalEventParameters.setComment("Rollback the Specimen");
        disposalEventParameters.setReason("Rollback the Specimen");
        disposalEventParameters.setTimestamp(new Date());
        disposalEventParameters.setUser(getCaTissueAdminUser());              
      
        caTissueAPIClient.insert(disposalEventParameters);
	}
	
	private Specimen updateSpecimenLabel(Specimen specimen) throws ApplicationException{	
		specimen.setLabel("DELETED_Label_"+getCurrentDateTime() );
		specimen.setBarcode("DELETED_Barcode_"+getCurrentDateTime());
		Specimen updatedSpecimen = updateSpecimen(specimen);
		return updatedSpecimen;
	}
	
	
	 private String getCurrentDateTime() {
		    Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    return sdf.format(cal.getTime());

		  }

	/**
	 * This method is used to get a User on the basis of the LoginName
	 * @return
	 * @throws ApplicationException
	 */
	private User getCaTissueAdminUser() throws ApplicationException{
		User user = new User();
		user.setLoginName("admin@admin.com");			
		user = caTissueAPIClient.searchById(User.class, user);	
		return user;
	}
	
	
	private List<SpecimenCollectionGroup> getSpecimenCollectionGroupList(SpecimenDetail specimenDetail) throws ApplicationException{
		String title = specimenDetail.getCollectionProtocol().getTitle() ;
		String label = specimenDetail.getCollectionProtocolEvent();
		return caTissueAPIClient.getApplicationService().query(CqlUtility.getSpecimenCollectionGroupListQuery(title, label));		
	}
	
	
}
