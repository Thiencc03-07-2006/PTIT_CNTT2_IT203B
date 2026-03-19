package bai3.add;

import bai3.command.*;
import bai3.invoker.RemoteControl;
import bai3.receiver.AirConditioner;
import bai3.receiver.Fan;
import bai3.receiver.Light;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        RemoteControl remote = new RemoteControl();

        while (true) {
            System.out.println("\n===== REMOTE CONTROL =====");
            System.out.println("1. Gán nút");
            System.out.println("2. Nhấn nút");
            System.out.println("3. Undo");
            System.out.println("0. Thoát");
            System.out.println("Chọn: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Chọn nút: ");
                    int slot = sc.nextInt();

                    System.out.println("Chọn chức năng:");
                    System.out.println("1. Light ON");
                    System.out.println("2. Light OFF");
                    System.out.println("3. Fan ON");
                    System.out.println("4. Fan OFF");
                    System.out.println("5. AC Set Temp");
                    System.out.println("Chọn: ");

                    int cmd = sc.nextInt();

                    Command command = null;

                    switch (cmd) {
                        case 1:
                            command = new LightOnCommand(light);
                            break;
                        case 2:
                            command = new LightOffCommand(light);
                            break;
                        case 3:
                            command = new FanOnCommand(fan);
                            break;
                        case 4:
                            command = new FanOffCommand(fan);
                            break;
                        case 5:
                            System.out.print("Nhập nhiệt độ: ");
                            int temp = sc.nextInt();
                            command = new ACSetTemperatureCommand(ac, temp);
                            break;
                    }

                    remote.setCommand(slot, command);
                    break;

                case 2:
                    System.out.print("Nhấn nút: ");
                    int press = sc.nextInt();
                    remote.pressButton(press);
                    break;

                case 3:
                    System.out.println("Undo...");
                    remote.undo();
                    break;

                case 0:
                    return;
            }
        }
    }
}