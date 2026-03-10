package bai2;

import java.util.Arrays;
import java.util.List;

public class Main {
    public record User(String username, String email, String status) {
    }

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("alice", "alice@gmail.com", "ACTIVE"),
                new User("bob", "bob@yahoo.com", "INACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE"));
        users.stream().filter(e -> e.email.endsWith("gmail.com")).map(User::username).forEach(System.out::println);
    }

}
