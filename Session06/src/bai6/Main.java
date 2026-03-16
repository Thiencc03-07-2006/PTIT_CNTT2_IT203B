package bai6;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CinemaSystem system = new CinemaSystem();
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Bắt đầu mô phỏng");
            System.out.println("2. Tạm dừng");
            System.out.println("3. Tiếp tục");
            System.out.println("4. Thêm vé");
            System.out.println("5. Xem thống kê");
            System.out.println("6. Phát hiện deadlock");
            System.out.println("7. Thoát");
            System.out.print("Mời nhập: ");
            int c = Integer.parseInt(sc.nextLine());
            switch (c) {

                case 1:
                    System.out.print("Số phòng: ");
                    int r = Integer.parseInt(sc.nextLine());
                    System.out.print("Vé/phòng: ");
                    int t = Integer.parseInt(sc.nextLine());
                    System.out.print("Số quầy: ");
                    int q = Integer.parseInt(sc.nextLine());
                    system.start(r, t, q);
                    break;
                case 2:
                    system.pauseSimulation();
                    break;
                case 3:
                    system.resumeSimulation();
                    break;
                case 4:
                    System.out.print("Tên phòng: ");
                    String room = sc.nextLine();
                    System.out.print("Số vé thêm: ");
                    int amount = Integer.parseInt(sc.nextLine());
                    system.addTicketsToRoom(room, amount);
                    break;
                case 5:
                    StatisticsService.printStats(system.getPools());
                    break;
                case 6:
                    system.detectDeadlock();
                    break;
                case 7:
                    system.stop();
                    System.out.println("Kết thúc chương trình.");
                    return;
            }
        }
    }
}