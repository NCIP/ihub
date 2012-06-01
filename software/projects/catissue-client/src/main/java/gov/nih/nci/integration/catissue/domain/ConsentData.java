package gov.nih.nci.integration.catissue.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import edu.wustl.catissuecore.domain.ConsentTierStatus;

/**
 * 
 * @author Rohit Gupta
 * 
 */
public class ConsentData {

	Set<ConsentTierStatus> consentTierStatusSet;

	public void setConsentTierStatusSet(
			Set<ConsentTierStatus> consentTierStatusSet) {
		this.consentTierStatusSet = consentTierStatusSet;
	}

	private String specimenLabel;

	public ConsentData() {
		consentTierStatusSet = new LinkedHashSet<ConsentTierStatus>();
	}

	public String getSpecimenLabel() {
		return specimenLabel;
	}

	public void setSpecimenLabel(String specimenLabel) {
		this.specimenLabel = specimenLabel;
	}

	public void add(ConsentTierStatus consentTierStatus) {
		consentTierStatusSet.add(consentTierStatus);
	}

	public Set<ConsentTierStatus> getConsentTierStatusSet() {
		return consentTierStatusSet;
	}

}
