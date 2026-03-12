package com.fastfood.model;

public class Food extends MenuItem {
    public Food(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculatePrice() {
        return getPrice()* 1.05; //them 5% thue
    }
}
