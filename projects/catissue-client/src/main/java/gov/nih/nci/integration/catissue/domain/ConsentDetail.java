package gov.nih.nci.integration.catissue.domain;

import edu.wustl.catissuecore.domain.CollectionProtocol;

/**
 * @author Rohit Gupta
 */
public class ConsentDetail {

    private String collectionProtocolEvent;
    private ConsentData consentData;
    private CollectionProtocol collectionProtocol;

    /**
     * Get collectionProtocolEvent
     * @return collectionProtocolEventId
     */
    public String getCollectionProtocolEvent() {
        return collectionProtocolEvent;
    }

    /**
     * Set collectionProtocolEvent 
     * @param collectionProtocolEvent - collectionProtocolEventId
     */
    public void setCollectionProtocolEvent(String collectionProtocolEvent) {
        this.collectionProtocolEvent = collectionProtocolEvent;
    }

    /**
     * To get ConsentData
     * @return ConsentData object
     */
    public ConsentData getConsentData() {
        return consentData;
    }

    /**
     * Set ConsentData
     * @param consentData - ConsentData to be set
     */
    public void setConsentData(ConsentData consentData) {
        this.consentData = consentData;
    }

    /**
     * To get CollectionProtocol
     * @return CollectionProtocol
     */
    public CollectionProtocol getCollectionProtocol() {
        return collectionProtocol;
    }

    /**
     * Set CollectionProtocol
     * @param collectionProtocol - CollectionProtocol
     */
    public void setCollectionProtocol(CollectionProtocol collectionProtocol) {
        this.collectionProtocol = collectionProtocol;
    }

}
