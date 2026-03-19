package JavaAdvanced.Ss9.Traffic.pattern.observer;

public interface TrafficLightObserver {
    void update(String color, boolean canPass);
}