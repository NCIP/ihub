package gov.nih.nci.integration.exception;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * 
 * @author chandrasekaravr
 * 
 */
public class IntegrationException extends Exception {

    /**
     * Default serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private int errorCode;

    private ErrorType errorType;

    /**
     * Constructor
     */
    public IntegrationException() {
        this(IntegrationError._1000, (Object) null);
    }

    /**
     * Constructor
     * 
     * @param integrationError - IntegrationError
     * @param objects - var arg object
     */
    public IntegrationException(IntegrationError integrationError, Object... objects) {
        this(integrationError.getErrorCode(), integrationError.getErrorType(), integrationError.getMessage(objects));
    }

    /**
     * Constructor
     * 
     * @param integrationError - IntegrationError
     * @param cause - Throwable
     * @param objects - var arg object
     */
    public IntegrationException(IntegrationError integrationError, Throwable cause, Object... objects) {
        this(integrationError.getErrorCode(), integrationError.getErrorType(), cause, integrationError
                .getMessage(objects));
    }

    /**
     * Constructor
     * 
     * @param errorCode - errorCode
     * @param errorType - ErrorType
     * @param message - message String
     */
    public IntegrationException(int errorCode, ErrorType errorType, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    /**
     * Constructor
     * 
     * @param errorCode - errorCode
     * @param errorType - ErrorType
     * @param cause - Throwable
     * @param message - message String
     */
    public IntegrationException(int errorCode, ErrorType errorType, Throwable cause, String message) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    /**
     * Constructor
     * 
     * @param message - message String
     * @param cause - Throwable
     */
    public IntegrationException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = IntegrationError._1000.getErrorCode();
        this.errorType = IntegrationError._1000.getErrorType();
    }

    /**
     * Constructor
     * 
     * @param message - message String
     */
    public IntegrationException(String message) {
        super(message);
        this.errorCode = IntegrationError._1000.getErrorCode();
        this.errorType = IntegrationError._1000.getErrorType();
    }

    /**
     * Constructor
     * 
     * @param cause - Throwable
     */
    public IntegrationException(Throwable cause) {
        super(cause);
        this.errorCode = IntegrationError._1000.getErrorCode();
        this.errorType = IntegrationError._1000.getErrorType();
    }

    /**
     * getErrorCode
     * 
     * @return errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * getErrorType
     * 
     * @return errorType
     */
    public ErrorType getErrorType() {
        return errorType;
    }

    /**
     * stackTraceAsString
     * 
     * @return String
     */
    public String stackTraceAsString() {
        String stackTraceStr = getMessage();
        if (getCause() != null) {
            try {
                stackTraceStr = URLEncoder.encode(ExceptionUtils.getFullStackTrace(getCause()), "UTF-8");
            } catch (UnsupportedEncodingException e1) {
                stackTraceStr = getMessage();
            }
        }
        return stackTraceStr;
    }
}
