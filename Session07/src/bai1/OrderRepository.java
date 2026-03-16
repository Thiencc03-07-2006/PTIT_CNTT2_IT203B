package bai1;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {

    private Map<String, Order> database = new HashMap<>();

    public void save(Order order) {
        database.put(order.getOrderId(), order);
        System.out.println("Đã lưu đơn hàng " + order.getOrderId());
    }

    public Order findById(String id) {
        return database.get(id);
    }
}