package bai5.command;

import bai5.receiver.AirConditioner;

public class SetACTempCommand implements Command {
    private AirConditioner ac;

    public SetACTempCommand(AirConditioner ac) {
        this.ac = ac;
    }

    public void execute() {
        System.out.println("SleepMode: Điều hòa set 28°C");
        ac.setTemperature(28);
    }
}