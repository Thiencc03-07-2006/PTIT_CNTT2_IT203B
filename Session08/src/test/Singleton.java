package test;

public class Singleton {
    public static void main(String[] args) {
        StudenMenu.getInstance();
        StudenMenu.getInstance();
    }
}

class StudenMenu {
    private static StudenMenu instance;

    private StudenMenu() {
    }

    public static StudenMenu getInstance() {
        if (instance == null) {
            System.out.println("khoi tao");
            instance = new StudenMenu();
        } else {
            System.out.println("da khoi tao");
        }
        return instance;
    }
}