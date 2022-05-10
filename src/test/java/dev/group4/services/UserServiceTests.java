package dev.group4.services;

import dev.group4.entities.User;
import dev.group4.repos.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {
    private User testUser;
    @Autowired
    private UserRepo userRepo;
    @Order(1)
    @Test
    void registerUser() {
        //String most likely would be Null or empty quotes, unknown till have a database to test on
        User user = new User("username", "password");
        userRepo.save(user);
        testUser = user;
        System.out.println(user);
        Assertions.assertNotNull(user.getUsername());

    }
    @Order(2)
    @Test
    void loginWithUsernameAndPassword(User testUser){
        User user1 = userRepo.findUserByUsernameAndPassword(testUser.getUsername(), testUser.getPassword());
        Assertions.assertNotNull(user1);

    }

}
