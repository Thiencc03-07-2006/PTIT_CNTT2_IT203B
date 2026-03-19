package test;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    public static void main(String[] args) {

        SourceNews source = new SourceNews();

        ReceiverObserver sub1 = new Subscriber("Người đọc A");
        ReceiverObserver sub2 = new Subscriber("Người đọc B");

        source.addReceiver(sub1);
        source.addReceiver(sub2);

        source.setNews("Tin nóng: Điểm biến động lớn!");
    }
}

// test.Observer (interface)
interface ReceiverObserver {
    void update(String news);
}

// Subject
class SourceNews {

    private List<ReceiverObserver> observerList = new ArrayList<>();
    private String news;

    public void addReceiver(ReceiverObserver receiverObserver) {
        observerList.add(receiverObserver);
    }

    public void removeReceiver(ReceiverObserver receiverObserver) {
        observerList.remove(receiverObserver);
    }

    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }

    public void notifyObservers() {
        for (ReceiverObserver observer : observerList) {
            observer.update(news);
        }
    }
}

// ConcreteObserver
class Subscriber implements ReceiverObserver {

    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " nhận tin: " + news);
    }
}