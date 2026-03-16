package bai5;

public class EmailNotification implements NotificationService {

    public void notifyCustomer(String message) {
        System.out.println("Gửi email xác nhận");
    }
}