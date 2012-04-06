package gov.nih.nci.integration.caaers;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

public class ClientPasswordCallback implements CallbackHandler {

    public void handle(Callback[] callbacks) throws IOException, 
            UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        System.out.println("inside ClientPasswordCallback, " + pc);
        System.out.println("inside ClientPasswordCallback, " + pc.getIdentifier());
        if ("mayo-super-user".equals(pc.getIdentifier())) {
            pc.setPassword("Hello-12");
        } 
    }
}

