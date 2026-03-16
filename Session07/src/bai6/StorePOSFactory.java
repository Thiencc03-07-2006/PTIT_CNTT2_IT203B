package bai6;

public class StorePOSFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscount() {
        return new POSDiscount();
    }

    public PaymentMethod createPayment() {
        return new CODPayment();
    }

    public NotificationService createNotification() {
        return new ReceiptPrinter();
    }
}