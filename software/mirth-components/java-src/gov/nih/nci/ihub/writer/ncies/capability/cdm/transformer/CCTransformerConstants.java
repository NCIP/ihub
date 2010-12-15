package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CCTransformerConstants {

	/*public static Logger logger = LogManager.getLogger(CCTransformerConstants.class);
	public static final String MRN_NAME = "MRN";
	public static final String COOPERATIVE_GROUP_IDENTIFIER_NAME = "COOPERATIVE_GROUP_IDENTIFIER";
	public static final String CLINICAL_CONNECTOR_ROOT = "2.16.840.1.113883.3.26.7";
	public static final String MRN_ROOT = CLINICAL_CONNECTOR_ROOT+ ".1";
	public static final String COOPERATIVE_GROUP_IDENTIFIER_ROOOT = CLINICAL_CONNECTOR_ROOT+".5";
	public static final String STUDY_IDENTIFIER_NAME = "Study Identifier";
	public static final String STUDY_IDENTIFIER_ROOT = CLINICAL_CONNECTOR_ROOT+ ".2";
	public static final String STUDY_SITE_IDENTIFIER_NAME = "Study Site Identifier";
	public static final String STUDY_SITE_IDENTIFIER_ROOT = CLINICAL_CONNECTOR_ROOT+ ".3";
	public static final String REGISTRATION_SITE_IDENTIFIER_NAME = "nciInstituteCode";
	public static final String REGISTRATION_SITE_IDENTIFIER_ROOT = CLINICAL_CONNECTOR_ROOT+ ".4";
	public static final String PATIENT_POSITION_IDENTIFIER_NAME = "Patient Position";
	public static final String PATIENT_POSITION_IDENTIFIER_ROOT = ".6";
	public static final String STUDY_PROTOCOL_IDENTIFIER_NAME = "Study Protocol";
	public static final String STUDY_PROTOCOL_IDENTIFIER_ROOT = ".7";
	public static final Map<String,String> NAME_ROOT_MAP = new HashMap<String, String>(3);

	static {
		//Override root values from properties files.
		Properties caxchangeProperties = new java.util.Properties();
		boolean loaded =false;
		try {
			InputStream propertiesStream = CCTransformerConstants.class.getClassLoader().getResourceAsStream("caxchange.properties");
			if (propertiesStream != null) {
				caxchangeProperties.load(propertiesStream);
				loaded = true;
				logger.info("Root values loaded from properties file.");
			}else{
				logger.warn("Could not find the properties file:caxchange.properties for overriding root values.");
			}
		}catch(Throwable t){
			logger.warn("Could not load the properties file:caxchange.properties for overriding root values."+t.getMessage());
			logger.info("Proceeding with default values.");
		}
		if (loaded){
		   String clinicalConnectorRoot =  caxchangeProperties.getProperty("clinicalconnector.root.value");
		   NAME_ROOT_MAP.put(MRN_NAME, clinicalConnectorRoot+ caxchangeProperties.getProperty("clinicalconnector.mrn.root.value"));
		   NAME_ROOT_MAP.put(STUDY_IDENTIFIER_NAME, clinicalConnectorRoot+ caxchangeProperties.getProperty("clinicalconnector.study.root.value"));
		   NAME_ROOT_MAP.put(STUDY_SITE_IDENTIFIER_NAME,clinicalConnectorRoot+ caxchangeProperties.getProperty("clinicalconnector.studysite.root.value"));
		   NAME_ROOT_MAP.put(REGISTRATION_SITE_IDENTIFIER_NAME, clinicalConnectorRoot+ caxchangeProperties.getProperty("clinicalconnector.registrationsite.root.value"));
		   NAME_ROOT_MAP.put(COOPERATIVE_GROUP_IDENTIFIER_NAME, clinicalConnectorRoot+ caxchangeProperties.getProperty("clinicalconnector.cooperative.group.identifier.root.value"));
		   NAME_ROOT_MAP.put(PATIENT_POSITION_IDENTIFIER_NAME, clinicalConnectorRoot+ caxchangeProperties.getProperty("clinicalconnector.patientposition.root.value"));
		   NAME_ROOT_MAP.put(STUDY_PROTOCOL_IDENTIFIER_NAME, clinicalConnectorRoot+ caxchangeProperties.getProperty("clinicalconnector.studyprotocol.root.value"));
		}
		else {
		   NAME_ROOT_MAP.put(MRN_NAME, MRN_ROOT);
		   NAME_ROOT_MAP.put(STUDY_IDENTIFIER_NAME,STUDY_IDENTIFIER_ROOT);
		   NAME_ROOT_MAP.put(STUDY_SITE_IDENTIFIER_NAME,STUDY_SITE_IDENTIFIER_ROOT);
		   NAME_ROOT_MAP.put(REGISTRATION_SITE_IDENTIFIER_NAME,REGISTRATION_SITE_IDENTIFIER_ROOT);
		   NAME_ROOT_MAP.put(COOPERATIVE_GROUP_IDENTIFIER_NAME,COOPERATIVE_GROUP_IDENTIFIER_ROOOT);
		   NAME_ROOT_MAP.put(PATIENT_POSITION_IDENTIFIER_NAME, PATIENT_POSITION_IDENTIFIER_ROOT);
		   NAME_ROOT_MAP.put(STUDY_PROTOCOL_IDENTIFIER_NAME, STUDY_PROTOCOL_IDENTIFIER_ROOT);
		}
	}*/

}
