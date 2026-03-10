package bai3;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    static List<Main.User> users = new ArrayList<>();

    public void add(Main.User user) {
        users.add(user);
    }

    Optional<Main.User> findUserByUsername(String username) {
        return users.stream().filter(e -> e.username().equals(username)).findFirst();
    }
}
