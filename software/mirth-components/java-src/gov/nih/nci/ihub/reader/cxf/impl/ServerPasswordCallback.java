/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.reader.cxf.impl;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.ws.security.WSPasswordCallback;

/**
 * This is a callback class for handling the WS Security
 * actions set in the WSS4JInInterceptor.
 *
 * @author Ekagra Software Technologies
 *
 */
public class ServerPasswordCallback implements CallbackHandler {

	static Logger logger = LogManager.getLogger(ServerPasswordCallback.class);
	
	private String password;
	
	private String identifier;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * javax.security.auth.callback.CallbackHandler#handle(javax.security.auth
	 * .callback.Callback[])
	 */
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		logger
				.debug("**********SERVER PASSWORD VALIDATOR BEAN CALLED**********");

		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
		logger.debug("User Identifier: " + pc.getIdentifer());

		if (pc.getIdentifer().equals(getIdentifier())) {
			// for password digest, set the password on the callback.
			// This will be compared to the password which was sent from the
			// client.
			// pc.setPassword("password");
			if (!pc.getPassword().equals(getPassword())) {
				throw new IOException("wrong password");
			}
		}
	}

}
