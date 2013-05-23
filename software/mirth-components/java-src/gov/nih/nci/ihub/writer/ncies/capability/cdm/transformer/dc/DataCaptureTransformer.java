/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.dc;

import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.BL;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.CD;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.Code;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.II;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.IVL_PQ;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.LoadLabsRequest;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.NullFlavor;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.PQ;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.PerformedClinicalResult;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.PerformedObservation;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.SC;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.ST;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.TS;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.Uid;
import gov.nih.nci.ihub.util.HubConstants;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.CCTransformException;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.CCTransformer;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.CCTransformerConstants;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DataCaptureTransformer extends CCTransformer {
	protected static Logger logger = LogManager
			.getLogger(DataCaptureTransformer.class);

	public Object convert2Request(String xml) throws CCTransformException {
		LoadLabsRequest request = new LoadLabsRequest();
		try {
			XLoadLabsRequest xrequest = readLoadLabsRequestXML(xml);
			PerformedClinicalResult[] performedClinicalResults = createPerformedClinicalResult(xrequest);
			request.setPerformedClinicalResult(performedClinicalResults);
			return request;
		} catch (Exception e) {
			logger
					.error(
							"Error transforming xml to clinical connector load labs request.",
							e);
			logger.error("XML:" + xml);
			throw new CCTransformException(
					"Error transforming xml to clinical connector load labs request.",
					e);
		}
	}

	public String convert2RequestString(String xml) throws CCTransformException {
		try {
			LoadLabsRequest request = new LoadLabsRequest();
			request = (LoadLabsRequest) convert2Request(xml);
			OMElement requestElement = request.getOMElement(request.MY_QNAME,
					OMAbstractFactory.getOMFactory());
			return requestElement.toString();
		} catch (Exception e) {
			logger
					.error(
							"Error transforming xml to clinical connector load labs request.",
							e);
			logger.error("XML:" + xml);
			throw new CCTransformException(
					"Error transforming xml to clinical connector load labs request.",
					e);
		}
	}

	public Object convert2RollbackRequest(String xml)
			throws CCTransformException {
		LoadLabsRequest request = new LoadLabsRequest();
		try {
			XLoadLabsRequest xrequest = readLoadLabsRequestXML(xml);
			PerformedClinicalResult[] performedClinicalResults = createPerformedClinicalResult(xrequest);
			request.setPerformedClinicalResult(performedClinicalResults);
			return request;
		} catch (Exception e) {
			logger
					.error(
							"Error transforming xml to clinical connector rollback load labs request.",
							e);
			logger.error("XML:" + xml);
			throw new CCTransformException(
					"Error transforming xml to clinical connector rollback load labs request.",
					e);
		}
	}

	public String convert2RollbackRequestString(String xml)
			throws CCTransformException {
		try {
			LoadLabsRequest request = new LoadLabsRequest();
			request = (LoadLabsRequest) convert2RollbackRequest(xml);
			OMElement requestElement = request.getOMElement(request.MY_QNAME,
					OMAbstractFactory.getOMFactory());
			return requestElement.toString();
		} catch (Exception e) {
			logger
					.error(
							"Error transforming xml to clinical connector load labs request.",
							e);
			logger.error("XML:" + xml);
			throw new CCTransformException(
					"Error transforming xml to clinical connector load labs request.",
					e);
		}
	}

	protected PerformedClinicalResult[] createPerformedClinicalResult(
			XLoadLabsRequest xrequest) {
		XPerformedClinicalResult[] xPerformedClinicalResults = xrequest.performedClinicalResults;
		if (xPerformedClinicalResults == null)
			return null;
		PerformedClinicalResult[] performedClinicalResults = new PerformedClinicalResult[xPerformedClinicalResults.length];
		int i = 0;
		for (XPerformedClinicalResult xPerformedClinicalResult : xPerformedClinicalResults) {
			PerformedClinicalResult performedClinicalResult = new PerformedClinicalResult();

			boolean asCollectedIndicator = false;
			if ("true"
					.equalsIgnoreCase(xPerformedClinicalResult.asCollectedIndicator))
				asCollectedIndicator = true;
			BL asCollectedIndicatorBL = new BL();
			asCollectedIndicatorBL.setValue(asCollectedIndicator);
			performedClinicalResult
					.setAsCollectedIndicator(asCollectedIndicatorBL);

			SC comment = new SC();
			comment.setValue(checkEmpty(xPerformedClinicalResult.comment));
			performedClinicalResult.setComment(comment);

			CD confidentialityCode = new CD();
			confidentialityCode
					.setCode(checkEmpty(xPerformedClinicalResult.confidentialityCode));
			performedClinicalResult.setConfidentialityCode(confidentialityCode);

			PQ numericalResult = new PQ();
			Code unit = new Code();
			unit
					.setCode(checkEmpty(xPerformedClinicalResult.numericalResultUnit));
			numericalResult.setUnit(unit);
			numericalResult
					.setPrecision(convert_string2int(
							checkEmpty(xPerformedClinicalResult.numericalResultPrecision),
							0));
			numericalResult
					.setValue(convert_string2double(
							checkEmpty(xPerformedClinicalResult.numericalResult),
							0.0d));
			performedClinicalResult.setNumericalResult(numericalResult);

			IVL_PQ referenceRange = new IVL_PQ();
			PQ high = new PQ();
			high.setValue(convert_string2double(
					checkEmpty(xPerformedClinicalResult.referenceRangeHigh),
					0.0d));
			referenceRange.setHigh(high);
			PQ low = new PQ();
			low.setValue(convert_string2double(
					checkEmpty(xPerformedClinicalResult.referenceRangeLow),
					0.0d));
			referenceRange.setLow(low);
			performedClinicalResult.setReferenceRange(referenceRange);

			ST referenceRangeComment = new ST();
			referenceRangeComment
					.setValue(checkEmpty(xPerformedClinicalResult.referenceRangeComment));
			performedClinicalResult
					.setReferenceRangeComment(referenceRangeComment);

			TS reportedDate = new TS();
			reportedDate
					.setValue(checkEmpty(xPerformedClinicalResult.reportedDate));
			performedClinicalResult.setReportedDate(reportedDate);

			CD reportedResultStatusCode = new CD();
			reportedResultStatusCode
					.setCode(checkEmpty(xPerformedClinicalResult.reportedResultStatusCode));
			performedClinicalResult
					.setReportedResultStatusCode(reportedResultStatusCode);

			ST textResult = new ST();
			textResult
					.setValue(checkEmpty(xPerformedClinicalResult.textResult));
			performedClinicalResult.setTextResult(textResult);

			CD uncertaintyCode = new CD();
			uncertaintyCode
					.setCode(checkEmpty(xPerformedClinicalResult.uncertaintyCode));
			performedClinicalResult.setUncertaintyCode(uncertaintyCode);

			performedClinicalResult
					.setPerformedObservation(createPerformedObservation(xPerformedClinicalResult));

			performedClinicalResults[i++] = performedClinicalResult;
		}
		return performedClinicalResults;
	}

	protected PerformedObservation createPerformedObservation(
			XPerformedClinicalResult xPerformedClinicalResult) {
		if (xPerformedClinicalResult == null) {
			throw new IllegalArgumentException(
					"Transformed PerformedClinicalResult cannot be empty.");
		}
		XPerformedObservation xPerformedObservation = xPerformedClinicalResult.performedObservation;

		PerformedObservation performedObservation = new PerformedObservation();

		CD activityNameCode = new CD();
		activityNameCode
				.setCode(checkEmpty(xPerformedObservation.activityNameCode));
		performedObservation.setActivityNameCode(activityNameCode);

		II studyProtocolIdentifier = new II();
		studyProtocolIdentifier
				.setIdentifierName(checkEmpty(xPerformedObservation.studyProtocolIdentifierName));
		studyProtocolIdentifier
				.setExtension(checkEmpty(xPerformedObservation.studyProtocolIdentifier));
		performedObservation.setStudyProtocolIdentifier(getIdentifier(
				xPerformedObservation.studyProtocolIdentifier,
				xPerformedObservation.studyProtocolIdentifierName));

		performedObservation.setStudySubjectIdentifier(getIdentifier(
				xPerformedObservation.studySubjectIdentifier,
				xPerformedObservation.studySubjectIdentifierName));

		return performedObservation;
	}

	protected String checkEmpty(String s) {
		return (s != null && !"".equals(s)) ? s : "";
	}

	protected II getIdentifier(String extension, String identifierName) {
		II identifier = new II();
		if ((extension == null) || (extension.equals(""))) {
			identifier.setNullFlavor(NullFlavor.NI);
		} else {
			identifier.setIdentifierName(identifierName);
			Uid uid = new Uid();
			uid
					.setUid(NAME_ROOT_MAP
							.get(identifierName));
			identifier.setRoot(uid);
			identifier.setExtension(checkEmpty(extension));
		}
		return identifier;
	}

	protected String checkDate(String s) {
		if (s != null && s.indexOf('-') != -1) {
			s = s.replaceAll("-", "");
		}
		return s;
	}

	protected double convert_string2double(String s, double defaultValue) {
		double result = defaultValue;

		try {
			Double D = new Double(s);
			result = D.doubleValue();
		} catch (Exception ex) {
			;
		}

		return result;
	}

	protected int convert_string2int(String s, int defaultValue) {
		int result = defaultValue;

		try {
			Integer I = new Integer(s);
			result = I.intValue();
		} catch (Exception ex) {
			;
		}

		return result;
	}

	protected XLoadLabsRequest readLoadLabsRequestXML(String xml)
			throws Exception {
		XLoadLabsRequest xrequest = new XLoadLabsRequest();
		XPathReader reader = new XPathReader(xml);
		NodeList results = reader
				.readNodeList("loadlabs:LoadLabsRequest/loadlabs:LabResult");
		if (results == null) {
			return xrequest;
		}
		XPerformedClinicalResult[] performedClinicalResults = new XPerformedClinicalResult[results
				.getLength()];
		xrequest.performedClinicalResults = performedClinicalResults;
		for (int i = 0; i < results.getLength(); i++) {
			logger.debug("Reading element " + i);
			Node node = results.item(i);
			XPerformedClinicalResult performedClinicalResult = new XPerformedClinicalResult();
			XPerformedObservation performedObservation = new XPerformedObservation();
			performedClinicalResult.performedObservation = performedObservation;

			performedClinicalResult.asCollectedIndicator = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:asCollectedIndicator");
			performedClinicalResult.comment = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:comment");
			performedClinicalResult.confidentialityCode = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:confidentiality");
			performedClinicalResult.numericalResult = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:numericResult");
			performedClinicalResult.numericalResultPrecision = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:numericPrecision");
			performedClinicalResult.numericalResultUnit = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:numericUnit");
			performedClinicalResult.referenceRangeHigh = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:referenceRangeHigh");
			performedClinicalResult.referenceRangeLow = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:referenceRangeLow");
			performedClinicalResult.referenceRangeComment = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:referenceRangeComment");
			performedClinicalResult.reportedDate = checkDate(reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:reportedDateTime"));
			performedClinicalResult.reportedResultStatusCode = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:reportedResultStatus");
			performedClinicalResult.textResult = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:textResult");
			performedClinicalResult.uncertaintyCode = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1) + "]/loadlabs:uncertainty");

			performedObservation.activityNameCode = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1)
							+ "]/loadlabs:StudySubject/loadlabs:PerformedActivity/loadlabs:name");
			performedObservation.studyProtocolIdentifier = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1)
							+ "]/loadlabs:StudySubject/loadlabs:PerformedStudy/loadlabs:Documentation/loadlabs:II/loadlabs:extension");
			// performedObservation.studyProtocolIdentifierName =
			// reader.read("xxx");
			performedObservation.studyProtocolIdentifierName = HubConstants.CC_STUDY_PROTOCOL_IDENTIFIER_NAME;
			if ((performedObservation.studyProtocolIdentifier != null)
					&& (performedObservation.studyProtocolIdentifier
							.startsWith("STUDY:"))) {
				performedObservation.studyProtocolIdentifier = performedObservation.studyProtocolIdentifier
						.substring(6);
			}

			performedObservation.studySubjectIdentifier = retrieve_studySubjectIdentifier(reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1)
							+ "]/loadlabs:StudySubject/loadlabs:Participant/loadlabs:II/loadlabs:extension"));
			// performedObservation.studySubjectIdentifierName =
			// retrieve_studySubjectIdentifierName(performedObservation.studySubjectIdentifier);
			performedObservation.studySubjectIdentifierName = HubConstants.CC_MRN_NAME; // retrieve_studySubjectIdentifierName(reader.read("loadlabs:LabResult/loadlabs:StudySubject/loadlabs:Participant/loadlabs:II/loadlabs:extension"));

			performedObservation.studySubjectIdentifierRoot = reader
					.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
							+ (i + 1)
							+ "]/loadlabs:StudySubject/loadlabs:Participant/loadlabs:II/loadlabs:root");

			if (performedObservation.studySubjectIdentifier == null
					|| "".equals(performedObservation.studySubjectIdentifier)) {
				performedObservation.studySubjectIdentifier = retrieve_studySubjectIdentifier(reader
						.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
								+ (i + 1)
								+ "]/loadlabs:StudySubject/loadlabs:II/loadlabs:extension"));
				performedObservation.studySubjectIdentifierName = HubConstants.CC_PATIENT_POSITION_IDENTIFIER_NAME; // retrieve_studySubjectIdentifierName(reader.read("loadlabs:LabResult/loadlabs:StudySubject/loadlabs:II/loadlabs:extension"));
				performedObservation.studySubjectIdentifierRoot = reader
						.read("/loadlabs:LoadLabsRequest/loadlabs:LabResult["
								+ (i + 1)
								+ "]/loadlabs:StudySubject/loadlabs:II/loadlabs:root");

			}
			logger.debug(performedClinicalResult);
			performedClinicalResults[i] = performedClinicalResult;
		}
		return xrequest;
	}

	protected String retrieve_studySubjectIdentifier(String s) {
		if (s == null)
			return "";
		s = s.trim();
		int colonIndex = s.indexOf(':');
		if (colonIndex < 0) {
			return "";
		}
		return s.substring(colonIndex + 1);
	}

	protected String retrieve_studySubjectIdentifierName(String s) {
		if (s == null)
			return "";
		s = s.trim();
		int colonIndex = s.indexOf(':');
		logger.debug("String:" + s + " colonIndex:" + colonIndex);
		if (colonIndex < 0) {
			return "";
		}
		return s.substring(0, colonIndex);
	}

	public static void main(String[] args) {
		System.out.println("MRN:".substring(0, 3));
	}
}
