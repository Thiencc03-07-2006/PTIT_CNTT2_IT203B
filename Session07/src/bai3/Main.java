package bai3;

public class Main {

    public static void main(String[] args) {
        System.out.println("COD - Số tiền 500000");
        PaymentProcessor codProcessor =
                new PaymentProcessor(new CODPayment());
        codProcessor.process(500000);

        System.out.println("\nThẻ tín dụng - Số tiền 1000000");
        PaymentProcessor cardProcessor =
                new PaymentProcessor(new CreditCardPayment());
        cardProcessor.process(1000000);

        System.out.println("\nVí MoMo - Số tiền 750000");
        PaymentProcessor momoProcessor =
                new PaymentProcessor(new MomoPayment());
        momoProcessor.process(750000);

        System.out.println("\nKiểm tra LSP: thay CreditCardPayment bằng MomoPayment");
        PaymentProcessor testProcessor =
                new PaymentProcessor(new MomoPayment());
        testProcessor.process(1000000);
    }
}