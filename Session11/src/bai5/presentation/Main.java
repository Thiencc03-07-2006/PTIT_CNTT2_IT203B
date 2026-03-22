package bai5.presentation;

import bai5.business.DoctorService;
import bai5.model.Doctor;

import java.util.List;
import java.util.Scanner;

/*
Kịch bản lỗi
1. Trùng khóa chính
Nhập ID đã tồn tại
→ SQLIntegrityConstraintViolationException
2. Dữ liệu quá dài
specialty > 50 ký tự
→ DataTruncation
3. Nhập rỗng
ID hoặc name rỗng
→ Validate ở Service
4. Sai kiểu dữ liệu
Nhập chữ vào menu (vd: "abc")
→ NumberFormatException
5. Lỗi kết nối DB
Sai password / chưa add driver
→ SQLException
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoctorService service = new DoctorService();
        int choice;
        while (true) {
            System.out.println("\n===== RIKKEI CARE =====");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Phải nhập số!");
                continue;
            }

            switch (choice) {
                case 1:
                    List<Doctor> list = service.getDoctors();
                    System.out.printf("%-10s | %-20s | %-20s\n", "ID", "NAME", "SPECIALTY");
                    list.forEach(e -> {
                        System.out.printf("%s|%s|%s\n", "-".repeat(11), "-".repeat(22), "-".repeat(21));
                        System.out.println(e.toString());
                    });
                    break;

                case 2:
                    System.out.print("Nhập ID: ");
                    String id = sc.nextLine();

                    System.out.print("Nhập tên: ");
                    String name = sc.nextLine();

                    System.out.print("Nhập chuyên khoa: ");
                    String sp = sc.nextLine();

                    Doctor d = new Doctor(id, name, sp);
                    service.addDoctor(d);
                    break;

                case 3:
                    service.statistic();
                    break;

                case 4:
                    System.out.println("Thoát...");
                    return;

                default:
                    System.out.println("Sai lựa chọn!");
            }
        }
    }
}