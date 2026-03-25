package bai5.controller;

import bai5.util.DatabaseHelper;

import java.sql.*;
import java.util.UUID;

public class BenhNhanController {

    public void tiepNhan(String ten, int tuoi, String maGiuong, double soTien) {

        Connection conn = null;

        try {
            conn = DatabaseHelper.getConnection();
            conn.setAutoCommit(false);

            // 1. Check giường
            String checkSql = "SELECT trangThai FROM Giuong WHERE maGiuong = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, maGiuong);

            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next() || !"TRONG".equals(rs.getString("trangThai"))) {
                throw new RuntimeException("Giường đã có người hoặc không tồn tại!");
            }

            // 2. Insert bệnh nhân
            String maBN = UUID.randomUUID().toString();

            String insertBN = "INSERT INTO BenhNhan VALUES (?, ?, ?)";
            PreparedStatement psBN = conn.prepareStatement(insertBN);
            psBN.setString(1, maBN);
            psBN.setString(2, ten);
            psBN.setInt(3, tuoi);
            psBN.executeUpdate();

            // 3. Update giường
            String updateGiuong = "UPDATE Giuong SET trangThai='DA_CO_NGUOI' WHERE maGiuong=?";
            PreparedStatement psG = conn.prepareStatement(updateGiuong);
            psG.setString(1, maGiuong);
            psG.executeUpdate();

            // 4. Insert tài chính
            String insertTC = "INSERT INTO TaiChinh(maBenhNhan, soTien, ngay) VALUES (?, ?, NOW())";
            PreparedStatement psTC = conn.prepareStatement(insertTC);
            psTC.setString(1, maBN);
            psTC.setDouble(2, soTien);
            psTC.executeUpdate();

            conn.commit();

            System.out.println("Tiếp nhận thành công!");

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Lỗi: " + e.getMessage());

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
            }
        }
    }
}