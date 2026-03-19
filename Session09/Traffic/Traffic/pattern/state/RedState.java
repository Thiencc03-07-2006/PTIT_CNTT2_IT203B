package JavaAdvanced.Ss9.Traffic.pattern.state;

import JavaAdvanced.Ss9.Traffic.entity.Direction;

public class RedState implements TrafficLightState {
    private int duration = 10;
    private int elapsedSeconds = 0;

    @Override
    public String getColor() {
        return "DO";
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void next(TrafficLightContext context) {
        elapsedSeconds++;
        if (elapsedSeconds >= duration) {
            System.out.println("Den chuyen tu DO sang XANH");
            context.setState(new GreenState());
            elapsedSeconds = 0;
        }
    }

    @Override
    public boolean canPass(Direction direction) {
        return false;
    }
}