package bai2;

import java.util.Scanner;

public class GroupDivision {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tổng số người dùng: ");
        int totalUsers = sc.nextInt();
        System.out.print("Nhập số nhóm muốn chia: ");
        int groups = sc.nextInt();
        try {
            int peoplePerGroup = totalUsers / groups;
            System.out.println("Mỗi nhóm có: " + peoplePerGroup + " người.");
        } catch (ArithmeticException e) {
            System.out.println("Không thể chia cho 0!");
        }
        sc.close();
    }
}