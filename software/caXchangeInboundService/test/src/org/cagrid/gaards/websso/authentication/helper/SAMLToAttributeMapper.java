/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package org.cagrid.gaards.websso.authentication.helper;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.util.HashMap;

import org.cagrid.gaards.websso.exception.AuthenticationConfigurationException;

public interface SAMLToAttributeMapper
{
	
	public HashMap<String, String> convertSAMLtoHashMap(SAMLAssertion samlAssertion) throws AuthenticationConfigurationException;
	
}
