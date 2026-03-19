package bai6.app;

import bai6.factory.MobileAppFactory;
import bai6.factory.POSFactory;
import bai6.factory.SalesChannelFactory;
import bai6.factory.WebsiteFactory;
import bai6.service.OrderService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Website");
            System.out.println("2. Mobile App");
            System.out.println("3. POS");
            System.out.println("0. Thoát");
            System.out.println("Chọn: ");

            int choice = sc.nextInt();

            SalesChannelFactory factory = null;

            switch (choice) {
                case 1:
                    factory = new WebsiteFactory();
                    System.out.println("Bạn chọn Website");
                    break;
                case 2:
                    factory = new MobileAppFactory();
                    System.out.println("Bạn chọn Mobile App");
                    break;
                case 3:
                    factory = new POSFactory();
                    System.out.println("Bạn chọn POS");
                    break;
                case 0:
                    return;
            }

            System.out.print("Nhập giá: ");
            double price = sc.nextDouble();

            System.out.print("Số lượng: ");
            int qty = sc.nextInt();

            OrderService service = new OrderService(factory);
            service.processOrder(price, qty);
        }
    }
}