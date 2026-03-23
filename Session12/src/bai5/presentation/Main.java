package bai5.presentation;

import bai5.dao.PatientDAO;
import bai5.model.Patient;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PatientDAO dao = new PatientDAO();

        while (true) {
            System.out.println("\n===== RHMS =====");
            System.out.println("1. Danh sách bệnh nhân");
            System.out.println("2. Thêm bệnh nhân");
            System.out.println("3. Cập nhật bệnh án");
            System.out.println("4. Xuất viện & tính phí");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    dao.getAllPatients();
                    break;

                case 2:
                    System.out.print("Tên: ");
                    String name = sc.nextLine();
                    System.out.print("Tuổi: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("Khoa: ");
                    String dept = sc.nextLine();
                    System.out.print("Bệnh: ");
                    String dis = sc.nextLine();
                    dao.addPatient(new Patient(0, name, age, dept, dis));
                    break;

                case 3:
                    System.out.print("ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Bệnh mới: ");
                    String newDis = sc.nextLine();
                    dao.updateDisease(id, newDis);
                    break;

                case 4:
                    System.out.print("ID: ");
                    int pid = Integer.parseInt(sc.nextLine());
                    dao.dischargePatient(pid);
                    break;

                case 5:
                    return;
            }
        }
    }
}