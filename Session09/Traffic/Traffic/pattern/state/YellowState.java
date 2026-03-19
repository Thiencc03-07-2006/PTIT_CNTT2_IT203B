package JavaAdvanced.Ss9.Traffic.pattern.state;

import JavaAdvanced.Ss9.Traffic.entity.Direction;

public class YellowState implements TrafficLightState {
    private int duration = 3;
    private int elapsedSeconds = 0;

    @Override
    public String getColor() {
        return "VANG";
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void next(TrafficLightContext context) {
        elapsedSeconds++;
        if (elapsedSeconds >= duration) {
            System.out.println("Den chuyen tu VANG sang DO");
            context.setState(new RedState());
            elapsedSeconds = 0;
        }
    }

    @Override
    public boolean canPass(Direction direction) {
        return false;
    }
}