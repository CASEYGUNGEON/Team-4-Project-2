package dev.group4.services;

import dev.group4.aspects.InvalidTimeException;
import dev.group4.entities.Potluck;
import dev.group4.repos.PotluckRepo;
import dev.group4.services.PotluckServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PotLuckServiceTests {

    static Potluck testPotluck = null;

    @Autowired
    private PotluckRepo potluckRepo;
    @Autowired
    private PotluckServiceImpl potluckService;

    @Test
    @Order(1)
    void schedulePotluckTest() throws InvalidTimeException {
        Potluck newPotLuck = new Potluck("First", System.currentTimeMillis() + 1000L, "username",true);
        testPotluck =potluckService.schedulePotluck(newPotLuck);
        Assertions.assertNotEquals("", newPotLuck.getId());
    }

    @Test
    @Order(2)
    void getAllPotlucksTest() throws InvalidTimeException {

        Potluck test1 = new Potluck("Second", System.currentTimeMillis() +10L, "username", true);
        Potluck test2 = new Potluck("Third", System.currentTimeMillis() +3600012L, "username", true);
        Potluck test3 = new Potluck("Fourth",System.currentTimeMillis() + 7200013L, "username", false);

        potluckService.schedulePotluck(test1);
        potluckService.schedulePotluck(test2);
        potluckService.schedulePotluck(test3);

        List<Potluck> list1 = potluckService.getAllPublicPotlucks();
        Assertions.assertEquals(3,list1.size()); //includes first visible from test before

    }


    @Test
    @Order(3)
    void changeTimeTest() throws InvalidTimeException {
        long test = testPotluck.getDateTime();
        Potluck retrievedPotLuck = potluckService.changePotluckTime(testPotluck, System.currentTimeMillis() + 3604000L);
        Assertions.assertNotEquals(test, retrievedPotLuck.getDateTime());
    }

    @Test
    @Order(4)
    void cancelTest() {
        System.out.println(testPotluck);
        Assertions.assertTrue(potluckService.cancelPotluck(testPotluck));
    }
}