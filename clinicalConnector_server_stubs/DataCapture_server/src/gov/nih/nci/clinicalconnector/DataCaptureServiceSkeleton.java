/**
 * DataCaptureServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 * 
 * This file was modified by adding business logic. 
 * 
 * @author John Chen
 */
package gov.nih.nci.clinicalconnector;

/**
 * DataCaptureServiceSkeleton java skeleton for the axisService
 */
public class DataCaptureServiceSkeleton {

    /**
     * Rolls back load labs.
     * 
     * @param request RollbackLoadLabsRequest 
     */
    public LoadLabsResponse loadLabs(LoadLabsRequest request) {
        return (new DataCaptureServiceServer()).loadLabs(request);
    }

    /**
     * Loads labs.
     * 
     * @param request LoadLabsRequest 
     */
    public RollbackLoadLabsResponse rollbackLoadLabs(RollbackLoadLabsRequest request) {
        return (new DataCaptureServiceServer()).rollbackLoadLabs(request);
    }
}