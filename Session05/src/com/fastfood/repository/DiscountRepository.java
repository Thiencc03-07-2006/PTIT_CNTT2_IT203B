package com.fastfood.repository;

import com.fastfood.model.Discount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepository {

    // Danh sách lưu trữ các mã giảm giá
    private List<Discount> discountList = new ArrayList<>();

    // Constructor: khởi tạo một số mã giảm giá mẫu để test
    public DiscountRepository() {
        discountList.add(new Discount("FF10", 10, LocalDate.now().plusDays(10)));
        discountList.add(new Discount("STUDENT15", 15, LocalDate.now().plusDays(5)));
        discountList.add(new Discount("VIP30", 30, LocalDate.now().plusDays(30)));
        discountList.add(new Discount("OLD5", 5, LocalDate.now().minusDays(1))); // mã đã hết hạn
    }

    // Thêm mã giảm giá mới
    public void addDiscount(Discount discount) {
        discountList.add(discount);
    }

    // Lấy toàn bộ danh sách mã giảm giá
    public List<Discount> getAllDiscounts() {
        return discountList;
    }

    // Tìm mã giảm giá theo code
    public Discount getDiscountByCode(String code) {
        return discountList.stream()
                .filter(discount -> discount.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    // Xóa mã giảm giá theo code
    public void removeDiscount(String code) {
        discountList.removeIf(discount -> discount.getCode().equalsIgnoreCase(code));
    }

    // Lấy danh sách các mã giảm giá còn hiệu lực
    public List<Discount> getValidDiscounts() {

        List<Discount> validDiscounts = new ArrayList<>();

        for (Discount discount : discountList) {
            if (discount.isValid()) {
                validDiscounts.add(discount);
            }
        }

        return validDiscounts;
    }
}