package JavaAdvanced.Ss9.Traffic.pattern.state;

import JavaAdvanced.Ss9.Traffic.entity.Direction;

public class GreenState implements TrafficLightState {
    private int duration = 15;
    private int elapsedSeconds = 0;

    @Override
    public String getColor() {
        return "XANH";
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void next(TrafficLightContext context) {
        elapsedSeconds++;
        if (elapsedSeconds >= duration) {
            System.out.println("Den chuyen tu XANH sang VANG");
            context.setState(new YellowState());
            elapsedSeconds = 0;
        }
    }

    @Override
    public boolean canPass(Direction direction) {
        return true;
    }
}