package bai1.factory;

import bai1.device.Device;
import bai1.device.Light;

public class LightFactory extends DeviceFactory {
    public Device createDevice() {
        System.out.println("LightFactory: Đã tạo đèn mới.");
        return new Light();
    }
}