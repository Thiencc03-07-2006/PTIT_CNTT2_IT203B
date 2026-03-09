package bai1;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        //Khởi tạo một đối tượng User mới với các giá trị mặc định.
        Supplier<User> createUser = () -> new User("test", "admin123", "ADMIN");
        //Kiểm tra xem một User có phải là Admin hay không (trả về true/false)
        Predicate<User> isAdmin = e -> e.getRole().equals("ADMIN");
        //Chuyển đổi một đối tượng User thành một chuỗi String chứa thông tin username.
        Function<User, String> getUsername = e -> e.getUsername();
        //In thông tin chi tiết của User ra màn hình Console.
        Consumer<User> toString = e -> System.out.println(e.toString());
        User user = createUser.get();
        System.out.println(isAdmin.test(user));
        System.out.println(getUsername.apply(user));
        toString.accept(user);
    }
}
