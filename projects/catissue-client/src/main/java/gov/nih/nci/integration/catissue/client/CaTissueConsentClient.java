package gov.nih.nci.integration.catissue.client;
import edu.wustl.catissuecore.domain.AbstractSpecimen;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.ConsentTier;
import edu.wustl.catissuecore.domain.ConsentTierStatus;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Specimen;
import gov.nih.nci.integration.catissue.domain.ConsentData;
import gov.nih.nci.integration.catissue.domain.ConsentDetail;
import gov.nih.nci.integration.catissue.domain.Consents;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * This is the Client class to perform Consent Related Operations (RegisterConsent, RollbackConsent etc).
 * @author Rohit Gupta
 */

public class CaTissueConsentClient {

	private static Logger LOG = LoggerFactory.getLogger(CaTissueConsentClient.class);
	private final CaTissueAPIClientWithRegularAuthentication caTissueAPIClient;	
	private XStream xStream = new XStream(new StaxDriver());
	
	
	public CaTissueConsentClient(String loginName, String password) throws Exception
	{
		super();		 
		Thread.currentThread().setContextClassLoader(CaTissueConsentClient.class.getClassLoader());		
		this.caTissueAPIClient = new CaTissueAPIClientWithRegularAuthentication(loginName, password);
		
		xStream.alias("consents", Consents.class);
		xStream.alias("participant", Participant.class);
		xStream.alias("consentDetails", ConsentDetail.class);
		xStream.alias("consentData", ConsentData.class);
		xStream.alias("collectionProtocol", CollectionProtocol.class);
		xStream.alias("consentTierStatus", ConsentTierStatus.class);
		xStream.alias("consentTier", ConsentTier.class);
		xStream.addImplicitCollection(ConsentData.class, "consentTierStatusSet");		
		xStream.addImplicitCollection(Consents.class, "consentDetailsList");
	}
	
	/**
	 * This method is used to fetch the existing Consents
	 * @param consentsListXMLStr
	 * @return
	 * @throws ApplicationException
	 */
	public String getExistingConsents(String consentsListXMLStr) throws ApplicationException{
		
		LOG.debug("Inside getExistingConsentsData...The Incoming XML for getExistingConsentsData() is --> " + consentsListXMLStr);
		
		// Parse the incoming XML String. The returned object will contain data from the incoming consents XML
		Consents incomingConsents = parseConsentsListXML(consentsListXMLStr);

		// Fetch the existing Consents
		Consents  exitingConsents = fetchExistingConsents(incomingConsents);
		
//		System.out.println("The Returning Response XML String is --> "+ xStream.toXML(exitingConsents));
		
		return xStream.toXML(exitingConsents);
	}
	
	
	/**
	 * This method is used Register the Consents 
	 * @param consentsListXMLStr
	 * @return
	 * @throws ApplicationException
	 */
	public String registerConsents(String consentsListXMLStr) throws ApplicationException{		
		LOG.debug("Inside CaTissueConsentClient...The Incoming XML for registerConsents() is --> " + consentsListXMLStr);
		System.out.println("Inside CaTissueConsentClient...The Incoming XML for registerConsents() is --> " + consentsListXMLStr);
		
		// Parse the incoming XML String. The returned object will contain data from the incoming specimens XML
		Consents incomingConsents = parseConsentsListXML(consentsListXMLStr);
		
		// perform the actual logic to register the Consents.. Also do the rollback, if required
		performRegisterConsents(incomingConsents);		
	
		//Returning NULL here as we don't need the returned values at the moment.
		// We can return the list of Consents, if required.
		return null;
	}
	
	
	/**
	 * This method is used to Rollback the Consents
	 * @param consentsListXMLStr
	 * @throws ApplicationException
	 */
	public void rollbackConsentRegistration(String consentsListXMLStr) throws ApplicationException{
		LOG.debug("Inside rollbackConsentRegistration...The Incoming XML for rollbackConsentRegistration() is --> " + consentsListXMLStr);
		
		// Parse the incoming XML String. The returned object will contain data from the incoming specimens XML
		Consents incomingConsents = parseConsentsListXML(consentsListXMLStr);

		//rollback the consents	
		rollbackConsentRegistration(incomingConsents);
	}
	
	
	/**
	 * This method will fetch the existing consents
	 * @param incomingConsents
	 * @return
	 * @throws ApplicationException
	 */
	private Consents fetchExistingConsents(Consents incomingConsents)throws ApplicationException{				
		Consents existingConsents = new Consents();
		List<ConsentDetail> exitingConsentDetailsList = new ArrayList<ConsentDetail>();				
		Iterator<ConsentDetail> incomingConsentDetailItr = incomingConsents.getConsentsDetailsList().iterator();		
		while(incomingConsentDetailItr.hasNext()){
			ConsentDetail consentDetail = incomingConsentDetailItr.next();	
			ConsentDetail existingConsentDetail = new ConsentDetail();					
			Specimen existingSpecimen = getExistingSpecimen(consentDetail.getConsentData().getSpecimenLabel().trim());
			existingConsentDetail.setConsentData(getConsentData(existingSpecimen));			
			exitingConsentDetailsList.add(existingConsentDetail);
		}		
		existingConsents.setConsentsDetailsList(exitingConsentDetailsList);		
		return existingConsents;		
	}
	
	
	/**
	 * This method is will register the Consents
	 * @param consents
	 * @throws ApplicationException
	 */
	private void performRegisterConsents(Consents consents)throws ApplicationException{
		List<ConsentDetail> consentDetailList = consents.getConsentsDetailsList();		
		Iterator<ConsentDetail> consentDetailItr = null;	
		Specimen existingSpecimen = null;		
		try{
			for(consentDetailItr = consentDetailList.iterator(); consentDetailItr.hasNext();)
			{			
				ConsentDetail consentDetail = consentDetailItr.next();					
				existingSpecimen = getExistingSpecimen(consentDetail.getConsentData().getSpecimenLabel().trim());	
				consentDetail = populateConsentTierId(consentDetail);
				existingSpecimen.setConsentTierStatusCollection(consentDetail.getConsentData().getConsentTierStatusSet());	
				updateSpecimen(existingSpecimen);		
				
				Collection<AbstractSpecimen> childSpecimenCollection = existingSpecimen.getChildSpecimenCollection();
				Iterator<AbstractSpecimen> itrChildSpecimen = childSpecimenCollection.iterator();
				while(itrChildSpecimen.hasNext()){
					Specimen childSpecimen= (Specimen) itrChildSpecimen.next();
					childSpecimen.setConsentTierStatusCollection(consentDetail.getConsentData().getConsentTierStatusSet());
					updateSpecimen(childSpecimen);
				}				
			}
		}catch(ApplicationException ae){		
			throw new ApplicationException("RegisterConsent Failed for Specimen"+ existingSpecimen.getLabel() +" and exception is " +ae.getCause());
		}			
	}
	
	
	private ConsentDetail populateConsentTierId(ConsentDetail consentDetail) throws ApplicationException{		
		Set<ConsentTierStatus> conTierStatusSet = consentDetail.getConsentData().getConsentTierStatusSet();
		Iterator<ConsentTierStatus> itrTierStatus = conTierStatusSet.iterator();
		
		while(itrTierStatus.hasNext()){
			ConsentTierStatus tierStatus = itrTierStatus.next();
			String stmt = tierStatus.getConsentTier().getStatement();
			CollectionProtocol cp = getExistingCollectionProtocol(consentDetail.getCollectionProtocol().getTitle());
			
			Collection<ConsentTier> consentTierCollection= cp.getConsentTierCollection();
			
			Iterator<ConsentTier> itrConsentTier = consentTierCollection.iterator();
			
			while(itrConsentTier.hasNext()){
				ConsentTier consentTier = itrConsentTier.next();
				if(stmt.equalsIgnoreCase(consentTier.getStatement())){
					tierStatus.getConsentTier().setId(consentTier.getId());
					break;
				}
			}						
		}
		
		return consentDetail;
	}
	
	private CollectionProtocol getExistingCollectionProtocol(String title) throws ApplicationException{		
		CollectionProtocol cp = new CollectionProtocol();
		cp.setTitle(title);
		cp = caTissueAPIClient.searchById(CollectionProtocol.class, cp);		
		return cp;		
	}
	
	/**
	 * This method will rollback the Consents
	 * @param consents
	 * @throws ApplicationException
	 */
	private void rollbackConsentRegistration(Consents consents) throws ApplicationException{		
		List<ConsentDetail> consentDetailList = consents.getConsentsDetailsList();		
		Iterator<ConsentDetail> consentDetailItr = null;	
		Specimen existingSpecimen = null;		
		try{
			for(consentDetailItr = consentDetailList.iterator(); consentDetailItr.hasNext();)
			{
				ConsentDetail consentDetail = consentDetailItr.next();	
				System.out.println("Rollback for Specimen --> " + consentDetail.getConsentData().getSpecimenLabel());
				existingSpecimen = getExistingSpecimen(consentDetail.getConsentData().getSpecimenLabel());
				existingSpecimen.setConsentTierStatusCollection(consentDetail.getConsentData().getConsentTierStatusSet());				
				updateSpecimen(existingSpecimen);
				System.out.println("Rollback completed for Specimen --> "+ consentDetail.getConsentData().getSpecimenLabel());
			}
		}catch(ApplicationException ae){
			// code for handling the exception
			
			System.out.println("Exception During Rollback Specimen .. " + ae.getStackTrace());
		}
			
		
	}
	/**
	 * This method is used to parse the incoming XML string and populate the 'Consents' object
	 * @param specimenListXMLStr
	 * @return
	 */
	private Consents parseConsentsListXML(String consentsListXMLStr) {		
		Consents consents = (Consents) xStream.fromXML(new StringReader(consentsListXMLStr));					
		return consents;
	}
	
	
	
	
	/**
	 * This method is used to fetch the ConsentData for given Specimen
	 * @param existingSpecimen
	 * @return
	 */
	private ConsentData getConsentData(Specimen existingSpecimen){
		ConsentData existingConsentData = new ConsentData();			
		Set<ConsentTierStatus> existingTierStatusCollection = new HashSet<ConsentTierStatus>(); 		
		Collection<ConsentTierStatus> consentTierStatusCollection = existingSpecimen.getConsentTierStatusCollection();
		
		if(consentTierStatusCollection!=null){
			Iterator<ConsentTierStatus> itr= consentTierStatusCollection.iterator();		
			while(itr.hasNext()){
				ConsentTierStatus tierStatus = itr.next();
				ConsentTierStatus exitingTierStatus = new ConsentTierStatus();
				exitingTierStatus.setStatus(tierStatus.getStatus());
				ConsentTier exitingConsentTier = new ConsentTier();
				exitingConsentTier.setId(tierStatus.getConsentTier().getId());
				exitingConsentTier.setStatement(tierStatus.getConsentTier().getStatement());
				exitingTierStatus.setConsentTier(exitingConsentTier);			
				existingTierStatusCollection.add(exitingTierStatus);
			}		
			existingConsentData.setConsentTierStatusSet(existingTierStatusCollection);
			existingConsentData.setSpecimenLabel(existingSpecimen.getLabel());	
		}
		
		return existingConsentData;
	}
	
	
	private Specimen updateSpecimen(Specimen specimen) throws ApplicationException{
		return caTissueAPIClient.update(specimen);
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
		
}

