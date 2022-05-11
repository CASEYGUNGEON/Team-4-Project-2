package dev.group4.api;

import dev.group4.entities.Potluck;
import dev.group4.services.PotluckServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PotLuckServiceTests {
    PotluckServiceImpl potluckService = new PotluckServiceImpl();
    static Potluck testpotluck = null;

    @Test
    @Order(1)
    void schedulePotluckTest() {
        Potluck newPotluck = new Potluck("First", 2000L, "username",true);
        PotLuckServiceTests.testpotluck = newPotluck;
        potluckService.schedulePotluck(newPotluck);
        System.out.println(testpotluck);
        Assertions.assertNotEquals("", testpotluck.getId());
    }

    @Test
    @Order(2)
    void getAllPotlucksTest() {
        List<Potluck> list = potluckService.getAllPotlucks();
        Assertions.assertNotNull(list.get(0).getId());
    }

    @Test
    @Order(3)
    void changeTimeTest() {
        testpotluck = potluckService.changePotluckTime(testpotluck, 3000);
        Assertions.assertEquals(3000, testpotluck.getDateTime());
    }

    @Test
    @Order(4)
    void cancelTest() {
        Assertions.assertTrue(potluckService.cancelPotluck(testpotluck));
    }
}