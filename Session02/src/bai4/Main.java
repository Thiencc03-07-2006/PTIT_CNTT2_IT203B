package bai4;

import bai1.User;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(new User("test1"), new User("test2"));
        users.stream().map(User::getUsername).forEach(System.out::println);
        List<String> names = Arrays.asList("Java", "Python", "C++");
        names.forEach(System.out::println);
        Supplier<User> supplier = User::new;
        User u = supplier.get();
        System.out.println(u.toString());
    }
}
