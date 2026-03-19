package JavaAdvanced.Ss9.Traffic.exception;

public class CollisionException extends Exception {
    public CollisionException(String message) {
        super("VA CHẠM: " + message);
    }
}