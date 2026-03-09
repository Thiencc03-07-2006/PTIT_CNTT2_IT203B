package bai3;

public interface Authenticatable {
    String getpassword();

    default boolean isAuthenticated() {
        return getpassword() != null && getpassword().isEmpty();
    }

    static String encrypt(String rawPassword) {
        return "ENCRYPT_" + rawPassword;
    }
}
