package bai5;

public class OrderService {

    private SalesChannelFactory factory;

    public OrderService(SalesChannelFactory factory) {
        this.factory = factory;
    }

    public void processOrder(double amount) {

        DiscountStrategy discount = factory.createDiscount();
        PaymentMethod payment = factory.createPayment();
        NotificationService notify = factory.createNotification();

        double finalAmount = discount.apply(amount);

        payment.pay(finalAmount);

        notify.notifyCustomer("Đơn hàng thành công");
    }
}