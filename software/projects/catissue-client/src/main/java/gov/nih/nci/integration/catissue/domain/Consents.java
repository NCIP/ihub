package gov.nih.nci.integration.catissue.domain;

import java.util.ArrayList;
import java.util.List;

import edu.wustl.catissuecore.domain.Participant;

/**
 * 
 * @author Rohit Gupta
 *
 */
public class Consents {

	List<ConsentDetail> consentDetailsList;	

	private Participant participant;

	public Consents() {
		consentDetailsList = new ArrayList<ConsentDetail>();
	}

	public void add(ConsentDetail consentDetail) {
		consentDetailsList.add(consentDetail);
	}

	public List<ConsentDetail> getConsentsDetailsList() {
		return consentDetailsList;
	}

	public void setConsentsDetailsList(List<ConsentDetail> consentDetailsList) {
		this.consentDetailsList = consentDetailsList;
	}
	
	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

}
