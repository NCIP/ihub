/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer;

import gov.nih.nci.ihub.util.HubConstants;

import java.util.HashMap;
import java.util.Map;


/**
 * A common interface for transforming Suite messages to Clinical Connector messages.
 * 
 * @author marwahah
 *
 */
public abstract class CCTransformer {
	
	public static final Map<String,String> NAME_ROOT_MAP = new HashMap<String, String>(3);
	
	public abstract  Object convert2Request(String xml) throws CCTransformException;
	
	public abstract  String convert2RequestString(String xml) throws CCTransformException;

	public abstract  Object convert2RollbackRequest(String xml) throws CCTransformException;
	
	public abstract  String convert2RollbackRequestString(String xml) throws CCTransformException;
	
	static {
	   NAME_ROOT_MAP.put(HubConstants.CC_MRN_NAME,HubConstants.CC_MRN_ROOT);
	   NAME_ROOT_MAP.put(HubConstants.CC_STUDY_IDENTIFIER_NAME,HubConstants.CC_STUDY_IDENTIFIER_ROOT);
	   NAME_ROOT_MAP.put(HubConstants.CC_STUDY_SITE_IDENTIFIER_NAME,HubConstants.CC_STUDY_SITE_IDENTIFIER_ROOT);
	   NAME_ROOT_MAP.put(HubConstants.CC_REGISTRATION_SITE_IDENTIFIER_NAME,HubConstants.CC_REGISTRATION_SITE_IDENTIFIER_ROOT);
	   NAME_ROOT_MAP.put(HubConstants.CC_COOPERATIVE_GROUP_IDENTIFIER_NAME,HubConstants.CC_COOPERATIVE_GROUP_IDENTIFIER_ROOOT);
	   NAME_ROOT_MAP.put(HubConstants.CC_PATIENT_POSITION_IDENTIFIER_NAME,HubConstants.CC_PATIENT_POSITION_IDENTIFIER_ROOT);
	   NAME_ROOT_MAP.put(HubConstants.CC_STUDY_PROTOCOL_IDENTIFIER_NAME,HubConstants.CC_STUDY_PROTOCOL_IDENTIFIER_ROOT);
	}
}
