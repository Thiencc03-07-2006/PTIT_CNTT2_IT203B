package com.fastfood.model;

public class Dessert extends MenuItem {

    public Dessert(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculatePrice() {
        // Mon trang mieng tinh gia goc, khong tinh thue
        return getPrice();
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Loai: Trang mieng" +
                " | Gia tinh: " + String.format("%.2f", calculatePrice());
    }
}

