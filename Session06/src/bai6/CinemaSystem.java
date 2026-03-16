package bai6;

import java.lang.management.*;
import java.util.*;
import java.util.concurrent.*;

public class CinemaSystem {

    private ExecutorService executor;
    private final List<TicketPool> pools = new ArrayList<>();

    public void start(int rooms, int capacity, int counters) {
        executor = Executors.newCachedThreadPool();
        for (int i = 0; i < rooms; i++) {
            char name = (char) ('A' + i);
            pools.add(new TicketPool(String.valueOf(name), capacity));
        }
        for (int i = 1; i <= counters; i++) {
            executor.submit(new BookingCounter("Quầy " + i, pools));
        }
        executor.submit(new TimeoutManager(pools));
        executor.submit(new DeadlockDetector());
        System.out.println("Hệ thống đã khởi tạo.");
    }

    public void stop() {
        if (executor != null) executor.shutdownNow();
    }

    public List<TicketPool> getPools() {
        return pools;
    }

    public void pauseSimulation() {
        BookingCounter.pauseAll();
        System.out.println("Đã tạm dừng tất cả quầy bán vé.");
    }

    public void resumeSimulation() {
        BookingCounter.resumeAll();
        System.out.println("Đã tiếp tục hoạt động.");
    }

    public void addTicketsToRoom(String room, int amount) {
        for (TicketPool p : pools) {
            if (p.getRoom().equals(room)) {
                p.addTickets(amount);
                return;
            }
        }
        System.out.println("Không tìm thấy phòng.");
    }

    public void detectDeadlock() {
        System.out.println("Đang quét deadlock...");
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] ids = bean.findDeadlockedThreads();
        if (ids == null) {
            System.out.println("Không phát hiện deadlock.");
        } else {
            System.out.println("Phát hiện deadlock!");
            ThreadInfo[] infos = bean.getThreadInfo(ids);
            for (ThreadInfo info : infos) {
                System.out.println("Thread: " + info.getThreadName());
            }
        }
    }
}