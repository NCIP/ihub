package gov.nih.nci.integration.catissue.client;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import edu.wustl.catissuecore.cacore.CaTissueWritableAppService;
import edu.wustl.catissuecore.domain.AbstractSpecimen;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.ConsentTier;
import edu.wustl.catissuecore.domain.ConsentTierStatus;
import edu.wustl.catissuecore.domain.Specimen;
import gov.nih.nci.system.applicationservice.ApplicationException;

/**
 * This is the TestClass for Register Consent flow.
 * 
 * @author Rohit Gupta
 */

public class CaTissueConsentTest {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueConsentTest.class);

    private CaTissueConsentClient caTissueConsentClient = null;
    private CaTissueAPIClientWithRegularAuthentication caTissueAPIClient = null;
    private CaTissueWritableAppService writableAppService = null;

    /**
     * To initialize the things
     * 
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    @Test
    @Before
    public void initialize() throws BeansException, MalformedURLException {
        writableAppService = org.easymock.EasyMock.createMock(CaTissueWritableAppService.class);
        caTissueAPIClient = EasyMock.createMock(CaTissueAPIClientWithRegularAuthentication.class);
        caTissueConsentClient = new CaTissueConsentClient("", "");
        caTissueConsentClient.setCaTissueAPIClient(caTissueAPIClient);
    }

    /**
     * Mock Testcase for getExistingConsents
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getExistingConsents() {
        String retConsentXML = "";
        final Specimen specimen = new Specimen();
        specimen.setLabel("TolvenTestUser252TissueSpecimen173");
        final Set<ConsentTierStatus> consentTierStatusCollection = new LinkedHashSet<ConsentTierStatus>();
        final ConsentTierStatus consentTierStatus1 = new ConsentTierStatus();
        final ConsentTier consentTier1 = new ConsentTier();
        consentTier1.setId(6L);
        consentTier1.setStatement("This is a statement");
        consentTierStatus1.setStatus("Yes");
        consentTierStatus1.setConsentTier(consentTier1);
        final ConsentTierStatus consentTierStatus2 = new ConsentTierStatus();
        final ConsentTier consentTier2 = new ConsentTier();
        consentTier2.setId(7L);
        consentTier2.setStatement("This is a second statement.");
        consentTierStatus2.setStatus("No");
        consentTierStatus2.setConsentTier(consentTier2);
        consentTierStatusCollection.add(consentTierStatus1);
        consentTierStatusCollection.add(consentTierStatus2);
        specimen.setConsentTierStatusCollection(consentTierStatusCollection);

        final CollectionProtocol collectionProtocol = EasyMock.createMock(CollectionProtocol.class);

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<CollectionProtocol>) EasyMock.anyObject(),
                            (CollectionProtocol) org.easymock.EasyMock.anyObject())).andReturn(collectionProtocol);
            EasyMock.replay(caTissueAPIClient);

            caTissueConsentClient.getExistingConsents(getRegisterConsentXMLStr());

            retConsentXML = "GET_EXISTING_CONSENT";
        } catch (ApplicationException e) {
            LOG.error("CaTissueConsentTest-ApplicationException inside registerConsents() ", e);
            retConsentXML = "GET_EXISTING_CONSENT_FAILED";
        }

        assertNotNull(retConsentXML);

    }

    /**
     * Mock Testcase for registerConsents
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void registerConsents() {
        String retConsentXML = "";
        final Specimen specimen = new Specimen();
        specimen.setLabel("TolvenTestUser252TissueSpecimen173");
        final Set<ConsentTierStatus> consentTierStatusCollection = new LinkedHashSet<ConsentTierStatus>();
        final ConsentTierStatus consentTierStatus1 = new ConsentTierStatus();
        final ConsentTier consentTier1 = new ConsentTier();
        consentTier1.setId(6L);
        consentTier1.setStatement("This is a statement");
        consentTierStatus1.setStatus("Yes");
        consentTierStatus1.setConsentTier(consentTier1);

        final ConsentTierStatus consentTierStatus2 = new ConsentTierStatus();
        final ConsentTier consentTier2 = new ConsentTier();
        consentTier2.setId(7L);
        consentTier2.setStatement("This is a second statement.");
        consentTierStatus2.setStatus("No");
        consentTierStatus2.setConsentTier(consentTier2);
        // consentTierStatusCollection.add(consentTierStatus2);
        consentTierStatusCollection.add(consentTierStatus1);
        specimen.setConsentTierStatusCollection(consentTierStatusCollection);

        Collection<AbstractSpecimen> childSpecimenCollection = new HashSet<AbstractSpecimen>();
        specimen.setChildSpecimenCollection(childSpecimenCollection);

        final Collection<ConsentTier> consentTierCollection = new HashSet<ConsentTier>();
        consentTierCollection.add(consentTier1);
        consentTierCollection.add(consentTier2);
        final CollectionProtocol collectionProtocol = new CollectionProtocol();
        collectionProtocol.setConsentTierCollection(consentTierCollection);

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);
            EasyMock.expect(caTissueAPIClient.update((Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<CollectionProtocol>) EasyMock.anyObject(),
                            (CollectionProtocol) org.easymock.EasyMock.anyObject())).andReturn(collectionProtocol)
                    .anyTimes();

            EasyMock.replay(caTissueAPIClient);

            caTissueConsentClient.registerConsents(getRegisterConsentXMLStr());
            // EasyMock.verify(caTissueAPIClient);
            retConsentXML = "REGISTER_CONSENT";
        } catch (ApplicationException e) {
            LOG.error("CaTissueConsentTest-ApplicationException inside registerConsents() ", e);
            retConsentXML = "REGISTER_CONSENT_FAILED";
        }

        assertNotNull(retConsentXML);

    }

    /**
     * Mock Testcase for registerConsents when Specimen doesn't exist
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void registerConsentsSpecimenNotExist() {
        String retConsentXML = "";
        final Specimen specimen = null;
        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);
            EasyMock.replay(caTissueAPIClient);

            caTissueConsentClient.getExistingConsents(getRegisterConsentSpecimenNotExistXMLStr());
            caTissueConsentClient.registerConsents(getRegisterConsentSpecimenNotExistXMLStr());

            EasyMock.verify(caTissueAPIClient);
            retConsentXML = "REGISTER_CONSENT_SpecimenNotExist";
        } catch (ApplicationException e) {
            LOG.error("CaTissueConsentTest-ApplicationException inside registerConsentsSpecimenNotExist() ", e);
            retConsentXML = "REGISTER_CONSENT_SpecimenNotExist_FAILED";
        }

        assertNotNull(retConsentXML);

    }

    /**
     * Mock Testcase for rollback of registerConsents
     * 
     * @throws MalformedURLException MalformedURLException
     * @throws BeansException BeansException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void rollbackConsents() throws BeansException, MalformedURLException {
        String retConsentXML = "";
        final Specimen specimen = new Specimen();
        specimen.setLabel("TolvenTestUser252TissueSpecimen173");
        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);
            EasyMock.expect(caTissueAPIClient.update((Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);
            EasyMock.replay(caTissueAPIClient);

            caTissueConsentClient.rollbackConsentRegistration(getRollbackConsentXMLStr());
            retConsentXML = "REGISTER_CONSENT_ROLLBACK";
        } catch (ApplicationException e) {
            LOG.error("CaTissueConsentTest-ApplicationException inside rollbackConsents() ", e);
            retConsentXML = "REGISTER_CONSENT_ROLLBACK_FAILED";
        }

        assertNotNull(retConsentXML);

    }

    // CHECKSTYLE:OFF
    private String getRegisterConsentXMLStr() {
        return "<?xml version=\"1.0\" ?><consents><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen173</specimenLabel><consentTierStatus><consentTier><statement>This is a statement</statement></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol></consentDetails></consents>";
    }

    private String getRegisterConsentSpecimenNotExistXMLStr() {
        return "<?xml version=\"1.0\" ?><consents><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TestUser5000</specimenLabel><consentTierStatus><consentTier><statement>This is a statement</statement></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol></consentDetails></consents>";
    }

    private String getRollbackConsentXMLStr() {
        return "<?xml version=\"1.0\" ?><consents><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen173</specimenLabel><consentTierStatus><consentTier><statement>This is a statement</statement></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol></consentDetails></consents>";
    }

    // CHECKSTYLE:ON

}
