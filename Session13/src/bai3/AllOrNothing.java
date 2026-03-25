package bai3;

/*
Báo cáo phân tích

Phân tích I/O (Input/Output)

Input:
maBenhNhan (int): Mã định danh duy nhất.
tienVienPhi (double): Số tiền viện phí cần thanh toán (phải > 0).
Output:
Trả về void hoặc ném ra Exception kèm thông báo chi tiết (Thành công/Lý do thất bại).
Trạng thái Database: Cả 3 bảng (Patient_Wallet, Beds, Patients) được cập nhật đồng bộ hoặc không có bảng nào thay đổi.

Đề xuất giải pháp

Vượt bẫy 1 (Số dư): Thực hiện một câu lệnh SELECT trước để kiểm tra số dư. Nếu balance < tienVienPhi, chủ động throw new Exception.
Vượt bẫy 2 (Row Affected): Kiểm tra giá trị trả về của executeUpdate(). Nếu bằng 0, nghĩa là mã bệnh nhân không tồn tại hoặc dữ liệu đã bị sai lệch, lập tức throw new Exception.
Tính nguyên tử: Toàn bộ code nằm trong khối try-catch. Chỉ gọi conn.commit() ở dòng cuối cùng của try. Bất kỳ ngoại lệ nào (kể cả ngoại lệ tự ném) đều dẫn đến conn.rollback() trong khối catch.

Thiết kế các bước:

Mở kết nối Database.
conn.setAutoCommit(false) để bắt đầu Transaction.
Truy vấn số dư hiện tại của bệnh nhân (Bẫy 1).
Thực thi Update 1: Trừ tiền ví (Kiểm tra row affected - Bẫy 2).
Thực thi Update 2: Giải phóng giường (Kiểm tra row affected).
Thực thi Update 3: Cập nhật trạng thái xuất viện (Kiểm tra row affected).
Gọi conn.commit() nếu không có lỗi.
Trong catch: Gọi conn.rollback().
Trong finally: Đóng kết nối để giải phóng tài nguyên.
 */

import bai1.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllOrNothing {
    public void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) throws Exception {
        Connection conn = null;
        PreparedStatement psSelect = null;
        PreparedStatement psUpdateWallet = null;
        PreparedStatement psUpdateBed = null;
        PreparedStatement psUpdatePatient = null;

        try {
            conn = DatabaseManager.getConnection();
            conn.setAutoCommit(false);

            // --- BẪY 1: KIỂM TRA SỐ DƯ (Logic nghiệp vụ) ---
            String sqlCheckBalance = "SELECT balance FROM Patient_Wallet WHERE patient_id = ?";
            psSelect = conn.prepareStatement(sqlCheckBalance);
            psSelect.setInt(1, maBenhNhan);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                double soDuHienTai = rs.getDouble("balance");
                if (soDuHienTai < tienVienPhi) {
                    // Chủ động ném lỗi để nhảy vào khối rollback
                    throw new Exception("LỖI: Số dư không đủ (Hiện có: " + soDuHienTai + "). Cần nạp thêm tiền!");
                }
            } else {
                throw new Exception("LỖI: Không tìm thấy ví của bệnh nhân ID: " + maBenhNhan);
            }

            // --- THAO TÁC 1: TRỪ TIỀN VIỆN PHÍ ---
            String sqlWallet = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            psUpdateWallet = conn.prepareStatement(sqlWallet);
            psUpdateWallet.setDouble(1, tienVienPhi);
            psUpdateWallet.setInt(2, maBenhNhan);
            int rowsWallet = psUpdateWallet.executeUpdate();

            // BẪY 2: Kiểm tra Row Affected
            if (rowsWallet == 0) throw new Exception("LỖI: Cập nhật ví thất bại (Bệnh nhân không tồn tại).");

            // --- THAO TÁC 2: GIẢI PHÓNG GIƯỜNG BỆNH ---
            String sqlBed = "UPDATE Beds SET status = 'AVAILABLE' WHERE current_patient_id = ?";
            psUpdateBed = conn.prepareStatement(sqlBed);
            psUpdateBed.setInt(1, maBenhNhan);
            int rowsBed = psUpdateBed.executeUpdate();

            // BẪY 2: Nếu bệnh nhân có trong viện thì phải có giường tương ứng
            if (rowsBed == 0) throw new Exception("LỖI: Không tìm thấy giường bệnh đang gán cho bệnh nhân này.");

            // --- THAO TÁC 3: CẬP NHẬT TRẠNG THÁI XUẤT VIỆN ---
            String sqlPatient = "UPDATE Patients SET status = 'DISCHARGED' WHERE id = ?";
            psUpdatePatient = conn.prepareStatement(sqlPatient);
            psUpdatePatient.setInt(1, maBenhNhan);
            int rowsPatient = psUpdatePatient.executeUpdate();

            if (rowsPatient == 0) throw new Exception("LỖI: Không thể cập nhật trạng thái xuất viện.");

            conn.commit();
            System.out.println("Thành công: Bệnh nhân " + maBenhNhan + " đã hoàn tất thủ tục xuất viện.");

        } catch (Exception e) {
            if (conn != null) {
                try {
                    System.err.println("Sự cố xảy ra: " + e.getMessage());
                    System.err.println("Đang thực hiện Rollback dữ liệu...");
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (psSelect != null) psSelect.close();
            if (psUpdateWallet != null) psUpdateWallet.close();
            if (psUpdateBed != null) psUpdateBed.close();
            if (psUpdatePatient != null) psUpdatePatient.close();
            if (conn != null) conn.close();
        }
    }
}
