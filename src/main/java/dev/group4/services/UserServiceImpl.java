package dev.group4.services;

import dev.group4.entities.User;

public class UserServiceImpl implements UserService{
    @Override
    public User registerUser(User user) {
        System.out.println("Adding a sout to registerUser in the UserServiceImpl to test pull");

        return null;
    }

    @Override
    public User login(String username, String password) {
        return null;
    }
}
