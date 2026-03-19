package bai4.app;

import bai4.observer.Fan;
import bai4.observer.Humidifier;
import bai4.observer.TemperatureSensor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TemperatureSensor sensor = new TemperatureSensor();
        Fan fan = new Fan();
        Humidifier humidifier = new Humidifier();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Đăng ký quạt");
            System.out.println("2. Đăng ký máy tạo ẩm");
            System.out.println("3. Set nhiệt độ");
            System.out.println("0. Thoát");
            System.out.println("Chọn: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sensor.attach(fan);
                    break;

                case 2:
                    sensor.attach(humidifier);
                    break;

                case 3:
                    System.out.print("Nhập nhiệt độ: ");
                    int temp = sc.nextInt();
                    sensor.setTemperature(temp);
                    break;

                case 0:
                    return;
            }
        }
    }
}