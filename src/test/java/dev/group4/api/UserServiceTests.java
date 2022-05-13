package dev.group4.api;

import dev.group4.aspects.InvalidCredentialException;
import dev.group4.entities.User;
import dev.group4.repos.UserRepo;
import dev.group4.services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {
    static User testUser = new User("username13", "Password22!");
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Test
    public void registerUser() throws InvalidCredentialException {
        //String most likely would be Null or empty quotes, unknown till have a database to test on
        userService.registerUser(testUser);//userRepo.save(user);
        System.out.println(testUser);
        Assertions.assertNotNull(testUser.getUsername());
        userRepo.delete(testUser);
    }

    @Test
    public void loginTest() throws InvalidCredentialException {
        userService.registerUser(testUser);
        User user1 = userService.login(testUser);
        Assertions.assertNotNull(user1);
        userRepo.delete(testUser);

    }

    ////SHOULD BE WRONG TESTS BEGIN HERE////////////SHOULD BE WRONG TESTS BEGIN HERE//////////////////////SHOULD BE WRONG TESTS BEGIN HERE/////////////////////
    @Test
    public void BlankUsernameOrPasswordOnCreateUserTest() throws InvalidCredentialException {
        User wrongUser = new User("","ProperPassword2!");
        Assertions.assertThrows(InvalidCredentialException.class , () -> userService.registerUser((wrongUser)), "This method SHOULD have thrown but did not");
    }

    @Test
    public void PasswordTooLongTest() throws InvalidCredentialException{
        User wrongUser = new User("properusername","tooLONNNNNGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG1!!!!!!!!!!!!!!!!!!!!!!!");
        Assertions.assertThrows(InvalidCredentialException.class , () -> userService.registerUser((wrongUser)), "This method SHOULD have thrown but did not");
    }
}
