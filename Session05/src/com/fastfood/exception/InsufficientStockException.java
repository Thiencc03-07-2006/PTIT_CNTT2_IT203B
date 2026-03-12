package com.fastfood.exception;

// Dùng khi khách hàng đặt số lượng món vượt quá số lượng còn lại trong kho.
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}