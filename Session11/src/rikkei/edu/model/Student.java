package rikkei.edu.model;

import rikkei.edu.util.InputMethod;
import rikkei.edu.validate.ShopValidate;

import java.time.LocalDate;
import java.util.List;

public class Student {
    private String studentId;
    private String fullName;
    private LocalDate dob;
    private Gender gender;
    private String phone;
    private String email;

    public Student() {
    }

    public Student(String studentId, String fullName, LocalDate dob, Gender gender, String phone, String email) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    // --- Getters and Setters ---
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // --- Phương thức Nhập dữ liệu ---
    public void inputData(List<Student> studentList) {
        // 1. Nhập Mã SV (Không trùng lặp, không rỗng)
        while (true) {
            System.out.print("Nhập mã sinh viên: ");
            String idInput = InputMethod.getString();
            if (idInput.isEmpty()) {
                System.out.println(ShopValidate.ANSI_RED + "Mã sinh viên không được để trống!" + ShopValidate.ANSI_RESET);
                continue;
            }
            boolean isExist = studentList.stream().anyMatch(s -> s.getStudentId().equals(idInput));
            if (isExist) {
                System.out.println(ShopValidate.ANSI_RED + "Mã sinh viên đã tồn tại! Vui lòng nhập lại." + ShopValidate.ANSI_RESET);
            } else {
                this.studentId = idInput;
                break;
            }
        }

        // 2. Nhập Họ Tên
        while (true) {
            System.out.print("Nhập họ và tên: ");
            String nameInput = InputMethod.getString();
            if (nameInput.isEmpty()) {
                System.out.println(ShopValidate.ANSI_RED + "Họ tên không được để trống!" + ShopValidate.ANSI_RESET);
            } else {
                this.fullName = nameInput;
                break;
            }
        }

        // 3. Nhập Ngày Sinh
        while (true) {
            System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
            String dobInput = InputMethod.getString();
            LocalDate parsedDate = ShopValidate.parseDate(dobInput);
            if (parsedDate != null) {
                this.dob = parsedDate;
                break;
            } else {
                System.out.println(ShopValidate.ANSI_RED + "Định dạng ngày tháng không hợp lệ!" + ShopValidate.ANSI_RESET);
            }
        }

        // 4. Nhập Giới Tính
        while (true) {
            System.out.print("Nhập giới tính (1: Nam, 2: Nữ, 3: Khác): ");
            int genderInput = InputMethod.getInteger();
            if (genderInput == 1) { this.gender = Gender.MALE; break; }
            else if (genderInput == 2) { this.gender = Gender.FEMALE; break; }
            else if (genderInput == 3) { this.gender = Gender.OTHER; break; }
            else {
                System.out.println(ShopValidate.ANSI_RED + "Lựa chọn không hợp lệ, vui lòng chọn 1, 2 hoặc 3." + ShopValidate.ANSI_RESET);
            }
        }

        // 5. Nhập Số Điện Thoại
        while (true) {
            System.out.print("Nhập số điện thoại (10 số, bắt đầu bằng 0): ");
            String phoneInput = InputMethod.getString();
            if (ShopValidate.isValidPhone(phoneInput)) {
                this.phone = phoneInput;
                break;
            } else {
                System.out.println(ShopValidate.ANSI_RED + "Số điện thoại không đúng định dạng!" + ShopValidate.ANSI_RESET);
            }
        }

        // 6. Nhập Email
        while (true) {
            System.out.print("Nhập email: ");
            String emailInput = InputMethod.getString();
            if (ShopValidate.isValidEmail(emailInput)) {
                this.email = emailInput;
                break;
            } else {
                System.out.println(ShopValidate.ANSI_RED + "Email không đúng định dạng!" + ShopValidate.ANSI_RESET);
            }
        }
    }

    // --- Phương thức Xuất dữ liệu (để hiển thị dạng bảng) ---
    public void displayData() {
        String dobString = this.dob.format(ShopValidate.DATE_FORMATTER);
        String genderString = (this.gender == Gender.MALE) ? "Nam" : (this.gender == Gender.FEMALE) ? "Nữ" : "Khác";

        System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-15s | %-25s |\n",
                this.studentId, this.fullName, dobString, genderString, this.phone, this.email);
    }
}