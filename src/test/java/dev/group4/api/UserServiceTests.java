package dev.group4.api;

import dev.group4.aspects.InvalidCredentialException;
import dev.group4.dtos.UserInfo;
import dev.group4.entities.User;
import dev.group4.repos.UserRepo;
import dev.group4.services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

@SpringBootTest
public class UserServiceTests {
    static User testUser = new User("username13", "Password22!");
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @Test
    public void registerUser() throws InvalidCredentialException {
        userService.registerUser(testUser);
        System.out.println(testUser);
        Assertions.assertNotNull(testUser.getUsername());
        userRepo.delete(testUser);
    }

    @Test
    public void loginTest() throws InvalidCredentialException {
        userService.registerUser(testUser);
        String authorization = testUser.getUsername()+":"+testUser.getPassword();
        UserInfo user1 = userService.login(authorization);
        Assertions.assertNotNull(user1.getAuthorization());
        userRepo.delete(testUser);
    }

    ////Negative Tests Start Here
    @Test
    public void BlankUsernameOrPasswordOnCreateUserTest(){
        User wrongUser = new User("","ProperPassword2!");
        Assertions.assertThrows(InvalidCredentialException.class , () -> userService.registerUser((wrongUser)), "This method SHOULD have thrown but did not");
    }

    @Test
    public void PasswordTooLongTest(){
        User wrongUser = new User("properusername","tooLONNNNNGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG1!!!!!!!!!!!!!!!!!!!!!!!");
        Assertions.assertThrows(InvalidCredentialException.class , () -> userService.registerUser((wrongUser)), "This method SHOULD have thrown but did not");
    }
    @Test
    public void IncorrectPasswordTest(){
        User wrongPassword = new User("username", "wrongpassword");
        String authorization = testUser.getUsername()+":"+testUser.getPassword();
        Assertions.assertThrows(InvalidCredentialException.class, () -> userService.login(authorization));

    }
}
