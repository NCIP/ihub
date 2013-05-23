/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.common;

import org.w3c.dom.Node;

/**
 * This interface defines the result of a grid service invocation.
 * 
 * @author steve
 */
public class GridInvocationResult {
	boolean isFault;
	Node result;
	boolean retry;
	Exception invocationException;
	
	public GridInvocationResult(boolean isFault, Node result, boolean retry) {
		super();
		this.isFault = isFault;
		this.result = result;
		this.retry = retry;
	}
	
	public GridInvocationResult(boolean isFault, Node result, boolean retry,
			Exception invocationException) {
		super();
		this.isFault = isFault;
		this.result = result;
		this.retry = retry;
		this.invocationException = invocationException;
	}
	
	public boolean isFault() {
		return isFault;
	}
	public void setFault(boolean isFault) {
		this.isFault = isFault;
	}
	public Node getResult() {
		return result;
	}
	public void setResult(Node result) {
		this.result = result;
	}
	public boolean isRetry() {
		return retry;
	}
	public void setRetry(boolean retry) {
		this.retry = retry;
	}
	public Exception getInvocationException() {
		return invocationException;
	}
	public void setInvocationException(Exception invocationException) {
		this.invocationException = invocationException;
	} 
	
}
