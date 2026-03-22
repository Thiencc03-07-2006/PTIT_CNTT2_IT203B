package rikkei.edu.run;

import rikkei.edu.business.service.IStudentService;
import rikkei.edu.business.service.StudentServiceImpl;
import rikkei.edu.model.Gender;
import rikkei.edu.model.Student;
import rikkei.edu.util.InputMethod;
import rikkei.edu.validate.ShopValidate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentApplication {
    private static final IStudentService studentService=new StudentServiceImpl();

    public static void main(String[] args) {

        while (true) {
            System.out.println(ShopValidate.ANSI_CYAN + "================= QUẢN LÝ SINH VIÊN =================" + ShopValidate.ANSI_RESET);
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Sửa thông tin sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm kiếm sinh viên theo tên hoặc số điện thoại");
            System.out.println("6. Sắp xếp sinh viên theo tên (A-Z)");
            System.out.println("7. Hiển thị danh sách phân trang");
            System.out.println("8. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    displayAllStudents();
                    break;
                case 2:
                    addNewStudent();
                    break;
                case 3:
//                    updateStudent();
                    break;
                case 4:
//                    deleteStudent();
                    break;
                case 5:
//                    searchStudent();
                    break;
                case 6:
//                    sortStudents();
                    break;
                case 7:
//                    displayPagination();
                    break;
                case 8:
                    System.out.println(ShopValidate.ANSI_GREEN + "Đã thoát chương trình!" + ShopValidate.ANSI_RESET);
                    System.exit(0);
                default:
                    System.out.println(ShopValidate.ANSI_RED + "Chức năng không hợp lệ! Vui lòng chọn từ 1-8." + ShopValidate.ANSI_RESET);
            }
        }
    }



    // --- CÁC PHƯƠNG THỨC XỬ LÝ CHỨC NĂNG MENU ---

    private static void displayAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        if (studentList.isEmpty()) {
            System.out.println(ShopValidate.ANSI_YELLOW + "Danh sách sinh viên đang trống!" + ShopValidate.ANSI_RESET);
            return;
        }
        printTableHeader();
        for (Student student : studentList) {
            student.displayData();
        }
        printTableFooter();
    }

//    // TÍNH NĂNG MỚI: PHÂN TRANG
//    private static void displayPagination() {
//        if (studentList.isEmpty()) {
//            System.out.println(ShopValidate.ANSI_YELLOW + "Danh sách sinh viên đang trống!" + ShopValidate.ANSI_RESET);
//            return;
//        }
//
//        int pageSize = 2; // Hiển thị 2 sinh viên/trang để dễ test với 5 dữ liệu mẫu
//        int totalPages = (int) Math.ceil((double) studentList.size() / pageSize);
//        int currentPage = 1;
//
//        while (true) {
//            // Cập nhật lại tổng số trang phòng trường hợp danh sách bị thay đổi ở thread khác (tuy ít xảy ra ở console app này)
//            totalPages = (int) Math.ceil((double) studentList.size() / pageSize);
//            if (currentPage > totalPages) currentPage = totalPages;
//
//            System.out.println(ShopValidate.ANSI_YELLOW + "\n--- HIỂN THỊ DANH SÁCH (TRANG " + currentPage + "/" + totalPages + ") ---" + ShopValidate.ANSI_RESET);
//            printTableHeader();
//
//            int startIndex = (currentPage - 1) * pageSize;
//            int endIndex = Math.min(startIndex + pageSize, studentList.size());
//
//            for (int i = startIndex; i < endIndex; i++) {
//                studentList.get(i).displayData();
//            }
//            printTableFooter();
//
//            System.out.println("Tùy chọn: [N] Next | [P] Prev | [S] Chọn trang | [E] Thoát");
//            System.out.print("Nhập lựa chọn của bạn: ");
//            String choice = InputMethod.getString().toUpperCase();
//
//            switch (choice) {
//                case "N":
//                    if (currentPage < totalPages) {
//                        currentPage++;
//                    } else {
//                        System.out.println(ShopValidate.ANSI_RED + "Bạn đang ở trang cuối cùng!" + ShopValidate.ANSI_RESET);
//                    }
//                    break;
//                case "P":
//                    if (currentPage > 1) {
//                        currentPage--;
//                    } else {
//                        System.out.println(ShopValidate.ANSI_RED + "Bạn đang ở trang đầu tiên!" + ShopValidate.ANSI_RESET);
//                    }
//                    break;
//                case "S":
//                    System.out.print("Nhập số trang muốn đến (1 - " + totalPages + "): ");
//                    int targetPage = InputMethod.getInteger();
//                    if (targetPage >= 1 && targetPage <= totalPages) {
//                        currentPage = targetPage;
//                    } else {
//                        System.out.println(ShopValidate.ANSI_RED + "Số trang không hợp lệ!" + ShopValidate.ANSI_RESET);
//                    }
//                    break;
//                case "E":
//                    System.out.println(ShopValidate.ANSI_GREEN + "Đã thoát chế độ phân trang." + ShopValidate.ANSI_RESET);
//                    return; // Thoát khỏi hàm để quay lại menu chính
//                default:
//                    System.out.println(ShopValidate.ANSI_RED + "Lựa chọn không hợp lệ, vui lòng nhập N, P, S hoặc E." + ShopValidate.ANSI_RESET);
//            }
//        }
//    }

    private static void addNewStudent() {
        System.out.println(ShopValidate.ANSI_YELLOW + "--- Thêm sinh viên mới ---" + ShopValidate.ANSI_RESET);
        Student newStudent = new Student();
        newStudent.inputData(studentService.getAllStudents());
        boolean isSuccess =  studentService.addNewStudent(newStudent);
        if (isSuccess) {
            System.out.println(ShopValidate.ANSI_GREEN + "Thêm sinh viên thành công!" + ShopValidate.ANSI_RESET);
        }else {
            System.out.println(ShopValidate.ANSI_RED + "Thêm sinh viên thát bại!" + ShopValidate.ANSI_RESET);
        }
    }
//
//    private static void updateStudent() {
//        System.out.print("Nhập mã sinh viên cần sửa: ");
//        String updateId = InputMethod.getString();
//        Student studentToUpdate = findStudentById(updateId);
//
//        if (studentToUpdate == null) {
//            System.out.println(ShopValidate.ANSI_RED + "Không tìm thấy sinh viên với mã: " + updateId + ShopValidate.ANSI_RESET);
//            return;
//        }
//
//        System.out.println(ShopValidate.ANSI_YELLOW + "Tiến hành cập nhật thông tin (Nhập thông tin mới)" + ShopValidate.ANSI_RESET);
//        updateStudentFields(studentToUpdate);
//    }
//
//    private static void updateStudentFields(Student student) {
//        while (true) {
//            System.out.print("Nhập họ và tên mới: ");
//            String nameInput = InputMethod.getString();
//            if (!nameInput.isEmpty()) {
//                student.setFullName(nameInput);
//                break;
//            } else {
//                System.out.println(ShopValidate.ANSI_RED + "Tên không được để trống!" + ShopValidate.ANSI_RESET);
//            }
//        }
//        System.out.println(ShopValidate.ANSI_GREEN + "Cập nhật thông tin thành công!" + ShopValidate.ANSI_RESET);
//    }
//
//    private static void deleteStudent() {
//        System.out.print("Nhập mã sinh viên cần xóa: ");
//        String deleteId = InputMethod.getString();
//        Student studentToDelete = findStudentById(deleteId);
//
//        if (studentToDelete != null) {
//            studentList.remove(studentToDelete);
//            System.out.println(ShopValidate.ANSI_GREEN + "Xóa sinh viên thành công!" + ShopValidate.ANSI_RESET);
//        } else {
//            System.out.println(ShopValidate.ANSI_RED + "Xóa thất bại! Không tìm thấy sinh viên với mã: " + deleteId + ShopValidate.ANSI_RESET);
//        }
//    }
//
//    private static void searchStudent() {
//        System.out.print("Nhập từ khóa (tên hoặc số điện thoại) cần tìm: ");
//        String keyword = InputMethod.getString().toLowerCase();
//
//        boolean isFound = false;
//        System.out.println(ShopValidate.ANSI_YELLOW + "--- Kết quả tìm kiếm ---" + ShopValidate.ANSI_RESET);
//
//        for (Student student : studentList) {
//            if (student.getFullName().toLowerCase().contains(keyword) ||
//                    student.getPhone().contains(keyword)) {
//                if (!isFound) {
//                    printTableHeader();
//                    isFound = true;
//                }
//                student.displayData();
//            }
//        }
//
//        if (isFound) {
//            printTableFooter();
//        } else {
//            System.out.println(ShopValidate.ANSI_RED + "Không tìm thấy sinh viên nào khớp với từ khóa!" + ShopValidate.ANSI_RESET);
//        }
//    }
//
//    private static void sortStudents() {
//        if (studentList.isEmpty()) {
//            System.out.println(ShopValidate.ANSI_YELLOW + "Danh sách trống, không có dữ liệu để sắp xếp!" + ShopValidate.ANSI_RESET);
//            return;
//        }
//
//        studentList.sort(Comparator.comparing(Student::getFullName));
//        System.out.println(ShopValidate.ANSI_GREEN + "Đã sắp xếp sinh viên theo tên (A-Z) thành công!" + ShopValidate.ANSI_RESET);
//        displayAllStudents();
//    }
//
//    // --- CÁC PHƯƠNG THỨC HỖ TRỢ (UTILITIES) ---
//
//    private static Student findStudentById(String id) {
//        for (Student student : studentList) {
//            if (student.getStudentId().equals(id)) {
//                return student;
//            }
//        }
//        return null;
//    }

    private static void printTableHeader() {
        System.out.println("+------------+---------------------------+-----------------+------------+-----------------+---------------------------+");
        System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-15s | %-25s |\n",
                "Mã SV", "Họ Tên", "Ngày Sinh", "Giới Tính", "Số Điện Thoại", "Email");
        System.out.println("+------------+---------------------------+-----------------+------------+-----------------+---------------------------+");
    }

    private static void printTableFooter() {
        System.out.println("+------------+---------------------------+-----------------+------------+-----------------+---------------------------+");
    }
}