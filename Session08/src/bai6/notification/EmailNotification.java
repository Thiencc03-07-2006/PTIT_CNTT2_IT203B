package bai6.notification;

public class EmailNotification implements NotificationService {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}