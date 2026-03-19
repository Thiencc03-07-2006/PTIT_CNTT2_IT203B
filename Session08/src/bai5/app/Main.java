package bai5.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bai5.command.*;
import bai5.observer.*;
import bai5.receiver.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Devices
        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        // Sensor
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.attach(fan);
        sensor.attach(ac);

        // Sleep Command
        List<Command> cmds = new ArrayList<>();
        cmds.add(new LightOffCommand(light));
        cmds.add(new SetACTempCommand(ac));
        cmds.add(new FanLowCommand(fan));

        Command sleepMode = new SleepModeCommand(cmds);

        while (true) {
            System.out.println("\n1. Kích hoạt Sleep Mode");
            System.out.println("2. Set nhiệt độ");
            System.out.println("3. Xem trạng thái");
            System.out.println("0. Thoát");
            System.out.println("Chọn: ");

            int c = sc.nextInt();

            switch (c) {
                case 1:
                    sleepMode.execute();
                    break;

                case 2:
                    System.out.print("Nhập nhiệt độ: ");
                    sensor.setTemperature(sc.nextInt());
                    break;

                case 3:
                    System.out.println("Quạt: " + fan.getStatus());
                    System.out.println("Điều hòa: " + ac.getTemperature());
                    break;

                case 0:
                    return;
            }
        }
    }
}