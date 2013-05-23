/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.common;


import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;

import javax.jbi.messaging.DeliveryChannel;
import javax.jbi.messaging.MessageExchange;

/**
 * This class provides grid service invocation functionality for the service unit.
 * 
 * @author steve
 */
public abstract class GridInvocationStrategy {

	protected String serviceUrl;
	protected boolean isItineraryBased;
    /**
     * Performs operations on the grid service.
     * @param channel
     * @param exchange
     * @param message
     * @return
     * @throws GridInvocationException
     */
    public abstract GridInvocationResult invokeGridService(boolean isRollback)
	    throws GridInvocationException;
    
	/**
	 * Gets the service url
	 * @param
	 * @return serviceUrl
	 * @throws
	 */
    public String getServiceUrl() {
		return serviceUrl;
	}

    /**
     * This method sets the service url
     * @param serviceUrl
     * @return
     * @throws
     */
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
	/**
	 * This method checks if grid 
	 * invocation strategy is itinerary based
	 * @param
	 * @return isItineraryBased
	 * @throws
	 */
	public boolean isItineraryBased() {
		return isItineraryBased;
	}
	
	/**
	 * This method sets if the itinerary based invocation 
	 * @param isItineraryBased
	 * @return
	 * @throws
	 */
	public void setItineraryBased(boolean isItineraryBased) {
		this.isItineraryBased = isItineraryBased;
	}

}
