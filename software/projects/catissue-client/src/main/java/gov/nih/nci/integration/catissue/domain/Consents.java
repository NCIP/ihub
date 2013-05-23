/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.catissue.domain;

import edu.wustl.catissuecore.domain.Participant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohit Gupta
 */
public class Consents {

    private List<ConsentDetail> consentDetailsList;
    private Participant participant;

    /**
     * Constructor
     */
    public Consents() {
        consentDetailsList = new ArrayList<ConsentDetail>();
    }

    /**
     * To add ConsentDetail to the list
     * @param consentDetail - ConsentDetail to be added 
     */
    public void add(ConsentDetail consentDetail) {
        consentDetailsList.add(consentDetail);
    }

    /**
     * Get the consentDetailsList
     * @return List<ConsentDetail>
     */
    public List<ConsentDetail> getConsentsDetailsList() {
        return consentDetailsList;
    }

    /**
     * Set the consentDetailsList 
     * @param consentDetailsList consentDetailsList
     */
    public void setConsentsDetailsList(List<ConsentDetail> consentDetailsList) {
        this.consentDetailsList = consentDetailsList;
    }

    /**
     * to get the participant
     * @return Participant
     */
    public Participant getParticipant() {
        return participant;
    }

    /**
     *To set the participant
     * @param participant - participant
     */
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

}
