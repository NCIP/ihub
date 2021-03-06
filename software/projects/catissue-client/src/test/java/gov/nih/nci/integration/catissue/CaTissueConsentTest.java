/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import edu.wustl.catissuecore.domain.CollectionProtocolEvent;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.ConsentTier;
import edu.wustl.catissuecore.domain.ConsentTierStatus;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.factory.CollectionProtocolEventFactory;
import edu.wustl.catissuecore.factory.CollectionProtocolFactory;
import edu.wustl.catissuecore.factory.SpecimenCollectionGroupFactory;
import gov.nih.nci.integration.catissue.client.CaTissueAPIClientWithRegularAuthentication;
import gov.nih.nci.integration.catissue.client.CaTissueConsentClient;
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
        specimen.setLabel("fbfe4b85-2bdc-4305-88be-3e1b763d8caa:135");
        final Set<ConsentTierStatus> consentTierStatusCollection = new LinkedHashSet<ConsentTierStatus>();
        final ConsentTierStatus consentTierStatus1 = new ConsentTierStatus();
        final ConsentTier consentTier1 = new ConsentTier();
        consentTier1.setId(6L);
        consentTier1.setStatement("Tier 1 consent response");
        consentTierStatus1.setStatus("Yes");
        consentTierStatus1.setConsentTier(consentTier1);
        final ConsentTierStatus consentTierStatus2 = new ConsentTierStatus();
        final ConsentTier consentTier2 = new ConsentTier();
        consentTier2.setId(7L);
        consentTier2.setStatement("Tier 2 consent response");
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
     * Mock Testcase for registerConsents when TierId not found
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void registerConsentsTierIdNotFound() {
        String retConsentXML = "";
        final Specimen specimen = new Specimen();
        specimen.setLabel("fbfe4b85-2bdc-4305-88be-3e1b763d8caa:135");

        final CollectionProtocol cp = new CollectionProtocol();
        cp.setShortTitle("6482:6482");
        cp.setTitle("6482:6482");
        final CollectionProtocolEvent cpv = new CollectionProtocolEvent();
        cpv.setCollectionPointLabel("5");
        cpv.setCollectionProtocol(cp);
        final CollectionProtocolRegistration cpr = new CollectionProtocolRegistration();
        final SpecimenCollectionGroup scg = new SpecimenCollectionGroup();
        cpr.setCollectionProtocol(cp);

        scg.setCollectionProtocolRegistration(cpr);
        scg.setCollectionProtocolEvent(cpv);
        specimen.setSpecimenCollectionGroup(scg);

        final Set<ConsentTierStatus> consentTierStatusCollection = new LinkedHashSet<ConsentTierStatus>();
        final ConsentTierStatus consentTierStatus1 = new ConsentTierStatus();
        final ConsentTier consentTier1 = new ConsentTier();
        consentTier1.setId(6L);
        consentTier1.setStatement("Tier 1 consent response");
        consentTierStatus1.setStatus("Yes");
        consentTierStatus1.setConsentTier(consentTier1);

        final ConsentTierStatus consentTierStatus2 = new ConsentTierStatus();
        final ConsentTier consentTier2 = new ConsentTier();
        consentTier2.setId(7L);
        consentTier2.setStatement("Tier 2 consent response");
        consentTierStatus2.setStatus("No");
        consentTierStatus2.setConsentTier(consentTier2);
        consentTierStatusCollection.add(consentTierStatus2);
        consentTierStatusCollection.add(consentTierStatus1);
        specimen.setConsentTierStatusCollection(consentTierStatusCollection);

        final Collection<AbstractSpecimen> childSpecimenCollection = new HashSet<AbstractSpecimen>();
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
     * Mock Testcase for registerConsents
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void registerConsents() {
        String retConsentXML = "";
        final Specimen specimen = new Specimen();
        specimen.setLabel("fbfe4b85-2bdc-4305-88be-3e1b763d8caa:135");
        final Collection<ConsentTier> ctColl = new LinkedHashSet<ConsentTier>();
        final ConsentTier ct1 = new ConsentTier();
        ct1.setId(1L);
        ct1.setStatement("Tier 1 consent response");
        final ConsentTier ct2 = new ConsentTier();
        ct2.setId(2L);
        ct2.setStatement("Tier 2 consent response");
        ctColl.add(ct1);
        ctColl.add(ct2);

        final CollectionProtocol cp = new CollectionProtocol();
        cp.setShortTitle("6482:6482");
        cp.setTitle("6482:6482");
        cp.setConsentTierCollection(ctColl);

        final CollectionProtocolEvent cpv = new CollectionProtocolEvent();
        cpv.setCollectionPointLabel("5");
        cpv.setCollectionProtocol(cp);
        final CollectionProtocolRegistration cpr = new CollectionProtocolRegistration();
        final SpecimenCollectionGroup scg = new SpecimenCollectionGroup();
        cpr.setCollectionProtocol(cp);

        scg.setCollectionProtocolRegistration(cpr);
        scg.setCollectionProtocolEvent(cpv);
        specimen.setSpecimenCollectionGroup(scg);

        final Set<ConsentTierStatus> consentTierStatusCollection = new LinkedHashSet<ConsentTierStatus>();
        final ConsentTierStatus consentTierStatus1 = new ConsentTierStatus();
        final ConsentTier consentTier1 = new ConsentTier();
        consentTier1.setId(6L);
        consentTier1.setStatement("Tier 1 consent response");
        consentTierStatus1.setStatus("Yes");
        consentTierStatus1.setConsentTier(consentTier1);

        final ConsentTierStatus consentTierStatus2 = new ConsentTierStatus();
        final ConsentTier consentTier2 = new ConsentTier();
        consentTier2.setId(7L);
        consentTier2.setStatement("Tier 2 consent response");
        consentTierStatus2.setStatus("No");
        consentTierStatus2.setConsentTier(consentTier2);
        consentTierStatusCollection.add(consentTierStatus2);
        consentTierStatusCollection.add(consentTierStatus1);
        specimen.setConsentTierStatusCollection(consentTierStatusCollection);

        final Collection<AbstractSpecimen> childSpecimenCollection = new HashSet<AbstractSpecimen>();
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
        specimen.setLabel("fbfe4b85-2bdc-4305-88be-3e1b763d8caa:137");
        final SpecimenCollectionGroup scg = SpecimenCollectionGroupFactory.getInstance().createObject();
        final CollectionProtocolEvent cpe = CollectionProtocolEventFactory.getInstance().createObject();
        final CollectionProtocol cp = CollectionProtocolFactory.getInstance().createObject();
        
        final ConsentTier ct1 = new ConsentTier();
        ct1.setId(1L);
        ct1.setStatement("Tier 1 consent response");
        final ConsentTier ct2 = new ConsentTier();
        ct2.setId(2L);
        ct2.setStatement("Tier 2 consent response");
        Collection<ConsentTier> ctCol = new LinkedHashSet<ConsentTier>();
        ctCol.add(ct1);
        ctCol.add(ct2);
        
        Collection<ConsentTierStatus> ctsCol = new LinkedHashSet<ConsentTierStatus>();
        final ConsentTierStatus cts1 = new ConsentTierStatus();
        cts1.setConsentTier(ct1);
        cts1.setStatus("yes");
        final ConsentTierStatus cts2 = new ConsentTierStatus();
        cts2.setConsentTier(ct2);
        cts2.setStatus("yes");        
       
        ctsCol.add(cts1);
        ctsCol.add(cts2);
        
        cp.setConsentTierCollection(ctCol);
        cpe.setCollectionProtocol(cp);
        scg.setCollectionProtocolEvent(cpe);
        specimen.setSpecimenCollectionGroup(scg);
        specimen.setConsentTierStatusCollection(ctsCol);
        
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

    private String getRegisterConsentXMLStr() {
        return getXMLString("RegisterConsent_Mock_catissue.xml");
    }

    private String getRegisterConsentSpecimenNotExistXMLStr() {
        return getXMLString("RegisterConsentSpecimenNotExist_Mock_catissue.xml");
    }

    private String getRollbackConsentXMLStr() {
        return getXMLString("RollbackSpecimen_Mock_catissue.xml");
    }

    private String getXMLString(String fileName) {
        final StringBuffer fileContents = new StringBuffer();
        final InputStream is = CaTissueConsentIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/consent/" + fileName);
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String strLine;
        try {
            while ((strLine = br.readLine()) != null) {
                fileContents.append(strLine);
            }
            is.close();
        } catch (IOException e) {
            LOG.error("CaTissueConsentTest-IOException inside getXMLString() ", e);
        }
        return fileContents.toString();
    }
}
