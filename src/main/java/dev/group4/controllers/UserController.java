package dev.group4.controllers;

import dev.group4.aspects.InvalidCredentialException;
import dev.group4.entities.User;
import dev.group4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User registerNewUser(@RequestBody User user){
        try {
            return this.userService.registerUser(user);
        } catch (InvalidCredentialException e) {
            throw new RuntimeException(e);
            //return null;
        }
    }

    @GetMapping("/users")
    public User login(@RequestBody User user) throws InvalidCredentialException {
        return this.userService.login(user);
    }

}
