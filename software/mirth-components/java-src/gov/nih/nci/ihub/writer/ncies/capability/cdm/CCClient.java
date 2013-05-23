/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import javax.xml.soap.SOAPException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.Stub;

/**
 * CCClient is an abstract super class for all Clinical Connector clients.
 * 
 * @author John Chen
 *
 */
abstract public class CCClient {
	protected String ccURL = "";
	protected String ccUsername = "";
	protected String ccPassword = "";
    protected long ccTimeout = 10000;
    public long getCcTimeout() {
		return ccTimeout;
	}

	public void setCcTimeout(long ccTimeout) {
		this.ccTimeout = ccTimeout;
	}

	public CCClient() {
	}

	public CCClient(String ccURL, String ccUsername, String ccPassword, long ccTimeout) {
		this.ccURL = ccURL;
		this.ccUsername = ccUsername;
		this.ccPassword = ccPassword;
		this.ccTimeout = ccTimeout;
		
	}
	
	/**
	 * Returns end-point url.
	 */
	public String getCCURL() {
		return ccURL;
	}
	
	/**
	 * Configures header
	 * 
	 * @param stub
	 * @throws AxisFault
	 * @throws SOAPException
	 */
	public void configStub(Stub stub) throws AxisFault, SOAPException {
		ServiceClient sc = stub._getServiceClient();
		sc.addHeader(CCUtil.getHeader(ccUsername, ccPassword));
		sc.getOptions().setTimeOutInMilliSeconds(getCcTimeout());
	}
	
	/**
	 * Creates a stub.
	 * 
	 * Subclasses should implement this method to create a specific stub.
	 * 
	 * @return
	 * @throws AxisFault
	 * @throws SOAPException
	 */
	public abstract Stub getStub() throws AxisFault, SOAPException;
	
	public String getCcURL() {
		return ccURL;
	}

	public void setCcURL(String ccURL) {
		this.ccURL = ccURL;
	}

	public String getCcUsername() {
		return ccUsername;
	}

	public void setCcUsername(String ccUsername) {
		this.ccUsername = ccUsername;
	}

	public String getCcPassword() {
		return ccPassword;
	}

	public void setCcPassword(String ccPassword) {
		this.ccPassword = ccPassword;
	}
	
	
}
