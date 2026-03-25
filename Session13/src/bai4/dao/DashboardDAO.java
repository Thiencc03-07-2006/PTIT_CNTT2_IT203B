package bai4.dao;

import bai4.model.BenhNhanDTO;
import bai4.model.DichVu;
import bai4.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DashboardDAO {
    public List<BenhNhanDTO> getDashboardData() {
        List<BenhNhanDTO> result;
        Map<String, BenhNhanDTO> map = new LinkedHashMap<>();

        String sql = """
                    SELECT 
                        bn.maBenhNhan,
                        bn.ten,
                        dv.id,
                        dv.tenDichVu,
                        dv.thoiGian
                    FROM BenhNhan bn
                    LEFT JOIN DichVuSuDung dv
                        ON bn.maBenhNhan = dv.maBenhNhan
                    WHERE bn.ngay = CURRENT_DATE
                    ORDER BY bn.maBenhNhan
                """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Tối ưu fetch dữ liệu lớn (optional nhưng nên có)
            ps.setFetchSize(500);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    String maBN = rs.getString("maBenhNhan");

                    // ===== STEP 1: Lấy hoặc tạo BenhNhanDTO =====
                    BenhNhanDTO bn = map.get(maBN);
                    if (bn == null) {
                        bn = new BenhNhanDTO();
                        bn.setMaBenhNhan(maBN);
                        bn.setTen(rs.getString("ten"));

                        // luôn khởi tạo list để tránh NullPointerException
                        bn.setDsDichVu(new ArrayList<>());

                        map.put(maBN, bn);
                    }

                    // ===== STEP 2: Mapping DichVu =====

                    // ===== XỬ LÝ "BẪY 2" =====
                    // Do dùng LEFT JOIN nên:
                    // - Nếu bệnh nhân CHƯA có dịch vụ
                    //   → toàn bộ cột dv.* sẽ NULL
                    // - Nếu dùng rs.getInt("id") sẽ trả về 0 → sai logic
                    // => Phải dùng getObject để detect NULL thật sự

                    Integer dvId = (Integer) rs.getObject("id");

                    if (dvId != null) {
                        // Có dịch vụ → mới tạo object

                        DichVu dv = new DichVu();
                        dv.setId(dvId);
                        dv.setTenDichVu(rs.getString("tenDichVu"));
                        dv.setThoiGian(rs.getTimestamp("thoiGian"));

                        bn.getDsDichVu().add(dv);
                    }

                    // ===== QUAN TRỌNG =====
                    // Nếu dvId == null:
                    // → KHÔNG add dịch vụ
                    // → Nhưng bệnh nhân vẫn tồn tại trong Map
                    // → => KHÔNG bị mất dữ liệu (đúng yêu cầu đề bài)
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn Dashboard", e);
        }

        // Convert Map → List
        result = new ArrayList<>(map.values());

        return result;
    }
}
