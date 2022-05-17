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
    @Disabled
    @Order(1)
    void schedulePotluckTest() throws InvalidTimeException {
        Potluck newPotLuck = new Potluck("First", System.currentTimeMillis() + 1000L, "username",true);
        testPotluck =potluckService.schedulePotluck(newPotLuck);
        System.out.println(testPotluck);
        Assertions.assertNotEquals("", newPotLuck.getId());
    }

    @Test
    @Disabled
    @Order(2)
    void getPotluckByIdTest(){
        Potluck retrievedPotluck = potluckService.getPotluckById(testPotluck.getId());
        System.out.println(retrievedPotluck);
        Assertions.assertEquals(testPotluck.getId(), retrievedPotluck.getId());
    }

    @Test
    @Disabled
    @Order(3)
    void getAllPotlucksTest() {
        list1 = potluckService.getAllPublicPotlucks();
        System.out.println(list1);
        Assertions.assertTrue(list1 != null && !list1.isEmpty());
    }

    @Test
    @Order(4)
    void getPotlucksByCreatorTest(){
        List<Potluck> retrievedPotlucks = potluckService.getPotlucksByCreator(testPotluck.getCreatorId());
        System.out.println(retrievedPotlucks);
        Assertions.assertNotEquals("", testPotluck.getCreatorId());
    }

    @Test
    @Disabled
    @Order(5)
    void changeTimeTest() throws InvalidTimeException {
        long test = testPotluck.getDateTime();
        testPotluck.setDateTime( System.currentTimeMillis() + 3604000L);
        Potluck retrievedPotLuck = potluckService.changePotluckTime(testPotluck);
        Assertions.assertNotEquals(test, retrievedPotLuck.getDateTime());
    }

    @Test
    @Disabled
    @Order(6)
    void cancelTest() {
        System.out.println(testPotluck);
        Assertions.assertTrue(potluckService.cancelPotluck(testPotluck));
    }
    //Negative Tests Start Here

    @Test
    @Disabled
    @Order(7)
    void registerPotluckForNegativeTest() throws InvalidTimeException {
        Potluck newPotLuck = new Potluck("First", System.currentTimeMillis() + 500L, "username",true);
        testPotluck1 =potluckService.schedulePotluck(newPotLuck);
        Assertions.assertNotEquals("", newPotLuck.getId());
    }

    @Test
    @Disabled
    @Order(8)
    void secondValidationTimeTest(){

        Potluck newPotLuck = new Potluck("Second", 1000L, "username",true);
        Assertions.assertThrows(InvalidTimeException.class, () -> potluckService.schedulePotluck(newPotLuck), "The potluck was scheduled but the time has passed.");
    }

    @Test
    @Disabled
    @Order(9)
    void cancelNegativeTest() {
        Assertions.assertTrue(potluckService.cancelPotluck(testPotluck1));
    }

    @Test
    @Disabled
    @Order(10)
    void noPotluckIdTest()throws NullPointerException{
        Assertions.assertThrows(NullPointerException.class,
               () -> potluckService.getPotluckById(testPotluck.getId()),
                "The potluck was found but shouldn't have.");
    }

}