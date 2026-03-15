package bai2;

public class Main {
    //Bài tập Khá: NHÀ CUNG CẤP VÉ ĐỊNH KỲ
    public static void main(String[] args) throws InterruptedException {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);
        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA, roomB);
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomA, roomB);
        TicketSupplier supplier = new TicketSupplier(roomA, roomB, 3, 3000, 3);
        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);
        Thread t3 = new Thread(supplier);
        t1.start();
        t2.start();
        t3.start();
        t3.join();
        Thread.sleep(5000);
        System.out.println("\nKết thúc chương trình");
        System.exit(0);
    }
}