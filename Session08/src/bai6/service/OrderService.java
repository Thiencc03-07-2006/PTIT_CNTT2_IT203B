package bai6.service;

import bai6.factory.SalesChannelFactory;
import bai6.notification.NotificationService;
import bai6.payment.PaymentMethod;
import bai6.strategy.DiscountStrategy;

public class OrderService {
    private DiscountStrategy discount;
    private PaymentMethod payment;
    private NotificationService notification;

    public OrderService(SalesChannelFactory factory) {
        this.discount = factory.createDiscountStrategy();
        this.payment = factory.createPaymentMethod();
        this.notification = factory.createNotificationService();
    }

    public void processOrder(double price, int quantity) {
        double total = price * quantity;
        System.out.println("Tổng tiền: " + total);

        double finalAmount = discount.applyDiscount(total);

        payment.pay(finalAmount);

        notification.send("Đơn hàng thành công!");
    }
}