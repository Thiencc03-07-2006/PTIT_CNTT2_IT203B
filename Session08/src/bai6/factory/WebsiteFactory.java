package bai6.factory;

import bai6.notification.EmailNotification;
import bai6.notification.NotificationService;
import bai6.payment.CreditCardPayment;
import bai6.payment.PaymentMethod;
import bai6.strategy.DiscountStrategy;
import bai6.strategy.WebsiteDiscount;

public class WebsiteFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() {
        return new WebsiteDiscount();
    }

    public PaymentMethod createPaymentMethod() {
        return new CreditCardPayment();
    }

    public NotificationService createNotificationService() {
        return new EmailNotification();
    }
}