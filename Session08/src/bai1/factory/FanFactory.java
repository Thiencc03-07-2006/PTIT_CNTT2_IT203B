package bai1.factory;

import bai1.device.Device;
import bai1.device.Fan;

public class FanFactory extends DeviceFactory {
    public Device createDevice() {
        System.out.println("FanFactory: Đã tạo quạt mới.");
        return new Fan();
    }
}