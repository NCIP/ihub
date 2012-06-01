package gov.nih.nci.integration.catissue.domain;

import edu.wustl.catissuecore.domain.CollectionProtocol;

/**
 * 
 * @author Rohit Gupta
 * 
 */
public class ConsentDetail {

    private String collectionProtocolEvent;

    private ConsentData consentData;

    private CollectionProtocol collectionProtocol;

    public String getCollectionProtocolEvent() {
        return collectionProtocolEvent;
    }

    public void setCollectionProtocolEvent(String collectionProtocolEvent) {
        this.collectionProtocolEvent = collectionProtocolEvent;
    }

    public ConsentData getConsentData() {
        return consentData;
    }

    public void setConsentData(ConsentData consentData) {
        this.consentData = consentData;
    }

    public CollectionProtocol getCollectionProtocol() {
        return collectionProtocol;
    }

    public void setCollectionProtocol(CollectionProtocol collectionProtocol) {
        this.collectionProtocol = collectionProtocol;
    }

}
