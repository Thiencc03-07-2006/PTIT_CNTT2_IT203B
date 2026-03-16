package bai1;

public class EmailService {

    public void sendOrderConfirmation(Customer customer, Order order) {
        System.out.println("Đã gửi email đến " + customer.getEmail() + ": Đơn hàng " + order.getOrderId() + " đã được tạo");
    }
}