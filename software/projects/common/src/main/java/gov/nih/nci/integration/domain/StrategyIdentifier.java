package gov.nih.nci.integration.domain;

/**
 * This class contains the Identifier for Strategy Class
 * 
 * @author Vinodh
 * 
 */
public enum StrategyIdentifier {
    /**
     * for caTissue Create Registration
     */
    CATISSUE_CREATE_REGISTRATION,

    /**
     * for caTissue Update Registration
     */
    CATISSUE_UPDATE_REGISTRATION,

    /**
     * for caAERS Create Registration
     */
    CAEERS_CREATE_REGISTRATION,

    /**
     * for caAERS Update Registration
     */
    CAEERS_UPDATE_REGISTRATION,

    /**
     * for caAERS Create Adverse Event
     */
    CAEERS_CREATE_AE,

    /**
     * for caAERS Update Adverse Event
     */
    CAEERS_UPDATE_AE,
    /**
     * for caTissue Create Specimen
     */
    CATISSUE_CREATE_SPECIMEN,

    /**
     * for caTissue Update Specimen
     */
    CATISSUE_UPDATE_SPECIMEN,

    /**
     * for caTissue Update Consent
     */
    CATISSUE_REGISTER_CONSENT;
}
