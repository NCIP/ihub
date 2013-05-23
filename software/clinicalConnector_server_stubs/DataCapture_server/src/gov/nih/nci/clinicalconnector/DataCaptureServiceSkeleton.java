/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
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
