package bai5;

public class InvoiceGenerator {

    public void generate(Order order, double discounted) {
        System.out.println("=== HÓA ĐƠN ===");
        System.out.println("Khách: " + order.getCustomer().getName());
        for (OrderItem i : order.getItems()) {

            System.out.println(i.getProduct().getName() + " - Số lượng: " + i.getQuantity() + " - Đơn giá: " + i.getProduct().getPrice() + " - Thành tiền: " + i.getTotal());
        }
        System.out.println("Tổng tiền: " + order.getTotal());
        System.out.println("Cần thanh toán: " + discounted);
    }
}