import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    String userId;
    String name;
    String role;

    public User(String userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
