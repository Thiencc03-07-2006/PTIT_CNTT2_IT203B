package bai6.strategy;

public class MemberDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        double discount = amount * 0.05;
        System.out.println("Giảm 5%: " + discount);
        return amount - discount;
    }
}