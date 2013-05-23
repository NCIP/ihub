/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
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
