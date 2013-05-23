/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.cache;

/**
 * @author Ajay
 *
 */
public class UserProxyCacheException extends Exception {

	public UserProxyCacheException() {
	}

	public UserProxyCacheException(String message) {
		super(message);
	}

	public UserProxyCacheException(Throwable cause) {
		super(cause);
	}

	public UserProxyCacheException(String message, Throwable cause) {
		super(message, cause);
	}

}
