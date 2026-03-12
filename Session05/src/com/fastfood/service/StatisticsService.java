package com.fastfood.service;

import com.fastfood.model.MenuItem;
import com.fastfood.model.Order;
import com.fastfood.model.OrderStatus;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticsService {
    private final List<Order> orders;

    public StatisticsService(List<Order> orders) {
        this.orders = orders;
    }

    // Doanh thu theo ngày
    public double getDailyRevenue(LocalDate date) {
        return orders.stream()
                .filter(o -> o.getOrderDate().toLocalDate().equals(date)
                        && o.getStatus() == OrderStatus.PAID)
                .mapToDouble(Order::getTotalAfterDiscount)
                .sum();
    }

    // Doanh thu theo tháng
    public double getMonthlyRevenue(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return orders.stream()
                .filter(o -> {
                    LocalDate d = o.getOrderDate().toLocalDate();
                    return YearMonth.from(d).equals(yearMonth) && o.getStatus() == OrderStatus.PAID;
                })
                .mapToDouble(Order::getTotalAfterDiscount)
                .sum();
    }

    // Danh sách món bán chạy nhất (theo số lượng bán)
    public List<Map.Entry<MenuItem, Integer>> getTopSellingItems(int limit) {
        Map<MenuItem, Integer> salesMap = new HashMap<>();

        orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.PAID)
                .flatMap(o -> o.getItems().stream())
                .forEach(item -> {
                    MenuItem mi = item.getMenuItem();
                    salesMap.merge(mi, item.getQuantity(), Integer::sum);
                });

        return salesMap.entrySet().stream()
                .sorted(Map.Entry.<MenuItem, Integer>comparingByValue().reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }
}