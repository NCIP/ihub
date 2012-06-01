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

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        if (userName.equals(pc.getIdentifier())) {
            pc.setPassword(password);
        }
    }
}
