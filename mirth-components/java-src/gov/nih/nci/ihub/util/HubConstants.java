package gov.nih.nci.ihub.util;

public class HubConstants {

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

}
