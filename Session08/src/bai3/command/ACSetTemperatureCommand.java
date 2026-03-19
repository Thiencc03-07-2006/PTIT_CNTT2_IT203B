package bai3.command;

import bai3.receiver.AirConditioner;

public class ACSetTemperatureCommand implements Command {
    private AirConditioner ac;
    private int newTemp;
    private int prevTemp;

    public ACSetTemperatureCommand(AirConditioner ac, int temp) {
        this.ac = ac;
        this.newTemp = temp;
    }

    public void execute() {
        prevTemp = ac.getTemperature();
        ac.setTemperature(newTemp);
    }

    public void undo() {
        ac.setTemperature(prevTemp);
    }
}