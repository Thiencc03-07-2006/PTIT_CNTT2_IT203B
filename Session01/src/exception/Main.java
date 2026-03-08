package exception;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyCheckedException ex = new MyCheckedException("MyCheckedException");
        MyUncheckedException uex = new MyUncheckedException("MyUncheckedException");
        Scanner sc = new Scanner(System.in);
        inputInteger(sc);
        sc.close();
    }

    public static int inputInteger(Scanner sc){
        while (true) {
            try {
                System.out.println("Nhap:");
                int a = Integer.parseInt(sc.nextLine());
                return a;
            } catch (NumberFormatException ex) {
                System.err.println("loi");
            } finally {
                System.out.println("done");
            }
        }
    }
}