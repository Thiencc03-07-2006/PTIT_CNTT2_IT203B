package exception;

import java.util.Scanner;

public class Test {
    //tao method nhap va tra ve kieu nguyen thuy
    // xu ly loi ngoai le
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        inputChar(sc);
        sc.close();
    }

    public static int inputInt(Scanner sc) {
        while (true) {
            try {
                System.out.print("Moi nhap vao int: ");
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mMoi nhap so int\u001B[0m");
            }
        }
    }

    public static byte inputByte(Scanner sc) {
        while (true) {
            try {
                System.out.print("Moi nhap vao byte: ");
                return Byte.parseByte(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mMoi nhap so byte\u001B[0m");
            }
        }
    }

    public static short inputShort(Scanner sc) {
        while (true) {
            try {
                System.out.print("Moi nhap vao short: ");
                return Short.parseShort(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mMoi nhap so short\u001B[0m");
            }
        }
    }


    public static long inputLong(Scanner sc) {
        while (true) {
            try {
                System.out.print("Moi nhap vao long: ");
                return Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mMoi nhap so long\u001B[0m");
            }
        }
    }

    public static double inputDouble(Scanner sc) {
        while (true) {
            try {
                System.out.print("Moi nhap vao double: ");
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mMoi nhap so double\u001B[0m");
            }
        }
    }

    public static float inputFloat(Scanner sc) {
        while (true) {
            try {
                System.out.print("Moi nhap vao float: ");
                return Float.parseFloat(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mMoi nhap so float\u001B[0m");
            }
        }
    }

    public static char inputChar(Scanner sc) {
        while (true) {
            try {
                System.out.print("Moi nhap vao char: ");
                String str = sc.nextLine().trim();
                if (str.isEmpty()) {
                    System.out.println("\u001B[31mKhong duoc de trong\u001B[0m");
                    continue;
                }
                return str.charAt(0);
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mMoi nhap so float\u001B[0m");
            }
        }
    }

    public static String inputString(Scanner sc) {
        while (true) {
            try {
                System.out.print("Moi nhap vao string: ");
                String str = sc.nextLine().trim();
                if (str.isEmpty()) {
                    System.out.println("\u001B[31mKhong duoc de trong\u001B[0m");
                    continue;
                }
                return str;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mMoi nhap so float\u001B[0m");
            }
        }
    }
    public static boolean inputBoolean(Scanner sc) {
        while (true) {
            System.out.print("Moi nhap vao boolean: ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }

            System.out.println("Chỉ nhập true hoặc false!");
        }
    }
}
