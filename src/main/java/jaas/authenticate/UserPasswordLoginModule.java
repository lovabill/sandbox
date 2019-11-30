package jaas.authenticate;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

/**
 * This sample LoginModule authenticates users with a password.
 * If user successfully authenticates itself, a <code>PersonPrincipal</code> with the user name
 * is added to the Subject.
 * This LoginModule recognizes the debug option. If set to true in the login Configuration,
 * debug messages will be output to the output stream, System.out.
 */
public class UserPasswordLoginModule implements LoginModule {

    // Initial state
    private Subject authenticatedUser;
    private CallbackHandler userInputCallbackHandler;
    private Map sharedState;
    private Map configOptions;

    // Configurable option
    private boolean debug = false;

    // Authentication input
    private String username;
    private String password;
    private String otp;

    // Authentication output
    private boolean succeeded = false;
    private boolean commitSucceeded = false;
    private UserPrincipal authenticatedUserPrincipal;

    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<java.lang.String, ?> sharedState,
                           Map<java.lang.String, ?> options) {
        this.authenticatedUser = subject;
        this.userInputCallbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.configOptions = options;
        debug = "true".equalsIgnoreCase((String) options.get("debug")); // Get value from configuration file
    }

    /**
     * Authenticate the user by prompting for a user name and password.
     *
     * @throws FailedLoginException if the authentication fails. <p>
     * @throws LoginException       if this LoginModule is unable to perform the authentication.
     */
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[3];
        callbacks[0] = new NameCallback("user name: ");
        callbacks[1] = new PasswordCallback("password: ", false);
        callbacks[2] = new TextInputCallback("otp: ");

        try {
            userInputCallbackHandler.handle(callbacks);
        } catch (IOException | UnsupportedCallbackException e) {
            e.printStackTrace();
            throw new LoginException(e.toString());
        }

        username = ((NameCallback) callbacks[0]).getName();
        password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());
        ((PasswordCallback) callbacks[1]).clearPassword();
        otp = ((TextInputCallback) callbacks[2]).getText();

        if (debug) {
            System.out.println("Username:" + username + " Password: " + password + " OTP: " + otp);
        }

        // verify the username/password
        boolean usernameCorrect = username.equals("penny");
        boolean passwordCorrect = usernameCorrect && password.equals("123");
        succeeded = usernameCorrect && passwordCorrect;

        if (debug && succeeded) {
            System.out.println("\t\t[" + this.getClass().getName() + "] authentication succeeded");
        }
        if (debug && !succeeded) {
            System.out.println("\t\t[" + this.getClass().getName() + "] authentication failed");
        }

        if (!succeeded) {
            succeeded = false;
            username = null;
            password = null;
            if (!usernameCorrect) {
                throw new FailedLoginException("User Name Incorrect");
            }
            if (!passwordCorrect) {
                throw new FailedLoginException("Password Incorrect");
            }
        }

        //True->commit(), False->abort()
        return true;
    }

    /**
     * A post action after the authentication finishes with return value 'true'.
     * This method adds information to the logged-in user if authenticated.
     * Clears the private state as well.
     */
    public boolean commit() throws LoginException {
        if (succeeded == false) {
            return false;
        } else {
            // add a authenticated identity (Principal) to the logged in user (Subject)
            authenticatedUserPrincipal = new UserPrincipal(username, "Penny"); //You can add any info on the subject.
            if (!authenticatedUser.getPrincipals().contains(authenticatedUserPrincipal)) {
                authenticatedUser.getPrincipals().add(authenticatedUserPrincipal);
            }

            if (debug) {
                System.out.println("\t\tAdded information on logged in user");
            }

            username = null;
            password = null;
            commitSucceeded = true;
            return true;
        }
    }

    /**
     * A post action after the authentication finishes with return value 'false'.
     * Handles errors and cleans state.
     */
    public boolean abort() throws LoginException {
        if (succeeded == false) {
            return false;
        } else if (succeeded == true && commitSucceeded == false) {
            // login succeeded but overall authentication failed
            succeeded = false;
            username = null;
            password = null;
            authenticatedUserPrincipal = null;
        } else {
            // overall authentication succeeded and commit succeeded, but someone else's commit failed
            logout();
        }
        return true;
    }

    /**
     * Logout the user. Reset state;
     */
    public boolean logout() throws LoginException {
        authenticatedUser = null;
        authenticatedUserPrincipal = null;
        succeeded = false;
        commitSucceeded = false;
        username = null;
        password = null;
        return true;
    }
}
