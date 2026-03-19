package JavaAdvanced.Ss9.Traffic.pattern.state;

import JavaAdvanced.Ss9.Traffic.entity.Direction;

public interface TrafficLightState {
    String getColor();
    int getDuration();
    void next(TrafficLightContext context);
    boolean canPass(Direction direction);
}