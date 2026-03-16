package bai5;

import java.util.*;

public class FileOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
        System.out.println("Đã lưu đơn hàng " + order.getId());
    }

    public List<Order> findAll() {
        return orders;
    }
}