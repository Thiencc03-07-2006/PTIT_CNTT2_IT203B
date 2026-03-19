package bai3.command;

import bai3.receiver.Fan;

public class FanOnCommand implements Command {
    private Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    public void execute() {
        fan.on();
    }

    public void undo() {
        fan.off();
    }
}