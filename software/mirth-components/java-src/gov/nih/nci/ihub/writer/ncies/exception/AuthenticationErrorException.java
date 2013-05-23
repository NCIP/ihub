/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
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
