package gov.nih.nci.integration.exception;

public enum IntegrationError {
	_1000(1000, ErrorType.UNKNOWN, "Unexpected error."),
	_1001(1001, ErrorType.VALIDATION, "The participant registration format is invalid for caTissue."),
	_1002(1002, ErrorType.VALIDATION, "No collection protocol was found in caTissue for the identifier ${0}."),
	_1003(1003, ErrorType.VALIDATION, "Participant ${0} is not registered to collection protocol ${1}."),
	_1004(1004, ErrorType.SECURITY, "Authentication to caTissue failed."),
	_1005(1005, ErrorType.SECURITY, "Insufficient privileges to register participant ${0} to collection protocol ${1}."),
	_1006(1006, ErrorType.SECURITY, "Insufficient privileges to deregister participant ${0} to collection protocol ${1}."),
	_1007(1007, ErrorType.VALIDATION, "Participant ${0} is already registered to collection protocol ${1}."),
	
	_1008(1008, ErrorType.VALIDATION, "The participant registration format is invalid for caAERS."),
	_1009(1009, ErrorType.VALIDATION, "No study was found in caAERS for the identifier ${0}."),
	_1010(1010, ErrorType.VALIDATION, "Participant ${0} is not registered to study ${1}."),
	_1011(1011, ErrorType.SECURITY, "Authentication to caAERS failed."),
	_1012(1012, ErrorType.SECURITY, "Insufficient privileges to register participant ${0} to caAERS ${1}."),
	_1013(1013, ErrorType.SECURITY, "Insufficient privileges to deregister participant ${0} to study ${1}."),
	_1014(1014, ErrorType.VALIDATION, "Participant ${0} is already registered to study ${1}."),	
	_1015(1015, ErrorType.SECURITY, "Authentication to iHub failed."),
	_1016(1016, ErrorType.VALIDATION, "The TRIM registration message is invalid."),
	_1017(1017, ErrorType.TRANSFORMATION, "Registration transformation could not be found."),
	_1018(1018, ErrorType.TRANSFORMATION, "Registration transformation failed."),
	_1019(1019, ErrorType.TRANSMISSION, "Could not connect to caTissue."),
	_1020(1020, ErrorType.TRANSMISSION, "Could not connect to caAERS."),
	_1021(1021, ErrorType.TRANSMISSION, "Error retrieving routing information."),
	_1022(1022, ErrorType.SECURITY, "Error retrieving security information.");
		
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
