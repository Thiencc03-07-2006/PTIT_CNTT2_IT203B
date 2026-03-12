package com.fastfood.model;

public class Drink extends MenuItem {
    // Kich thuoc: S, M, L
    private String size;

    public Drink(int id, String name, double price, String size) {
        super(id, name, price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public double calculatePrice() {
        // Tinh gia theo size: S giu nguyen, M tang 20%, L tang 50%
        switch (size.toUpperCase()) {
            case "M":
                return getPrice() * 1.2;
            case "L":
                return getPrice() * 1.5;
            default: // Size S hoac mac dinh
                return getPrice();
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Size: " + size +
                " | Gia tinh: " + String.format("%.2f", calculatePrice());
    }
}
