package bai4;

/*
Phân tích sự lãng phí tài nguyên của Database Server khi phải phân tích (Parse) và lập kế hoạch thực thi (Execution Plan) 1.000 lần cho cùng một cấu trúc câu lệnh.

Phân tích cú pháp (Parsing) 1.000 lần: Với mỗi câu lệnh nối chuỗi, Database coi đó là một câu lệnh hoàn toàn mới. Nó phải kiểm tra cú pháp, kiểm tra quyền hạn và tên bảng/cột lại từ đầu.
Lập kế hoạch thực thi (Execution Plan) 1.000 lần: Database phải tốn tài nguyên CPU để tính toán con đường tối ưu nhất để ghi dữ liệu vào đĩa cho từng câu lệnh riêng lẻ.
Chiếm dụng bộ nhớ (Shared Pool): 1.000 câu lệnh khác nhau (do giá trị data khác nhau) sẽ chiếm dụng không gian lưu trữ cache của Database, gây lãng phí bộ nhớ đệm.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExecutionPlan {
    public void uploadTestResults(Connection conn, List<TestResult> list) {
        String sql = "INSERT INTO Results(data) VALUES(?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (TestResult tr : list) {
                pstmt.setString(1, tr.getData());
                pstmt.executeUpdate();
            }
            System.out.println("Đã nạp thành công " + list.size() + " kết quả xét nghiệm.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
