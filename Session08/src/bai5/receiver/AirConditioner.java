package bai5.receiver;

import bai5.observer.Observer;

public class AirConditioner implements Observer {
    private int temperature = 25;

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    @Override
    public void update(int temp) {
        if (temp > 30) {
            System.out.println("Điều hòa: vẫn giữ " + temperature + "°C");
        }
    }

    public int getTemperature() {
        return temperature;
    }
}