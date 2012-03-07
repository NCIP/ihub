package gov.nih.nci.integration.invoker;

public class ServiceInvocationResult {
	
	private String result;
	private Exception invocationException;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Exception getInvocationException() {
		return invocationException;
	}
	public void setInvocationException(Exception invocationException) {
		this.invocationException = invocationException;
	}
	
	public boolean isFault() {
		return invocationException != null;
	}
	
	public boolean isRetry() {
		//TODO: get purpose & Impl
		return false;
	}
	
}
