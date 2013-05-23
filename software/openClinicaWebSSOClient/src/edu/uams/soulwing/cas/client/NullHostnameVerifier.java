/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package edu.uams.soulwing.cas.client;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
/**

* Host name verifier that does not perform any checks.

*/

public class NullHostnameVerifier implements HostnameVerifier {

	public boolean verify(String hostname, SSLSession session) {
		
		return true;
	}

}
