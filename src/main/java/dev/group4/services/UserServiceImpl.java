package dev.group4.services;

import dev.group4.aspects.InvalidCredentialException;
import dev.group4.dtos.UserInfo;
import dev.group4.entities.User;
import dev.group4.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Component
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public User registerUser(User user) throws InvalidCredentialException {
        String username = user.getUsername();
        String password = user.getPassword();
        if (userRepo.findById(username).isPresent())
            throw new InvalidCredentialException("Username is already in use. Username : " + username);
        if (username.length() == 0 || username.length() > 20)
            throw new InvalidCredentialException("Username is not the required length. Current length:" + username.length());
        if (password.length() == 0 || password.length() > 20)
            throw new InvalidCredentialException("Password is not the required length. Current length:" + password.length());
        if (characterValidation(password))
            return this.userRepo.save(user);
        else {
            throw new InvalidCredentialException("The Password did not contain the required special characters.");
        }
    }

    private boolean characterValidation(String password) {
        boolean special = false, capital = false, number = false;
        for (int i = 0; i < password.length(); i++) {
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
    public UserInfo login(String authorization) throws InvalidCredentialException {

        String username = authorization.substring(0,authorization.indexOf(':'));
        String pass = authorization.substring(authorization.indexOf(':')+1);
        User user = new User(username,pass);

        if (userRepo.findById(username).isPresent()) {
            String password = userRepo.findById(username).get().getPassword();
            if (user.getPassword().equals(password))
                return new UserInfo(Base64.getEncoder()
                        .encodeToString((username+":"+password)
                                .getBytes(StandardCharsets.UTF_8)));
        }
        throw new InvalidCredentialException("Login was invalid");

    }
}


