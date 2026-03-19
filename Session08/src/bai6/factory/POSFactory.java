package bai6.factory;

import bai6.notification.NotificationService;
import bai6.notification.PrintReceipt;
import bai6.payment.CODPayment;
import bai6.payment.PaymentMethod;
import bai6.strategy.DiscountStrategy;
import bai6.strategy.MemberDiscount;

public class POSFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() {
        return new MemberDiscount();
    }

    public PaymentMethod createPaymentMethod() {
        return new CODPayment();
    }

    public NotificationService createNotificationService() {
        return new PrintReceipt();
    }
}