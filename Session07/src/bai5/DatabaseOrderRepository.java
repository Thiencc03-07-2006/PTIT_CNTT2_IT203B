package bai5;

import java.util.*;

public class DatabaseOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu database: " + order.getId());
    }

    public List<Order> findAll() {
        return orders;
    }
}