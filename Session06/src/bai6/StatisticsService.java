package bai6;

import java.util.*;

public class StatisticsService {

    public static void printStats(List<TicketPool> pools) {
        System.out.println("\n=== THỐNG KÊ ===");
        int totalSold = 0;
        for (TicketPool p : pools) {
            System.out.println("Phòng " + p.getRoom() + ": " + p.soldCount() + "/" + p.total());
            totalSold += p.soldCount();
        }
        System.out.println("Tổng vé bán: " + totalSold);
    }
}