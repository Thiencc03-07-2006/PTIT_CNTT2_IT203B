package bai3;

import bai1.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    private String roomName;
    private List<Ticket> tickets;
    private int nextId = 1;

    public TicketPool(String roomName, int initialTickets) {
        this.roomName = roomName;
        tickets = new ArrayList<>();
        addTickets(initialTickets);
    }

    public synchronized Ticket sellTicket() {
        while (true) {
            for (Ticket t : tickets) {
                if (!t.isSold()) {
                    t.setSold(true);
                    return t;
                }
            }
            try {
                System.out.println("Hết vé phòng " + roomName + ", đang chờ...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            String id = roomName + "-" + String.format("%03d", nextId++);
            tickets.add(new Ticket(id, roomName));
        }
        System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
        notifyAll();
    }
}