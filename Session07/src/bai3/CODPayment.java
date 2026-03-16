package bai3;

public class CODPayment implements CODPayable {

    @Override
    public void processCOD(double amount) {
        System.out.println("Xử lý thanh toán COD: " + amount + " - Thành công");
    }

    @Override
    public void pay(double amount) {
        processCOD(amount);
    }
}