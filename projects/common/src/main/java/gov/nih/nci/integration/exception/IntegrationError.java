package gov.nih.nci.integration.exception;

import gov.nih.nci.integration.util.ErrorMessagesUtil;

/**
 * This class contains different errorcodes which will be used within Transcend-iHub integration module. It loads
 * corresponding error messages from the properties file.
 * 
 * @author Vinodh
 * 
 */

public enum IntegrationError {
    // CHECKSTYLE:OFF
    _1000(1000, ErrorType.UNKNOWN),
    _1001(1001, ErrorType.VALIDATION),
    _1002(1002, ErrorType.VALIDATION),
    _1003(1003, ErrorType.VALIDATION),
    _1004(1004, ErrorType.SECURITY),
    _1005(1005, ErrorType.SECURITY),
    _1006(1006, ErrorType.SECURITY),
    _1007(1007, ErrorType.VALIDATION),

    _1008(1008, ErrorType.VALIDATION),
    _1009(1009, ErrorType.VALIDATION),
    _1010(1010, ErrorType.VALIDATION),
    _1011(1011, ErrorType.SECURITY),
    _1012(1012, ErrorType.VALIDATION),
    _1013(1013, ErrorType.SECURITY),
    _1014(1014, ErrorType.VALIDATION),
    _1015(1015, ErrorType.SECURITY),
    _1016(1016, ErrorType.VALIDATION),
    _1017(1017, ErrorType.TRANSFORMATION),
    _1018(1018, ErrorType.TRANSFORMATION),
    _1019(1019, ErrorType.TRANSMISSION),
    _1020(1020, ErrorType.TRANSMISSION),

    _1021(1021, ErrorType.TRANSMISSION),
    _1022(1022, ErrorType.SECURITY),

    _1023(1017, ErrorType.TRANSFORMATION),
    _1024(1018, ErrorType.TRANSFORMATION),
    _1025(1025, ErrorType.TRANSFORMATION),
    _1026(1026, ErrorType.TRANSFORMATION),
    _1027(1027, ErrorType.TRANSFORMATION),

    _1031(1031, ErrorType.VALIDATION),
    _1032(1032, ErrorType.VALIDATION),
    _1033(1033, ErrorType.VALIDATION),
    _1034(1034, ErrorType.VALIDATION),
    _1035(1035, ErrorType.VALIDATION),

    _1041(1041, ErrorType.TRANSFORMATION),
    _1042(1042, ErrorType.TRANSMISSION),
    _1043(1043, ErrorType.TRANSMISSION),
    _1050(1050, ErrorType.VALIDATION),

    _1051(1051, ErrorType.TRANSMISSION),
    _1052(1052, ErrorType.TRANSMISSION),
    _1053(1053, ErrorType.TRANSMISSION),
    _1054(1054, ErrorType.TRANSMISSION),
    _1055(1055, ErrorType.TRANSMISSION),

    _1060(1060, ErrorType.VALIDATION),
    _1061(1061, ErrorType.VALIDATION),
    _1062(1062, ErrorType.VALIDATION),
    _1063(1063, ErrorType.VALIDATION),
    _1064(1064, ErrorType.VALIDATION),
    _1065(1065, ErrorType.VALIDATION),
    _1066(1066, ErrorType.VALIDATION),
    _1068(1068, ErrorType.VALIDATION),
    _1069(1069, ErrorType.VALIDATION),
    _1070(1070, ErrorType.VALIDATION),
    _1071(1071, ErrorType.VALIDATION),
    _1072(1072, ErrorType.VALIDATION),
    _1073(1073, ErrorType.VALIDATION),
    _1074(1074, ErrorType.VALIDATION),
    _1075(1075, ErrorType.VALIDATION),

    _1079(1079, ErrorType.VALIDATION),
    _1080(1080, ErrorType.VALIDATION),
    _1081(1081, ErrorType.VALIDATION),
    _1082(1082, ErrorType.VALIDATION),
    _1083(1083, ErrorType.VALIDATION),
    _1084(1084, ErrorType.VALIDATION),
    _1085(1085, ErrorType.VALIDATION),
    _1086(1086, ErrorType.VALIDATION),
    _1087(1087, ErrorType.VALIDATION),
    _1088(1088, ErrorType.VALIDATION),
    _1089(1089, ErrorType.VALIDATION),
    _1090(1090, ErrorType.VALIDATION),
    _1091(1091, ErrorType.VALIDATION),
    _1092(1092, ErrorType.VALIDATION),
    _1093(1093, ErrorType.VALIDATION),
    _1094(1094, ErrorType.VALIDATION),
    _1095(1095, ErrorType.VALIDATION),
    _1096(1096, ErrorType.VALIDATION),
    _1097(1097, ErrorType.VALIDATION),
    _1098(1098, ErrorType.VALIDATION),
    _1099(1099, ErrorType.VALIDATION),
    _1100(1100, ErrorType.VALIDATION),
    _1101(1101, ErrorType.VALIDATION),
    _1106(1106, ErrorType.VALIDATION),
    _1107(1107, ErrorType.VALIDATION);

    private int errorCode;

    private ErrorType errorType;

    private String msgTemplate;

    private IntegrationError(int errorCode, ErrorType errorType) {
        this.errorCode = errorCode;
        this.errorType = errorType;
        this.msgTemplate = ErrorMessagesUtil.getMessage(String.valueOf(errorCode));
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getMessage(Object... objects) {
        if (objects == null) { // NOPMD
            return msgTemplate;
        }
        return String.format(msgTemplate, objects);
    }

    // CHECKSTYLE:ON
}
