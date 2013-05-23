/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.exception;

public enum ErrorType {
	UNKNOWN,
	VALIDATION,
	SECURITY,
	TRANSFORMATION,
	TRANSMISSION,
	MALFORM,
	STORAGE,
	IDENTITY_RESOLUTION;
}
