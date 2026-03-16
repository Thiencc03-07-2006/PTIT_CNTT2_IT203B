package bai4;

import java.util.ArrayList;
import java.util.List;

public class FileOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
        System.out.println("Lưu đơn hàng vào file: " + order.getOrderId());
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }
}