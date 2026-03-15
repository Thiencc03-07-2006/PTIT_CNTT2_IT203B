package bai4;

import bai1.Ticket;
import bai2.TicketPool;

import java.util.Random;

public class BookingCounter implements Runnable {

    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount = 0;

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public TicketPool getRoomA() {
        return roomA;
    }

    public void setRoomA(TicketPool roomA) {
        this.roomA = roomA;
    }

    public TicketPool getRoomB() {
        return roomB;
    }

    public void setRoomB(TicketPool roomB) {
        this.roomB = roomB;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public void sellCombo() {
        synchronized (roomA) {
            synchronized (roomB) {
                Ticket ticketA = roomA.sellTicket();
                Ticket ticketB = roomB.sellTicket();
                if (ticketA != null && ticketB != null) {
                    soldCount++;
                    System.out.println(counterName + " bán combo thành công: " + ticketA.getTicketId() + " & " + ticketB.getTicketId());
                } else {
                    if (ticketA != null) {
                        ticketA.setSold(false);
                    }
                    if (ticketB != null) {
                        ticketB.setSold(false);
                    }
                    System.out.println(counterName +
                            " bán combo thất bại");
                }
            }
        }
    }

    @Override
    public void run() {

        while (roomA.remainingTickets() > 0 && roomB.remainingTickets() > 0) {
            sellCombo();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(counterName + " dừng vì hết vé.");
    }
}