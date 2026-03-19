package bai5.receiver;

import bai5.observer.Observer;

public class Fan implements Observer {
    private String speed = "OFF";

    public void setLow() {
        speed = "LOW";
        System.out.println("Quạt: Chạy tốc độ thấp");
    }

    public void setHigh() {
        speed = "HIGH";
        System.out.println("Quạt: Chạy tốc độ mạnh");
    }

    @Override
    public void update(int temp) {
        if (temp > 30) {
            setHigh();
        }
    }

    public String getStatus() {
        return speed;
    }
}