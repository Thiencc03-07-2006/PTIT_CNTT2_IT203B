package bai3;

public class MomoPayment implements EWalletPayable {

    @Override
    public void processMomo(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + amount + " - Thành công");
    }

    @Override
    public void pay(double amount) {
        processMomo(amount);
    }
}