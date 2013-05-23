/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
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
