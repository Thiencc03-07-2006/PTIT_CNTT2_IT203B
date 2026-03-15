package bai5;

public class Ticket {

    private String ticketId;
    private boolean isHeld;
    private boolean isSold;
    private boolean isVIP;
    private long holdExpiryTime;

    public Ticket(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public boolean isHeld() {
        return isHeld;
    }

    public void setHeld(boolean held) {
        isHeld = held;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public long getHoldExpiryTime() {
        return holdExpiryTime;
    }

    public void setHoldExpiryTime(long holdExpiryTime) {
        this.holdExpiryTime = holdExpiryTime;
    }
}