package bai6;

public class EmailNotification implements NotificationService {

    public void notifyCustomer(String message) {
        System.out.println("Gửi email xác nhận");
    }
}