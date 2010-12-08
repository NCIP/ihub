package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.sr;

public class XPerformedSubjectMilestone {
    String informedConsentDate;
    String registrationDate;
    String registrationSiteIdentifier;
    String registrationSiteIdentifierName;
    
    @Override
    public String toString() {
        return "informedConsentDate : "+this.informedConsentDate+
        "\nregistrationDate : "+this.registrationDate+
        "\nregistrationSiteIdentifier : "+this.registrationSiteIdentifier+
        "\nregistrationSiteIdentifierName : "+this.registrationSiteIdentifierName;
    }
}
