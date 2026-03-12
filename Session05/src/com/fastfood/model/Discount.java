package com.fastfood.model;

import java.time.LocalDate;

public class Discount {

    // Mã giảm giá (ví dụ: FF10, VIP20...)
    private String code;

    // Phần trăm giảm giá
    private double percentage;

    // Ngày hết hạn của mã
    private LocalDate expiryDate;

    // Constructor khởi tạo mã giảm giá
    public Discount(String code, double percentage, LocalDate expiryDate) {
        this.code = code;
        this.percentage = percentage;
        this.expiryDate = expiryDate;
    }

    // Lấy mã giảm giá
    public String getCode() {
        return code;
    }

    // Cập nhật mã giảm giá
    public void setCode(String code) {
        this.code = code;
    }

    // Lấy phần trăm giảm giá
    public double getPercentage() {
        return percentage;
    }

    // Cập nhật phần trăm giảm giá
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    // Lấy ngày hết hạn
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    // Cập nhật ngày hết hạn
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Kiểm tra mã giảm giá còn hiệu lực hay không
    // Mã hợp lệ nếu ngày hiện tại <= ngày hết hạn
    public boolean isValid() {
        return !LocalDate.now().isAfter(expiryDate);
    }

    // Hiển thị thông tin mã giảm giá
    @Override
    public String toString() {
        return "Code: " + code +
                " | Percentage: " + percentage + "%" +
                " | Expiry Date: " + expiryDate;
    }
}