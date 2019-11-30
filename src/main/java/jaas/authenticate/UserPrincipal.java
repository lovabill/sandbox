package jaas.authenticate;

import java.io.Serializable;
import java.security.Principal;

public class UserPrincipal implements Principal, Serializable {

    private String username;
    private String displayName;

    public UserPrincipal(String username, String displayName) {
        if (username == null) {
            throw new NullPointerException("illegal null input");
        }
        this.username = username;
        this.displayName = displayName;
    }

    public String getName() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String toString() {
        return (username);
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof UserPrincipal)) return false;

        UserPrincipal that = (UserPrincipal) o;
        if (this.getName().equals(that.getName())) return true;
        return false;
    }

    public int hashCode() {
        return username.hashCode();
    }
}
