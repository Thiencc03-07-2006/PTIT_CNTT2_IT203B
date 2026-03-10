package bai5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public record User(String username, String email, String status) {
    }

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("alexander", "a@gmail.com", "ACTIVE"),
                new User("bob", "b@gmail.com", "INACTIVE"),
                new User("charlotte", "c@gmail.com", "ACTIVE"),
                new User("Benjamin", "d@gmail.com", "ACTIVE"),
                new User("tom", "t@gmail.com", "ACTIVE")
        );
        users.stream().sorted(Comparator.comparingInt((User e) -> e.username().length()).reversed())
                .limit(3)
                .forEach(System.out::println);
    }
}
