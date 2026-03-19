package bai6.strategy;

public class WebsiteDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        double discount = amount * 0.1;
        System.out.println("Giảm 10%: " + discount);
        return amount - discount;
    }
}