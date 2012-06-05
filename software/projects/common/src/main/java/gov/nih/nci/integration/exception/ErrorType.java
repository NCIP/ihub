package gov.nih.nci.integration.exception;

/**
 * This enum class contains different ErrorTypes used within Transcend-iHub integration module
 * 
 * @author Vinodh
 * 
 */
public enum ErrorType {
    // CHECKSTYLE:OFF
    UNKNOWN,
    VALIDATION,
    SECURITY,
    TRANSFORMATION,
    TRANSMISSION,
    MALFORM,
    STORAGE,
    IDENTITY_RESOLUTION;
    // CHECKSTYLE:ON
}
