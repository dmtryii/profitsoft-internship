package com.dmtryii.task.repository;

import com.dmtryii.task.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRepository {
    private static final List<User> users;

    static {
        users = Collections.synchronizedList(new ArrayList<>());
        users.add(new User("admin", "admin@gmail.com", "admin"));
        users.add(new User("dmtryii", "dmtryii@gmail.com","qwerty"));
    }

    public static boolean isValidData(String login, String password) {
        return users.stream()
                    .anyMatch(
                            user -> (user.getUsername().equals(login) || user.getEmail().equals(login))
                                    && user.getPassword().equals(password)
                    );
    }

    public static List<User> getUsers() {
        return users;
    }
}
