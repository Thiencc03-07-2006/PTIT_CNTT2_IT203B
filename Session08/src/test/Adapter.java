package test;

public class Adapter {
}

class YoungHuman {
    void move2foot() {
        System.out.println("di chuye 2 chan");
    }
}

interface OldHuman {
    void move3food();
}

class HumanAdapter implements OldHuman {
    private YoungHuman old;

    public HumanAdapter(YoungHuman old) {
        this.old = old;
    }

    @Override
    public void move3food() {
        old.move2foot();

        System.out.println("di chuye 3 chan");
    }
}