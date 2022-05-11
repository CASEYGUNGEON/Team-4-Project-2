package dev.group4.services;

import dev.group4.aspects.InvalidUserNameException;
import dev.group4.entities.User;
import dev.group4.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;


    @Override
    public User registerUser(User user) throws InvalidUserNameException {
        String username = user.getUsername();
        String password = user.getPassword();
        if(username.length()==0 || username.length()>20)
            throw new InvalidUserNameException();

        //TODO throw an exception in service username and password==
        return this.userRepo.save(user);
    }

    @Override
    public User login(User user) {
        //TODO throw an exception if you can't login
        String username = user.getUsername();
        String password = user.getPassword();
        return null;
    }
}
