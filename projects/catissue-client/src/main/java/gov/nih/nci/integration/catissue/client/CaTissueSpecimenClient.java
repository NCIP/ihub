package gov.nih.nci.integration.catissue.client;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.DisposalEventParameters;
import edu.wustl.catissuecore.domain.FluidSpecimen;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Specimen;
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
 * 
 * @author Rohit Gupta
 *
 */
public class CaTissueSpecimenClient {

	private static Logger LOG = LoggerFactory.getLogger(CaTissueSpecimenClient.class);
	private final CaTissueAPIClientWithRegularAuthentication caTissueAPIClient;	
	private XStream xStream = new XStream(new StaxDriver());
	
	private static final String ACTIVITY_STATUS_ACTIVE = "Active";
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
	 * Creates specimens in caTissue
	 * 
	 * @param specimenListXMLStr -- The XML string for creating the bio-specimen which may contain multiple specimens.
	 * @return
	 * @throws Exception
	 */
	public String createSpecimens(String specimenListXMLStr) throws ApplicationException{
		
		LOG.debug("Inside CaTissueSpecimenClient...The Incoming XML for createSpecimens() is --> " + specimenListXMLStr);
		
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
	 * 
	 * @param specimenListXMLStr -- The XML string for creating the bio-specimen which may contain multiple specimens.
	 * @return
	 * @throws Exception
	 */
	public String updateSpecimens(String specimenListXMLStr) throws ApplicationException{
		
		LOG.debug("Inside CaTissueSpecimenClient... updateSpecimens()..The Incoming XML is --> " + specimenListXMLStr);
		
		// This object contain data from the incoming specimens xml
		Specimens specimens = parseSpecimenListXML(specimenListXMLStr);
		
		// perform the actual logic to Updating the Specimens.. Also do the rollback, if required.. 
		// The returned list will contain existing specimen(which are updated.. i.e it will have specimen values before updation) 
		List<Specimen> existingSpecimenList = performUpdateSpecimens(specimens);
		
		// Copy the exiting Specimen and return in the form of XML
		return xStream.toXML(copyFromExistingSpecimen(existingSpecimenList));
	}
	
	/**
	 * Deletes/Rollback specimens in caTissue
	 * @param specimenListXMLStr
	 * @return
	 * @throws Exception
	 */
	public String rollbackSpecimens(String specimenListXMLStr) throws ApplicationException{
		
		LOG.debug("Inside CaTissueSpecimenClient... rollbackSpecimens()..The Incoming XML is --> " + specimenListXMLStr);
		
		// This object contain data from the incoming specimens xml
		Specimens specimens = parseSpecimenListXML(specimenListXMLStr);
		
		// write a method which will rollback the things...		
		 performDisableSpecimens(specimens);
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
	
	/**
	 * This method has the code/logic to call the createSpecimen.
	 * @param specimens to be created
	 * @return
	 * @throws Exception
	 */
	private void performCreateSpecimens(Specimens specimens) throws ApplicationException{
		List<Specimen> createdSpecimenList = new ArrayList<Specimen>();		
			
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
			
			System.out.println("scgList.size --> " + scgList.size());
			
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
			
			Specimen retSpecimen = null;
			try{
				// method call to createSpecimen
				retSpecimen = createSpecimen(specimen);
			}catch(Exception e){
				// Method call to 'Soft Delete' the Specimens
				softDeleteCreatedSpecimens(createdSpecimenList);
				throw new ApplicationException("CreateSpecimen Failed for Label"+ specimen.getLabel() +" and exception is " +e.getCause());				
			}			
		
			// adding the returned specimen into the list to be returned from this method
			createdSpecimenList.add(retSpecimen);			
		}
		
	}
	
	
	
	private Specimen createSpecimen(Specimen specimen) throws ApplicationException{
		return caTissueAPIClient.insert(specimen);
	}


	
	/**
	 * This method has the code/logic to Update the Specimens.
	 * @param specimens
	 * @return
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
			// Method call to Rollback the Specimen
			rollbackUpdatedSpecimens(existSpecimenList);
			throw new ApplicationException("UpdateSpecimen Failed for Label"+ specimenDetail.getSpecimen().getLabel() +" and exception is " +e.getCause());	
		}
		
		
		return existSpecimenList;
	}
	
	
	private void rollbackUpdatedSpecimens(List<Specimen> specimenList) throws ApplicationException{
		Iterator<Specimen> itr = null;
		
		for(itr = specimenList.iterator(); itr.hasNext();)
		{
			Specimen existingSpecimen =itr.next();		
			updateSpecimen(existingSpecimen);
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
	
		
	private List<Specimen> copyFromExistingSpecimen(List<Specimen> existingSpecimenList){		

		List<Specimen> specimenList = new ArrayList<Specimen>();
		Iterator<Specimen> specimenItr = null;				
		for(specimenItr = existingSpecimenList.iterator(); specimenItr.hasNext();)
		{	
			Specimen existingSpecimen =specimenItr.next(); 
			Specimen specimen = new Specimen();			
			specimen.setId(existingSpecimen.getId());
			specimen.setInitialQuantity(existingSpecimen.getInitialQuantity());
			specimen.setPathologicalStatus(existingSpecimen.getPathologicalStatus());
			specimen.setSpecimenClass(existingSpecimen.getSpecimenClass());
			specimen.setSpecimenType(existingSpecimen.getSpecimenType());			
			specimen.setActivityStatus(existingSpecimen.getActivityStatus());
			specimen.setAvailableQuantity(existingSpecimen.getAvailableQuantity());
			specimen.setBarcode(existingSpecimen.getBarcode());
			specimen.setLabel(existingSpecimen.getLabel());
			
			SpecimenCollectionGroup specimenCollectionGroup = new SpecimenCollectionGroup();			
			specimenCollectionGroup.setId(existingSpecimen.getSpecimenCollectionGroup().getId());
			specimen.setSpecimenCollectionGroup(specimenCollectionGroup);
			
			specimenList.add(specimen);
		}
		
		return specimenList;
	}
	
	
	
	/**
	 * This method has the code/logic to Disable/delete the Specimens.
	 * @param specimens -- specimens to be rollback
	 * @return
	 * @throws Exception
	 */
	private String performDisableSpecimens(Specimens specimens) throws ApplicationException{

		List<SpecimenDetail> specimenDetailList = specimens.getSpecimenDetailList();
		Iterator<SpecimenDetail> specimenDetailItr = null;

		for(specimenDetailItr = specimenDetailList.iterator(); specimenDetailItr.hasNext();)
		{
		SpecimenDetail specimenDetail = specimenDetailItr.next(); 
		
		 // This is for handling the rollback of the inserted specimen.. 
        updateSpecimenLabel(specimenDetail.getSpecimen());
        
		DisposalEventParameters disposalEventParameters = new  DisposalEventParameters();
        Specimen specimen = new  Specimen();
        specimen.setActivityStatus(ACTIVITY_STATUS_DISABLED);
        specimen.setLabel(specimenDetail.getSpecimen().getLabel());        
        disposalEventParameters.setSpecimen(specimen);
        disposalEventParameters.setActivityStatus(ACTIVITY_STATUS_DISABLED);
        disposalEventParameters.setComment("Rollback the Specimen");
        disposalEventParameters.setReason("Rollback the Specimen");
        disposalEventParameters.setTimestamp(new Date());
        disposalEventParameters.setUser(getCaTissueAdminUser());              
      
        caTissueAPIClient.insert(disposalEventParameters);	
		}

		return null;
	}
	
	private void softDeleteCreatedSpecimens(List<Specimen> specimenList) throws ApplicationException{		
		Iterator<Specimen> itr = null;
		for(itr = specimenList.iterator(); itr.hasNext();)
		{
			Specimen existingSpecimen =itr.next();					
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
		String shortTitle = specimenDetail.getCollectionProtocol().getShortTitle() ;
		String label = specimenDetail.getCollectionProtocolEvent();
		return caTissueAPIClient.getApplicationService().query(CqlUtility.getSpecimenCollectionGroupListQuery(shortTitle, label));		
	}
	
	
}
