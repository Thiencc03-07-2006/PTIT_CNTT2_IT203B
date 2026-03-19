package bai6.payment;

public class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán thẻ tín dụng: " + amount);
    }
}