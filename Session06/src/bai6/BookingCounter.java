package bai6;

import java.util.*;

public class BookingCounter implements Runnable {

    private final String name;
    private final List<TicketPool> pools;

    private static volatile boolean paused = false;

    public static void pauseAll() {
        paused = true;
    }

    public static void resumeAll() {
        paused = false;
    }

    public BookingCounter(String name, List<TicketPool> pools) {
        this.name = name;
        this.pools = pools;
    }

    @Override
    public void run() {
        System.out.println(name + " bắt đầu bán vé...");
        Random random = new Random();
        while (true) {
            if (paused) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    return;
                }
                continue;
            }
            TicketPool pool = pools.get(random.nextInt(pools.size()));
            Ticket t = pool.holdTicket(false);
            if (t != null) {
                System.out.println(name + " giữ " + t.getId());
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    return;
                }
                if (pool.sellHeldTicket(t)) {
                    System.out.println(name + " thanh toán " + t.getId());
                }
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                return;
            }
        }
    }
}