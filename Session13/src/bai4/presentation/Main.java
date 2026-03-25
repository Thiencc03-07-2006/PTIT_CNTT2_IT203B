package bai4.presentation;

import bai4.dao.DashboardDAO;
import bai4.model.BenhNhanDTO;
import bai4.model.DichVu;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        DashboardDAO service = new DashboardDAO();

        List<BenhNhanDTO> list = service.getDashboardData();

        for (BenhNhanDTO bn : list) {
            System.out.println("Bệnh nhân: " + bn.getMaBenhNhan() + " - " + bn.getTen());

            if (bn.getDsDichVu().isEmpty()) {
                System.out.println("   (Chưa có dịch vụ)"); // thể hiện BẪY 2
            } else {
                for (DichVu dv : bn.getDsDichVu()) {
                    System.out.println("   - " + dv.getTenDichVu() + " | " + dv.getThoiGian());
                }
            }
        }
    }
}