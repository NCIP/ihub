/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.util;

public class IntegrationConstants {
	
	//response tags
	public static final String NS = "http://caXchange.nci.nih.gov/messaging";
	public static final String ROOT_NODE = "caXchangeRequestMessage";
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
	
	public static final String SERVICE_TYPE_REGISTRATION = "Registration";
	public static final String SERVICE_TYPE_AE = "AE";
	
	//overall response status
	public static final String OVERALL_RESPONSE_STATUS_SUCCESS = "SUCCESS";
	public static final String OVERALL_RESPONSE_STATUS_FAILURE = "FAILURE";
	
	//target response status
	public static final String TARGET_RESPONSE_STATUS_SUCCESSFUL = "SUCCESSFUL";
	public static final String TARGET_RESPONSE_STATUS_FAULT = "FAULT";
	public static final String TARGET_RESPONSE_STATUS_TIMEOUT = "TIMEOUT";
	
	//target details
	public static final String TARGET_DETAIL_CAAERS = "caAERS";	
	//FIXIT: Keeping these to match lookup table type_ihub
	public static final String TARGET_DETAIL_PSC = "PSC";
	public static final String TARGET_DETAIL_CTOM = "CTOM";
	public static final String TARGET_DETAIL_C3PR = "C3PR";
	public static final String TARGET_DETAIL_DELEGATION = "DELEGATION";
	public static final String TARGET_DETAIL_C3D = "C3D";
	public static final String TARGET_DETAIL_MEDIDATA_RAVE = "MEDIDATA_RAVE";
	public static final String TARGET_DETAIL_VALIDATION = "VALIDATION";
	public static final String TARGET_DETAIL_AUTHENTICATION = "AUTHENTICATION";

	//static responses
	public static final String SUCCESS_RESULT_ON_VOID_RETURN = "<result>success</result>";
	
	public static final String MIRTH_HTTP_LISTENER_USER = "mirth.http.listener.user";
	public static final String MIRTH_HTTP_LISTENER_PASSWORD = "mirth.http.listener.password";
	public static final String MIRTH_HTTP_LISTENER_URL = "mirth.http.listener.url";
}
