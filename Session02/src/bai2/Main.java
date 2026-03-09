package bai2;

public class Main {

    public static void main(String[] args) {
        PasswordValidator isValidOverrive = password -> password.length() >= 8;
        System.out.println(isValidOverrive.isValid("12345678"));
        System.out.println(isValidOverrive.isValid("1234"));
    }
}
