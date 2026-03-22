package bai2;

import java.sql.*;

/*
Giải thích tại sao lệnh if không đủ để xử lý yêu cầu "in danh sách". Con trỏ của ResultSet sẽ thay đổi như thế nào sau mỗi lần gọi next()?

if:

Lệnh if chỉ kiểm tra điều kiện một lần duy nhất. Khi rs.next() được gọi, con trỏ (cursor) di chuyển từ vị trí trước dòng đầu tiên (before first) đến dòng dữ liệu thứ nhất.
Sau khi in ra dòng đầu tiên, khối lệnh kết thúc và chương trình thoát khỏi logic truy xuất, dẫn đến việc các dòng dữ liệu từ thứ 2 trở đi bị bỏ qua hoàn toàn.

Con trỏ ResultSet:

Khi mới nhận kết quả từ executeQuery, con trỏ nằm ở vị trí ngay trước dòng đầu tiên.
Mỗi lần gọi phương thức .next(), con trỏ sẽ nhảy xuống dòng tiếp theo.
Phương thức này trả về true nếu có dòng dữ liệu hợp lệ và false nếu đã đi hết bảng.
Để duyệt qua toàn bộ danh sách, chúng ta cần một cấu trúc có khả năng lặp lại việc gọi .next() cho đến khi nó trả về false.
 */

public class PharmacyCatalogue {
    public void showList(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT medicine_name, stock FROM Pharmacy");
        System.out.println("--- DANH MỤC THUỐC TRONG KHO ---");
        while (rs.next()) {
            String name = rs.getString("medicine_name");
            int quantity = rs.getInt("stock");
            System.out.println("Tên thuốc: " + name + " | Số lượng tồn: " + quantity);
        }
    }
}
