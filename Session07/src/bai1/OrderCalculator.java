package bai1;

public class OrderCalculator {

    public double calculateTotal(Order order) {
        double total = 0;

        for (OrderItem item : order.getItems()) {
            total += item.getTotalPrice();
        }

        return total;
    }
}