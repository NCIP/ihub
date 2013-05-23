/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.caaers;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

public class ClientPasswordCallback implements CallbackHandler {
	
	private String userName;
	
	private String password;
	
    public ClientPasswordCallback(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public void handle(Callback[] callbacks) throws IOException, 
            UnsupportedCallbackException {
		System.out.println("psswd clbck cl " + getClass().getClassLoader());
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        if (userName.equals(pc.getIdentifier())) {
            pc.setPassword(password);
        } 
    }
}

