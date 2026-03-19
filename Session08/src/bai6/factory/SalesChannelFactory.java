package bai6.factory;

import bai6.notification.NotificationService;
import bai6.payment.PaymentMethod;
import bai6.strategy.DiscountStrategy;

public interface SalesChannelFactory {
    DiscountStrategy createDiscountStrategy();

    PaymentMethod createPaymentMethod();

    NotificationService createNotificationService();
}