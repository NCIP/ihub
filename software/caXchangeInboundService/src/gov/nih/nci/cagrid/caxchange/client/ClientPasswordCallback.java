/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.cagrid.caxchange.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.ws.security.WSPasswordCallback;
/**
 * This is a callback class for handling the WS Security
 * actions set in the WSS4JOutInterceptor.
 * 
 * @author Ekagra Software Technologies
 * 
 */
public class ClientPasswordCallback implements CallbackHandler {

	static Logger logger = LogManager.getLogger(ClientPasswordCallback.class);

	/*
	 * Gets called based on the action set in the WSS4JOutInterceptor
	 * 
	 * @see
	 * javax.security.auth.callback.CallbackHandler#handle(javax.security.auth
	 * .callback.Callback[])
	 */
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		logger.debug("**********PASSWORD VALIDATOR BEAN CALLED**********");

		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];

			int usage = pc.getUsage();
			//logger.debug("identifier: " + pc.getIdentifier());
			logger.debug("usage: " + pc.getUsage());

			if (usage == WSPasswordCallback.USERNAME_TOKEN) {
				logger.debug("USERNAME PASSWORD BLOCK");
				// username token pwd...
				pc.setPassword("password");				
			} else if (usage == WSPasswordCallback.SIGNATURE) {
				logger.debug("Signature BLOCK");
				// set the password for client's keystore.keyPassword
				pc.setPassword("wsClientPassword");
			}
		}

	}

}
