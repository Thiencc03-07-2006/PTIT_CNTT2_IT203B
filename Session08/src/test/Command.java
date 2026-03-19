package test;

public class Command {
    public static void main(String[] args) {
        Light light=new Light();
        LightCommand on=new TurnOnLight(light);
        LightCommand off=new TurnOffLight(light);
        RemotoLight remotoLight=new RemotoLight();
        remotoLight.setLightCommand(off);
        remotoLight.pressPowerButton();
        remotoLight.undoPowerButton();
    }
}

class Light {
    public void turnOn() {
        System.out.println("Bat den");
    }

    public void turnOff() {
        System.out.println("Tat den");
    }
}

interface LightCommand {
    void execute();

    void undo();
}

class TurnOnLight implements LightCommand {
    private Light light;

    public TurnOnLight(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

class TurnOffLight implements LightCommand {
    private Light light;

    public TurnOffLight(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

class RemotoLight {
    private LightCommand lightCommand;

    public void setLightCommand(LightCommand lightCommand) {
        this.lightCommand = lightCommand;
    }

    void pressPowerButton() {
        lightCommand.execute();
    }

    void undoPowerButton() {
        lightCommand.undo();
    }
}