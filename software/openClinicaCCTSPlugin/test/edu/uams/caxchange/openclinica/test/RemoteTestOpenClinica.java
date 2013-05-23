/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package edu.uams.caxchange.openclinica.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.Stub;
import org.apache.axis2.databinding.types.NormalizedString;
import org.openclinica.ws.ccts.subject.v1.CommitRequest;
import org.openclinica.ws.ccts.subject.v1.CommitResponse;
import org.openclinica.ws.ccts.subject.v1.CustomStringType;
import org.openclinica.ws.ccts.subject.v1.GenderType;
import org.openclinica.ws.ccts.subject.v1.StudyType;
import org.openclinica.ws.ccts.subject.v1.SubjectType;

import edu.uams.caxchange.openclinica.subjectRegistration.RegistrationHeader;
import edu.uams.caxchange.openclinica.subjectRegistration.output.WsServiceStub;
import gov.nih.nci.caxchange.consumer.CaXchangeConsumerException;

public class RemoteTestOpenClinica {
	private static final String URI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private static final String PREFIX = "wsse";
	private static final String LOCAL_PART = "Security";
	private static boolean propertyFileRead;
	private static String openClinicaURL;
	private static String openClinicaUserID;
	private static String openClinicaPassword;


	/**
	 * @param args
	 * @throws ServiceException 
	 * @throws RemoteException 
	 * @throws SOAPException 
	 */
	public static void main(String[] args) throws ServiceException, RemoteException, SOAPException {
		initialize();
		WsServiceStub ws = new WsServiceStub(openClinicaURL);
		
		Stub stub = (Stub) ws;
		ServiceClient service = stub._getServiceClient();
		OMElement x = RegistrationHeader.getHeader(openClinicaUserID, openClinicaPassword);
		service.addHeader(x);
				
		CommitResponse out = ws.commit(getCommitRequest());

	}

	private static CommitRequest getCommitRequest() {
		CommitRequest out = new CommitRequest();
		out.setGridId(getCustomStringType("48484848"));
		out.setSubject(getSubject());
		out.setStudy(getStudy());
		return out;
	}

	private static StudyType getStudy() {
		StudyType out = new StudyType();
		out.setOid(getCustomStringType("XXXX"));
		return out;
	}

	private static SubjectType getSubject() { 
		SubjectType out = new SubjectType();
		out.setDateOfBirth(new Date("12/12/2008"));
		out.setEnrollmentDate(new Date("12/12/2008"));
		out.setGender(GenderType.m);
		out.setPersonId(   getCustomStringType("sdfdf"));
		out.setSecondaryId(getCustomStringType("?"));
		out.setStudySubjectId(getCustomStringType("asdfsd"));
		
		return out;

	}
	
	private static CustomStringType getCustomStringType(String x) {
		CustomStringType sType = new CustomStringType();
		sType.setCustomStringType(new NormalizedString(x));
		return sType;
	}
	
	private static void initialize() {
		if (propertyFileRead) {
			return;
		}
	    
    
    	ResourceBundle rb = ResourceBundle.getBundle("OpenClinicaCCTSPlugin");
        openClinicaURL      = rb.getString("openClinicaURL");	        
        openClinicaUserID   = rb.getString("openClinicaUserID");
        openClinicaPassword = rb.getString("openClinicaPassword");
        propertyFileRead = true;
		
	}

}
