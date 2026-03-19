package bai2.app;

import bai2.adapter.OldThermometer;
import bai2.adapter.TemperatureSensor;
import bai2.adapter.ThermometerAdapter;
import bai2.facade.SmartHomeFacade;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OldThermometer old = new OldThermometer();
        TemperatureSensor sensor = new ThermometerAdapter(old);

        SmartHomeFacade facade = new SmartHomeFacade(sensor);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Xem nhiệt độ");
            System.out.println("2. Rời nhà");
            System.out.println("3. Chế độ ngủ");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    facade.getCurrentTemperature();
                    break;
                case 2:
                    facade.leaveHome();
                    break;
                case 3:
                    facade.sleepMode();
                    break;
                case 0:
                    System.out.println("Thoát.");
                    return;
                default:
                    System.out.println("Sai lựa chọn.");
            }
        }
    }
}