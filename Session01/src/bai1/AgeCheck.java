package bai1;

import java.time.Year;
import java.util.Scanner;

public class AgeCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nhập năm sinh của bạn: ");
            String input = sc.nextLine();
            int birthYear = Integer.parseInt(input);
            int currentYear = Year.now().getValue();
            int age = currentYear - birthYear;
            System.out.println("Tuổi của bạn là: " + age);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập năm sinh bằng số (ví dụ: 2000).");
        } finally {
            sc.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}
