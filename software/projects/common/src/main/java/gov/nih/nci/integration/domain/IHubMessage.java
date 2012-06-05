package gov.nih.nci.integration.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Represents the message that is sent to iHub. 
 * This will be persisted and used to pass back the ultimate response to the caller
 * 
 * @author Vinodh
 * 
 */
@Entity
public class IHubMessage extends AbstractIdentity {

    private Date startTime;
    private Date endTime;

    private String request;
    private String response;

    private Status status;

    /**
     * 
     * @return startTime
     */
    @NotNull
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime - startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime - endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 
     * @return request
     */
    @NotNull
    @Column(length = 50000)
    public String getRequest() {
        return request;
    }

    /**
     * 
     * @param request - request
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * 
     * @return response
     */
    public String getResponse() {
        return response;
    }

    /**
     * 
     * @param response - response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * 
     * @return status
     */
    @Enumerated
    @NotNull
    public Status getStatus() {
        return status;
    }

    /**
     * 
     * @param status - status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}
