package JavaAdvanced.Ss9.Traffic.exception;

public class TrafficJamException extends Exception {
    public TrafficJamException(String message) {
        super("KẸT XE: " + message);
    }
}