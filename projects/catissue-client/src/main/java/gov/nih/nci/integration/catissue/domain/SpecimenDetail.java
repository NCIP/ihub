package gov.nih.nci.integration.catissue.domain;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.Specimen;

/**
 * 
 * @author Rohit Gupta
 *
 */
public class SpecimenDetail {

	private Specimen specimen;
	
	private CollectionProtocol collectionProtocol;
	
	private String collectionProtocolEvent;
	

	public String getCollectionProtocolEvent() {
		return collectionProtocolEvent;
	}

	public void setCollectionProtocolEvent(String collectionProtocolEvent) {
		this.collectionProtocolEvent = collectionProtocolEvent;
	}

	public Specimen getSpecimen() {
		return specimen;
	}

	public void setSpecimen(Specimen specimen) {
		this.specimen = specimen;
	}

	public CollectionProtocol getCollectionProtocol() {
		return collectionProtocol;
	}

	public void setCollectionProtocol(CollectionProtocol collectionProtocol) {
		this.collectionProtocol = collectionProtocol;
	}
	
}
