package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.sr;

import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.CD;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.ENPN;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.ENXP;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.EntityNamePartQualifier;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.EntityNamePartType;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.II;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.NullFlavor;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.PerformedSubjectMilestone;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.RegisterSubjectRequest;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.ST;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.ScheduledArm;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.ScheduledEpoch;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.Set_EntityNamePartQualifier;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.StudySubject;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.TS;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.Uid;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.CCTransformException;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.CCTransformer;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.CCTransformerConstants;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SubjectRegistrationTransformer implements CCTransformer {
	Logger logger = LogManager.getLogger(SubjectRegistrationTransformer.class);

	public Object convert2Request(String xml) throws CCTransformException {
		RegisterSubjectRequest request = new RegisterSubjectRequest();
		try {
			XRegisterSubjectRequest xrequest = readRegisterSubjectRequestXML(xml);
			StudySubject studySubject = createStudySubject(xrequest);
			request.setStudySubject(studySubject);
			PerformedSubjectMilestone registrationMilestone = createRegistrationMilestone(xrequest);
			request.setPerformedSubjectMilestone(registrationMilestone);
			ScheduledEpoch scheduledEpoch = createScheduledEpoch(xrequest);
			request.setScheduledEpoch(scheduledEpoch);
			ScheduledArm scheduledArm = createScheduledArm(xrequest);
			request.setScheduledArm(scheduledArm);
			OMElement requestElement = request.getOMElement(
					RegisterSubjectRequest.MY_QNAME, OMAbstractFactory
							.getOMFactory());
			logger.info("GENERIC CLINICAL CONNECTOR REQUEST: "
					+ requestElement.toString());
			return request;
		} catch (Exception e) {
			logger
					.error(
							"Error transforming xml to subject registration request",
							e);
			logger.error("XML:" + xml);
			throw new CCTransformException(
					"Error transforming xml to subject registration request", e);
		}
	}

	public String convert2RequestString(String xml) throws CCTransformException {
		try {
			RegisterSubjectRequest request = new RegisterSubjectRequest();
			request = (RegisterSubjectRequest) convert2Request(xml);
			OMElement requestElement = request.getOMElement(request.MY_QNAME,
					OMAbstractFactory.getOMFactory());
			logger.info("REQUEST STRING: " + requestElement.toString());
			return requestElement.toString();
		} catch (Exception e) {
			logger
					.error(
							"Error transforming xml to subject registration request",
							e);
			logger.error("XML:" + xml);
			throw new CCTransformException(
					"Error transforming xml to subject registration request", e);
		}
	}

	public Object convert2RollbackRequest(String xml)
			throws CCTransformException {
		RegisterSubjectRequest request = new RegisterSubjectRequest();
		try {
			XRegisterSubjectRequest xrequest = readRegisterSubjectRequestXML(xml);
			StudySubject studySubject = createStudySubject(xrequest);
			request.setStudySubject(studySubject);
			PerformedSubjectMilestone registrationMilestone = createRegistrationMilestone(xrequest);
			request.setPerformedSubjectMilestone(registrationMilestone);
			ScheduledEpoch scheduledEpoch = createScheduledEpoch(xrequest);
			request.setScheduledEpoch(scheduledEpoch);
			ScheduledArm scheduledArm = createScheduledArm(xrequest);
			request.setScheduledArm(scheduledArm);
			return request;
		} catch (Exception e) {
			logger
					.error(
							"Error transforming xml to rollback subject registration request",
							e);
			logger.error("XML:" + xml);
			throw new CCTransformException(
					"Error transforming xml to rollback subject registration request",
					e);
		}
	}

	public String convert2RollbackRequestString(String xml)
			throws CCTransformException {
		try {
			RegisterSubjectRequest request = new RegisterSubjectRequest();
			request = (RegisterSubjectRequest) convert2RollbackRequest(xml);
			OMElement requestElement = request.getOMElement(request.MY_QNAME,
					OMAbstractFactory.getOMFactory());
			return requestElement.toString();
		} catch (Exception e) {
			logger
					.error(
							"Error transforming xml to subject registration request",
							e);
			logger.error("XML:" + xml);
			throw new CCTransformException(
					"Error transforming xml to subject registration request", e);
		}
	}

	public Node insertPatientPosition(Document input, String position,
			String targetSystemName) throws Exception {
		Document output = input;
		Element root = output.getDocumentElement();
		String namespace = root.getNamespaceURI();
		String prefix = root.getPrefix();

		Element ppNode = output.createElementNS(namespace, "identifier");

		Element positionValue = output.createElementNS(namespace, "value");
		positionValue.setTextContent(position);

		Element systemName = output.createElementNS(namespace, "systemName");
		systemName.setTextContent(targetSystemName);

		Element type = output.createElementNS(namespace, "type");
		type.setTextContent("Patient Position");

		Attr attr = output.createAttributeNS(namespace, "type");
		attr.setValue("SystemAssignedIdentifierType");
		attr.setPrefix("xsi");

		ppNode.appendChild(positionValue);
		ppNode.appendChild(systemName);
		ppNode.appendChild(type);
		ppNode.setAttribute("xmlns:xsi",
				"http://www.w3.org/2001/XMLSchema-instance");
		ppNode.setAttributeNode(attr);
		root.appendChild(ppNode);

		if (prefix != null && !"".equals(prefix.trim())) {
			ppNode.setPrefix(prefix);
			positionValue.setPrefix(prefix);
			systemName.setPrefix(prefix);
			type.setPrefix(prefix);
			attr.setValue(prefix + ":" + "SystemAssignedIdentifierType");
		}

		return output;
	}

	protected StudySubject createStudySubject(XRegisterSubjectRequest xrequest) {
		XStudySubject xStudySubject = xrequest.studySubject;

		StudySubject studySubject = new StudySubject();
		TS birthDate = new TS();
		birthDate.setValue(checkEmpty(xStudySubject.birthDate));
		studySubject.setBirthDate(birthDate);

		studySubject.setIdentifier(getIdentifier(xStudySubject.identifier,
				xStudySubject.identifierName));

		ENPN nameENPN = new ENPN();
		ENXP lastNameENXP = new ENXP();
		lastNameENXP.setType(EntityNamePartType.FAM);
		lastNameENXP.setValue(getInitials(checkEmpty(xStudySubject.lastName)));
		Set_EntityNamePartQualifier entityNamePartQualifiers = new Set_EntityNamePartQualifier();
		entityNamePartQualifiers
				.setEntityNamePartQualifier(new EntityNamePartQualifier[] { EntityNamePartQualifier.IN });
		lastNameENXP.setQualifier(entityNamePartQualifiers);
		nameENPN.addPart(lastNameENXP);
		ENXP firstNameENXP = new ENXP();
		firstNameENXP.setType(EntityNamePartType.GIV);
		firstNameENXP
				.setValue(getInitials(checkEmpty(xStudySubject.firstName)));
		firstNameENXP.setQualifier(entityNamePartQualifiers);
		nameENPN.addPart(firstNameENXP);

		studySubject.setName(nameENPN);

		CD raceCode = new CD();
		raceCode.setCode(checkEmpty(xStudySubject.raceCode));
		studySubject.setRaceCode(raceCode);

		CD sexCode = new CD();
		sexCode.setCode(checkEmpty(xStudySubject.sexCode));
		studySubject.setSexCode(sexCode);

		studySubject.setStudyIdentifier(getIdentifier(
				xStudySubject.studyIdentifier,
				CCTransformerConstants.STUDY_IDENTIFIER_NAME));

		studySubject.setStudySiteIdentifier(getIdentifier(
				xStudySubject.studySiteIdentifier,
				CCTransformerConstants.STUDY_SITE_IDENTIFIER_NAME));

		return studySubject;
	}

	protected PerformedSubjectMilestone createRegistrationMilestone(
			XRegisterSubjectRequest xrequest) {
		XPerformedSubjectMilestone xPerformedSubjectMilestone = xrequest.performedSubjectMilestone;

		PerformedSubjectMilestone registrationMilestone = new PerformedSubjectMilestone();

		TS informedConsentDate = new TS();
		informedConsentDate
				.setValue(checkEmpty(xPerformedSubjectMilestone.informedConsentDate));
		registrationMilestone.setInformedConsentDate(informedConsentDate);

		TS registrationDate = new TS();
		registrationDate
				.setValue(checkEmpty(xPerformedSubjectMilestone.registrationDate));
		registrationMilestone.setRegistrationDate(registrationDate);

		registrationMilestone.setRegistrationSiteIdentifier(getIdentifier(
				xPerformedSubjectMilestone.registrationSiteIdentifier,
				xPerformedSubjectMilestone.registrationSiteIdentifierName));

		return registrationMilestone;
	}

	protected ScheduledEpoch createScheduledEpoch(
			XRegisterSubjectRequest xrequest) {
		XScheduledEpoch xScheduledEpoch = xrequest.scheduledEpoch;

		ScheduledEpoch scheduledEpoch = new ScheduledEpoch();

		String description = checkEmpty(xScheduledEpoch.description);
		ST descriptionST = new ST();
		descriptionST.setValue(description);

		String name = checkEmpty(xScheduledEpoch.name);
		ST nameST = new ST();
		nameST.setValue(name);

		TS scheduledStartDateTS = new TS();
		scheduledStartDateTS
				.setValue(checkEmpty(xScheduledEpoch.scheduledStartDate));

		CD typeCodeCD = new CD();
		typeCodeCD.setCode(checkEmpty(xScheduledEpoch.typeCode));

		scheduledEpoch.setDescription(descriptionST);
		scheduledEpoch.setName(nameST);
		scheduledEpoch.setScheduledStartDate(scheduledStartDateTS);
		scheduledEpoch.setTypeCode(typeCodeCD);

		return scheduledEpoch;
	}

	protected ScheduledArm createScheduledArm(XRegisterSubjectRequest xrequest) {
		XScheduledArm xScheduledArm = xrequest.scheduledArm;

		ScheduledArm scheduledArm = new ScheduledArm();

		String description = checkEmpty(xScheduledArm.description);
		ST descriptionST = new ST();
		descriptionST.setValue(description);

		String name = checkEmpty(xScheduledArm.name);
		ST nameST = new ST();
		nameST.setValue(name);

		CD typeCodeCD = new CD();
		typeCodeCD.setCode(checkEmpty(xScheduledArm.typeCode));

		scheduledArm.setDescription(descriptionST);
		scheduledArm.setName(nameST);
		scheduledArm.setTypeCode(typeCodeCD);

		return scheduledArm;
	}

	protected String checkEmpty(String s) {
		return (s != null && !"".equals(s)) ? s : "";
	}

	protected String getInitials(String s) {
		if (s.length() > 1) {
			return s.substring(0, 1).toUpperCase();
		}
		return s.toUpperCase();
	}

	protected II getIdentifier(String extension, String identifierName) {
		II identifier = new II();
		if ((extension == null) || (extension.equals(""))) {
			identifier.setNullFlavor(NullFlavor.NI);
		} else {
			identifier.setIdentifierName(identifierName);
			Uid uid = new Uid();
			uid
					.setUid(CCTransformerConstants.NAME_ROOT_MAP
							.get(identifierName));
			identifier.setRoot(uid);
			identifier.setExtension(checkEmpty(extension));
		}
		return identifier;
	}

	protected String checkDate(String s) {
		if (s != null && s.indexOf('-') != -1) {
			s = s.replaceAll("-", "");
		}
		return s;
	}

	public XRegisterSubjectRequest readRegisterSubjectRequestXML(String xml)
			throws Exception {
		XRegisterSubjectRequest xrequest = new XRegisterSubjectRequest();
		XPerformedSubjectMilestone performedSubjectMilestone = new XPerformedSubjectMilestone();
		XStudySubject studySubject = new XStudySubject();
		XScheduledEpoch scheduledEpoch = new XScheduledEpoch();
		;
		XScheduledArm scheduledArm = new XScheduledArm();
		xrequest.performedSubjectMilestone = performedSubjectMilestone;
		xrequest.studySubject = studySubject;
		xrequest.scheduledEpoch = scheduledEpoch;
		xrequest.scheduledArm = scheduledArm;

		XPathReader reader = new XPathReader(xml);

		performedSubjectMilestone.informedConsentDate = checkDate(reader
				.read("ccts:registration/ccts:informedConsentFormSignedDate"));
		performedSubjectMilestone.registrationDate = checkDate(reader
				.read("ccts:registration/ccts:startDate"));
		performedSubjectMilestone.registrationSiteIdentifier = reader
				.read("ccts:registration/ccts:participant/ccts:identifier/ccts:healthcareSite/ccts:nciInstituteCode");
		performedSubjectMilestone.registrationSiteIdentifierName = CCTransformerConstants.REGISTRATION_SITE_IDENTIFIER_NAME;

		studySubject.birthDate = checkDate(reader
				.read("ccts:registration/ccts:participant/ccts:birthDate"));
		studySubject.identifier = reader
				.read("ccts:registration/ccts:participant/ccts:identifier/ccts:value");
		studySubject.identifierName = reader
				.read("ccts:registration/ccts:participant/ccts:identifier/ccts:type");
		studySubject.firstName = reader
				.read("ccts:registration/ccts:participant/ccts:firstName");
		studySubject.lastName = reader
				.read("ccts:registration/ccts:participant/ccts:lastName");
		studySubject.raceCode = reader
				.read("ccts:registration/ccts:participant/ccts:raceCode");
		studySubject.sexCode = reader
				.read("ccts:registration/ccts:participant/ccts:administrativeGenderCode");
		studySubject.studyIdentifier = reader
				.read("ccts:registration/ccts:studyRef/ccts:identifier/ccts:value");
		studySubject.studyIdentifierName = reader
				.read("ccts:registration/ccts:studyRef/ccts:identifier/ccts:type");
		studySubject.studySiteIdentifier = reader
				.read("ccts:registration/ccts:studySite/ccts:healthcareSite/ccts:nciInstituteCode");
		studySubject.studySiteIdentifierName = "nciInstituteCode";

		scheduledEpoch.description = reader
				.read("ccts:registration/ccts:scheduledEpoch/ccts:epoch/ccts:descriptionText");
		scheduledEpoch.name = reader
				.read("ccts:registration/ccts:scheduledEpoch/ccts:epoch/ccts:name");
		scheduledEpoch.scheduledStartDate = checkDate(reader
				.read("ccts:registration/ccts:scheduledEpoch/ccts:startDate"));
		scheduledEpoch.typeCode = "";// not in xml

		scheduledArm.description = reader
				.read("ccts:registration/ccts:scheduledEpoch/ccts:scheduledArm/ccts:arm/ccts:descriptionText");
		scheduledArm.name = reader
				.read("ccts:registration/ccts:scheduledEpoch/ccts:scheduledArm/ccts:arm/ccts:name");
		scheduledArm.typeCode = "";// not in xml

		return xrequest;
	}
}
