package bai6.payment;

public class CODPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Thanh toán COD: " + amount);
    }
}