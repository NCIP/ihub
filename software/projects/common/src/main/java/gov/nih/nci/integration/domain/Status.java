/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.domain;

/**
 * Enumeration containing the status
 * 
 * @author Vinodh
 * 
 */
public enum Status {
    /**
     * Process
     */
    PROCESS,
    /**
     * Success
     */
    SUCCESS,
    /**
     * Failed
     */
    FAILED,
    /**
     * Retry
     */
    RETRY;
}
