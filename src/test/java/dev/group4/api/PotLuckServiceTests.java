package dev.group4.api;

import dev.group4.aspects.InvalidCredentialException;
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
    static Potluck testPotluck1 = null;
    static List<Potluck> list1 = null;
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
    void getPotluckByIdTest(){
        Potluck retrievedPotluck = potluckService.getPotluckById(testPotluck.getId());
        System.out.println(retrievedPotluck);
        Assertions.assertEquals(testPotluck.getId(), retrievedPotluck.getId());
    }

    @Test
    @Order(3)
    void getAllPotlucksTest() throws InvalidTimeException {

        list1 = potluckService.getAllPublicPotlucks();
        System.out.println(list1);
        Assertions.assertTrue(list1 != null && !list1.isEmpty());
    }

    @Test
    @Order(5)
    void changeTimeTest() throws InvalidTimeException {
        long test = testPotluck.getDateTime();
        testPotluck.setDateTime( System.currentTimeMillis() + 3604000L);
        Potluck retrievedPotLuck = potluckService.changePotluckTime(testPotluck);
        Assertions.assertNotEquals(test, retrievedPotLuck.getDateTime());
    }

    @Test
    @Order(6)
    void cancelTest() {
        System.out.println(testPotluck);
        Assertions.assertTrue(potluckService.cancelPotluck(testPotluck));
    }

    //negative tests

    @Test
    @Order(7)
    void registerPotluckForNegativeTest() throws InvalidTimeException {
        Potluck newPotLuck = new Potluck("First", System.currentTimeMillis() + 500L, "username",true);
        testPotluck1 =potluckService.schedulePotluck(newPotLuck);
        Assertions.assertNotEquals("", newPotLuck.getId());
    }

    @Test
    @Order(8)
    void firstValidationTimeTest()throws InvalidTimeException{

        Potluck newPotLuck = new Potluck("Second", System.currentTimeMillis() + 10000L,
                "username",true);
        Assertions.assertThrows(InvalidTimeException.class,
                () -> potluckService.schedulePotluck(newPotLuck),
                "The potluck was scheduled but it is within an hour of a currently scheduled Potluck.");
    }

    @Test
    @Order(9)
    void secondValidationTimeTest()throws InvalidTimeException{

        Potluck newPotLuck = new Potluck("Second", 1000L, "username",true);
        Assertions.assertThrows(InvalidTimeException.class,
                () -> potluckService.schedulePotluck(newPotLuck),
                "The potluck was scheduled but the time has passed.");
    }

    @Test
    @Order(10)
    void cancelNegativeTest() {
        Assertions.assertTrue(potluckService.cancelPotluck(testPotluck1));
    }

    @Test
    @Order(11)
    void noPotluckIdTest()throws NullPointerException{
        Assertions.assertThrows(NullPointerException.class,
               () -> potluckService.getPotluckById(testPotluck.getId()),
                "The potluck was found but shouldn't have.");
    }

}