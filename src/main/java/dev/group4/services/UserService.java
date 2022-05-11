package dev.group4.services;

import dev.group4.aspects.InvalidUserNameException;
import dev.group4.entities.User;

public interface UserService {
    User registerUser(User user) throws InvalidUserNameException;
    public User login(User user);
}
