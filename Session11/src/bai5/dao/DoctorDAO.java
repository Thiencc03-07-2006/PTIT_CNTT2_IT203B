package bai5.dao;

import bai5.model.Doctor;
import bai5.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public List<Doctor> getAllDoctors() {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("specialty")
                );
                list.add(d);
            }

        } catch (Exception e) {
            System.out.println("Lỗi lấy danh sách: " + e.getMessage());
        }
        return list;
    }

    public boolean addDoctor(Doctor d) {
        String sql = "INSERT INTO Doctors VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getId());
            ps.setString(2, d.getName());
            ps.setString(3, d.getSpecialty());

            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Trùng mã bác sĩ!");
        } catch (DataTruncation e) {
            System.out.println("Dữ liệu quá dài!");
        } catch (Exception e) {
            System.out.println("Lỗi thêm: " + e.getMessage());
        }
        return false;
    }

    public void statisticBySpecialty() {
        String sql = "SELECT specialty, COUNT(*) as total FROM Doctors GROUP BY specialty";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("=== THỐNG KÊ ===");
            while (rs.next()) {
                System.out.println(
                        rs.getString("specialty") + " : " + rs.getInt("total")
                );
            }

        } catch (Exception e) {
            System.out.println("Lỗi thống kê: " + e.getMessage());
        }
    }
}