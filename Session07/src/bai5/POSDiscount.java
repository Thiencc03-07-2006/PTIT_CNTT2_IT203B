package bai5;

public class POSDiscount implements DiscountStrategy {

    public double apply(double amount) {
        System.out.println("Giảm giá 5% cho thành viên tại cửa hàng");
        return amount * 0.95;
    }
}