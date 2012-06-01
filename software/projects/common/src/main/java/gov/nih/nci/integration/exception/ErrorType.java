package gov.nih.nci.integration.exception;

public enum ErrorType {
    /**
     * Unknown
     */
    UNKNOWN,

    /**
     * Validation
     */
    VALIDATION,

    /**
     * Security
     */
    SECURITY,

    /**
     * Transformation
     */
    TRANSFORMATION,

    /**
     * Transmission
     */
    TRANSMISSION,

    /**
     * Malform
     */
    MALFORM,

    /**
     * Storage
     */
    STORAGE,

    /**
     * Identity Resolution
     */
    IDENTITY_RESOLUTION;
}
