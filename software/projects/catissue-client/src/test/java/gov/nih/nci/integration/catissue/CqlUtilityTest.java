/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import edu.wustl.catissuecore.cacore.CaTissueWritableAppService;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Race;
import edu.wustl.catissuecore.factory.CollectionProtocolFactory;
import edu.wustl.catissuecore.factory.CollectionProtocolRegistrationFactory;
import edu.wustl.catissuecore.factory.ParticipantFactory;
import edu.wustl.catissuecore.factory.RaceFactory;
import gov.nih.nci.integration.catissue.client.CaTissueAPIClientWithRegularAuthentication;
import gov.nih.nci.integration.catissue.client.CaTissueParticipantClient;
import gov.nih.nci.integration.catissue.client.CqlUtility;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLQuery;

/**
 * This is the TestClass for Participant Registration flow.
 * 
 * @author Rohit Gupta
 */

public class CqlUtilityTest {

    private static final Logger LOG = LoggerFactory.getLogger(CqlUtilityTest.class);

    private CaTissueParticipantClient caTissueParticipantClient = null;
    private CaTissueAPIClientWithRegularAuthentication caTissueAPIClient = null;
    private CaTissueWritableAppService writableAppService = null;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);

    /**
     * To initialize the things
     * 
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    @Before
    public void initialize() throws BeansException, MalformedURLException {
        writableAppService = org.easymock.EasyMock.createMock(CaTissueWritableAppService.class);
        caTissueAPIClient = EasyMock.createMock(CaTissueAPIClientWithRegularAuthentication.class);
        caTissueParticipantClient = new CaTissueParticipantClient("", "");
        caTissueParticipantClient.setCaTissueAPIClient(caTissueAPIClient);
    }

    /**
     * Mock Testcase for Update Participant Registration
     * 
     * @throws ParseException - ParseException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getParticipantsForCollectionProtocol() throws ParseException {

        final String cpTitle = "6482";

        final Participant participant = getParticipant();
        final List<Object> participantList = new ArrayList<Object>();
        participantList.add(participant);

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(caTissueAPIClient.update((Participant) org.easymock.EasyMock.anyObject())).andReturn(
                    participant);
            EasyMock.expect(writableAppService.query((CQLQuery) org.easymock.EasyMock.anyObject())).andReturn(
                    participantList);

            EasyMock.replay(caTissueAPIClient);
            EasyMock.replay(writableAppService);

            final List<Participant> retList = caTissueParticipantClient.getParticipantsForCollectionProtocol(cpTitle);
            assertNotNull(retList);
        } catch (ApplicationException e) {
            LOG.error("CaTissueParticipantTest-ApplicationException inside getParticipantsForCollectionProtocol() ", e);
        }

    }

    /**
     * 
     */
    @Test
    public void getTissueSpecimensForCP() {
        final String cpTitle = "6482";
        final CQLQuery query = CqlUtility.getTissueSpecimensForCP(cpTitle);
        assertNotNull(query);
    }

    /**
     * 
     */
    @Test
    public void getFluidSpecimensWithReviewEventRecordForCP() {
        final String cpTitle = "6482";
        final CQLQuery query = CqlUtility.getFluidSpecimensWithReviewEventRecordForCP(cpTitle);
        assertNotNull(query);
    }

    private Participant getParticipant() throws ParseException {
        final ParticipantFactory prtcpntFact = ParticipantFactory.getInstance();
        final Participant participant = prtcpntFact.createObject();

        participant.setActivityStatus("Active");
        participant.setBirthDate(dateFormat.parse("19410502"));
        participant.setEthnicity("Unknown");
        participant.setGender("Unspecified");
        participant.setFirstName("JOHN5");
        // participant.setLastName("DOE5");
        // MRN or Medical Identifier is being set as lastName for identification
        participant.setLastName("488060801");
        participant.setVitalStatus("Alive");
        participant.setSocialSecurityNumber("123-05-0608");

        final Race race = RaceFactory.getInstance().createObject();
        race.setParticipant(participant);
        race.setRaceName("White");
        participant.getRaceCollection().add(race);
        participant.getCollectionProtocolRegistrationCollection().add(initCollectionProtocolRegistration(participant));
        return participant; 
    }

    private CollectionProtocolRegistration initCollectionProtocolRegistration(Participant participant) {

        final CollectionProtocolFactory cpFact = CollectionProtocolFactory.getInstance();
        final CollectionProtocol cp = cpFact.createObject();
        final String cpTitle = "CP-01";
        cp.setTitle(cpTitle);

        final CollectionProtocolRegistrationFactory cprFact = CollectionProtocolRegistrationFactory.getInstance();
        final CollectionProtocolRegistration collectionProtocolRegistration = cprFact.createObject();
        collectionProtocolRegistration.setCollectionProtocol(cp);

        collectionProtocolRegistration.setParticipant(participant);
        collectionProtocolRegistration.setActivityStatus("Active");
        collectionProtocolRegistration.setRegistrationDate(new Date());
        collectionProtocolRegistration.setConsentSignatureDate(new Date());
        collectionProtocolRegistration.setProtocolParticipantIdentifier("123050608");
        return collectionProtocolRegistration;
    }

}
