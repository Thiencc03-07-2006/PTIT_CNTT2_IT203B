package JavaAdvanced.Ss9.Traffic.pattern.state;

import JavaAdvanced.Ss9.Traffic.entity.Direction;
import JavaAdvanced.Ss9.Traffic.pattern.observer.TrafficLightObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TrafficLightContext {
    private TrafficLightState currentState;
    private List<TrafficLightObserver> observers = new ArrayList<>();
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean isRunning = true;

    public TrafficLightContext() {
        this.currentState = new RedState();
        startLightCycle();
    }

    public void setState(TrafficLightState state) {
        this.currentState = state;
        notifyObservers();
        System.out.println("DEN " + state.getColor());
    }

    public String getCurrentColor() {
        return currentState.getColor();
    }

    public boolean canPass(Direction direction) {
        return currentState.canPass(direction);
    }

    private void notifyObservers() {
        for (TrafficLightObserver observer : observers) {
            observer.update(currentState.getColor(), currentState.canPass(null));
        }
    }

    private void startLightCycle() {
        scheduler.scheduleAtFixedRate(() -> {
            if (isRunning) {
                currentState.next(TrafficLightContext.this);
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        isRunning = false;
        scheduler.shutdown();
    }
}