package gov.nih.nci.integration.exception;

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

	public IntegrationException() {
		this(IntegrationError._1000, (Object) null);
	}

	public IntegrationException(IntegrationError integrationError,
			Object... objects) {
		this(integrationError.getErrorCode(), integrationError.getErrorType(),
				integrationError.getMessage(objects));
	}
	
	public IntegrationException(IntegrationError integrationError, Throwable cause,
			Object... objects) {
		this(integrationError.getErrorCode(), integrationError.getErrorType(), cause,
				integrationError.getMessage(objects));
	}
	
	private IntegrationException(int errorCode, ErrorType errorType,
			String message) {
		super(message);
		this.errorCode = errorCode;
		this.errorType = errorType;
	}

	private IntegrationException(int errorCode, ErrorType errorType, Throwable cause,
			String message) {
		super(message, cause);
		this.errorCode = errorCode;
		this.errorType = errorType;
	}

	public IntegrationException(String message, Throwable cause) {
		super(message, cause);
		this.errorCode = IntegrationError._1000.getErrorCode();
		this.errorType = IntegrationError._1000.getErrorType();
	}

	public IntegrationException(String message) {
		super(message);
		this.errorCode = IntegrationError._1000.getErrorCode();
		this.errorType = IntegrationError._1000.getErrorType();
	}

	public IntegrationException(Throwable cause) {
		super(cause);
		this.errorCode = IntegrationError._1000.getErrorCode();
		this.errorType = IntegrationError._1000.getErrorType();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

}
