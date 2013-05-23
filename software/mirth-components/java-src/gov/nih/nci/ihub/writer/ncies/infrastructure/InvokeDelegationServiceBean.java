/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.infrastructure;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.caxchange.security.CaXchangePrincipal;
import gov.nih.nci.ihub.cache.UserProxyCache;
import gov.nih.nci.ihub.cache.UserProxyCacheImpl;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;

import java.io.StringReader;
import java.security.Principal;

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
				delegatedCredentialReference = (DelegatedCredentialReference) Utils
						.deserializeObject(
								new StringReader(delegatedCredentialRef),
								DelegatedCredentialReference.class,
								DelegatedCredentialUserClient.class
										.getResourceAsStream("client-config.wsdd"));
			} catch (DeserializationException e) {
				throw new GridInvocationException(
						"Unable to deserialize the Delegation Reference", e);
			}
			DelegatedCredentialUserClient delegatedCredentialUserClient = null;
			try {
				logger
						.debug("**** delegatedCredentialReference.getEndpointReference"
								+ delegatedCredentialReference
										.getEndpointReference());
				delegatedCredentialUserClient = new DelegatedCredentialUserClient(
						delegatedCredentialReference, hostCredential);
			} catch (Exception e) {
				throw new GridInvocationException(
						"Unable to Initialize the Delegation Lookup Client", e);
			}
			GlobusCredential userCredential;
			try {
				long startTime = new java.util.Date().getTime();
				userCredential = delegatedCredentialUserClient
						.getDelegatedCredential();
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
