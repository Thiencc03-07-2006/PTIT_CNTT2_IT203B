package bai1.device;

public class AirConditioner implements Device {
    public void turnOn() {
        System.out.println("Điều hòa: Bật.");
    }

    public void turnOff() {
        System.out.println("Điều hòa: Tắt.");
    }
}