package com.fastfood.service;

import com.fastfood.model.MenuItem;
import com.fastfood.repository.MenuRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MenuService {
    private MenuRepository repository;
    public MenuService(MenuRepository repository) {
        this.repository=repository;
    }

    // tim theo ten
    public List<MenuItem> searchByName(String keyword) {
        return repository.getAllMenuItems().stream()
                .filter(item->item.getName().toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // tim theo khoang gia
    public List<MenuItem> searchByPriceRange(double min, double max) {
        return repository.getAllMenuItems().stream()
                .filter(item->item.getPrice()>=min && item.getPrice()<=max)
                .collect(Collectors.toList());
    }
}
