package gov.nih.nci.integration.catissue.client;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolEvent;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.FluidSpecimen;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.ReviewEventParameters;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.domain.TissueSpecimen;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLGroup;
import gov.nih.nci.system.query.cql.CQLLogicalOperator;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;

/**
 * 
 * @author from catissue client
 * 
 */
public final class CqlUtility {

    private static final String SHORT_TITLE = "shortTitle";
    private static final String COLLECTION_PROTOCOL = "collectionProtocol";

    private CqlUtility() {

    }

    /**
     * Get the CQLQuery for the participant for given collection protocol title
     * 
     * @param shortTitle - short title of the CP
     * @return CQLQuery object
     */
    public static CQLQuery getParticipantsForCP(String shortTitle) {
        final CQLAttribute association2Attribute = createAttribute(SHORT_TITLE, shortTitle, CQLPredicate.EQUAL_TO);
        final CQLAssociation association2 = createAssociation(CollectionProtocol.class, COLLECTION_PROTOCOL);
        association2.setAttribute(association2Attribute);

        final CQLAttribute association1Attribute = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLGroup group2 = createGroup(CQLLogicalOperator.AND, association1Attribute, association2);

        final CQLAssociation association1 = createAssociation(CollectionProtocolRegistration.class,
                "collectionProtocolRegistrationCollection");
        association1.setGroup(group2);

        final CQLAttribute targetAttribute = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLGroup group1 = createGroup(CQLLogicalOperator.AND, targetAttribute, association1);

        return createTargetQuery(Participant.class, group1);
    }

    /**
     * Get the CQLQuery for the participant for given patientId
     * 
     * @param patientId patientId
     * @param cpShortTitle - short Title of CP
     * @return CQLQuery object
     */
    public static CQLQuery getParticipantForPatientId(String patientId, String cpShortTitle) {

        final CQLAttribute cpStAtt = createAttribute(SHORT_TITLE, cpShortTitle, CQLPredicate.EQUAL_TO);
        final CQLAssociation cpAssoc = createAssociation(CollectionProtocol.class, COLLECTION_PROTOCOL);
        cpAssoc.setAttribute(cpStAtt);

        final CQLAttribute cprIdAtt = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLGroup cprAndCpGrp = createGroup(CQLLogicalOperator.AND, cprIdAtt, cpAssoc);

        final CQLAssociation cprAssoc = createAssociation(CollectionProtocolRegistration.class,
                "collectionProtocolRegistrationCollection");
        cprAssoc.setGroup(cprAndCpGrp);

        final CQLAttribute patientLnAtt = createAttribute("lastName", patientId, CQLPredicate.EQUAL_TO);

        final CQLGroup prtcpntAndCprGroup = createGroup(CQLLogicalOperator.AND, patientLnAtt, cprAssoc);

        return createTargetQuery(Participant.class, prtcpntAndCprGroup);
    }

    /**
     * Get the CQLQuery for the participant for given SSN
     * 
     * @param ssn - SSN
     * @return CQLQuery object
     */
    public static CQLQuery getParticipantForSSN(String ssn) {
        final CQLAttribute targetAttribute2 = createAttribute("socialSecurityNumber", ssn, CQLPredicate.EQUAL_TO);

        final CQLAttribute targetAttribute = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLGroup group1 = createGroup(CQLLogicalOperator.AND, targetAttribute, targetAttribute2);

        return createTargetQuery(Participant.class, group1);
    }

    /**
     * Get the FluidSpecimen with Review Event Record for given shortTitle CollectionProtocol
     * 
     * @param shortTitle - shortTitle of CP
     * @return CQLQuery object
     */
    public static CQLQuery getFluidSpecimensWithReviewEventRecordForCP(String shortTitle) {
        final CQLAssociation association2 = createSCGAssociationWithIDandCPRAssociation(shortTitle);

        final CQLAttribute association1Attribute = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLAssociation association1 = createAssociation(ReviewEventParameters.class, "specimenEventCollection");
        association1.setAttribute(association1Attribute);

        final CQLAttribute targetAttribute = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLGroup group = createGroup(CQLLogicalOperator.AND, targetAttribute, association1, association2);

        return createTargetQuery(FluidSpecimen.class, group);
    }

    /**
     * Get the TissueSpecimen for given collection protocol
     * 
     * @param shortTitle - shortTitle of CP
     * @return CQLQuery object
     */
    public static CQLQuery getTissueSpecimensForCP(String shortTitle) {
        final CQLAssociation association2 = createSCGAssociationWithIDandCPRAssociation(shortTitle);
        final CQLAttribute targetAttribute = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLGroup group = createGroup(CQLLogicalOperator.AND, targetAttribute, association2);

        return createTargetQuery(TissueSpecimen.class, group);
    }

    /**
     * 
     * @param ppi - ppi
     * @return CQLQuery object
     */
    public static CQLQuery getSpecimenMalePPI(String ppi) {
        final CQLAssociation association3 = createAssociationWithIDNotNullandOtherAttribute("participant", "gender",
                "Male Gender", CQLPredicate.EQUAL_TO, CQLLogicalOperator.AND);
        final CQLAttribute attribute1Association2 = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLAttribute attribute2Association2 = createAttribute("protocolParticipantIdentifier", ppi,
                CQLPredicate.EQUAL_TO);
        final CQLGroup group3 = createGroup(CQLLogicalOperator.AND, attribute1Association2, attribute2Association2,
                association3);

        final CQLAssociation association2 = createAssociation(CollectionProtocolRegistration.class,
                "collectionProtocolRegistration");
        association2.setGroup(group3);
        final CQLAttribute attribute1Association1 = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLGroup group2 = createGroup(CQLLogicalOperator.AND, attribute1Association1, association2);

        final CQLAssociation association1 = createAssociation(SpecimenCollectionGroup.class, "specimenCollectionGroup");
        association1.setGroup(group2);

        final CQLAttribute targetAttribute = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLGroup group1 = createGroup(CQLLogicalOperator.AND, targetAttribute, association1);

        return createTargetQuery(Specimen.class, group1);
    }

    private static CQLQuery createTargetQuery(Class<?> klass, Object... cQLObjects) {
        final CQLObject target = new CQLObject();
        target.setName(klass.getName());

        for (Object object : cQLObjects) {
            if (object instanceof CQLAttribute) {
                target.setAttribute((CQLAttribute) object);
            } else if (object instanceof CQLAssociation) {
                target.setAssociation((CQLAssociation) object);
            } else if (object instanceof CQLGroup) {
                target.setGroup((CQLGroup) object);
            }
        }

        final CQLQuery cqlQuery = new CQLQuery();
        cqlQuery.setTarget(target);

        return cqlQuery;
    }

    private static CQLAttribute createAttribute(String name, String value, CQLPredicate predicate) {
        final CQLAttribute attribute = new CQLAttribute();
        attribute.setName(name);

        if (CQLPredicate.IS_NOT_NULL != predicate || CQLPredicate.IS_NULL != predicate) {
            attribute.setValue(value);
        }

        attribute.setPredicate(predicate);
        return attribute;
    }

    private static CQLGroup createGroup(CQLLogicalOperator operator, Object... objects) {
        final CQLGroup group = new CQLGroup();

        for (Object object : objects) {
            if (object instanceof CQLAttribute) {
                group.addAttribute((CQLAttribute) object);
            } else if (object instanceof CQLAssociation) {
                group.addAssociation((CQLAssociation) object);
            }
        }
        group.setLogicOperator(operator);

        return group;
    }

    private static CQLAssociation createAssociation(Class<?> klass, String targetRoleName) {
        final CQLAssociation association = new CQLAssociation();
        association.setName(klass.getName());
        association.setTargetRoleName(targetRoleName);

        return association;
    }

    private static CQLAssociation createAssociationWithIDNotNullandOtherAttribute(String roleName, String name,
            String value, CQLPredicate predicate, CQLLogicalOperator operator) {

        final CQLAttribute attribute1 = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLAttribute attribute2 = createAttribute(name, value, predicate);

        final CQLGroup group = createGroup(operator, attribute1, attribute2);

        final CQLAssociation association = createAssociation(CollectionProtocol.class, roleName);
        association.setGroup(group);

        return association;
    }

    private static CQLAssociation createCPAssociationWithIDandTitle(String shortTitle) {
        return createAssociationWithIDNotNullandOtherAttribute(COLLECTION_PROTOCOL, SHORT_TITLE, shortTitle,
                CQLPredicate.EQUAL_TO, CQLLogicalOperator.AND);
    }

    private static CQLAssociation createCPRAssociationWithIDandCPAssociation(String shortTitle) {
        final CQLAssociation association1 = createCPAssociationWithIDandTitle(shortTitle);
        final CQLAttribute attribute = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLGroup group = createGroup(CQLLogicalOperator.AND, attribute, association1);

        final CQLAssociation association2 = createAssociation(CollectionProtocolRegistration.class,
                "collectionProtocolRegistration");
        association2.setGroup(group);

        return association2;
    }

    private static CQLAssociation createSCGAssociationWithIDandCPRAssociation(String shortTitle) {
        final CQLAssociation association1 = createCPRAssociationWithIDandCPAssociation(shortTitle);
        final CQLAttribute attribute = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLGroup group = createGroup(CQLLogicalOperator.AND, attribute, association1);

        final CQLAssociation association2 = createAssociation(SpecimenCollectionGroup.class, "specimenCollectionGroup");
        association2.setGroup(group);

        return association2;
    }

    /**
     * Get the CQLQuery for the SpecimenCollectionGroup for given shortTitle & label
     * 
     * @param shortTitle - shortTitle of CollectionProtocol
     * @param cpLabel - CollectionPointLabel of CollectionProtocolEvent
     * @return CQLQuery object
     */
    public static CQLQuery getSpecimenCollectionGroupListQuery(String shortTitle, String cpLabel) {
        final CQLAttribute association2Attribute = createAttribute(SHORT_TITLE, shortTitle, CQLPredicate.EQUAL_TO);
        final CQLAssociation association2 = createAssociation(CollectionProtocol.class, COLLECTION_PROTOCOL);
        association2.setAttribute(association2Attribute);

        final CQLAttribute association11Attribute = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLAttribute association12Attribute = createAttribute("collectionPointLabel", cpLabel,
                CQLPredicate.EQUAL_TO);
        final CQLGroup group1 = createGroup(CQLLogicalOperator.AND, association11Attribute, association12Attribute,
                association2);
        final CQLAssociation association1 = createAssociation(CollectionProtocolEvent.class, "collectionProtocolEvent");
        association1.setGroup(group1);

        final CQLAttribute targetAttribute = createAttribute("id", null, CQLPredicate.IS_NOT_NULL);
        final CQLGroup group0 = createGroup(CQLLogicalOperator.AND, targetAttribute, association1);

        return createTargetQuery(SpecimenCollectionGroup.class, group0);
    }

}
