package com.fastfood.model;

public class OrderItem {
    private final MenuItem menuItem;
    private final int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        if (menuItem == null) throw new IllegalArgumentException("MenuItem không được null");
        if (quantity <= 0) throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() { return menuItem; }
    public int getQuantity() { return quantity; }

    // Giá của item này (giá món * số lượng)
    public double getSubTotal() {
        return menuItem.calculatePrice() * quantity;
    }

    @Override
    public String toString() {
        return menuItem.getName() + " x " + quantity + " = " + getSubTotal();
    }
}