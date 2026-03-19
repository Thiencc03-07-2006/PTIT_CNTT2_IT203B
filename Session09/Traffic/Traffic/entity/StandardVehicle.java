package JavaAdvanced.Ss9.Traffic.entity;

public class StandardVehicle extends Vehicle {
    private boolean hasStopped = false;

    public StandardVehicle(String id, String type, int speed, Direction direction) {
        super(id, type, speed, 1, direction);
    }

    @Override
    public void run() {
        while (isRunning) {
            if (!hasStopped) {
                move();
            }
            try {
                Thread.sleep(1000 / Math.max(1, speed));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    public void move() {
        System.out.println(String.format("%s #%s dang di chuyen tren duong %s",
                type, id, direction.getVietnamese()));
        currentPosition.setX(currentPosition.getX() + 1);
    }

    @Override
    public void stop() {
        this.hasStopped = true;
        System.out.println(String.format("%s #%s dung lai vi den do", type, id));
    }

    @Override
    public void resume() {
        this.hasStopped = false;
        System.out.println(String.format("%s #%s tiep tuc di chuyen", type, id));
    }

    @Override
    public void emergencyOverride() {

    }
}