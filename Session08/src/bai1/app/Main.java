package bai1.app;

import bai1.connection.HardwareConnection;
import bai1.device.Device;
import bai1.factory.AirConditionerFactory;
import bai1.factory.DeviceFactory;
import bai1.factory.FanFactory;
import bai1.factory.LightFactory;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Device> devices = new ArrayList<>();

        HardwareConnection connection = null;
        boolean isConnected = false;

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Kết nối phần cứng");
            System.out.println("2. Tạo thiết bị");
            System.out.println("3. Bật thiết bị");
            System.out.println("4. Tắt thiết bị");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    if (!isConnected) {
                        connection = HardwareConnection.getInstance();
                        connection.connect();
                        isConnected = true;
                    } else {
                        HardwareConnection.getInstance(); // vẫn singleton
                    }
                    break;

                case 2:
                    System.out.println("1. Đèn");
                    System.out.println("2. Quạt");
                    System.out.println("3. Điều hòa");
                    System.out.print("Chọn loại: ");
                    int type = sc.nextInt();

                    DeviceFactory factory = null;

                    if (type == 1) factory = new LightFactory();
                    else if (type == 2) factory = new FanFactory();
                    else if (type == 3) factory = new AirConditionerFactory();

                    if (factory != null) {
                        Device d = factory.createDevice();
                        devices.add(d);
                    }
                    break;

                case 3:
                    if (devices.isEmpty()) {
                        System.out.println("Chưa có thiết bị.");
                        break;
                    }
                    for (int i = 0; i < devices.size(); i++) {
                        System.out.println((i + 1) + ". Device " + (i + 1));
                    }
                    System.out.print("Chọn: ");
                    int onIndex = sc.nextInt() - 1;
                    devices.get(onIndex).turnOn();
                    break;

                case 4:
                    if (devices.isEmpty()) {
                        System.out.println("Chưa có thiết bị.");
                        break;
                    }
                    for (int i = 0; i < devices.size(); i++) {
                        System.out.println((i + 1) + ". Device " + (i + 1));
                    }
                    System.out.print("Chọn: ");
                    int offIndex = sc.nextInt() - 1;
                    devices.get(offIndex).turnOff();
                    break;

                case 0:
                    System.out.println("Thoát chương trình.");
                    return;

                default:
                    System.out.println("Sai lựa chọn.");
            }
        }
    }
}