package bai6;

public class Ticket {

    private final String id;

    private boolean sold;
    private boolean held;
    private boolean vip;

    private long holdExpiry;

    public Ticket(String id, boolean vip) {
        this.id = id;
        this.vip = vip;
    }

    public String getId() {
        return id;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public boolean isHeld() {
        return held;
    }

    public void setHeld(boolean held) {
        this.held = held;
    }

    public long getHoldExpiry() {
        return holdExpiry;
    }

    public void setHoldExpiry(long holdExpiry) {
        this.holdExpiry = holdExpiry;
    }

    public boolean isVip() {
        return vip;
    }
}