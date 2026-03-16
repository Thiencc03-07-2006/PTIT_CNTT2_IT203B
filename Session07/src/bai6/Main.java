package bai6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chọn kênh bán:");
        System.out.println("1. Website");
        System.out.println("2. Mobile App");
        System.out.println("3. POS");
        System.out.print("Mời nhập: ");
        int choice = sc.nextInt();
        SalesChannelFactory factory = null;
        switch (choice) {
            case 1:
                factory = new WebsiteFactory();
                System.out.println("Bạn đã chọn kênh Website");
                break;
            case 2:
                factory = new MobileAppFactory();
                System.out.println("Bạn đã chọn kênh Mobile App");
                break;
            case 3:
                factory = new StorePOSFactory();
                System.out.println("Bạn đã chọn kênh POS");
                break;
        }
        OrderService service = new OrderService(factory);
        service.processOrder(10000000);
    }
}