package bai5;

public interface SalesChannelFactory {

    DiscountStrategy createDiscount();

    PaymentMethod createPayment();

    NotificationService createNotification();
}