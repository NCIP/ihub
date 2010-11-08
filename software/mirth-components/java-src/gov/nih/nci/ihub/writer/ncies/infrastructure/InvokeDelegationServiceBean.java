package gov.nih.nci.ihub.writer.ncies.infrastructure;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.caxchange.security.CaXchangePrincipal;
import gov.nih.nci.ihub.cache.UserProxyCache;
import gov.nih.nci.ihub.cache.UserProxyCacheImpl;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.business.RegistrationInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.common.AggregatedResponseBuilder;
import gov.nih.nci.ihub.writer.ncies.common.GenerateResponseBean;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;

import java.io.StringReader;
import java.security.Principal;
import java.util.ArrayList;

import javax.security.auth.Subject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.cagrid.gaards.cds.client.DelegatedCredentialUserClient;
import org.cagrid.gaards.cds.delegated.stubs.types.DelegatedCredentialReference;
import org.cagrid.gaards.cds.stubs.types.CDSInternalFault;
import org.cagrid.gaards.cds.stubs.types.DelegationFault;
import org.cagrid.gaards.cds.stubs.types.PermissionDeniedFault;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.GlobusCredentialException;
import org.globus.wsrf.encoding.DeserializationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class InvokeDelegationServiceBean {

	public static Logger logger = LogManager
			.getLogger(InvokeDelegationServiceBean.class);

	private String gridIdentifier;
	private String delegatedCredentialRef;
	private String certificateFilePath;
	private String keyFilePath;
	private static GlobusCredential hostCredential = null;
	// TODO userProxyCache needs to be configured during development
	private long userProxyCacheTimeToLive;
	public static UserProxyCache userProxyCache;

	public InvokeDelegationServiceBean(String gridIdentifier,
			String delegatedCredentialRef, String certificateFilePath,
			String keyFilePath, long userProxyCacheTimeToLive) {
		super();
		this.gridIdentifier = gridIdentifier;
		this.delegatedCredentialRef = delegatedCredentialRef;
		this.certificateFilePath = certificateFilePath;
		this.keyFilePath = keyFilePath;
		this.userProxyCacheTimeToLive = userProxyCacheTimeToLive;
	}

	public Subject invokeDelegationService() throws Exception {
		try {
			userProxyCache = new UserProxyCacheImpl();
			userProxyCache.init();
			userProxyCache.setMaxTimeToLive(userProxyCacheTimeToLive);

			delegatedCredentialRef = delegatedCredentialRef.replace('@', '"');
			hostCredential = new GlobusCredential(certificateFilePath,
					keyFilePath);
			GlobusCredential userCredential = null;
			userCredential = getCachedGlobusCredential();

			if (userCredential == null) {
				logger.debug("User credential not found in cache.");
				userCredential = getGlobusCredentialFromDelgationService();
			}
//TODO Double check if this code needs to be un-commented
//			if (!(userCredential.getIdentity().equals(gridIdentifier))) {
//				throw new InvalidDelegatedCredentialsException(
//						"Identity of the delegated grid user:"
//								+ userCredential.getIdentity()
//								+ " does not match the identity of the grid user:"
//								+ gridIdentifier);
//			}
			Subject subject = new Subject();

			CaXchangePrincipal principal = new CaXchangePrincipal();
			principal.setName(userCredential.getIdentity());
			subject.getPrincipals().add((Principal) principal);

			subject.getPrivateCredentials().add(userCredential);

			logger.info("usercredential=" + userCredential.toString());
			System.out.println("UserCredential: " + userCredential.toString());
			return subject;

		} catch (Exception e) {
			logger.error("Error authenticating", e);
			throw e;
		}
	}

	/**
	 * Returns the GlobusCredential object
	 * 
	 * @return
	 * @throws Exception
	 */
	public GlobusCredential getCachedGlobusCredential() throws Exception {
		String delegationEPR = null;
		try {
			delegationEPR = delegatedCredentialRef;
			GlobusCredential userCredentials = null;
			if (userProxyCache != null) {
				userCredentials = userProxyCache.get(delegationEPR);
			}
			try {
				if (userCredentials != null) {
					userCredentials.verify();
				}
			} catch (GlobusCredentialException gce) {
				userProxyCache.remove(delegationEPR);
				userCredentials = null;
			}
			return userCredentials;
		} catch (Exception e) {
			logger.error("Error retreiving user credentials from cache for:"
					+ delegationEPR, e);
			throw new Exception(
					"Error retreiving user credentials from cache for:"
							+ delegationEPR, e);
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public GlobusCredential getGlobusCredentialFromDelgationService()
			throws Exception {
		try {
			DelegatedCredentialReference delegatedCredentialReference = null;
			try {
				logger.info("**** Delegated EPR:" + delegatedCredentialRef);
				System.out.println("PROCESS 1: " + delegatedCredentialRef);
				delegatedCredentialReference = (DelegatedCredentialReference) Utils
						.deserializeObject(
								new StringReader(delegatedCredentialRef),
								DelegatedCredentialReference.class,
								DelegatedCredentialUserClient.class
										.getResourceAsStream("client-config.wsdd"));
				System.out.println("PROCESS 2");
			} catch (DeserializationException e) {
				throw new GridInvocationException(
						"Unable to deserialize the Delegation Reference", e);
			}
			DelegatedCredentialUserClient delegatedCredentialUserClient = null;
			try {
				System.out.println("PROCESS 2.1");
				logger
						.debug("**** delegatedCredentialReference.getEndpointReference"
								+ delegatedCredentialReference
										.getEndpointReference());
				System.out.println("PROCESS 2.2");
				delegatedCredentialUserClient = new DelegatedCredentialUserClient(
						delegatedCredentialReference, hostCredential);
				System.out.println("PROCESS 2.3");
			} catch (Exception e) {
				System.out.println("PROCESS 2.4: " + e.getMessage());
				throw new GridInvocationException(
						"Unable to Initialize the Delegation Lookup Client", e);
			}
			GlobusCredential userCredential;
			System.out.println("PROCESS 3");
			try {
				long startTime = new java.util.Date().getTime();
				userCredential = delegatedCredentialUserClient
						.getDelegatedCredential();
				System.out.println("PROCESS 4");
				long endTime = new java.util.Date().getTime();
				logger.debug("Time for delegation service:"
						+ (endTime - startTime));
			} catch (CDSInternalFault e) {
				logger
						.error("Internal error getting delegated credentials.",
								e);
				throw e;
			} catch (DelegationFault e) {
				logger
						.error(
								"Delegation fault occurred getting delegated credentials.",
								e);
				throw e;
			} catch (PermissionDeniedFault e) {
				logger
						.error(
								"Permission denied fault getting delegated credentials.",
								e);
				throw e;
			}
			if (userProxyCache != null) {
				userProxyCache.put(delegatedCredentialRef, userCredential);
			}
			return userCredential;
		} catch (Exception e) {
			logger
					.error(
							"Error getting user credentials from the delegation service.",
							e);
			throw new Exception(
					"Error getting user credentials from the delegation service.",
					e);
		}
	}

	public class InvalidDelegatedCredentialsException extends Exception {
		public InvalidDelegatedCredentialsException() {
			super();
		}

		public InvalidDelegatedCredentialsException(String message,
				Throwable cause) {
			super(message, cause);
		}

		public InvalidDelegatedCredentialsException(String message) {
			super(message);
		}

		public InvalidDelegatedCredentialsException(Throwable cause) {
			super(cause);
		}

	}

	public static void main(String args[]) {
		/*
		 * InvokeDelegationServiceBean delegationServiceBean = new
		 * InvokeDelegationServiceBean(
		 * "/O=caBIG/OU=caGrid/OU=Stage LOA1/OU=Dorian/CN=cctsdev1",
		 * 
		 * "<ns1:DelegatedCredentialReference xmlns:ns1=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types\">      <ns2:EndpointReference xsi:type=\"ns2:EndpointReferenceType\" xmlns:ns2=\"http://schemas.xmlsoap.org/ws/2004/03/addressing\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">       <ns2:Address xsi:type=\"ns2:AttributedURI\">https://cagrid-cds-stage.nci.nih.gov:8443/wsrf/services/cagrid/DelegatedCredential</ns2:Address>       <ns2:ReferenceProperties xsi:type=\"ns2:ReferencePropertiesType\">        <ns2:DelegatedCredentialKey xmlns:ns2=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential\">         <ns3:delegationId xmlns:ns3=\"http://gaards.cagrid.org/cds\">6280</ns3:delegationId>        </ns2:DelegatedCredentialKey>       </ns2:ReferenceProperties>       <ns2:ReferenceParameters xsi:type=\"ns2:ReferenceParametersType\"/></ns2:EndpointReference></ns1:DelegatedCredentialReference>"
		 * ,
		 * 
		 * "C:/projects_svn/caBIGIntegrationHub/" +
		 * "common/resources/ihub/ncias-c278-v.nci.nih.gov-cert.pem",
		 * 
		 * "C:/projects_svn/caBIGIntegrationHub/" +
		 * "common/resources/ihub/ncias-c278-v.nci.nih.gov-key.pem" );
		 */
		// ClassPathXmlApplicationContext context = new
		// ClassPathXmlApplicationContext("gov/nih/nci/ihub/config/beans-config.xml");
		// UserProxyCache userProxyCache =
		// (UserProxyCache)context.getBean("userProxyCache");
		InvokeDelegationServiceBean delegationServiceBean = new InvokeDelegationServiceBean(
				"/O=caBIG/OU=caGrid/OU=Stage LOA1/OU=Dorian/CN=dev2_ld_user",

				"<ns1:DelegatedCredentialReference xmlns:ns1=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types\">      <ns2:EndpointReference xsi:type=\"ns2:EndpointReferenceType\" xmlns:ns2=\"http://schemas.xmlsoap.org/ws/2004/03/addressing\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">       <ns2:Address xsi:type=\"ns2:AttributedURI\">https://cagrid-cds-stage.nci.nih.gov:8443/wsrf/services/cagrid/DelegatedCredential</ns2:Address>       <ns2:ReferenceProperties xsi:type=\"ns2:ReferencePropertiesType\">        <ns2:DelegatedCredentialKey xmlns:ns2=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential\">         <ns3:delegationId xmlns:ns3=\"http://gaards.cagrid.org/cds\">7690</ns3:delegationId>        </ns2:DelegatedCredentialKey>       </ns2:ReferenceProperties>       <ns2:ReferenceParameters xsi:type=\"ns2:ReferenceParametersType\"/></ns2:EndpointReference></ns1:DelegatedCredentialReference>",

				"C:/projects_svn/caBIGIntegrationHub/common/resources/ihub/ncias-c278-v.nci.nih.gov-cert.pem",

				"C:/projects_svn/caBIGIntegrationHub/common/resources/ihub/ncias-c278-v.nci.nih.gov-key.pem",
				600);

		try {
			GenerateResponseBean generateResponseBean = new GenerateResponseBean();
			// delegationServiceBean.setUserProxyCache(userProxyCache);
			Subject subject = delegationServiceBean.invokeDelegationService();
			RegistrationInvocationStrategy invocationStrategy = new RegistrationInvocationStrategy(
					subject,
					"https://ncias-d282-v.nci.nih.gov:29543/ctom-wsrf/services/cagrid/RegistrationConsumer",
					"<registration gridId=@74703559-2c2f-4bcd-8f01-3f8898e8fbea@ xmlns=@gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain@ xmlns:xsi=@http://www.w3.org/2001/XMLSchema-instance@ xmlns:c3pr=@gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain@>   <studySite>      <healthcareSite>         <name>Duke University Medical Center</name>         <address>            <city>Durham</city>            <stateCode>NC</stateCode>            <countryCode>USA</countryCode>         </address>         <nciInstituteCode>NC010</nciInstituteCode>      </healthcareSite>   </studySite>   <studyRef>      <identifier xsi:type=@OrganizationAssignedIdentifierType@>         <type>Coordinating Center Identifier</type>         <value>SMOKE_TEST_5</value>         <primaryIndicator>true</primaryIndicator>         <healthcareSite>            <name>Duke University Medical Center</name>            <address>               <city>Durham</city>               <stateCode>NC</stateCode>               <countryCode>USA</countryCode>            </address>            <nciInstituteCode>NC010</nciInstituteCode>         </healthcareSite>      </identifier>      <identifier xsi:type=@OrganizationAssignedIdentifierType@>         <type>Protocol Authority Identifier</type>         <value>SMOKE_TEST_5</value>         <primaryIndicator>false</primaryIndicator>         <healthcareSite>            <name>Cancer Therapy Evaluation Program</name>            <address>               <streetAddress>9000 Rockville Pike</streetAddress>               <city>Bethesda</city>               <stateCode>MD</stateCode>               <postalCode>20892</postalCode>               <countryCode>USA</countryCode>            </address>            <nciInstituteCode>CTEP</nciInstituteCode>         </healthcareSite>      </identifier>   </studyRef>   <participant gridId=@9a18bcfa-1711-44d0-bede-7545e1d9a3d9@>      <firstName>Garth</firstName>      <lastName>Brooks</lastName>      <address/>      <administrativeGenderCode>Male</administrativeGenderCode>      <birthDate>1965-09-08</birthDate>      <ethnicGroupCode>Non Hispanic or Latino</ethnicGroupCode>      <raceCode>Black_or_African_American</raceCode>      <identifier xsi:type=@OrganizationAssignedIdentifierType@>         <type>MRN</type>         <value>GARTH-001</value>         <primaryIndicator>true</primaryIndicator>         <healthcareSite>            <name>Duke University Medical Center</name>            <address>               <city>Durham</city>               <stateCode>NC</stateCode>               <countryCode>USA</countryCode>            </address>            <nciInstituteCode>NC010</nciInstituteCode>         </healthcareSite>      </identifier>      <identifier xsi:type=@SystemAssignedIdentifierType@>         <type>SUBJECT_IDENTIFIER</type>         <value>G-53dc814e-08c0-4c36-a5dc-71d09172cb29</value>         <primaryIndicator>false</primaryIndicator>         <systemName>C3PR</systemName>      </identifier>   </participant>   <startDate>2010-08-11</startDate>   <informedConsentFormSignedDate>2010-08-11</informedConsentFormSignedDate>   <informedConsentVersion>general</informedConsentVersion>   <identifier xsi:type=@SystemAssignedIdentifierType@>      <type>Study Subject Identifier</type>      <value>G-1a13e380-27ca-48f3-aa76-7365bc5f2f3d</value>      <primaryIndicator>false</primaryIndicator>      <systemName>C3PR</systemName>   </identifier>   <identifier xsi:type=@OrganizationAssignedIdentifierType@>      <type>COORDINATING_CENTER_ASSIGNED_STUDY_SUBJECT_IDENTIFIER</type>      <value>6</value>      <primaryIndicator>false</primaryIndicator>      <healthcareSite>         <name>Duke University Medical Center</name>         <address>            <city>Durham</city>            <stateCode>NC</stateCode>            <countryCode>USA</countryCode>         </address>         <nciInstituteCode>NC010</nciInstituteCode>      </healthcareSite>   </identifier>   <scheduledEpoch xsi:type=@ScheduledTreatmentEpochType@ gridId=@c902dbc4-9883-473b-bedb-aa6b70bed793@>      <startDate>2010-08-11</startDate>      <epoch xsi:type=@TreatmentEpochType@ gridId=@c902dbc4-9883-473b-bedb-aa6b70bed793@>         <name>epoch1</name>         <descriptionText/>         <arm gridId=@200dcd1f-faf2-49de-afb5-840f3c030d33@>            <name>Arm A</name>            <descriptionText/>         </arm>      </epoch>      <scheduledArm>         <arm gridId=@200dcd1f-faf2-49de-afb5-840f3c030d33@>            <name>Arm A</name>            <descriptionText/>         </arm>      </scheduledArm>      <eligibilityIndicator>true</eligibilityIndicator>   </scheduledEpoch></registration>",
					"CTOM");
			GridInvocationResult gridInvocationResult = invocationStrategy
					.invokeGridService(false);
			Document ctomGridInvocationResultDocument = generateResponseBean
					.createOutputDocument("CTOM", "", null,
							gridInvocationResult);
			ArrayList<Document> responseList = new ArrayList<Document>();
			String ctomGridInvocationResultString = IntegrationHubUtil.xmlToString(ctomGridInvocationResultDocument);
			Document stringXMLTODomDocument = IntegrationHubUtil.stringToDOMDocument(ctomGridInvocationResultString);
			responseList.add(stringXMLTODomDocument);
			
			
			System.out.println("CTOM Compiled Response: "
					+ IntegrationHubUtil.xmlToString(stringXMLTODomDocument));

			invocationStrategy = new RegistrationInvocationStrategy(
					subject,
					"https://ncias-d282-v.nci.nih.gov:29543/wsrf-caaers/services/cagrid/RegistrationConsumer",
					"<registration gridId=@74703559-2c2f-4bcd-8f01-3f8898e8fbea@ xmlns=@gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain@ xmlns:xsi=@http://www.w3.org/2001/XMLSchema-instance@ xmlns:c3pr=@gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain@>   <studySite>      <healthcareSite>         <name>Duke University Medical Center</name>         <address>            <city>Durham</city>            <stateCode>NC</stateCode>            <countryCode>USA</countryCode>         </address>         <nciInstituteCode>NC010</nciInstituteCode>      </healthcareSite>   </studySite>   <studyRef>      <identifier xsi:type=@OrganizationAssignedIdentifierType@>         <type>Coordinating Center Identifier</type>         <value>SMOKE_TEST_5</value>         <primaryIndicator>true</primaryIndicator>         <healthcareSite>            <name>Duke University Medical Center</name>            <address>               <city>Durham</city>               <stateCode>NC</stateCode>               <countryCode>USA</countryCode>            </address>            <nciInstituteCode>NC010</nciInstituteCode>         </healthcareSite>      </identifier>      <identifier xsi:type=@OrganizationAssignedIdentifierType@>         <type>Protocol Authority Identifier</type>         <value>SMOKE_TEST_5</value>         <primaryIndicator>false</primaryIndicator>         <healthcareSite>            <name>Cancer Therapy Evaluation Program</name>            <address>               <streetAddress>9000 Rockville Pike</streetAddress>               <city>Bethesda</city>               <stateCode>MD</stateCode>               <postalCode>20892</postalCode>               <countryCode>USA</countryCode>            </address>            <nciInstituteCode>CTEP</nciInstituteCode>         </healthcareSite>      </identifier>   </studyRef>   <participant gridId=@9a18bcfa-1711-44d0-bede-7545e1d9a3d9@>      <firstName>Garth</firstName>      <lastName>Brooks</lastName>      <address/>      <administrativeGenderCode>Male</administrativeGenderCode>      <birthDate>1965-09-08</birthDate>      <ethnicGroupCode>Non Hispanic or Latino</ethnicGroupCode>      <raceCode>Black_or_African_American</raceCode>      <identifier xsi:type=@OrganizationAssignedIdentifierType@>         <type>MRN</type>         <value>GARTH-001</value>         <primaryIndicator>true</primaryIndicator>         <healthcareSite>            <name>Duke University Medical Center</name>            <address>               <city>Durham</city>               <stateCode>NC</stateCode>               <countryCode>USA</countryCode>            </address>            <nciInstituteCode>NC010</nciInstituteCode>         </healthcareSite>      </identifier>      <identifier xsi:type=@SystemAssignedIdentifierType@>         <type>SUBJECT_IDENTIFIER</type>         <value>G-53dc814e-08c0-4c36-a5dc-71d09172cb29</value>         <primaryIndicator>false</primaryIndicator>         <systemName>C3PR</systemName>      </identifier>   </participant>   <startDate>2010-08-11</startDate>   <informedConsentFormSignedDate>2010-08-11</informedConsentFormSignedDate>   <informedConsentVersion>general</informedConsentVersion>   <identifier xsi:type=@SystemAssignedIdentifierType@>      <type>Study Subject Identifier</type>      <value>G-1a13e380-27ca-48f3-aa76-7365bc5f2f3d</value>      <primaryIndicator>false</primaryIndicator>      <systemName>C3PR</systemName>   </identifier>   <identifier xsi:type=@OrganizationAssignedIdentifierType@>      <type>COORDINATING_CENTER_ASSIGNED_STUDY_SUBJECT_IDENTIFIER</type>      <value>6</value>      <primaryIndicator>false</primaryIndicator>      <healthcareSite>         <name>Duke University Medical Center</name>         <address>            <city>Durham</city>            <stateCode>NC</stateCode>            <countryCode>USA</countryCode>         </address>         <nciInstituteCode>NC010</nciInstituteCode>      </healthcareSite>   </identifier>   <scheduledEpoch xsi:type=@ScheduledTreatmentEpochType@ gridId=@c902dbc4-9883-473b-bedb-aa6b70bed793@>      <startDate>2010-08-11</startDate>      <epoch xsi:type=@TreatmentEpochType@ gridId=@c902dbc4-9883-473b-bedb-aa6b70bed793@>         <name>epoch1</name>         <descriptionText/>         <arm gridId=@200dcd1f-faf2-49de-afb5-840f3c030d33@>            <name>Arm A</name>            <descriptionText/>         </arm>      </epoch>      <scheduledArm>         <arm gridId=@200dcd1f-faf2-49de-afb5-840f3c030d33@>            <name>Arm A</name>            <descriptionText/>         </arm>      </scheduledArm>      <eligibilityIndicator>true</eligibilityIndicator>   </scheduledEpoch></registration>",
					"caAERS");
			GridInvocationResult caAersGridInvocationResult = invocationStrategy
					.invokeGridService(false);
			Document caaersGridInvocationResultDocument = generateResponseBean
					.createOutputDocument("caAERS", "", null,
							caAersGridInvocationResult);
			responseList.add(caaersGridInvocationResultDocument);
			System.out.println("caAERS Compiled Response: "
					+ IntegrationHubUtil.xmlToString(caaersGridInvocationResultDocument));

			AggregatedResponseBuilder responseBuilder = new AggregatedResponseBuilder();
			Document aggregatedResponse = responseBuilder
					.buildAggregatedDocument(responseList, false);
			System.out.println("Aggregated Response: "
					+ IntegrationHubUtil.xmlToString(aggregatedResponse));
			
			

			Node overallResponse = generateResponseBean
					.generateResponseFromAggregatedResponse(
							"myExternalIdentifier", "1", aggregatedResponse);
			System.out.println("Overall Response: "
					+ IntegrationHubUtil.xmlToString(overallResponse));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getGridIdentifier() {
		return gridIdentifier;
	}

	public void setGridIdentifier(String gridIdentifier) {
		this.gridIdentifier = gridIdentifier;
	}

	public String getDelegatedCredentialRef() {
		return delegatedCredentialRef;
	}

	public void setDelegatedCredentialRef(String delegatedCredentialRef) {
		this.delegatedCredentialRef = delegatedCredentialRef;
	}

	public String getCertificateFilePath() {
		return certificateFilePath;
	}

	public void setCertificateFilePath(String certificateFilePath) {
		this.certificateFilePath = certificateFilePath;
	}

	public String getKeyFilePath() {
		return keyFilePath;
	}

	public void setKeyFilePath(String keyFilePath) {
		this.keyFilePath = keyFilePath;
	}

	public static GlobusCredential getHostCredential() {
		return hostCredential;
	}

	public static void setHostCredential(GlobusCredential hostCredential) {
		InvokeDelegationServiceBean.hostCredential = hostCredential;
	}

	public UserProxyCache getUserProxyCache() {
		return userProxyCache;
	}
}
