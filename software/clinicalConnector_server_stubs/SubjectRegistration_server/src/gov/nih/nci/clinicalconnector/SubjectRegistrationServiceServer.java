/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.clinicalconnector;

import java.rmi.RemoteException;

import _21090.org.iso.BL;
import _21090.org.iso.II;
import _21090.org.iso.Uid;
/**
 * SubjectRegistrationServiceServer has business logic.
 */
public class SubjectRegistrationServiceServer extends ServerBase {

    private static int ppn = 500;

    private int getPPN() {
        ppn = ppn + 1;
        return ppn;
    }

    /**
     * Rolls back subject registration.
     *
     * @param request RollbackRegisterSubjectRequest
     */
    public RegisterSubjectResponse rollbackRegisterSubject(RegisterSubjectRequest rollbackRegisterSubjectRequest) throws Exception, CCBusinessFaultMessage,CCDataValidationFaultMessage,CCSystemFaultMessage {

        log("########### enter rollbackRegisterSubject()");

        StudySubject studySubject = rollbackRegisterSubjectRequest.getStudySubject();
        II identifier = studySubject.getIdentifier();
        String identifierName = identifier.getIdentifierName();
        String idExtension = identifier.getExtension();
        log("received subject identifier: " + identifierName + " - " + idExtension);

        RegisterSubjectResponse response = new RegisterSubjectResponse();

        String pidName = "Patient Position";
        String pidExtension = "" + getPPN();
        II pidII = new II();
        pidII.setIdentifierName(pidName);
        pidII.setExtension(pidExtension);
        Uid uid = new Uid();
        uid.setUid("nci.cc.subject.patientposition");
        pidII.setRoot(uid);
        response.setPatientIdentifier(pidII);
        log("returned patientIdentifier:  " + pidName + " - " + pidExtension);

        BL indicatorBL = new BL();
        indicatorBL.setValue(true);
        response.setIndicator(indicatorBL);

        log("########### leave rollbackRegisterSubject()");
        log("");
        log("");
        return response;
    }

    /**
     * Registers a subject.
     *
     * @param registerSubjectRequest
     * @throws CCBusinessFaultMessage 
     */
    public RegisterSubjectResponse registerSubject(RegisterSubjectRequest registerSubjectRequest) throws RemoteException, CCBusinessFaultMessage,CCDataValidationFaultMessage,CCSystemFaultMessage {

        log("########### enter registerSubject()");

        StudySubject studySubject = registerSubjectRequest.getStudySubject();
        II identifier = studySubject.getIdentifier();
        String identifierName = identifier.getIdentifierName();
        String idExtension = identifier.getExtension();
        log("received subject identifier: " + identifierName + " - " + idExtension);

        RegisterSubjectResponse response = new RegisterSubjectResponse();

        String pidName = "Patient Position";
        String pidExtension = "" + getPPN();
        II pidII = new II();
        pidII.setIdentifierName(pidName);
        pidII.setExtension(pidExtension);
        Uid uid = new Uid();
        uid.setUid("2.16.840.1.113883.3.26.7.6");
        pidII.setRoot(uid);
        response.setPatientIdentifier(pidII);
        log("returned patientIdentifier:  " + pidName + " - " + pidExtension);

        BL indicatorBL = new BL();
        indicatorBL.setValue(true);
        response.setIndicator(indicatorBL);
    	if (ppn%3 == 0) {
    		CCBusinessError be = new CCBusinessError();
    		be.setCode(BusinessErrorCode.CC10120);
    		be.setMessage("Failed to register subject of id " + idExtension + " because of incorrect study identifier.");
    		CCBusinessFault bf = new CCBusinessFault();
    		bf.setCCBusinessFault(be);
    		CCBusinessFaultMessage bfm = new CCBusinessFaultMessage();
    		bfm.setFaultMessage(bf);
			throw bfm;
         }

    	if (ppn%4 == 0) {
    		CCSystemError se = new CCSystemError();
    		se.setCode(SystemErrorCode.CC10030);
    		se.setMessage("Failed to register subject database not available.");
    		CCSystemFault sf = new CCSystemFault();
    		sf.setCCSystemFault(se);
    		CCSystemFaultMessage sfm = new CCSystemFaultMessage();
    		sfm.setFaultMessage(sf);
			throw sfm;
    	}
    	if (ppn%5 == 0) {
    		CCDataValidationError ve = new CCDataValidationError();
    		ve.setCode(BusinessErrorCode.CC10120);
    		ve.setMessage("Data validations failed.");
    		ValidationError[] ves = new ValidationError[2];
    		ValidationError ve1 = new ValidationError();
    		ve1.setInputName("birthDate");
    		ve1.setMessage("Invalid date format");
    		ValidationError ve2 = new ValidationError();
    		ve2.setInputName("MRN");
    		ve2.setMessage("Invalid  format");
    		ves[0]=ve1;ves[1]=ve2;
    		ve.setValidationError(ves);
    		CCDataValidationFault dvf = new CCDataValidationFault();
    		dvf.setCCDataValidationFault(ve);
    		CCDataValidationFaultMessage dvfm = new CCDataValidationFaultMessage();
    		dvfm.setFaultMessage(dvf);
			throw dvfm;
         }    	
        log("########### leave registerSubject()");
        log("");
        log("");
        return response;
    }
}
