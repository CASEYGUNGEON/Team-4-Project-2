package dev.group4.controllers;

import dev.group4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //TODO ADD ROUTES
}
