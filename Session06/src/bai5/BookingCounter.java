package bai5;

import java.util.*;

public class BookingCounter implements Runnable {

    private String counterName;
    private List<TicketPool> pools;
    private Random random = new Random();

    public BookingCounter(String counterName, List<TicketPool> pools) {
        this.counterName = counterName;
        this.pools = pools;
    }

    @Override
    public void run() {
        while (true) {
            TicketPool pool = pools.get(random.nextInt(pools.size()));
            boolean isVIP = random.nextBoolean();
            Ticket ticket = pool.holdTicket(isVIP);
            if (ticket != null) {
                System.out.println(counterName + ": Đã giữ vé " + ticket.getTicketId());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                pool.sellHeldTicket(ticket);
                System.out.println(counterName + ": Thanh toán thành công " + ticket.getTicketId());
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }
}