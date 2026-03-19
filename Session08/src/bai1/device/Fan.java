package bai1.device;

public class Fan implements Device {
    public void turnOn() {
        System.out.println("Quạt: Quay.");
    }

    public void turnOff() {
        System.out.println("Quạt: Dừng.");
    }
}