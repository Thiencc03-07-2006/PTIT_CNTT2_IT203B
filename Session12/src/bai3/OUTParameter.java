package bai3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/*
Tại sao JDBC bắt buộc phải gọi registerOutParameter() trước khi thực thi? Nếu tham số đầu ra là kiểu DECIMAL trong SQL thì trong Java phải đăng ký bằng hằng số nào trong lớp Types?

Cung cấp thông tin kiểu dữ liệu cho JDBC Driver: Khác với tham số đầu vào (IN) mà Java có thể tự suy luận kiểu dữ liệu từ biến truyền vào, tham số OUT cần được định nghĩa trước kiểu dữ liệu để Driver chuẩn bị bộ nhớ và cơ chế chuyển đổi dữ liệu từ SQL sang Java tương ứng.
Khởi tạo bộ đệm nhận giá trị: Nếu không đăng ký, JDBC sẽ coi đó là một tham số chưa được định nghĩa hoàn chỉnh, dẫn đến lỗi "index out of range" khi bạn cố gắng truy xuất dữ liệu sau đó.

Ánh xạ kiểu DECIMAL:

Nếu trong SQL, tham số đầu ra có kiểu là DECIMAL hoặc NUMERIC, trong Java bạn phải đăng ký bằng hằng số java.sql.Types.DECIMAL.
 */

public class OUTParameter {
    public void lookUpCosts(Connection conn) {
        int surgeryId = 505;

        String sql = "{call GET_SURGERY_FEE(?, ?)}";

        try (CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, surgeryId);
            cstmt.registerOutParameter(2, java.sql.Types.DECIMAL);
            cstmt.execute();
            double cost = cstmt.getDouble(2);
            System.out.println("--- THÔNG TIN CHI PHÍ ---");
            System.out.println("Mã phẫu thuật: " + surgeryId);
            System.out.println("Tổng chi phí cần thanh toán: " + cost + " VND");
        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi thủ tục: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
