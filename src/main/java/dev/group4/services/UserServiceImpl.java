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
        if(userRepo.findById(username).isPresent())
            throw new InvalidUserNameException("Username is already in use. Username : "+username);
        if(username.length()==0 || username.length()>20)
            throw new InvalidUserNameException("Username is not the required length. Current length:"+username.length());
        if(password.length()==0 || password.length()>20)
            throw new InvalidUserNameException("Password is not the required length. Current length:"+password.length());
        if(characterValidation(password))
            return this.userRepo.save(user);
        else{
            throw new InvalidUserNameException("The Password did not contain the required special characters.");
        }
    }

    private boolean characterValidation(String password){
        boolean special = false, capital = false, number = false;
        for(int i=0 ; i<password.length();i++) {
            if (Character.isDigit(password.charAt(i)))
                number = true;
            else if (Character.isUpperCase(password.charAt(i)))
                capital = true;
            else if (!Character.isLetterOrDigit(password.charAt(i)))
                special = true;
        }
        return special && capital && number;
    }

    @Override
    public User login(User user) throws InvalidUserNameException {

        String username = user.getUsername();

        if(userRepo.findById(username).isPresent()) {
            String password = userRepo.findById(username).get().getPassword();
            if(password.equals(user.getPassword()))
                return user;
        }
        throw new InvalidUserNameException("Login was invalid");
    }
}
