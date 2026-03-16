package bai2;

public class OrderCalculator {

    private DiscountStrategy discountStrategy;

    public OrderCalculator(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculate(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount);
    }
}