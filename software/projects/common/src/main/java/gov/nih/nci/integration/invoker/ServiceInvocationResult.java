package gov.nih.nci.integration.invoker;

/**
 * This class contains the result of the service invocation
 * 
 * @author Vinodh
 * 
 */
public class ServiceInvocationResult {

	private String result;
	private Exception invocationException;
	private boolean retry = false;

	private boolean dataChanged = false;
	private Object originalData = null;
	private Long messageId;

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

	public void setRetry(boolean retry) {
		this.retry = retry;
	}

	public boolean isRetry() {
		return retry;
	}

	public boolean isDataChanged() {
		return dataChanged;
	}

	public void setDataChanged(boolean dataChanged) {
		this.dataChanged = dataChanged;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Object getOriginalData() {
		return originalData;
	}

	public void setOriginalData(Object originalData) {
		this.originalData = originalData;
	}

}
