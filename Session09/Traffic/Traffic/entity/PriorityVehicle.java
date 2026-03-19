package JavaAdvanced.Ss9.Traffic.entity;

public class PriorityVehicle extends Vehicle {
    private boolean emergencyMode = true;

    public PriorityVehicle(String id, String type, int speed, Direction direction) {
        super(id, type, speed, 10, direction);
    }

    @Override
    public void resume() {
        // Xe ưu tiên không cần resume vì luôn chạy
    }

    @Override
    public void move() {
        System.out.println(String.format("[Time: %ds] XE CỨU THƯƠNG #%s đang ưu tiên di chuyển trên đường %s",
                (System.currentTimeMillis() / 1000) % 100, id, direction));
        currentPosition.setX(currentPosition.getX() + 2);
    }

    @Override
    public void stop() {
        if (!emergencyMode) {
            System.out.println(String.format("Xe cứu thương #%s dừng lại", id));
        }
    }

    @Override
    public void emergencyOverride() {
        System.out.println(String.format("XE CỨU THƯƠNG #%s đang vượt đèn đỏ!", id));
        move();
    }
}