package bai6.factory;

import bai6.notification.NotificationService;
import bai6.notification.PushNotification;
import bai6.payment.MomoPayment;
import bai6.payment.PaymentMethod;
import bai6.strategy.DiscountStrategy;
import bai6.strategy.FirstTimeDiscount;

public class MobileAppFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() {
        return new FirstTimeDiscount();
    }

    public PaymentMethod createPaymentMethod() {
        return new MomoPayment();
    }

    public NotificationService createNotificationService() {
        return new PushNotification();
    }
}