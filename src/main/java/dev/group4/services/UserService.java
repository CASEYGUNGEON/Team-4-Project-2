package dev.group4.services;

import dev.group4.entities.User;

public interface UserService {
    User registerUser(User user);
    User login(String username,String password);
}
