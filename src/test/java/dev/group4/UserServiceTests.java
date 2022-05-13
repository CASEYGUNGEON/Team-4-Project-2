package dev.group4;

import dev.group4.aspects.InvalidCredentialException;
import dev.group4.entities.User;
import dev.group4.repos.UserRepo;
import dev.group4.services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTests {
    static User testUser;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Order(1)
    @Test
    public void registerUser() throws InvalidCredentialException {
        //String most likely would be Null or empty quotes, unknown till have a database to test on
        User user = new User("username", "Password!1");
        testUser = userService.registerUser(user);//userRepo.save(user);
        System.out.println(testUser);
        Assertions.assertNotNull(user.getUsername());
    }

    @Order(2)
    @Test
    public void loginTest() throws InvalidCredentialException {
        System.out.println(testUser);
        User user1 = userService.login(testUser);
        Assertions.assertNotNull(user1);
        userRepo.delete(testUser);

    }

}
