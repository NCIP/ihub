/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.catissue.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import edu.wustl.catissuecore.domain.ConsentTierStatus;

/** 
 * @author Rohit Gupta 
 */
public class ConsentData {

    private Set<ConsentTierStatus> consentTierStatusSet;
    private String specimenLabel;

    /**
     * Constructor
     */
    public ConsentData() {
        consentTierStatusSet = new LinkedHashSet<ConsentTierStatus>();
    }

    /**
     * Set the ConsentTierStatusSet
     * 
     * @param consentTierStatusSet - Set<ConsentTierStatus>
     */
    public void setConsentTierStatusSet(Set<ConsentTierStatus> consentTierStatusSet) {
        this.consentTierStatusSet = consentTierStatusSet;
    }

    /**
     * Get SpecimenLabel
     * @return SpecimenLabel
     */
    public String getSpecimenLabel() {
        return specimenLabel;
    }

    /**
     * Set SpecimenLabel
     * @param specimenLabel - SpecimenLabel
     */
    public void setSpecimenLabel(String specimenLabel) {
        this.specimenLabel = specimenLabel;
    }

    /**
     * Add a ConsentTierStatus to the consentTierStatusSet
     * @param consentTierStatus - object to be added in the Set
     */
    public void add(ConsentTierStatus consentTierStatus) {
        consentTierStatusSet.add(consentTierStatus);
    }

    /**
     * To get ConsentTierStatusSet
     * @return Set<ConsentTierStatus>
     */
    public Set<ConsentTierStatus> getConsentTierStatusSet() {
        return consentTierStatusSet;
    }

}
