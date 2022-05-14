package dev.group4.controllers;

import dev.group4.aspects.InvalidCredentialException;
import dev.group4.aspects.LoggingAspect;
import dev.group4.aspects.Secured;
import dev.group4.dtos.UserInfo;
import dev.group4.entities.User;
import dev.group4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;

@Component
@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User registerNewUser(@RequestBody User user) throws InvalidCredentialException {
            return this.userService.registerUser(user);
    }

    @GetMapping("/users")
    @Secured
    public UserInfo login() throws InvalidCredentialException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        String authorization = request.getHeader("Authorization");
        authorization = new String(Base64.getDecoder().decode(authorization.substring(authorization.indexOf(' ') + 1)));
        return this.userService.login(authorization);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleInvalidTimeException(Throwable e){
        LoggingAspect.LogError(e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage().substring(e.getMessage().indexOf(":")+1));
    }

}
