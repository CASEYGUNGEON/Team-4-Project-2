package dev.group4.api;

import dev.group4.aspects.InvalidTimeException;
import dev.group4.entities.Potluck;
import dev.group4.repos.PotluckRepo;
import dev.group4.services.PotluckServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PotLuckServiceTests {

    static Potluck testPotluck = null;

    @Autowired
    private PotluckRepo potluckRepo;
    @Autowired
    private PotluckServiceImpl potluckService;

    @Test
    void schedulePotluckTest() throws InvalidTimeException {
        Potluck newPotLuck = new Potluck("first", System.currentTimeMillis() + 1000L, "username",true);
        testPotluck =potluckService.schedulePotluck(newPotLuck);
        Assertions.assertNotEquals("", newPotLuck.getId());
    }

    @Test
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
    void changeTimeTest() throws InvalidTimeException {
        long test = testPotluck.getDateTime();
        testPotluck.setDateTime( System.currentTimeMillis() + 3604000L);
        Potluck retrievedPotLuck = potluckService.changePotluckTime(testPotluck);
        Assertions.assertNotEquals(test, retrievedPotLuck.getDateTime());
    }

    @Test
    void cancelTest() {
        System.out.println(testPotluck);
        Assertions.assertTrue(potluckService.cancelPotluck(testPotluck));
    }

    //negative tests

    @Test
    void registerPotluckForNegativeTest() throws InvalidTimeException {
        Potluck newPotLuck = new Potluck("first", System.currentTimeMillis() + 500L, "username",true);
        testPotluck1 =potluckService.schedulePotluck(newPotLuck);
        Assertions.assertNotEquals("", newPotLuck.getId());
    }

    @Test
    void firstValidationTimeTest()throws InvalidTimeException{

        Potluck newPotLuck = new Potluck("second", System.currentTimeMillis() + 10000L,
                "username",true);
        Assertions.assertThrows(InvalidTimeException.class,
                () -> potluckService.schedulePotluck(newPotLuck),
                "The time you wish to schedule the potluck is within an hour of a currently scheduled Potluck.");
    }

    @Test
    void secondValidationTimeTest()throws InvalidTimeException{

        Potluck newPotLuck = new Potluck("second", 1000L, "username",true);
        Assertions.assertThrows(InvalidTimeException.class,
                () -> potluckService.schedulePotluck(newPotLuck),
                "The time you wish to schedule the potluck has already passed.");
    }

    @Test
    void cancelNegativeTest() {
        Assertions.assertTrue(potluckService.cancelPotluck(testPotluck1));
    }

    @Test
    void noPotluckIdTest()throws NullPointerException{
        Assertions.assertThrows(NullPointerException.class,
               () -> potluckService.getPotluckById(testPotluck.getId()),
                "Potluck id was not found: " + testPotluck.getId());
    }
}