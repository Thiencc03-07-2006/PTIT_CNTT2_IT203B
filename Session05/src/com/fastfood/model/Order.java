package com.fastfood.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String customerName;
    private LocalDateTime orderDate;
    private List<OrderItem> items = new ArrayList<>();
    private OrderStatus status;
    private String discountCode;
    private double discountAmount;

    public Order(String orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName != null ? customerName : "Khách vãng lai";
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.discountCode = null;
        this.discountAmount = 0.0;
    }

    // Getter & Setter
    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public List<OrderItem> getItems() { return new ArrayList<>(items); } // defensive copy
    public OrderStatus getStatus() { return status; }
    public String getDiscountCode() { return discountCode; }
    public double getDiscountAmount() { return discountAmount; }

    public void setStatus(OrderStatus status) { this.status = status; }
    public void setDiscountCode(String discountCode) { this.discountCode = discountCode; }
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }

    // Thêm món vào đơn hàng
    public void addItem(MenuItem menuItem, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
        items.add(new OrderItem(menuItem, quantity));
    }

    // Tính tổng tiền trước giảm giá
    public double calculateTotal() {
        return items.stream()
                .mapToDouble(item -> item.getMenuItem().calculatePrice() * item.getQuantity())
                .sum();
    }

    // Tổng sau giảm giá
    public double getTotalAfterDiscount() {
        return calculateTotal() - discountAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", status=" + status +
                ", total=" + getTotalAfterDiscount() +
                '}';
    }
}