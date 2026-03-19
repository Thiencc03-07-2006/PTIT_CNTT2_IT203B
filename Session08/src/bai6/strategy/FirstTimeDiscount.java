package bai6.strategy;

public class FirstTimeDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        double discount = amount * 0.15;
        System.out.println("Giảm 15%: " + discount);
        return amount - discount;
    }
}