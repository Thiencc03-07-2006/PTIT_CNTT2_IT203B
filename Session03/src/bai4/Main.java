package bai4;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public record User(String username, String email, String status) {
    }

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("alice", "alice1@gmail.com", "ACTIVE"),
                new User("bob", "bob@gmail.com", "INACTIVE"),
                new User("alice", "alice2@gmail.com", "ACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE")
        );
        users.stream().collect(Collectors.toMap(
                User::username,
                u -> u,
                (u1, u2) -> u1
        )).values().forEach(System.out::println);
    }
}
