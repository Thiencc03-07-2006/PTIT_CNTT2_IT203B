package com.fastfood;

import com.fastfood.exception.InsufficientStockException;
import com.fastfood.exception.InvalidDiscountException;
import com.fastfood.exception.InvalidOrderIdException;
import com.fastfood.model.*;
import com.fastfood.repository.*;
import com.fastfood.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        MenuRepository menuRepository = new MenuRepository();
        DiscountRepository discountRepository = new DiscountRepository();
        OrderRepository orderRepository = new OrderRepository();

        MenuService menuService = new MenuService(menuRepository);
        OrderService orderService = new OrderService(orderRepository, discountRepository);

        int choice;

        do {
            showMenu();
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    showMenuItems(menuRepository);
                    break;

                case 2:
                    addMenuItem(menuRepository);
                    break;

                case 3:
                    removeMenuItem(menuRepository);
                    break;

                case 4:
                    searchMenu(menuService);
                    break;

                case 5:
                    createOrder(orderService);
                    break;

                case 6:
                    addItemToOrder(orderService, menuRepository);
                    break;

                case 7:
                    showOrders(orderRepository);
                    break;

                case 8:
                    applyDiscount(orderService);
                    break;

                case 9:
                    payOrder(orderService);
                    break;

                case 10:
                    statistics(orderRepository);
                    break;

                case 0:
                    System.out.println("Thoát chương trình");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        } while (choice != 0);
    }

    private static void showMenu() {

        System.out.println("\n===== FAST FOOD MANAGEMENT =====");

        System.out.println("1. Hiển thị menu");
        System.out.println("2. Thêm món vào menu");
        System.out.println("3. Xóa món khỏi menu");
        System.out.println("4. Tìm món theo tên");

        System.out.println("5. Tạo đơn hàng");
        System.out.println("6. Thêm món vào đơn hàng");
        System.out.println("7. Xem danh sách đơn hàng");
        System.out.println("8. Áp dụng discount");

        System.out.println("9. Thanh toán đơn hàng");
        System.out.println("10. Thống kê doanh thu");

        System.out.println("0. Thoát");

        System.out.print("Chọn: ");
    }

    // ================= MENU =================

    private static void showMenuItems(MenuRepository repo) {

        List<MenuItem> list = repo.getAllMenuItems();

        if (list.isEmpty()) {
            System.out.println("Menu đang trống");
            return;
        }

        list.forEach(System.out::println);
    }

    private static void addMenuItem(MenuRepository repo) {

        System.out.print("Nhập ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Tên món: ");
        String name = sc.nextLine();

        System.out.print("Giá: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.println("Loại món:");
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.println("3. Dessert");

        int type = Integer.parseInt(sc.nextLine());

        MenuItem item = null;

        switch (type) {

            case 1:
                item = new Food(id, name, price);
                break;

            case 2:
                System.out.print("Size (S/M/L): ");
                String size = sc.nextLine();
                item = new Drink(id, name, price, size);
                break;

            case 3:
                item = new Dessert(id, name, price);
                break;
        }

        repo.addMenuItem(item);

        System.out.println("Đã thêm món thành công");
    }

    private static void removeMenuItem(MenuRepository repo) {

        System.out.print("Nhập ID món cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());

        repo.removeMenuItem(id);

        System.out.println("Đã xóa món");
    }

    private static void searchMenu(MenuService service) {

        System.out.print("Nhập tên món cần tìm: ");
        String keyword = sc.nextLine();

        List<MenuItem> result = service.searchByName(keyword);

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy món");
        } else {
            result.forEach(System.out::println);
        }
    }

    // ================= ORDER =================

    private static void createOrder(OrderService service) {
        System.out.print("Nhập order id: ");
        String id = sc.nextLine();
        System.out.print("Tên khách: ");
        String name = sc.nextLine();

        try {
            Order order = new Order(id, name);
            service.createOrder(order);
            System.out.println("Tạo đơn hàng thành công");
        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static void addItemToOrder(OrderService service, MenuRepository repo) {
        try {
            System.out.print("Nhập order id: ");
            String orderId = sc.nextLine();
            System.out.print("Nhập id món: ");
            int itemId = Integer.parseInt(sc.nextLine());
            System.out.print("Số lượng: ");
            int quantity = Integer.parseInt(sc.nextLine());

            MenuItem item = repo.getAllMenuItems().stream()
                    .filter(m -> m.getId() == itemId)
                    .findFirst()
                    .orElse(null);

            if (item == null) {
                System.err.println("Không tìm thấy món!");
                return;
            }

            OrderItem orderItem = new OrderItem(item, quantity);

            // ĐÂY LÀ NƠI BẮT LỖI HẾT HÀNG HOẶC SAI ID
            service.addItem(orderId, orderItem);
            System.out.println("Đã thêm món vào đơn thành công");

        } catch (InsufficientStockException | InvalidOrderIdException e) {
            System.err.println(">>> THÔNG BÁO LỖI: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Lỗi: Dữ liệu nhập vào phải là số!");
        }
    }

    private static void applyDiscount(OrderService service) {
        System.out.print("Nhập order id: ");
        String orderId = sc.nextLine();
        System.out.print("Nhập mã giảm giá: ");
        String code = sc.nextLine();

        try {
            service.applyDiscount(orderId, code);
            System.out.println("Áp dụng discount thành công");
        } catch (InvalidDiscountException | InvalidOrderIdException e) {
            System.err.println(">>> THÔNG BÁO LỖI: " + e.getMessage());
        }
    }

    private static void showOrders(OrderRepository repo) {

        if (repo.getAll().isEmpty()) {
            System.out.println("Chưa có đơn hàng");
            return;
        }

        repo.getAll().values().forEach(System.out::println);
    }

    private static void payOrder(OrderService service) {

        System.out.print("Nhập order id: ");
        String orderId = sc.nextLine();

        try {

            double total = service.payOrder(orderId);

            System.out.println("Thanh toán thành công");
            System.out.println("Tổng tiền: " + total);

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }


    // ================= STATISTICS =================

    private static void statistics(OrderRepository repo) {

        StatisticsService statisticsService =
                new StatisticsService(repo.getAll().values().stream().toList());

        double revenue = statisticsService.getDailyRevenue(LocalDate.now());

        System.out.println("Doanh thu hôm nay: " + revenue);
    }
}