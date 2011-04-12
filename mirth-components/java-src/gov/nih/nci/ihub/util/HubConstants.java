package gov.nih.nci.ihub.util;

public class HubConstants {

	//response tags
	public static final String NS = "http://caXchange.nci.nih.gov/messaging";
	public static final String REQUEST_ELEMENT = "request";
	public static final String REQUEST_PAYLOAD_ELEMENT = "businessMessagePayload";
	public static final String META_DATA_ELEMENT = "metadata";
	public static final String EXTERNAL_IDENTIFIER = "externalIdentifier";
	public static final String CAXCHANGE_IDENTIFIER = "caXchangeIdentifier";
	public static final String RESPONSE_ELEMENT = "targetResponse";
	public static final String ERROR_RESPONSE_ELEMENT = "targetError";
	public static final String ERROR_CODE_ELEMENT = "errorCode";
	public static final String ERROR_DESCRIPTION_ELEMENT = "errorDescription";
	public static final String TARGET_SERVICE_ID_ELEMENT = "targetServiceIdentifier";
	public static final String TARGET_SERVICE_OP_ELEMENT = "targetServiceOperation";
	public static final String TARGET_STATUS_ELEMENT = "targetMessageStatus";
	public static final String RESPONSE_PAYLOAD_ELEMENT = "targetBusinessMessage";
	public static final String SCHEMA_DEFINITION_ELEMENT = "xmlSchemaDefinition";
	public static final String OPERATION_NAME_ELEMENT = "operationName";
	public static final String SERVICE_TYPE_ELEMENT = "serviceType";

	//error codes
	public static final String OK = "000";
	public static final String UNKNOWN = "001";
	public static final String AXIS_FAULT = "101";
	public static final String MALFORMED_URI = "201";
	public static final String REMOTE_EXCEPTION = "202";
	public static final String CONNECT_EXCEPTION = "203";
	public static final String TIMEOUT = "204";
	public static final String TRANSFORMER_EXCEPTION = "301";
	public static final String DESERIALIZATION_EXCEPTION = "302";
	public static final String SAX_EXCEPTION = "303";
	public static final String PARSER_CONFIGURATION_EXCEPTION = "304";
	public static final String IO_EXCEPTION = "305";
	public static final String ERROR_STORING_MESSAGE = "306";
	public static final String UNHANDLED_MESSAGE_TYPE = "401";
	public static final String ERROR_INVOKING_DELEGATION_SERVICE = "500";
	public static final String CDS_INTERNAL_FAULT = "501";
	public static final String DELEGATION_FAULT = "502";
	public static final String PERMISSION_DENIED_FAULT = "503";
	public static final String PAYLOAD_VALIDATION_EXCEPTION = "600";
	public static final String ERROR_GETTING_METADATA = "650";
	
	//message status
	public static final String MESSAGE_STATUS_OPEN = "OPEN";
	public static final String MESSAGE_STATUS_IN_PROCESS = "IN_PROCESS";
	public static final String MESSAGE_STATUS_PROCESSED = "PROCESSED";
	public static final String MESSAGE_STATUS_RESPONSE_RETURNED = "RESPONSE_RETURNED";
	
	//service types
	public static final String SERVICE_TYPE_SCHEDULE_MODIFICATION = "SCHEDULE_MODIFICATION";
	public static final String SERVICE_TYPE_LAB_BASED_AE = "LAB_BASED_AE";
	public static final String SERVICE_TYPE_LOAD_LAB_TO_CDMS = "LOAD_LAB_TO_CDMS";
	public static final String SERVICE_TYPE_CT_LAB_DATA = "CT_LAB_DATA";
	public static final String SERVICE_TYPE_STUDY_CREATION = "STUDY_CREATION";
	public static final String SERVICE_TYPE_PERSON = "PERSON";
	public static final String SERVICE_TYPE_ORGANIZATION = "ORGANIZATION";
	public static final String SERVICE_TYPE_CLINICAL_RESEARCH_STAFF = "CLINICAL_RESEARCH_STAFF";
	public static final String SERVICE_TYPE_HEALTH_CARE_FACILITY = "HEALTH_CARE_FACILITY";
	public static final String SERVICE_TYPE_HEALTH_CARE_PROVIDER = "HEALTH_CARE_PROVIDER";
	public static final String SERVICE_TYPE_IDENTIFIED_ORGANIZATION = "IDENTIFIED_ORGANIZATION";
	public static final String SERVICE_TYPE_IDENTIFIED_PERSON = "IDENTIFIED_PERSON";
	public static final String SERVICE_TYPE_ORGANIZATIONAL_CONTACT = "ORGANIZATIONAL_CONTACT";
	public static final String SERVICE_TYPE_RESEARCH_ORGANIZATION = "RESEARCH_ORGANIZATION";
	public static final String SERVICE_TYPE_OVERSIGHT_COMMITTEE = "OVERSIGHT_COMMITTEE";
	public static final String SERVICE_TYPE_STUDY_PROTOCOL = "STUDY_PROTOCOL";
	public static final String SERVICE_TYPE_ARM = "ARM";
	public static final String SERVICE_TYPE_STUDY_SITE = "STUDY_SITE";
	public static final String SERVICE_TYPE_PLANNED_ACTIVITY = "PLANNED_ACTIVITY";
	public static final String SERVICE_TYPE_STUDY_CONTACT = "STUDY_CONTACT";
	public static final String SERVICE_TYPE_STUDY_DISEASE = "STUDY_DISEASE";
	public static final String SERVICE_TYPE_STUDY_OVERALL_STATUS = "STUDY_OVERALL_STATUS";
	public static final String SERVICE_TYPE_STUDY_SITE_CONTACT = "STUDY_SITE_CONTACT";
	public static final String SERVICE_TYPE_STUDY_RELATIONSHIP = "STUDY_RELATIONSHIP";
	public static final String SERVICE_TYPE_STUDY_RESOURCING = "STUDY_RESOURCING";
	public static final String SERVICE_TYPE_STUDY_IND_IDE = "STUDY_IND_IDE";
	public static final String SERVICE_TYPE_STUDY_ONHOLD = "STUDY_ONHOLD";
	public static final String SERVICE_TYPE_STUDY_OUTCOME_MEASURE = "STUDY_OUTCOME_MEASURE";
	public static final String SERVICE_TYPE_STUDY_RECRUITMENT_STATUS = "STUDY_RECRUITMENT_STATUS";
	public static final String SERVICE_TYPE_STUDY_REGULATORY_AUTHORITY = "STUDY_REGULATORY_AUTHORITY";
	public static final String SERVICE_TYPE_STUDY_SITE_ACCRUAL_STATUS = "STUDY_SITE_ACCRUAL_STATUS";
	public static final String SERVICE_TYPE_DOCUMENT = "DOCUMENT";
	public static final String SERVICE_TYPE_DOCUMENT_WORKFLOW_STATUS = "DOCUMENT_WORKFLOW_STATUS";
	public static final String SERVICE_TYPE_ORGANIZATION_BUSINESS_SERVICE = "ORGANIZATION_BUSINESS_SERVICE";
	public static final String SERVICE_TYPE_PERSON_BUSINESS_SERVICE = "PERSON_BUSINESS_SERVICE";
	public static final String SERVICE_TYPE_PO_BUSINESS = "PO_BUSINESS";
	public static final String SERVICE_TYPE_REGISTER_SUBJECT = "REGISTER_SUBJECT";
	public static final String SERVICE_TYPE_CATISSUE = "CATISSUE";
	
	//overall response status
	public static final String OVERALL_RESPONSE_STATUS_SUCCESS = "SUCCESS";
	public static final String OVERALL_RESPONSE_STATUS_FAILURE = "FAILURE";
	
	//target response status
	public static final String TARGET_RESPONSE_STATUS_SUCCESSFUL = "SUCCESSFUL";
	public static final String TARGET_RESPONSE_STATUS_FAULT = "FAULT";
	public static final String TARGET_RESPONSE_STATUS_TIMEOUT = "TIMEOUT";
	
	//target details
	public static final String TARGET_DETAIL_CAAERS = "caAERS";
	public static final String TARGET_DETAIL_PSC = "PSC";
	public static final String TARGET_DETAIL_CTOM = "CTOM";
	public static final String TARGET_DETAIL_C3PR = "C3PR";
	public static final String TARGET_DETAIL_DELEGATION = "DELEGATION";
	public static final String TARGET_DETAIL_C3D = "C3D";
	public static final String TARGET_DETAIL_MEDIDATA_RAVE = "MEDIDATA_RAVE";
	public static final String TARGET_DETAIL_VALIDATION = "VALIDATION";
	public static final String TARGET_DETAIL_AUTHENTICATION = "AUTHENTICATION";
	
	//client-config.wsdd location
	public static final String WSDD_FILE_LOCATION_PREFIX = "wsdd/";
	public static final String WSDD_FILE_LOCATION_SUFFIX = "/client-config.wsdd";
	
	public static final String CC_MRN_NAME = "MRN";
	public static final String CC_COOPERATIVE_GROUP_IDENTIFIER_NAME = "COOPERATIVE_GROUP_IDENTIFIER";
	public static final String CC_CLINICAL_CONNECTOR_ROOT = "2.16.840.1.113883.3.26.7";
	public static final String CC_MRN_ROOT = CC_CLINICAL_CONNECTOR_ROOT+ ".1";
	public static final String CC_COOPERATIVE_GROUP_IDENTIFIER_ROOOT = CC_CLINICAL_CONNECTOR_ROOT+".5";
	public static final String CC_STUDY_IDENTIFIER_NAME = "Study Identifier";
	public static final String CC_STUDY_IDENTIFIER_ROOT = CC_CLINICAL_CONNECTOR_ROOT+ ".2";
	public static final String CC_STUDY_SITE_IDENTIFIER_NAME = "Study Site Identifier";
	public static final String CC_STUDY_SITE_IDENTIFIER_ROOT = CC_CLINICAL_CONNECTOR_ROOT+ ".3";
	public static final String CC_REGISTRATION_SITE_IDENTIFIER_NAME = "nciInstituteCode";
	public static final String CC_REGISTRATION_SITE_IDENTIFIER_ROOT = CC_CLINICAL_CONNECTOR_ROOT+ ".4";
	public static final String CC_PATIENT_POSITION_IDENTIFIER_NAME = "Patient Position";
	public static final String CC_PATIENT_POSITION_IDENTIFIER_ROOT = ".6";
	public static final String CC_STUDY_PROTOCOL_IDENTIFIER_NAME = "Study Protocol";
	public static final String CC_STUDY_PROTOCOL_IDENTIFIER_ROOT = ".7";
	
	public static final String ORCHESTRATION_PBS_TRANSFORM_INPUT_TO_IDENTIFIED_PERSON_XSL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:po=\"http://po.coppa.nci.nih.gov\" xmlns:ISO=\"uri:iso.org:21090\"><xsl:output method=\"xml\" version=\"1.0\" encoding=\"UTF-8\" indent=\"yes\"/><xsl:template match=\"po:Id\" ><IdentifiedPerson xmlns=\"http://po.coppa.nci.nih.gov\" xmlns:ISO=\"uri:iso.org:21090\"> <identifier nullFlavor=\"NI\"/> <playerIdentifier nullFlavor=\"NI\"/> <scoperIdentifier nullFlavor=\"NI\"/> <assignedId><xsl:attribute name=\"root\" ><xsl:apply-templates select=\"@root\" /></xsl:attribute><xsl:attribute name=\"extension\" ><xsl:apply-templates select=\"@extension\" /></xsl:attribute> </assignedId><status code=\"active\"/> </IdentifiedPerson> </xsl:template> <xsl:template match=\"*\"><xsl:copy><xsl:apply-templates select=\"*|@*|text()\" /></xsl:copy></xsl:template></xsl:stylesheet>";
	public static final String ORCHESTRATION_PBS_TRANSFORM_IDENFIFIED_PERSON_TO_PERSON_XSL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:po=\"http://po.coppa.nci.nih.gov\" xmlns:cax=\"http://caXchange.nci.nih.gov/messaging\" xmlns:ISO=\"uri:iso.org:21090\"><xsl:output method=\"xml\" version=\"1.0\" encoding=\"UTF-8\" indent=\"yes\"/><xsl:template match=\"po:playerIdentifier\" ><Id xmlns=\"http://po.coppa.nci.nih.gov\" xmlns:ISO=\"uri:iso.org:21090\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> <xsl:attribute name=\"root\" ><xsl:apply-templates select=\"@root\" /></xsl:attribute><xsl:attribute name=\"extension\" ><xsl:apply-templates select=\"@extension\" /></xsl:attribute> </Id> </xsl:template></xsl:stylesheet>";
	public static final String ORCHESTRATION_PBS_OPERATION_IDENTIFIED_PERSON = "search";
	public static final String ORCHESTRATION_PBS_OPERATION_PERSON = "getById";
	public static final String ORCHESTRATION_PBS_RESULT_IDENTIFIED_PERSON_LOOKUP_ELEMENT = "playerIdentifier";
	public static final String ORCHESTRATION_PBS_EXCEPTION_MESSAGE_IDENTIFIED_PERSON = "CTEP Identifier not found";	
	public static final String ORCHESTRATION_PBS_RESULT_PO_NAMESPACE = "http://po.coppa.nci.nih.gov";
	
	public static final String ORCHESTRATION_OBS_TRANSFORM_INPUT_TO_IDENTIFIED_ORG_XSL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:po=\"http://po.coppa.nci.nih.gov\" xmlns:cax=\"http://caXchange.nci.nih.gov/messaging\" xmlns:ISO=\"uri:iso.org:21090\"><xsl:output method=\"xml\" version=\"1.0\" encoding=\"UTF-8\" indent=\"yes\"/><xsl:template match=\"po:Id\" ><IdentifiedOrganization xmlns=\"http://po.coppa.nci.nih.gov\" xmlns:ISO=\"uri:iso.org:21090\"> <identifier nullFlavor=\"NI\"/> <playerIdentifier nullFlavor=\"NI\"/> <scoperIdentifier nullFlavor=\"NI\"/> <assignedId><xsl:attribute name=\"root\" ><xsl:apply-templates select=\"@root\" /></xsl:attribute><xsl:attribute name=\"extension\" ><xsl:apply-templates select=\"@extension\" /></xsl:attribute> </assignedId><status code=\"active\"/> </IdentifiedOrganization> </xsl:template> <xsl:template match=\"*\"><xsl:copy><xsl:apply-templates select=\"*|@*|text()\" /></xsl:copy></xsl:template></xsl:stylesheet>";	
	public static final String ORCHESTRATION_OBS_TRANSFORM_IDENFIFIED_ORG_TO_ORG_XSL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:po=\"http://po.coppa.nci.nih.gov\" xmlns:cax=\"http://caXchange.nci.nih.gov/messaging\" xmlns:ISO=\"uri:iso.org:21090\"><xsl:output method=\"xml\" version=\"1.0\" encoding=\"UTF-8\" indent=\"yes\"/><xsl:template match=\"po:playerIdentifier\" ><Id xmlns=\"http://po.coppa.nci.nih.gov\" xmlns:ISO=\"uri:iso.org:21090\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> <xsl:attribute name=\"root\" ><xsl:apply-templates select=\"@root\" /></xsl:attribute><xsl:attribute name=\"extension\" ><xsl:apply-templates select=\"@extension\" /></xsl:attribute> </Id> </xsl:template> </xsl:stylesheet>";
	public static final String ORCHESTRATION_OBS_OPERATION_IDENTIFIED_ORG = "search";
	public static final String ORCHESTRATION_OBS_OPERATION_ORG = "getById";
	public static final String ORCHESTRATION_OBS_RESULT_IDENTIFIED_ORG_LOOKUP_ELEMENT = "playerIdentifier";
	public static final String ORCHESTRATION_OBS_EXCEPTION_MESSAGE_IDENTIFIED_ORG = "CTEP Identifier not found";	
	public static final String ORCHESTRATION_OBS_RESULT_PO_NAMESPACE = "http://po.coppa.nci.nih.gov";
}
