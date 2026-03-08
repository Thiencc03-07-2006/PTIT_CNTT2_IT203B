package exception;

public class MyCheckedException extends Exception {
    private int code;

    MyCheckedException(String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
