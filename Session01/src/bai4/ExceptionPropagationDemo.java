package bai4;

import java.io.IOException;

public class ExceptionPropagationDemo {

    // Method C
    public static void saveToFile() throws IOException {
        // Giả lập lỗi ghi file
        throw new IOException("Lỗi khi ghi dữ liệu vào file!");
    }

    // Method B
    public static void processUserData() throws IOException {
        System.out.println("Đang xử lý dữ liệu người dùng...");
        saveToFile(); // gọi method C
    }

    // Method A
    public static void main(String[] args) {
        try {
            processUserData(); // gọi method B
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi lưu dữ liệu: " + e.getMessage());
        }
    }
}