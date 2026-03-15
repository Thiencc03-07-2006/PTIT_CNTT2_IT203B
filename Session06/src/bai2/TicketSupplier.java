package bai2;

public class TicketSupplier implements Runnable {

    private TicketPool roomA;
    private TicketPool roomB;
    private int supplyCount;
    private int interval;
    private int rounds;

    public TicketSupplier(TicketPool roomA, TicketPool roomB, int supplyCount, int interval, int rounds) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.supplyCount = supplyCount;
        this.interval = interval;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        for (int i = 1; i <= rounds; i++) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            roomA.addTickets(supplyCount);
            roomB.addTickets(supplyCount);
            System.out.println("Nhà cung cấp đã hoàn thành lần cung cấp " + i);
        }
        System.out.println("Nhà cung cấp kết thúc.");
    }
}