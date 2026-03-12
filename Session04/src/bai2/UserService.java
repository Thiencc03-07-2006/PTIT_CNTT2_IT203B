package bai2;

public class UserService {
    boolean checkRegistrationAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Kiểm tra dữ liệu không hợp lệ (tuổi âm)");
        }
        return age >= 18;
    }
}
