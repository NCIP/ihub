package gov.nih.nci.integration.catissue.domain;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.Specimen;
import gov.nih.nci.dynext.guidance_for_breast_core_biopsy.GuidanceForBreastCoreBiopsy;

/**
 * @author Rohit Gupta
 */
public class SpecimenDetail {

    private Specimen specimen;
    private CollectionProtocol collectionProtocol;
    private String collectionProtocolEvent;
    private GuidanceForBreastCoreBiopsy guidanceForBreastCoreBiopsy;

    /**
     * To get collectionProtocolEvent
     * 
     * @return collectionProtocolEvent
     */
    public String getCollectionProtocolEvent() {
        return collectionProtocolEvent;
    }

    /**
     * To set collectionProtocolEvent
     * 
     * @param collectionProtocolEvent - collectionProtocolEvent
     */
    public void setCollectionProtocolEvent(String collectionProtocolEvent) {
        this.collectionProtocolEvent = collectionProtocolEvent;
    }

    /**
     * To get specimen
     * 
     * @return specimen
     */
    public Specimen getSpecimen() {
        return specimen;
    }

    /**
     * to set the specimen
     * 
     * @param specimen - specimen to be set
     */
    public void setSpecimen(Specimen specimen) {
        this.specimen = specimen;
    }

    /**
     * To get the CollectionProtocol
     * 
     * @return CollectionProtocol
     */
    public CollectionProtocol getCollectionProtocol() {
        return collectionProtocol;
    }

    /**
     * To set the CollectionProtocol
     * 
     * @param collectionProtocol - CollectionProtocol to be set
     */
    public void setCollectionProtocol(CollectionProtocol collectionProtocol) {
        this.collectionProtocol = collectionProtocol;
    }

    /**
     * 
     * @return GuidanceForBreastCoreBiopsy
     */
    public GuidanceForBreastCoreBiopsy getGuidanceForBreastCoreBiopsy() {
        return guidanceForBreastCoreBiopsy;
    }

    /**
     * 
     * @param guidanceForBreastCoreBiopsy - GuidanceForBreastCoreBiopsy
     */
    public void setGuidanceForBreastCoreBiopsy(GuidanceForBreastCoreBiopsy guidanceForBreastCoreBiopsy) {
        this.guidanceForBreastCoreBiopsy = guidanceForBreastCoreBiopsy;
    }

}
