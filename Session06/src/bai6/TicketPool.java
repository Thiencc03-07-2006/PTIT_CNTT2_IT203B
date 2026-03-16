package bai6;

import java.util.*;

public class TicketPool {

    private final String room;
    private final List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String room, int capacity) {
        this.room = room;
        for (int i = 1; i <= capacity; i++) {
            tickets.add(new Ticket(room + "-" + String.format("%03d", i), false));
        }
    }

    public synchronized Ticket holdTicket(boolean vip) {
        for (Ticket t : tickets) {
            if (!t.isSold() && !t.isHeld()) {
                t.setHeld(true);
                t.setHoldExpiry(System.currentTimeMillis() + 5000);
                return t;
            }
        }
        return null;
    }

    public synchronized boolean sellHeldTicket(Ticket t) {
        if (t != null && t.isHeld()) {
            t.setHeld(false);
            t.setSold(true);
            return true;
        }
        return false;
    }

    public synchronized void releaseExpiredTickets() {
        long now = System.currentTimeMillis();
        for (Ticket t : tickets) {
            if (t.isHeld() && !t.isSold()) {
                if (now > t.getHoldExpiry()) {
                    t.setHeld(false);
                    System.out.println("TimeoutManager: Vé " + t.getId() + " hết hạn -> trả lại kho");
                }
            }
        }
    }

    public synchronized int soldCount() {
        int c = 0;
        for (Ticket t : tickets)
            if (t.isSold()) c++;
        return c;
    }

    public int total() {
        return tickets.size();
    }

    public String getRoom() {
        return room;
    }

    public synchronized void addTickets(int amount) {
        int start = tickets.size() + 1;
        for (int i = 0; i < amount; i++) {
            tickets.add(new Ticket(room + "-" + String.format("%03d", start + i), false));
        }
        System.out.println("Đã thêm " + amount + " vé vào phòng " + room);
    }
}