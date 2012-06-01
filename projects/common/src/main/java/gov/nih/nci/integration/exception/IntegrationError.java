package gov.nih.nci.integration.exception;

public enum IntegrationError {

	_1000(1000, ErrorType.UNKNOWN, "Unexpected error."), // USED
	_1001(1001, ErrorType.VALIDATION,
			"The participant registration format is invalid for caTissue."), _1002(
			1002, ErrorType.VALIDATION,
			"No collection protocol was found in caTissue for the identifier %1$s."), _1003(
			1003, ErrorType.VALIDATION,
			"Participant %1$s is not registered to collection protocol %2$s."), _1004(
			1004, ErrorType.SECURITY, "Authentication to caTissue failed."), _1005(
			1005,
			ErrorType.SECURITY,
			"Insufficient privileges to register participant %1$s to collection protocol %2$s."), _1006(
			1006,
			ErrorType.SECURITY,
			"Insufficient privileges to deregister participant %1$s to collection protocol %2$s."), _1007(
			1007, ErrorType.VALIDATION,
			"Participant %1$s is already registered to collection protocol %2$s."),

	_1008(1008, ErrorType.VALIDATION,
			"The participant registration format is invalid for caAERS."), _1009(
			1009, ErrorType.VALIDATION, "No study was found in caAERS or %1$s."), _1010(
			1010, ErrorType.VALIDATION,
			"Unable to retrieve participant from caAERS. Check the identifier"), // USED
	_1011(1011, ErrorType.SECURITY, "Authentication to caAERS failed."), _1012(
			1012, ErrorType.VALIDATION,
			"Organisation not found in caAERS or %1$s"), _1013(1013,
			ErrorType.SECURITY,
			"Insufficient privileges to deregister participant %1$s to study %2$s."), _1014(
			1014, ErrorType.VALIDATION,
			"%1$s or participant may be already registered to study."), _1015(
			1015, ErrorType.SECURITY, "Authentication to iHub failed."), _1016(
			1016, ErrorType.VALIDATION,
			"The TRIM registration message is invalid."), _1017(1017,
			ErrorType.TRANSFORMATION,
			"Registration transformation could not be found."), _1018(1018,
			ErrorType.TRANSFORMATION, "Registration transformation failed.%1$s"), // USED
	_1019(1019, ErrorType.TRANSMISSION, "Could not connect to caTissue."), _1020(
			1020, ErrorType.TRANSMISSION, "Could not connect to caAERS."),

	_1021(1021, ErrorType.TRANSMISSION, "Error retrieving routing information."), _1022(
			1022, ErrorType.SECURITY, "Error retrieving security information."),

	_1023(1017, ErrorType.TRANSFORMATION,
			"Adverse Event transformation could not be found."), _1024(1018,
			ErrorType.TRANSFORMATION,
			"Adverse Event transformation failed.%1$s"), // USED
	_1025(1025, ErrorType.TRANSFORMATION,
			"TransformerConfigurationException occured while initializing the transformer."), // USED
	_1026(1026, ErrorType.TRANSFORMATION,
			"TransformerException occured while initializing the transformer."), // USED
	_1027(
			1027,
			ErrorType.TRANSFORMATION,
			"TransformerException occured while performing xsl transformation on source XML."), // USED

	_1041(1041, ErrorType.TRANSFORMATION,
			"Transformation to CaCISRequest failed.%1$s"), // USED
	_1042(1042, ErrorType.TRANSMISSION, "Could not persist source message."), // USED
	_1043(1043, ErrorType.TRANSMISSION, "Error from CaCISChannel: %1$s"), // USED
	_1050(1050, ErrorType.VALIDATION, "Unknown message type."), // USED

	_1051(1051, ErrorType.TRANSMISSION,
			"Error while invoking CaTissueClient: %1$s"), // USED
	_1052(1052, ErrorType.TRANSMISSION,
			"Error initializing CaTissueClient: %1$s"), // USED
	_1053(1053, ErrorType.TRANSMISSION,
			"Error while invoking CaAERSClient: %1$s"), // USED
	_1054(1054, ErrorType.TRANSMISSION, "Error initializing CaAERSClient: %1$s"), // USED
	_1055(1055, ErrorType.TRANSMISSION, "Could not connect to CaCISChannel!"), // USED

	_1060(1060, ErrorType.VALIDATION,
			"New Transformer cannot be initialized. TransformerFactory is null!"), // USED
	_1061(1061, ErrorType.VALIDATION,
			"New Transformer cannot be initialized. XSL file name cannot be empty!"), // USED
	_1062(1062, ErrorType.VALIDATION, "Requires a ServiceBroadcaster!"), // USED
	_1063(1063, ErrorType.VALIDATION, "Requires a valid referenceMessageId!"), // USED
	_1064(1064, ErrorType.VALIDATION, "Requires a non-empty message!"), // USED
	_1065(1065, ErrorType.VALIDATION, "Requires a ServiceInvocationStrategy!"), // USED
	_1066(1066, ErrorType.VALIDATION, "Cannot authenticate user."), _1068(1068,
			ErrorType.VALIDATION, "Credentials cannot be null."), _1069(1069,
			ErrorType.VALIDATION,
			"Url security feature for groups is not supported."), _1070(1070,
			ErrorType.VALIDATION, "User Groups Collection cannot be empty."), _1071(
			1071, ErrorType.VALIDATION,
			"Url security feature for username is not supported."), _1072(1072,
			ErrorType.VALIDATION, "Username cannot be empty."), _1073(1073,
			ErrorType.VALIDATION, "Error authenticating user:"), _1074(1074,
			ErrorType.VALIDATION, "Name of the service cannot be empty."), _1075(
			1075, ErrorType.VALIDATION, "Change the configuration file!"),

	_1080(1080, ErrorType.VALIDATION,
			"Specimen with the same LABEL already exists."), // USED
	_1081(1081, ErrorType.VALIDATION, "Specimen Type is invalid."), // USED
	_1082(1082, ErrorType.VALIDATION, "Tissue Side is invalid."), // USED
	_1083(1083, ErrorType.VALIDATION, "Tissue Site is invalid."), // USED
	_1084(1084, ErrorType.VALIDATION,
			"Specimen Collection Group not found in caTissue."), // USED
	_1085(1085, ErrorType.VALIDATION,
			"Available Quantity cannot be greater than the Initial Quantity."), // USED
	_1086(1086, ErrorType.VALIDATION, "Pathological Status is invalid."), // USED
	_1087(1087, ErrorType.VALIDATION,
			"Collection Protocol Event cannot be changed while updating the Specimen."), // USED
	_1088(1088, ErrorType.VALIDATION,
			"Collection Protocol can't be changed while updating the Specimen."), // USED
	_1089(1089, ErrorType.VALIDATION,
			"Specimen Class can't be changed while updating the Specimen."), // USED
	_1090(1090, ErrorType.VALIDATION, "Specimen for given LABEL doesn't exist."), // USED
	_1091(1091, ErrorType.VALIDATION,
			"Collection Protocol was not found in caTissue."), // USED
	_1092(1092, ErrorType.VALIDATION,
			"ConsentTier Statement was not found for given CollectionProtocol in caTissue."); // USED

	private int errorCode;

	private ErrorType errorType;

	private String msgTemplate;

	private IntegrationError(int errorCode, ErrorType errorType,
			String msgTemplate) {
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

	public String getMessage(Object... objects) {
		if (objects == null) {
			return msgTemplate;
		}
		return String.format(msgTemplate, objects);
	}

}
