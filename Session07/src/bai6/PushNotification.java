package bai6;

public class PushNotification implements NotificationService {

    public void notifyCustomer(String message) {
        System.out.println("Gửi push notification: " + message);
    }
}