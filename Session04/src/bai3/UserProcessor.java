package bai3;

public class UserProcessor {
    String processEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email phải chứa @.");
        }
        String[] parts = email.split("@");
        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Email phải chứa tên miền.");
        }
        return email.toLowerCase();
    }
}
