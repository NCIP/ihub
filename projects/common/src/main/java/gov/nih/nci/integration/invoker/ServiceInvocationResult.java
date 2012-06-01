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

    /**
     * getResult
     * 
     * @return result
     */
    public String getResult() {
        return result;
    }

    /**
     * setResult
     * 
     * @param result - String
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * getInvocationException
     * 
     * @return - invocationException
     */
    public Exception getInvocationException() {
        return invocationException;
    }

    /**
     * setInvocationException
     * 
     * @param invocationException - Exception
     */
    public void setInvocationException(Exception invocationException) {
        this.invocationException = invocationException;
    }

    /**
     * isFault
     * 
     * @return true/false
     */
    public boolean isFault() {
        return invocationException != null;
    }

    /**
     * setRetry
     * 
     * @param retry - boolean
     */
    public void setRetry(boolean retry) {
        this.retry = retry;
    }

    /**
     * isRetry
     * 
     * @return boolean
     */
    public boolean isRetry() {
        return retry;
    }

    /**
     * isDataChanged
     * 
     * @return boolean
     */
    public boolean isDataChanged() {
        return dataChanged;
    }

    /**
     * setDataChanged
     * 
     * @param dataChanged - boolean
     */
    public void setDataChanged(boolean dataChanged) {
        this.dataChanged = dataChanged;
    }

    /**
     * getMessageId
     * 
     * @return - messageId
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * setMessageId
     * 
     * @param messageId - messageId
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * getOriginalData
     * 
     * @return data
     */
    public Object getOriginalData() {
        return originalData;
    }

    /**
     * setOriginalData
     * 
     * @param originalData - object
     */
    public void setOriginalData(Object originalData) {
        this.originalData = originalData;
    }

}
