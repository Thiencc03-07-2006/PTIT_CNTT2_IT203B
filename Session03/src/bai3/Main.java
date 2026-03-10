package bai3;

import java.util.Optional;

public class Main {

    public record User(String username, String email, String status) {
    }

    public static void main(String[] args) {

        UserRepository repository = new UserRepository();
        repository.add(new User("alice", "alice@gmail.com", "ACTIVE"));
        repository.add(new User("bob", "bob@yahoo.com", "INACTIVE"));
        repository.add(new User("alice", "alicee@gmail.com", "INACTIVE"));
        Optional<User> user = repository.findUserByUsername("alice");
        user.ifPresent(e -> System.out.println("Welcome " + e.username()));
        System.out.println(user.map(e -> "Welcome " + e.username()).orElse("Guest login"));
    }
}
