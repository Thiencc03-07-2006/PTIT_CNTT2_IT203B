package JavaAdvanced.Ss9.Traffic.entity;

public abstract class Vehicle implements Runnable {
    protected String id;
    protected String type;
    protected int speed;
    protected int priority;
    protected volatile boolean isRunning = true;
    protected volatile boolean isWaiting = false;
    protected Position currentPosition;
    protected Direction direction;

    public Vehicle(String id, String type, int speed, int priority, Direction direction) {
        this.id = id;
        this.type = type;
        this.speed = speed;
        this.priority = priority;
        this.direction = direction;
        this.currentPosition = new Position(0, 0);
    }

    public abstract void move();
    public abstract void stop();
    public abstract void emergencyOverride();

    public String getId() { return id; }
    public String getType() { return type; }
    public int getPriority() { return priority; }
    public Direction getDirection() { return direction; }
    public Position getCurrentPosition() { return currentPosition; }

    @Override
    public void run() {
        while (isRunning) {
            try {
                move();
                Thread.sleep(1000 / speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public abstract void resume();
    public void stopVehicle() {
        this.isRunning = false;
    }
}