package gov.nih.nci.ihub.writer.ncies.infrastructure;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;
import gov.nih.nci.cagrid.authentication.bean.Credential;
import gov.nih.nci.cagrid.authentication.client.AuthenticationClient;
import gov.nih.nci.cagrid.authentication.stubs.types.AuthenticationProviderFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InsufficientAttributeFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InvalidCredentialFault;
import gov.nih.nci.cagrid.dorian.client.IFSUserClient;
import gov.nih.nci.cagrid.dorian.common.DorianFault;
import gov.nih.nci.cagrid.dorian.ifs.bean.ProxyLifetime;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidAssertionFault;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidProxyFault;
import gov.nih.nci.cagrid.dorian.stubs.types.PermissionDeniedFault;
import gov.nih.nci.cagrid.dorian.stubs.types.UserPolicyFault;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.ihub.writer.ncies.exception.AuthenticationConfigurationException;
import gov.nih.nci.ihub.writer.ncies.exception.AuthenticationErrorException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.types.URI.MalformedURIException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.cagrid.gaards.websso.authentication.helper.GridCredentialDelegator;
import org.cagrid.gaards.websso.authentication.helper.impl.GridCredentialDelegatorImpl;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.GlobusCredentialException;

public class CaGridAuthenticationManager {
	private String username,
			password,
			authenticationServiceURL,
			dorianServiceURL = "https://cagrid-dorian-stage.nci.nih.gov:8443/wsrf/services/cagrid/Dorian",
			credentialDelegationServiceURL, hostIdentity;

	private static final String DELEGATED_CREDENTIAL_REFERENCE = "delegatedCredentialRef";
	private int proxyLifetimeHours = 12, proxyLifetimeMinutes,
			proxyLifetimeSeconds, delegationPathLength;

	private ProxyLifetime proxyLifetime = null;

	private static Map<String, GlobusCredential> cache = new HashMap<String, GlobusCredential>(2);
	
	private static Map<String, String> delegationCredentialCache = new HashMap<String, String>(2);
	
	private List<String> hostIdentities;
	public static Logger logger = LogManager.getLogger(CaGridAuthenticationManager.class);

	public CaGridAuthenticationManager(String username, String password,
			String authenticationServiceURL, String dorianServiceURL,
			String credentialDelegationServiceURL, int proxyLifetimeHours,
			String hostIdentity) {
		super();
		this.username = username;
		this.password = password;
		this.authenticationServiceURL = authenticationServiceURL;
		this.dorianServiceURL = dorianServiceURL;
		this.credentialDelegationServiceURL = credentialDelegationServiceURL;
		this.proxyLifetimeHours = proxyLifetimeHours;		
		this.hostIdentity = hostIdentity;
	}

	/**
	 * Authenticate the user credentials and retrieve samlAssertion for
	 * authentication Service Obtain the GlobusCredential for the Authenticated
	 * User from Dorian Validate the Proxy or GlobusCredential Delegate the
	 * Globus Credentials
	 * 
	 * @return String - delegated credential reference
	 * @throws Exception
	 */
	public String getDelegatedCredentialReference() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getDelegatedCredentialReference() - start");
		}
		try {			
			verifyUserCredential(dorianServiceURL, authenticationServiceURL,username, password);
			GlobusCredential userCredential = getCachedCredential(dorianServiceURL, authenticationServiceURL, username,password);
			if (userCredential == null) {
				logger.debug("User credential not set or is expired.");
				SAMLAssertion samlAssertion = authenticate();
				userCredential = obtainProxy(samlAssertion);
				setCachedCredential(dorianServiceURL, authenticationServiceURL,username, password, userCredential);
				GridCredentialDelegator gridCredentialDelegator = new GridCredentialDelegatorImpl(credentialDelegationServiceURL, proxyLifetimeHours,
						proxyLifetimeMinutes, proxyLifetimeSeconds);
				hostIdentities = new ArrayList<String>();
				hostIdentities.add(hostIdentity);
				String newDelegatedCredentialReference = gridCredentialDelegator.delegateGridCredential(userCredential, proxyLifetime,hostIdentities);
				setDelegationCredentialCache(DELEGATED_CREDENTIAL_REFERENCE + username,newDelegatedCredentialReference);
			}			
			logger.info("Delegated Credential Reference for User: "+username+"\n "+ getDelegationCredentialCache(DELEGATED_CREDENTIAL_REFERENCE + username));
			return getDelegationCredentialCache(DELEGATED_CREDENTIAL_REFERENCE + username);

		} catch (Exception e) {
			logger.error("Error authenticating", e);
			throw e;
		}
	}

	/**
	 * Using a specific username, password and autnenticationServiceURL, creates
	 * and returns a SAMLAssertion
	 * 
	 * @param authenticationServiceURL
	 * @param userName
	 * @param password
	 * @return SAMLAssertion
	 * @throws AuthenticationErrorException
	 * @throws AuthenticationConfigurationException
	 */
	public SAMLAssertion authenticate() throws AuthenticationErrorException,
			AuthenticationConfigurationException {
		if (logger.isDebugEnabled()) {
			logger.debug("authenticate() - start");
		}

		SAMLAssertion samlAssertion = null;
		BasicAuthenticationCredential basicAuthenticationCredential = new BasicAuthenticationCredential();
		basicAuthenticationCredential.setUserId(username);
		basicAuthenticationCredential.setPassword(password);
		Credential credential = new Credential();
		credential.setBasicAuthenticationCredential(basicAuthenticationCredential);

		AuthenticationClient authenticationClient;
		try {
			authenticationClient = new AuthenticationClient(authenticationServiceURL, credential);
		} catch (MalformedURIException e) {
			logger.error("authenticate()", e);
			throw new AuthenticationConfigurationException("Invalid Authentication Service URL : " + e.getMessage());
		} catch (RemoteException e) {
			logger.error("authenticate()", e);
			throw new AuthenticationConfigurationException("Error accessing the Authentication Service : "+ e.getMessage());
		}
		try {
			samlAssertion = authenticationClient.authenticate();
		} catch (InvalidCredentialFault e) {
			logger.error("authenticate()", e);
			throw new AuthenticationErrorException("Invalid Credentials : "+ e.getMessage());
		} catch (InsufficientAttributeFault e) {
			logger.error("authenticate()", e);
			throw new AuthenticationConfigurationException("Insufficient Attribute configured for the Authentication Service : "+ e.getMessage());
		} catch (AuthenticationProviderFault e) {
			logger.error("authenticate()", e);
			throw new AuthenticationConfigurationException("Error accessing the Authentication Service : "+ e.getMessage());
		} catch (RemoteException e) {
			logger.error("authenticate()", e);
			throw new AuthenticationConfigurationException("Error accessing the Authentication Service : "+ e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("authenticate() - end");
		}
		return samlAssertion;
	}

	/**
	 * Obtains GlobusCredential from the SAMLAssertion
	 * 
	 * @param samlAssertion
	 * @return GlobusCredential
	 * @throws AuthenticationConfigurationException
	 * @throws AuthenticationErrorException
	 */
	public GlobusCredential obtainProxy(SAMLAssertion samlAssertion)
			throws AuthenticationConfigurationException, AuthenticationErrorException {
		if (logger.isDebugEnabled()) {
			logger.debug("obtainProxy(SAMLAssertion) - start");
		}

		GlobusCredential globusCredential = null;
		IFSUserClient ifsUserClient = null;
		
		try {
			ifsUserClient = new IFSUserClient(dorianServiceURL);
		} catch (MalformedURIException e) {logger.error("obtainProxy(SAMLAssertion)", e);
			throw new AuthenticationConfigurationException("Invalid Dorian Service URL : " + e.getMessage());
		} catch (RemoteException e) {
			logger.error("obtainProxy(SAMLAssertion)", e);
			throw new AuthenticationConfigurationException("Error accessing the Dorian Service : " + e.getMessage());
		}
		// Setting the lifetime object
		proxyLifetime = new ProxyLifetime();
		proxyLifetime.setHours(getProxyLifetimeHours());
		proxyLifetime.setMinutes(getProxyLifetimeMinutes());
		proxyLifetime.setSeconds(getProxyLifetimeSeconds());
		try {
			globusCredential = ifsUserClient.createProxy(samlAssertion,proxyLifetime, getDelegationPathLength());
		} catch (DorianFault e) {
			logger.error("obtainProxy(SAMLAssertion)", e);
			throw new AuthenticationConfigurationException("Error accessing the Dorian Service : " + e.getMessage());
		} catch (gov.nih.nci.cagrid.dorian.stubs.types.DorianInternalFault e) {
			logger.error("obtainProxy(SAMLAssertion)", e);
			throw new AuthenticationConfigurationException("Error accessing the Dorian Service : " + e.getMessage());
		} catch (InvalidAssertionFault e) {
			logger.error("obtainProxy(SAMLAssertion)", e);
			throw new AuthenticationConfigurationException("Invalid SAML Assertion : " + e.getMessage());
		} catch (InvalidProxyFault e) {
			logger.error("obtainProxy(SAMLAssertion)", e);
			throw new AuthenticationConfigurationException("Error accessing the Dorian Service : " + e.getMessage());
		} catch (UserPolicyFault e) {
			logger.error("obtainProxy(SAMLAssertion)", e);
			throw new AuthenticationConfigurationException("Error accessing the Dorian Service : " + e.getMessage());
		} catch (PermissionDeniedFault e) {
			logger.error("obtainProxy(SAMLAssertion)", e);
			throw new AuthenticationErrorException("Permission denied while obtaining Grid Credentials : "+ e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("obtainProxy(SAMLAssertion) - end");
		}
		return globusCredential;
	}

	protected GlobusCredential getCachedCredential(String dorianServiceUrl, String authenticationServiceUrl, String user, String password) {
		if (logger.isDebugEnabled()) {
			logger.debug("getCachedCredential(String, String, String, String) - start");
		}

		GlobusCredential returnGlobusCredential = cache.get(authenticationServiceUrl + user);
		if (logger.isDebugEnabled()) {
			logger.debug("getCachedCredential(String, String, String, String) - end");
		}
		return returnGlobusCredential;
	}

	protected void setCachedCredential(String dorianServiceUrl,String authenticationServiceUrl,String user,
			String password,GlobusCredential userCreds) {
		if (logger.isDebugEnabled()) {
			logger.debug("setCachedCredential(String, String, String, String, GlobusCredential) - start");
		}

		cache.put(authenticationServiceUrl + user, userCreds);

		if (logger.isDebugEnabled()) {
			logger.debug("setCachedCredential(String, String, String, String, GlobusCredential) - end");
		}
	}

	protected void setDelegationCredentialCache(String key, String delegatedCredentialRef) {
		if (logger.isDebugEnabled()) {
			logger.debug("setDelegationCredentialCache(String key, String delegatedCredentialRef) - start");
		}
		
		delegationCredentialCache.put(key, delegatedCredentialRef);
		
		if (logger.isDebugEnabled()) {
			logger.debug(" Delegation Credential Cache, Set");
		}
		
	}

	protected String getDelegationCredentialCache(String key) {
		String delegatedCredentialReference = delegationCredentialCache.get(key);
		if (logger.isDebugEnabled()) {
			logger.debug("Delegated Credential Reference:"+delegatedCredentialReference);
		}
		return delegatedCredentialReference;
	}

	/**
	 * Verify the GlobusCredential and sets it to null if the credentials
	 * verification fails
	 */
	private void verifyUserCredential(String dorianServiceUrl,
			String authenticationServiceUrl, String user, String password) {
		if (logger.isDebugEnabled()) {
			logger.debug("verifyUserCredential(String, String, String, String) - start");
		}

		try {
			logger.info("Verifying User Credentials");
			GlobusCredential userCredential = getCachedCredential(dorianServiceUrl, authenticationServiceUrl, user, password);
			if (userCredential != null) {
				userCredential.verify();
			}
		} catch (GlobusCredentialException gce) {
			logger.error("Error verifying globus credential:", gce);
			logger.info(" Failed to get User Credentials, clearing cache");
			setCachedCredential(dorianServiceUrl, authenticationServiceUrl, user, password, null);
			setDelegationCredentialCache(DELEGATED_CREDENTIAL_REFERENCE, null);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("verifyUserCredential(String, String, String, String) - end");
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthenticationServiceURL() {
		return authenticationServiceURL;
	}

	public void setAuthenticationServiceURL(String authenticationServiceURL) {
		this.authenticationServiceURL = authenticationServiceURL;
	}

	public String getDorianServiceURL() {
		return dorianServiceURL;
	}

	public void setDorianServiceURL(String dorianServiceURL) {
		this.dorianServiceURL = dorianServiceURL;
	}

	public String getCredentialDelegationServiceURL() {
		return credentialDelegationServiceURL;
	}

	public void setCredentialDelegationServiceURL(
			String credentialDelegationServiceURL) {
		this.credentialDelegationServiceURL = credentialDelegationServiceURL;
	}

	public int getProxyLifetimeHours() {
		return proxyLifetimeHours;
	}

	public void setProxyLifetimeHours(int proxyLifetimeHours) {
		this.proxyLifetimeHours = proxyLifetimeHours;
	}

	public int getProxyLifetimeMinutes() {
		return proxyLifetimeMinutes;
	}

	public void setProxyLifetimeMinutes(int proxyLifetimeMinutes) {
		this.proxyLifetimeMinutes = proxyLifetimeMinutes;
	}

	public int getProxyLifetimeSeconds() {
		return proxyLifetimeSeconds;
	}

	public void setProxyLifetimeSeconds(int proxyLifetimeSeconds) {
		this.proxyLifetimeSeconds = proxyLifetimeSeconds;
	}

	public int getDelegationPathLength() {
		return delegationPathLength;
	}

	public void setDelegationPathLength(int delegationPathLength) {
		this.delegationPathLength = delegationPathLength;
	}

	public ProxyLifetime getProxyLifetime() {
		return proxyLifetime;
	}

	public void setProxyLifetime(ProxyLifetime proxyLifetime) {
		this.proxyLifetime = proxyLifetime;
	}
	
	public String getHostIdentity() {
		return hostIdentity;
	}

	public void setHostIdentity(String hostIdentity) {
		this.hostIdentity = hostIdentity;
	}

}
