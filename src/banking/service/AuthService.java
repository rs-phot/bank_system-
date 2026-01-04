package banking.service;

import banking.model.User;
import java.util.ArrayList;
import java.util.List;

public class AuthService {

    private List<User> users;

    public AuthService() {
        users = new ArrayList<>();
    }

    public void register(User user) {
        users.add(user);
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}

