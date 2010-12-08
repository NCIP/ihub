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
	
	//client-config.wsdd location
	public static final String WSDD_FILE_LOCATION_PREFIX = "wsdd/";
	public static final String WSDD_FILE_LOCATION_SUFFIX = "/client-config.wsdd";

}
