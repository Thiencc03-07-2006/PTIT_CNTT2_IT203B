package bai6;

import java.util.*;

public class TimeoutManager implements Runnable {

    private final List<TicketPool> pools;

    public TimeoutManager(List<TicketPool> pools) {
        this.pools = pools;
    }

    @Override
    public void run() {
        while (true) {
            for (TicketPool p : pools)
                p.releaseExpiredTickets();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                return;
            }
        }
    }
}