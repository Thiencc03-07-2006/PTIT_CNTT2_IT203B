package rikkei.edu.business.service;

import rikkei.edu.business.dao.IStudentDao;
import rikkei.edu.business.dao.StudentDaoImpl;
import rikkei.edu.model.Student;

import java.util.List;

public class StudentServiceImpl implements IStudentService{
    private final IStudentDao studentDao = new StudentDaoImpl();
    @Override
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public boolean addNewStudent(Student s) {
        return studentDao.insert(s);
    }
}
