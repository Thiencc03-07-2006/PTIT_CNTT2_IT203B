package bai5.command;

import java.util.List;

public class SleepModeCommand implements Command {
    private List<Command> commands;

    public SleepModeCommand(List<Command> commands) {
        this.commands = commands;
    }

    public void execute() {
        System.out.println("\n=== KÍCH HOẠT CHẾ ĐỘ NGỦ ===");
        for (Command c : commands) {
            c.execute();
        }
    }
}