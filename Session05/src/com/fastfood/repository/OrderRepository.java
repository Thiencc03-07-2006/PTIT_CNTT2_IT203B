package com.fastfood.repository;

import com.fastfood.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderRepository {

    private Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }

    public Map<String, Order> getAll() {
        return orders;
    }
}