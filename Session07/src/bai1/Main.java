package bai1;

public class Main {

    public static void main(String[] args) {

        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);
        System.out.println("Tạo sản phẩm: SP01 - Laptop - 15000000, SP02 - Chuột - 300000");
        System.out.println("Đã thêm sản phẩm SP01, SP02");

        Customer customer = new Customer("Nguyễn Văn A", "a@example.com", "Hà Nội");
        System.out.println("Tạo khách hàng: Nguyễn Văn A - a@example.com");
        System.out.println("Đã thêm khách hàng");

        Order order = new Order("ORD001", customer);
        order.addItem(new OrderItem(p1, 1));
        order.addItem(new OrderItem(p2, 2));
        System.out.println("Tạo đơn hàng: SP01 (1 cái), SP02 (2 cái)");
        System.out.println("Đơn hàng ORD001 được tạo");

        OrderCalculator calculator = new OrderCalculator();
        System.out.println("Tính tổng tiền");
        double total = calculator.calculateTotal(order);
        System.out.println("Tổng tiền: " +  total);
        OrderRepository repository = new OrderRepository();
        System.out.println("Lưu đơn hàng");
        repository.save(order);
        EmailService emailService = new EmailService();
        System.out.println("Gửi email xác nhận");
        emailService.sendOrderConfirmation(customer, order);
    }
}