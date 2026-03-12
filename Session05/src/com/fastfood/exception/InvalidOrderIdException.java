package com.fastfood.exception;

//Dùng khi tìm kiếm hoặc cập nhật một đơn hàng có ID không tồn tại trong OrderRepository.
public class InvalidOrderIdException extends RuntimeException {
    public InvalidOrderIdException(String message) {
        super(message);
    }
}