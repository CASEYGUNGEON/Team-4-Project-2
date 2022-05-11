package dev.group4.services;

import dev.group4.entities.User;
import dev.group4.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;



    @Override
    public User registerUser(User user) {

        return this.userRepo.save(user);
    }

    @Override
    public User login(String username, String password) {


        return null;
    }
}
