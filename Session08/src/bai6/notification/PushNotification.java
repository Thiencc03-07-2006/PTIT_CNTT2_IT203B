package bai6.notification;

public class PushNotification implements NotificationService {
    public void send(String message) {
        System.out.println("Push Notification: " + message);
    }
}