package bai6;

public class WebsiteDiscount implements DiscountStrategy {

    public double apply(double amount) {
        System.out.println("Áp dụng giảm giá 10% cho đơn hàng website");
        return amount * 0.9;
    }
}