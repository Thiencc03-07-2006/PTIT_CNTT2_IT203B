package bai4;

import bai2.TicketPool;

public class Main {
    //BÁN VÉ COMBO - TRÁNH DEADLOCK
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);
        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomA, roomB);
        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);
        t1.start();
        t2.start();
    }
}