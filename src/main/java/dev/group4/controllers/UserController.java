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

    /*As a guest I can create a Potlukk account
    POST {host}/users

    As a guest I can login to a Potlukk account to become a registered User
    GET {host}/users/{user_id}?=username?=password*/

    //TODO ADD ROUTES
}
