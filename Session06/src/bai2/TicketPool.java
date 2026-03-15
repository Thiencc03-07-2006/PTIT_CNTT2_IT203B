package bai2;

import bai1.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets;
    private int nextId = 1;

    public TicketPool(String roomName, int totalTicket) {
        this.roomName = roomName;
        tickets = new ArrayList<>();
        for (int i = 0; i < totalTicket; i++) {
            String id = roomName + "-" + String.format("%03d", i);
            tickets.add(new Ticket(id, roomName));
        }
    }

    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            String id = roomName + "-" + String.format("%03d", nextId++);
            tickets.add(new Ticket(id, roomName));
        }
        System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
    }

    public synchronized Ticket sellTicket() {
        for (Ticket t : tickets) {
            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }
        return null;
    }

    public int remainingTickets() {
        int count = 0;
        for (Ticket t : tickets) {
            if (!t.isSold()) count++;
        }
        return count;
    }
}
