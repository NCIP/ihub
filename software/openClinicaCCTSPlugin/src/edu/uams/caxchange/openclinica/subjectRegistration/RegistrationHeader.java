/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package edu.uams.caxchange.openclinica.subjectRegistration;

import javax.xml.soap.SOAPException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPFactory;
	
public class RegistrationHeader {
	private static final String URI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private static final String PREFIX = "wsse";
	private static final String LOCAL_PART = "Security";

	public static OMElement getHeader(String openClinicaUserID, String openClinicaPassword) throws SOAPException {
			SOAPFactory sFactory = OMAbstractFactory.getSOAP12Factory(); 
			OMNamespace headerNS = sFactory.createOMNamespace(URI, PREFIX);
		    OMElement  security  = sFactory.createOMElement("Security", headerNS); // create SOAP elements specifying prefix and URI

		    OMElement  userToken = sFactory.createOMElement("UsernameToken", headerNS);
			OMNamespace userTokenAttributeNS = sFactory.createOMNamespace("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "wsu");
			OMAttribute userTokenAttribute = sFactory.createOMAttribute("id", userTokenAttributeNS, "UsernameToken-27777511");
			
			userToken.addAttribute(userTokenAttribute);
											
			security.addChild(userToken);
			
			OMElement userName = sFactory.createOMElement("Username", headerNS);
			userName.addChild(sFactory.createOMText(openClinicaUserID));
			
			OMElement password = sFactory.createOMElement("Password", headerNS);
			password.addChild(sFactory.createOMText(openClinicaPassword));
			
			
			OMAttribute passwordAttribute   = sFactory.createOMAttribute("Type", headerNS, "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
			password.addAttribute(passwordAttribute);
			
			userToken.addChild(userName);
			userToken.addChild(password);

			return security;
		}
	
}
