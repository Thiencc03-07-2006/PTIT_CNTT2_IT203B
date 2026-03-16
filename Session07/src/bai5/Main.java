package bai5;

import java.util.*;

public class Main {

    static List<Product> products = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderRepository repo = new FileOrderRepository();
        NotificationService notify = new EmailNotification();
        OrderService service = new OrderService(repo, notify);
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Xem đơn hàng");
            System.out.println("5. Tổng doanh thu");
            System.out.println("0. Thoát");
            System.out.print("Mời nhập: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    addCustomer(sc);
                    break;
                case 3:
                    createOrder(service);
                    break;
                case 4:
                    for (Order o : service.getOrders()) {
                        System.out.println(o.getId() + " - " + o.getCustomer().getName() + " - " +  o.getFinalAmount());
                    }
                    break;
                case 5:
                    double revenue = service.getOrders().stream().mapToDouble(Order::getFinalAmount).sum();
                    System.out.println("Tổng doanh thu: " +  revenue);
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void createOrder(OrderService service) {
        Customer c = customers.get(0);
        Product p = products.get(0);
        Order order = new Order("ORD001", c);
        order.addItem(new OrderItem(p, 1));
        DiscountStrategy discount = new PercentageDiscount(10);
        PaymentMethod payment = new CreditCardPayment();
        InvoiceGenerator invoice = new InvoiceGenerator();
        service.createOrder(order, discount, payment);
        invoice.generate(order, order.getFinalAmount());
    }

    private static void addCustomer(Scanner sc) {
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("ĐT: ");
        String phone = sc.nextLine();
        customers.add(new Customer(name, email, phone));
        System.out.println("Đã thêm khách hàng");
    }

    private static void addProduct(Scanner sc) {
        System.out.print("Mã: ");
        String id = sc.nextLine();
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Giá: ");
        double price = sc.nextDouble();
        sc.nextLine();
        products.add(new Product(id, name, price, "Điện tử"));
        System.out.println("Đã thêm sản phẩm " + id);
    }
}