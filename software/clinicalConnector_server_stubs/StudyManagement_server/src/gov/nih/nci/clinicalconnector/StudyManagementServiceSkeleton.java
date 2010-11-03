/**
 * StudyManagementServiceSkeleton.java
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
 * StudyManagementServiceSkeleton java skeleton for the axisService
 */
public class StudyManagementServiceSkeleton {

    /**
     * Rolls back study creation.
     * 
     * @param request RollbackCreateStudyRequest 
     */
    public RollbackCreateStudyResponse rollbackCreateStudy(RollbackCreateStudyRequest request) {
        return (new StudyManagementServiceServer()).rollbackCreateStudy(request);
    }

    /**
     * Creates a study.
     * 
     * @param request CreateStudyRequest 
     */
    public CreateStudyResponse createStudy(CreateStudyRequest request) {
        return (new StudyManagementServiceServer()).createStudy(request);
    }
}