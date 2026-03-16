package bai5;

public class EmailNotification implements NotificationService {

    public void send(String message, String recipient) {
        System.out.println("Đã gửi email xác nhận");
    }
}