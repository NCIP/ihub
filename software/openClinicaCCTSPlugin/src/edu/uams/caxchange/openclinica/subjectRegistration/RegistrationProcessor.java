package edu.uams.caxchange.openclinica.subjectRegistration;

import edu.uams.caxchange.openclinica.subjectRegistration.output.WsServiceStub;
import gov.nih.nci.cabig.ccts.domain.IdentifierType;
import gov.nih.nci.cabig.ccts.domain.ParticipantType;
import gov.nih.nci.cabig.ccts.domain.Registration;
import gov.nih.nci.cabig.ccts.domain.StudyRefType;
import gov.nih.nci.caxchange.consumer.CaXchangeConsumerException;
import gov.nih.nci.caxchange.consumer.CaXchangeMessageConsumer;
import gov.nih.nci.ccts.grid.client.RegistrationConsumerClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.Stub;
import org.apache.axis2.databinding.types.NormalizedString;
import org.apache.axis2.databinding.utils.ConverterUtil;
import org.apache.log4j.Logger;
import org.globus.wsrf.encoding.DeserializationException;
import org.openclinica.ws.ccts.subject.v1.CommitRequest;
import org.openclinica.ws.ccts.subject.v1.CommitResponse;
import org.openclinica.ws.ccts.subject.v1.RollbackRequest;
import org.openclinica.ws.ccts.subject.v1.RollbackResponse;
import org.openclinica.ws.ccts.subject.v1.CustomStringType;
import org.openclinica.ws.ccts.subject.v1.GenderType;
import org.openclinica.ws.ccts.subject.v1.StudyType;
import org.openclinica.ws.ccts.subject.v1.SubjectType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RegistrationProcessor implements CaXchangeMessageConsumer {

	Logger log = Logger.getLogger(RegistrationProcessor.class);
	private String openClinicaURL;
	private String openClinicaUserID;
	private String openClinicaPassword;
	private boolean propertyFileRead;
	
	public void commit(Node node) throws CaXchangeConsumerException {
		initialize();
		processRequest(node, "commit");
	}


	public Node process(Node node) throws CaXchangeConsumerException {
		initialize();		
		return processRequest(node, "process");
	}
	
	public void rollback(Node node) throws CaXchangeConsumerException {
		initialize();
		processRequest(node, "rollback");
		return;
	}
	
	private Node processRequest(Node node, String method) throws CaXchangeConsumerException {
		log.info(method + " invoked.");   
        
		try {
			Registration request = getRequest(node);
			logRequestInfo(request);
			if (!("rollback".equals(method))) {
			   callOpenClinica(request);
			}else {
				callOpenClinicaRollback(request);
			}
            
            return getC3PRSuccessReturnObject();
		} catch(Throwable e) {
            log.error("Error parsing return document.",e);
            throw new CaXchangeConsumerException("Error parsing return document.",e);
      }

		
	}

	private Node getC3PRSuccessReturnObject() throws CaXchangeConsumerException {
		Node out = null;
		
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        Document        resultDoc;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			resultDoc = documentBuilder.parse(new InputSource(new StringReader("<result>success</result>")));
			out = resultDoc.getParentNode();
		} catch (Exception e) {
			log.error("error trying to create success return object", e);
			throw new CaXchangeConsumerException("error trying to create success return object", e);
		}
        return out;

	}


	private void callOpenClinica(Registration request) throws MalformedURLException, ServiceException, RemoteException, SOAPException {
		System.setProperty(ConverterUtil.SYSTEM_PROPERTY_ADB_CONVERTERUTIL, CustomConverterUtil.class.getName());
		WsServiceStub ws = new WsServiceStub(openClinicaURL);
		
		Stub stub = (Stub) ws;
		ServiceClient service = stub._getServiceClient();
		service.addHeader(RegistrationHeader.getHeader(openClinicaUserID, openClinicaPassword));
				
		CommitResponse response = ws.commit(getCommitRequest(request));

	}
	
	private void callOpenClinicaRollback(Registration request) throws MalformedURLException, ServiceException, RemoteException, SOAPException {
		System.setProperty(ConverterUtil.SYSTEM_PROPERTY_ADB_CONVERTERUTIL, CustomConverterUtil.class.getName());
		WsServiceStub ws = new WsServiceStub(openClinicaURL);
		
		Stub stub = (Stub) ws;
		ServiceClient service = stub._getServiceClient();
		service.addHeader(RegistrationHeader.getHeader(openClinicaUserID, openClinicaPassword));
				
		RollbackResponse response = ws.rollback(getRollbackRequest(request));
		printRollbackResponse(response);

	}	

	private CommitRequest getCommitRequest(Registration in) throws CaXchangeConsumerException {
		CommitRequest out = new CommitRequest();
		out.setGridId(getCustomStringType(in.getGridId()));
		out.setSubject(getSubject(in));
		out.setStudy(getStudy(in));
		
		printCommitRequest(out);
		
		return out;
	}

	private RollbackRequest getRollbackRequest(Registration in) throws CaXchangeConsumerException {
		RollbackRequest out = new RollbackRequest();
		out.setGridId(getCustomStringType(in.getGridId()));
		out.setSubject(getSubject(in));
		out.setStudy(getStudy(in));
		
		printRollbackRequest(out);
		
		return out;
	}	


	private CustomStringType getCustomStringType(String x) {
		CustomStringType sType = new CustomStringType();
		sType.setCustomStringType(new NormalizedString(x));
		return sType;
	}
	private StudyType getStudy(Registration in) throws CaXchangeConsumerException {
		StudyType out = new StudyType();
		out.setUniqueIdentifier(getCustomStringType(getStudyOID(in)));
		return out;
	}

	private String getStudyOID(Registration in) throws CaXchangeConsumerException {
		String out = null;
		if (in != null) {
			StudyRefType ref = in.getStudyRef();
			if (ref != null) {
				IdentifierType[] iList = ref.getIdentifier();
				if (iList != null) {
					for (IdentifierType ident: iList) {
						if (ident.getType().equalsIgnoreCase("Coordinating Center Identifier")) {
							out = ident.getValue();
							break;
						}						
					}
				}
			}
		}
		if (out == null) {
			throw new CaXchangeConsumerException("could not find studyref.identifiertype of Coordinating center identifier");
		}
		return out;
	}
	private SubjectType getSubject(Registration in) throws CaXchangeConsumerException {
		SubjectType out = new SubjectType();
		Date birth = in.getParticipant().getBirthDate();
		
		SimpleDateFormat fmt = new SimpleDateFormat(CustomConverterUtil.defaultFormat);

		Date date = java.sql.Date.valueOf(fmt.format(birth));
		
		out.setDateOfBirth(date);
		
		Date enrolled = in.getStartDate();
		date = java.sql.Date.valueOf(fmt.format(enrolled));

		out.setEnrollmentDate(date);
		out.setGender(getGender(in.getParticipant().getAdministrativeGenderCode()));
		out.setPersonId( getPersonID(in));
		out.setSecondaryId(getCustomStringType("?"));
		out.setStudySubjectId(getPersonID(in));
		
		return out;
	}

/*	private SubjectType getSubject(Registration in) throws CaXchangeConsumerException {
		SubjectType out = new SubjectType();
		Date birth = in.getParticipant().getBirthDate();
		
		Date date = null;
		date = java.sql.Date.valueOf("2008-12-12");
		
		out.setDateOfBirth(date);
		
		Date enrolled = in.getStartDate();
		date = java.sql.Date.valueOf("2008-12-12");
		
		out.setEnrollmentDate(date);
		out.setGender(GenderType.f);
		out.setPersonId(getCustomStringType("x1x1x1"));
		out.setSecondaryId(getCustomStringType("?"));
		out.setStudySubjectId(getCustomStringType("y6y6y6y6"));
		
		return out;
	}
*/
	private GenderType getGender(String administrativeGenderCode) {
		GenderType out = null;
		if (administrativeGenderCode != null) {
			if (administrativeGenderCode.equalsIgnoreCase("Female")) {
				out = GenderType.f;
			} else {
				out = GenderType.m;
			}
		}
		
		return out;
	}


	private CustomStringType getPersonID(Registration in) throws CaXchangeConsumerException {
		String out = null;
		if (in != null) {
			 ParticipantType ref = in.getParticipant();
			if (ref != null) {
				IdentifierType[] iList = ref.getIdentifier();
				if (iList != null) {
					for (IdentifierType ident: iList) {
						if (ident.getType().equalsIgnoreCase("MRN")) {
							out = ident.getValue();
							break;
						}						
					}
				}
			}
		}
		if (out == null) {
			throw new CaXchangeConsumerException("could not find participant.identifiertype of MRN");
		}
		return getCustomStringType(out);
	}


	private void logRequestInfo(Registration request) {
		log.info("*****************************************");
        log.info("request.gridID                         = " + request.getGridId());
        log.info("Participant Identified Type            = " + request.getParticipant().getIdentifier(0).getType());
        log.info("Participant Identified Value           = " + request.getParticipant().getIdentifier(0).getValue());
        log.info("Participant Grid ID                    = " + request.getParticipant().getGridId());
        log.info("Participant Administrative Gender Code = " + request.getParticipant().getAdministrativeGenderCode());
        log.info("Participant Birth Date                 = " + request.getParticipant().getBirthDate());
        log.info("*****************************************");
        
		
	}
	private void printCommitRequest(CommitRequest out) {
		log.info("*****************************************");
        log.info("openClinica Request"); 
        log.info("gridID = " +out.getGridId());
        SubjectType sub = out.getSubject();
        log.info("subject.personId = " + sub.getPersonId());
        log.info("subject.studySubjectId = " + sub.getStudySubjectId());
        log.info("subject.secondaryId = " + sub.getSecondaryId());
        log.info("subject.enrollment = " + sub.getEnrollmentDate());
        log.info("subject.gender = " + sub.getGender());
        log.info("subject.dob"  + sub.getDateOfBirth());
        log.info("study.uniqueIdentifier = " + out.getStudy().getUniqueIdentifier());
        log.info("*****************************************"); 
		
	}
	
	private void printRollbackRequest(RollbackRequest out) {
		log.info("*****************************************");
        log.info("openClinica Rollback Request"); 
        log.info("gridID = " +out.getGridId());
        SubjectType sub = out.getSubject();
        log.info("subject.personId = " + sub.getPersonId());
        log.info("subject.studySubjectId = " + sub.getStudySubjectId());
        log.info("subject.secondaryId = " + sub.getSecondaryId());
        log.info("subject.enrollment = " + sub.getEnrollmentDate());
        log.info("subject.gender = " + sub.getGender());
        log.info("subject.dob"  + sub.getDateOfBirth());
        log.info("study.uniqueIdentifier = " + out.getStudy().getUniqueIdentifier());
        log.info("*****************************************");

	}	
	
	private void printRollbackResponse(RollbackResponse out) {
		log.info("*****************************************");
        log.info("openClinica Rollback Response"); 
        log.info("Result = " +out.getResult());
        log.info("*****************************************");

	}		

	private Registration getRequest(Node nodeIn) throws TransformerFactoryConfigurationError, TransformerException, DeserializationException, SAXException {
		InputStream deseralizeStream = RegistrationConsumerClient.class.getResourceAsStream("client-config.wsdd");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new DOMSource(nodeIn);
        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);
        transformer.transform(source, result);        
        Registration request = (Registration)gov.nih.nci.cagrid.common.Utils.deserializeObject(new StringReader(stringWriter.toString()), Registration.class, deseralizeStream);
        return request;
	}


	private void initialize()  {
		if (propertyFileRead) {
			return;
		}
		ResourceBundle rb = ResourceBundle.getBundle("OpenClinicaCCTSPlugin");
        openClinicaURL      = rb.getString("openClinicaURL");	        
        openClinicaUserID   = rb.getString("openClinicaUserID");
        openClinicaPassword = rb.getString("openClinicaPassword");
        log.info("openClinicaURL    = " + openClinicaURL);
        log.info("openClinicaUserID = " + openClinicaUserID);
        propertyFileRead = true;		
	}

}
