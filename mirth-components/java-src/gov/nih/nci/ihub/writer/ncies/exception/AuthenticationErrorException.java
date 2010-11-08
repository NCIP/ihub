package gov.nih.nci.ihub.writer.ncies.exception;

public class AuthenticationErrorException extends Exception {

	public AuthenticationErrorException() {
	}

	public AuthenticationErrorException(String message) {
		super(message);
	}

	public AuthenticationErrorException(Throwable cause) {
		super(cause);
	}

	public AuthenticationErrorException(String message, Throwable cause) {
		super(message, cause);
	}

}
