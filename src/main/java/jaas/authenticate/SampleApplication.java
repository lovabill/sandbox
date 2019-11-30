package jaas.authenticate;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * JavaSE application using JAAS to authenticate. Find credentials in {@link UserPasswordLoginModule}.login() method.
 */
public class SampleApplication {

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("src/main/resources/LoginConfigProfiles.config");
        System.setProperty("java.security.auth.login.config", path.toAbsolutePath().toString());

        try {
            LoginContext lc = new LoginContext("UserPasswordWithDebug", new ConsoleLoginCallbackHandler());
            lc.login();
            UserPrincipal loggedInUser = (UserPrincipal) lc.getSubject().getPrincipals().stream().findFirst().get();
            System.out.println("Authentication succeeded! Hello " + loggedInUser.getDisplayName());
            execute();
        } catch (LoginException e) {
            System.err.println("Authentication failed:" + e.getMessage());
            System.exit(-1);
        }
    }

    private static void execute() {
        System.out.println("Executing application now...Done!");
    }
}

