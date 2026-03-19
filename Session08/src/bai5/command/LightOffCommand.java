package bai5.command;

import bai5.receiver.Light;

public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        System.out.println("SleepMode: Tắt đèn");
        light.off();
    }
}