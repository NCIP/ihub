/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.exception;


public enum IntegrationError {
	
	_1000(1000, ErrorType.UNKNOWN, "Unexpected error."),
	_1001(1001, ErrorType.VALIDATION, "The participant registration format is invalid for caTissue."),
	_1002(1002, ErrorType.VALIDATION, "No collection protocol was found in caTissue for the identifier %1$s."),
	_1003(1003, ErrorType.VALIDATION, "Participant %1$s is not registered to collection protocol %2$s."),
	_1004(1004, ErrorType.SECURITY, "Authentication to caTissue failed."),
	_1005(1005, ErrorType.SECURITY, "Insufficient privileges to register participant %1$s to collection protocol %2$s."),
	_1006(1006, ErrorType.SECURITY, "Insufficient privileges to deregister participant %1$s to collection protocol %2$s."),
	_1007(1007, ErrorType.VALIDATION, "Participant %1$s is already registered to collection protocol %2$s."),
	
	_1008(1008, ErrorType.VALIDATION, "The participant registration format is invalid for caAERS."),
	_1009(1009, ErrorType.VALIDATION, "No study was found in caAERS for the identifier %1$s."),
	_1010(1010, ErrorType.VALIDATION, "Participant %1$s is not registered to study %2$s."),
	_1011(1011, ErrorType.SECURITY, "Authentication to caAERS failed."),
	_1012(1012, ErrorType.SECURITY, "Insufficient privileges to register participant %1$s to caAERS %2$s."),
	_1013(1013, ErrorType.SECURITY, "Insufficient privileges to deregister participant %1$s to study %2$s."),
	_1014(1014, ErrorType.VALIDATION, "Participant %1$s is already registered to study %2$s."),	
	_1015(1015, ErrorType.SECURITY, "Authentication to iHub failed."),
	_1016(1016, ErrorType.VALIDATION, "The TRIM registration message is invalid."),
	_1017(1017, ErrorType.TRANSFORMATION, "Registration transformation could not be found."),
	_1018(1018, ErrorType.TRANSFORMATION, "Registration transformation failed.%1$s"),
	_1019(1019, ErrorType.TRANSMISSION, "Could not connect to caTissue."),
	_1020(1020, ErrorType.TRANSMISSION, "Could not connect to caAERS."),
	_1021(1021, ErrorType.TRANSMISSION, "Error retrieving routing information."),
	_1022(1022, ErrorType.SECURITY, "Error retrieving security information."),
	_1023(1017, ErrorType.TRANSFORMATION, "Adverse Event transformation could not be found."),
	_1024(1018, ErrorType.TRANSFORMATION, "Adverse Event transformation failed.%1$s"),
	_1025(1025, ErrorType.TRANSFORMATION, "TransformerConfigurationException occured while initializing the transformer."),
	_1026(1026, ErrorType.TRANSFORMATION, "TransformerException occured while initializing the transformer."),
	_1027(1027, ErrorType.TRANSFORMATION, "TransformerException occured while performing xsl transformation on source XML."),
	
	_1041(1041, ErrorType.TRANSFORMATION, "Transformation to CaCISRequest failed.%1$s"),
	_1042(1042, ErrorType.TRANSMISSION, "Could not persist source message."),
	_1043(1042, ErrorType.TRANSMISSION, "Error from CaCISChannel: %1$s"),
	_1050(1050, ErrorType.VALIDATION, "Unknown message type."),
	
	_1051(1051, ErrorType.TRANSMISSION, "Error while invoking CaTissueClient: %1$s"),
	_1052(1052, ErrorType.TRANSMISSION, "Error initializing CaTissueClient: %1$s"),
	_1053(1053, ErrorType.TRANSMISSION, "Error while invoking CaAERSClient: %1$s"),
	_1054(1054, ErrorType.TRANSMISSION, "Error initializing CaAERSClient: %1$s"),
		
	_1060(1060, ErrorType.VALIDATION, "New Transformer cannot be initialized. TransformerFactory is null!"),
	_1061(1061, ErrorType.VALIDATION, "New Transformer cannot be initialized. XSL file name cannot be empty!"),
	_1062(1062, ErrorType.VALIDATION, "Requires a ServiceBroadcaster!"),
	_1063(1063, ErrorType.VALIDATION, "Requires a valid referenceMessageId!"),
	_1064(1064, ErrorType.VALIDATION, "Requires a non-empty message!"),
	_1065(1065, ErrorType.VALIDATION, "Requires a ServiceInvocationStrategy!"),
	_1066(1066, ErrorType.VALIDATION, "Cannot authenticate user."),
	_1067(1067, ErrorType.VALIDATION, "Url should be using secured protocol to use Globus Credentials."),
	_1068(1068, ErrorType.VALIDATION, "Credentials can not be null."),
	_1069(1069, ErrorType.VALIDATION, "Url security feature for groups is not supported."),
	_1070(1070, ErrorType.VALIDATION, "User Groups Collection cannot be empty."),
	_1071(1071, ErrorType.VALIDATION, "Url security feature for username is not supported."),
	_1072(1072, ErrorType.VALIDATION, "Username cannot be empty."),
	_1073(1073, ErrorType.VALIDATION, "Error authenticating user:"),
	_1074(1074, ErrorType.VALIDATION, "Name of the service can not be empty."),
	_1075(1075, ErrorType.VALIDATION, "Change the configuration file!"),
	
	
	_1081(1081, ErrorType.MALFORM, "MalformedURLException Occured."),
	_1082(1082, ErrorType.MALFORM, "GlobusCredentialException Occured."),
	_1083(1083, ErrorType.MALFORM, "BeansException Occured.");
		
	private int errorCode;
	
	private ErrorType errorType;
	
	private String msgTemplate;

	private IntegrationError(int errorCode, ErrorType errorType, String msgTemplate) {
		this.errorCode = errorCode;
		this.errorType = errorType;
		this.msgTemplate = msgTemplate;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public String getMessage(Object...objects) {
		if(objects == null) {
			return msgTemplate;
		}
		return String.format(msgTemplate, objects);
	}
	
}
