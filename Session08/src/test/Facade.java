package test;

public class Facade {
    public static void main(String[] args) {
        FacadeRestaurant use=new FacadeRestaurant();
        use.order();
        use.pay();
    }
}

class FacadeRestaurant {
    private Chef chef = new Chef();
    private Staff staff = new Staff();
    private Manager manager = new Manager();

    void order() {
        staff.order();
        chef.cook();
    }

    void pay() {
        staff.invoice();
        manager.feedback();
        manager.complainToChef();
    }
}

class Chef {
    void cook() {
        System.out.println("Nhan order tu boi ban");
    }
}

class Staff {
    void order() {
        System.out.println("Order tu khach");
    }

    void invoice() {
        System.out.println("Gui hoa don");
    }
}

class Manager {
    void feedback() {
        System.out.println("Nhan phan hoi tu khach");
    }

    void complainToChef() {
        System.out.println("Noi lai voi dai bep");
    }
}
