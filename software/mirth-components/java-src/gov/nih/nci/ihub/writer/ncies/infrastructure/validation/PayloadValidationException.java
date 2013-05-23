/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.infrastructure.validation;
/**
 * An exception thrown for payload validation.
 * 
 * @author marwahah
 *
 */
public class PayloadValidationException extends Exception {

	public PayloadValidationException() {
		// TODO Auto-generated constructor stub
	}

	public PayloadValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PayloadValidationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public PayloadValidationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
