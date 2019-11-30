package jaas.authenticate;

import javax.security.auth.callback.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CLI login handler for typing in the credentials and other inputs like OTP.
 */
public class ConsoleLoginCallbackHandler implements CallbackHandler {

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof TextInputCallback) {
                TextInputCallback tc = (TextInputCallback) callbacks[i];
                System.err.print(tc.getPrompt());
                System.err.flush();
                tc.setText((new BufferedReader(new InputStreamReader(System.in))).readLine());
            } else if (callbacks[i] instanceof NameCallback) {
                NameCallback nc = (NameCallback) callbacks[i];
                System.err.print(nc.getPrompt());
                System.err.flush();
                nc.setName((new BufferedReader(new InputStreamReader(System.in))).readLine());
            } else if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback pc = (PasswordCallback) callbacks[i];
                System.err.print(pc.getPrompt());
                System.err.flush();
                pc.setPassword((new BufferedReader(new InputStreamReader(System.in))).readLine().toCharArray());
            } else {
                throw new UnsupportedCallbackException
                        (callbacks[i], "Unrecognized Callback");
            }
        }
    }
}
