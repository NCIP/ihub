package gov.nih.nci.integration.catissue.domain;

import java.util.ArrayList;
import java.util.List;

import edu.wustl.catissuecore.domain.Participant;

/**
 * @author Rohit Gupta
 */
public class Specimens {

    private final List<SpecimenDetail> specimenDetailList;
    private Participant participant;

    /**
     * Constructor
     */
    public Specimens() {
        specimenDetailList = new ArrayList<SpecimenDetail>();
    }

    /**
     * To add a SpecimenDetail in the list
     * 
     * @param specimenDetail - SpecimenDetail to be added
     */
    public void add(SpecimenDetail specimenDetail) {
        specimenDetailList.add(specimenDetail);
    }

    /**
     * To get the specimenDetailList
     * 
     * @return specimenDetailList
     */
    public List<SpecimenDetail> getSpecimenDetailList() {
        return specimenDetailList;
    }

    /**
     * To get the participant
     * 
     * @return participant
     */
    public Participant getParticipant() {
        return participant;
    }

    /**
     * To set the participant
     * 
     * @param participant - participant to be set
     */
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

}
