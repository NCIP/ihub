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