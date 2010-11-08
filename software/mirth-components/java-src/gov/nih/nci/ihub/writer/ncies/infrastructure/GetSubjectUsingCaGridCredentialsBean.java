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
import gov.nih.nci.caxchange.security.CaXchangePrincipal;
import gov.nih.nci.ihub.writer.ncies.exception.AuthenticationConfigurationException;
import gov.nih.nci.ihub.writer.ncies.exception.AuthenticationErrorException;

import java.rmi.RemoteException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Subject;

import org.apache.axis.types.URI.MalformedURIException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.GlobusCredentialException;

public class GetSubjectUsingCaGridCredentialsBean {
	private String username,
			password,
			authenticationServiceURL,
			dorianServiceURL = "https://cagrid-dorian-stage.nci.nih.gov:8443/wsrf/services/cagrid/Dorian";

	private int proxyLifetimeHours = 12, proxyLifetimeMinutes,
			proxyLifetimeSeconds, delegationPathLength;

	private ProxyLifetime proxyLifetime = null;

	private static Map<String, GlobusCredential> cache = new HashMap<String, GlobusCredential>(
			2);
	public static Logger logger = LogManager
			.getLogger(GetSubjectUsingCaGridCredentialsBean.class);

	public GetSubjectUsingCaGridCredentialsBean(String username,
			String password, String authenticationServiceURL,
			String dorianServiceURL, int proxyLifetimeHours) {
		super();
		this.username = username;
		this.password = password;
		this.authenticationServiceURL = authenticationServiceURL;
		this.dorianServiceURL = dorianServiceURL;
		this.proxyLifetimeHours = proxyLifetimeHours;
	}

	public Subject getSubjectUsingCaGridCredentials() throws Exception {
		long timeBefore = new java.util.Date().getTime();

		try {
			verifyUserCredential(dorianServiceURL, authenticationServiceURL,
					username, password);
			GlobusCredential userCredential = getCachedCredential(
					dorianServiceURL, authenticationServiceURL, username,
					password);
			if (userCredential == null) {
				logger.debug("User credential not set or is expired.");

				// Authenticate the user credentials and retrieve
				SAMLAssertion samlAssertion = authenticate(
						authenticationServiceURL, username, password);

				// Obtained the GlobusCredential for the Authenticated User
				userCredential = obtainProxy(samlAssertion);
				setCachedCredential(dorianServiceURL, authenticationServiceURL,
						username, password, userCredential);
			}

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
	public SAMLAssertion authenticate(String authenticationServiceURL,
			String userName, String password)
			throws AuthenticationErrorException,
			AuthenticationConfigurationException {
		SAMLAssertion samlAssertion = null;
		BasicAuthenticationCredential basicAuthenticationCredential = new BasicAuthenticationCredential();
		basicAuthenticationCredential.setUserId(userName);
		basicAuthenticationCredential.setPassword(password);
		Credential credential = new Credential();
		credential
				.setBasicAuthenticationCredential(basicAuthenticationCredential);

		AuthenticationClient authenticationClient;
		try {
			authenticationClient = new AuthenticationClient(
					authenticationServiceURL, credential);
		} catch (MalformedURIException e) {
			throw new AuthenticationConfigurationException(
					"Invalid Authentication Service URL : " + e.getMessage());
		} catch (RemoteException e) {
			throw new AuthenticationConfigurationException(
					"Error accessing the Authentication Service : "
							+ e.getMessage());
		}
		try {
			samlAssertion = authenticationClient.authenticate();
		} catch (InvalidCredentialFault e) {
			throw new AuthenticationErrorException("Invalid Credentials : "
					+ e.getMessage());
		} catch (InsufficientAttributeFault e) {
			throw new AuthenticationConfigurationException(
					"Insufficient Attribute configured for the Authentication Service : "
							+ e.getMessage());
		} catch (AuthenticationProviderFault e) {
			throw new AuthenticationConfigurationException(
					"Error accessing the Authentication Service : "
							+ e.getMessage());
		} catch (RemoteException e) {
			throw new AuthenticationConfigurationException(
					"Error accessing the Authentication Service : "
							+ e.getMessage());
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
			throws AuthenticationConfigurationException,
			AuthenticationErrorException {
		GlobusCredential globusCredential = null;

		IFSUserClient ifsUserClient = null;
		try {
			ifsUserClient = new IFSUserClient(dorianServiceURL);
		} catch (MalformedURIException e) {
			throw new AuthenticationConfigurationException(
					"Invalid Dorian Service URL : " + e.getMessage());
		} catch (RemoteException e) {
			throw new AuthenticationConfigurationException(
					"Error accessing the Dorian Service : " + e.getMessage());
		}
		// Setting the lifetime object
		proxyLifetime = new ProxyLifetime();
		proxyLifetime.setHours(getProxyLifetimeHours());
		proxyLifetime.setMinutes(getProxyLifetimeMinutes());
		proxyLifetime.setSeconds(getProxyLifetimeSeconds());
		try {
			globusCredential = ifsUserClient.createProxy(samlAssertion,
					proxyLifetime, getDelegationPathLength());
		} catch (DorianFault e) {
			throw new AuthenticationConfigurationException(
					"Error accessing the Dorian Service : " + e.getMessage());
		} catch (gov.nih.nci.cagrid.dorian.stubs.types.DorianInternalFault e) {
			throw new AuthenticationConfigurationException(
					"Error accessing the Dorian Service : " + e.getMessage());
		} catch (InvalidAssertionFault e) {
			throw new AuthenticationConfigurationException(
					"Invalid SAML Assertion : " + e.getMessage());
		} catch (InvalidProxyFault e) {
			throw new AuthenticationConfigurationException(
					"Error accessing the Dorian Service : " + e.getMessage());
		} catch (UserPolicyFault e) {
			throw new AuthenticationConfigurationException(
					"Error accessing the Dorian Service : " + e.getMessage());
		} catch (PermissionDeniedFault e) {
			throw new AuthenticationErrorException(
					"Permission denied while obtaining Grid Credentials : "
							+ e.getMessage());
		}
		return globusCredential;
	}	

	protected GlobusCredential getCachedCredential(String dorianServiceUrl,
			String authenticationServiceUrl, String user, String password) {
		return cache.get(authenticationServiceUrl + user);
	}

	protected void setCachedCredential(String dorianServiceUrl,
			String authenticationServiceUrl, String user, String password,
			GlobusCredential userCreds) {
		cache.put(authenticationServiceUrl + user, userCreds);
	}

	/**
	 * Verifys the GlobusCredential and sets it to null if the credential
	 * verification fails
	 */
	private void verifyUserCredential(String dorianServiceUrl,
			String authenticationServiceUrl, String user, String password) {
		try {
			GlobusCredential userCredential = getCachedCredential(
					dorianServiceUrl, authenticationServiceUrl, user, password);
			if (userCredential != null) {
				userCredential.verify();
			}
		} catch (GlobusCredentialException gce) {
			logger.error("Error verifying globus credential:", gce);
			setCachedCredential(dorianServiceUrl, authenticationServiceUrl,
					user, password, null);
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

}
