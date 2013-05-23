/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.clinicalconnector;

import _21090.org.iso.BL;
import _21090.org.iso.II;
import _21090.org.iso.ST;

/**
 * StudyManagementServiceServer has business logic.
 */
public class StudyManagementServiceServer extends ServerBase {

    /**
     * Rolls back study creation.
     * 
     * @param request RollbackCreateStudyRequest 
     */
    public RollbackCreateStudyResponse rollbackCreateStudy(RollbackCreateStudyRequest request) {

        log("########### enter rollbackCreateStudy()");
        
        StudyProtocol studyProtocol = request.getStudyProtocol();
        II identifier = studyProtocol.getIdentifier();
        String identifierName = identifier.getIdentifierName();
        String idExtension = identifier.getExtension();
        log("received study identifier: " + identifierName + " - " + idExtension);
        
        ST officialTitle = studyProtocol.getOfficialTitle();
        log("received study official title: " + officialTitle.getValue());
        
        RollbackCreateStudyResponse response = new RollbackCreateStudyResponse();
        
        String sidName = "DOCUMENT";
        String sidExtension = "1234567";
        II sid = new II();
        sid.setIdentifierName(sidName);
        sid.setExtension(sidExtension);
        response.setStudyIdentifier(sid);
        log("returned study identifier:  " + sidName + " - " + sidExtension);
        
        BL indicatorBL = new BL();
        indicatorBL.setValue(true);
        response.setIndicator(indicatorBL);
        
        log("########### leave rollbackCreateStudy()");
        log("");
        log("");
        return response;
    }

    /**
     * Creates a study.
     * 
     * @param request CreateStudyRequest 
     */
    public CreateStudyResponse createStudy(CreateStudyRequest request) {

        log("########### enter createStudy()");
        
        StudyProtocol studyProtocol = request.getStudyProtocol();
        II identifier = studyProtocol.getIdentifier();
        String identifierName = identifier.getIdentifierName();
        String idExtension = identifier.getExtension();
        log("received study identifier: " + identifierName + " - " + idExtension);
        
        ST officialTitle = studyProtocol.getOfficialTitle();
        log("received study official title: " + officialTitle.getValue());
        
        CreateStudyResponse response = new CreateStudyResponse();
        
        String sidName = "DOCUMENT";
        String sidExtension = "1234567";
        II sid = new II();
        sid.setIdentifierName(sidName);
        sid.setExtension(sidExtension);
        response.setStudyIdentifier(sid);
        log("returned study identifier:  " + sidName + " - " + sidExtension);
        
        BL indicatorBL = new BL();
        indicatorBL.setValue(true);
        response.setIndicator(indicatorBL);
        
        log("########### leave createStudy()");
        log("");
        log("");
        return response;
    }
}
