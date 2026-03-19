package bai4.observer;

public class Fan implements Observer {

    @Override
    public void update(int temperature) {
        if (temperature < 20) {
            System.out.println("Quạt: Nhiệt độ thấp, TẮT");
        } else if (temperature <= 25) {
            System.out.println("Quạt: Nhiệt độ trung bình, chạy vừa");
        } else {
            System.out.println("Quạt: Nhiệt độ cao, chạy mạnh");
        }
    }
}