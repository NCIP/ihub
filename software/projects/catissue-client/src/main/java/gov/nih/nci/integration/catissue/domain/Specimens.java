package gov.nih.nci.integration.catissue.domain;

import java.util.ArrayList;
import java.util.List;

import edu.wustl.catissuecore.domain.Participant;

/**
 * 
 * @author Rohit Gupta
 *
 */
public class Specimens {

	List<SpecimenDetail> specimenDetailList;

	private Participant participant;

	public Specimens() {
		specimenDetailList = new ArrayList<SpecimenDetail>();
	}

	public void add(SpecimenDetail specimenDetail) {
		specimenDetailList.add(specimenDetail);
	}

	public List<SpecimenDetail> getSpecimenDetailList() {
		return specimenDetailList;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

}