import bai1.UserValidator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println(sum(1, 2));
        System.out.println(new UserValidator().isValidUsername("abc"));
    }

    public static int sum(int a, int b) {
        return a + b;
    }
}