package bai5;

import java.util.*;

public class Order {

    private String id;
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private double finalAmount;

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double getTotal() {
        return items.stream().mapToDouble(OrderItem::getTotal).sum();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getId() {
        return id;
    }

    public void setFinalAmount(double amount) {
        this.finalAmount = amount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }
}