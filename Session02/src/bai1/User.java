package bai1;

import java.util.Random;

public class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username) {
        this.username = username;
        this.password = String.format("%10s", username).replace(" ", "#");
        this.role = "GUEST";
    }

    public User() {
        Random rad = new Random();
        String name = String.format("%10s", String.valueOf(rad.nextInt(99999999)).replace(" ", "#"));
        this.username = name;
        this.password = name;
        this.role = "GUEST";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
