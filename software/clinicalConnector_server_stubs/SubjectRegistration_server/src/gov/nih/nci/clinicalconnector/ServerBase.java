/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.clinicalconnector;

/**
 * ServerBase provides common functionalities for all service 
 * implementation classes.
 * 
 * @author John Chen
 *
 */
abstract public class ServerBase {
	
	/**
	 * Logs an object which is usually a debug message.
	 * 
	 * @param o
	 */
    protected void log(Object o) {
        System.out.println((new java.util.Date()) + " -- " + o);
    }
}
