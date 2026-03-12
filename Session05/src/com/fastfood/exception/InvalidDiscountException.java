package com.fastfood.exception;

//Dùng khi mã giảm giá nhập vào bị sai, đã hết hạn hoặc không đủ điều kiện áp dụng.
public class InvalidDiscountException extends RuntimeException {
    public InvalidDiscountException(String message) {
        super(message);
    }
}