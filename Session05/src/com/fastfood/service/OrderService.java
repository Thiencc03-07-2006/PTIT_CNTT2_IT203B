package com.fastfood.service;

import com.fastfood.exception.InvalidDiscountException;
import com.fastfood.exception.InvalidOrderIdException;
import com.fastfood.model.Discount;
import com.fastfood.model.Order;
import com.fastfood.model.OrderItem;
import com.fastfood.model.OrderStatus;
import com.fastfood.repository.DiscountRepository;
import com.fastfood.repository.OrderRepository;

import java.time.LocalDate;

public class OrderService {

    private OrderRepository orderRepository;
    private DiscountRepository discountRepository;

    public OrderService(OrderRepository orderRepository,
                        DiscountRepository discountRepository) {

        this.orderRepository = orderRepository;
        this.discountRepository = discountRepository;
    }

    // Tạo đơn hàng
    public void createOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (order.getOrderId() == null || order.getOrderId().trim().isEmpty()) {
            throw new IllegalArgumentException("Order id is required");
        }

        orderRepository.save(order);
    }

    // Thêm món vào đơn hàng
    public void addItem(String orderId, OrderItem item) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order id is required");
        }

        if (item == null) {
            throw new IllegalArgumentException("Order item cannot be null");
        }

        if (item.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() ->
                        new InvalidOrderIdException("Order not found"));

        order.addItem(item.getMenuItem(), item.getQuantity());
    }

    // Áp dụng mã giảm giá
    public void applyDiscount(String orderId, String code) {

        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order id is required");
        }

        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Discount code is required");
        }

        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() ->
                        new InvalidOrderIdException("Order ID not found"));

        Discount discount = discountRepository.getDiscountByCode(code);
        if (discount == null) {
            throw new InvalidDiscountException("Discount code invalid");
        }

        // kiểm tra hạn sử dụng
        if (discount.getExpiryDate().isBefore(LocalDate.now())) {
            throw new InvalidDiscountException("Discount expired");
        }

        if (discount.getPercentage() <= 0 || discount.getPercentage() > 100) {
            throw new InvalidDiscountException("Invalid discount percentage");
        }
        order.setDiscountAmount(discount.getPercentage());
    }

    // Tính tổng tiền đơn hàng
    public double calculateTotal(String orderId) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order id is required");
        }

        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() ->
                        new InvalidOrderIdException("Order not found"));

        if (order.getItems().isEmpty()) {
            throw new IllegalStateException("Order has no items");
        }

        return order.calculateTotal();
    }

    // Thanh toán đơn hàng
    public double payOrder(String orderId) {

        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order id is required");
        }

        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() ->
                        new InvalidOrderIdException("Order not found"));

        if (order.getItems().isEmpty()) {
            throw new IllegalStateException("Order has no items");
        }

        if (order.getStatus() == OrderStatus.PAID) {
            throw new IllegalStateException("Order already paid");
        }

        double total = order.getTotalAfterDiscount();

        order.setStatus(OrderStatus.PAID);

        return total;
    }
}