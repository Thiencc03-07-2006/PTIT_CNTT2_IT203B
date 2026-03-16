package bai6;

public class MobileDiscount implements DiscountStrategy {

    public double apply(double amount) {
        System.out.println("Áp dụng giảm giá 15% cho lần đầu");
        return amount * 0.85;
    }
}