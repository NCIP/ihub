package gov.nih.nci.integration.caaers;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

/**
 * 
 * @author Vinodh
 *
 */
public class ClientPasswordCallback implements CallbackHandler {

    private final String userName;

    private final String password;

    /**
     * Constructor
     * @param userName - userName
     * @param password - password
     */
    public ClientPasswordCallback(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    /**
     * handle
     * @param callbacks Callback[]     
     * @throws IOException - IOException
     * @throws UnsupportedCallbackException - UnsupportedCallbackException
     */
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        final WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        if (userName.equals(pc.getIdentifier())) {
            pc.setPassword(password);
        }
    }
}
