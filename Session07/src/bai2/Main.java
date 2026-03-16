package bai2;

public class Main {

    public static void main(String[] args) {

        double total = 1_000_000;

        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng PercentageDiscount 10%");
        OrderCalculator calc1 = new OrderCalculator(new PercentageDiscount(10));
        System.out.println("Số tiền sau giảm: " + (long)calc1.calculate(total));

        System.out.println("\nĐơn hàng: tổng tiền 1.000.000, áp dụng FixedDiscount 50.000");
        OrderCalculator calc2 = new OrderCalculator(new FixedDiscount(50000));
        System.out.println("Số tiền sau giảm: " + (long)calc2.calculate(total));

        System.out.println("\nĐơn hàng: tổng tiền 1.000.000, áp dụng NoDiscount");
        OrderCalculator calc3 = new OrderCalculator(new NoDiscount());
        System.out.println("Số tiền sau giảm: " + (long)calc3.calculate(total));

        System.out.println("\nThêm HolidayDiscount 15%");
        OrderCalculator calc4 = new OrderCalculator(new HolidayDiscount());
        System.out.println("Số tiền sau giảm: " + (long)calc4.calculate(total));
    }
}