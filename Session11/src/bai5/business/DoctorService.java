package bai5.business;

import bai5.dao.DoctorDAO;
import bai5.model.Doctor;

import java.util.List;

public class DoctorService {
    private DoctorDAO dao = new DoctorDAO();

    public List<Doctor> getDoctors() {
        return dao.getAllDoctors();
    }

    public boolean addDoctor(Doctor d) {
        if (d.getId().isEmpty() || d.getName().isEmpty()) {
            System.out.println("Không được để trống!");
            return false;
        }
        return dao.addDoctor(d);
    }

    public void statistic() {
        dao.statisticBySpecialty();
    }
}