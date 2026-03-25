package bai5.view;

import bai5.controller.BenhNhanController;

import java.util.Scanner;

public class LeTanView {

    private BenhNhanController controller = new BenhNhanController();
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Xem giường trống");
            System.out.println("2. Tiếp nhận bệnh nhân");
            System.out.println("0. Thoát");

            System.out.print("Chọn: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    xemGiuong();
                    break;
                case "2":
                    tiepNhan();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Sai lựa chọn!");
            }
        }
    }

    private void xemGiuong() {
        System.out.println("(Demo) Giường A1, A2 đang trống...");
    }

    private void tiepNhan() {
        try {
            System.out.print("Tên: ");
            String ten = sc.nextLine();

            System.out.print("Tuổi: ");
            int tuoi = Integer.parseInt(sc.nextLine());

            System.out.print("Mã giường: ");
            String maGiuong = sc.nextLine();

            System.out.print("Tiền: ");
            double tien = Double.parseDouble(sc.nextLine());

            controller.tiepNhan(ten, tuoi, maGiuong, tien);

        } catch (NumberFormatException e) {
            System.out.println("Dữ liệu nhập sai định dạng!");
        }
    }
}