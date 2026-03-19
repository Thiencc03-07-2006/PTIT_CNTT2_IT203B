package bai1.factory;

import bai1.device.AirConditioner;
import bai1.device.Device;

public class AirConditionerFactory extends DeviceFactory {
    public Device createDevice() {
        System.out.println("AirConditionerFactory: Đã tạo điều hòa mới.");
        return new AirConditioner();
    }
}