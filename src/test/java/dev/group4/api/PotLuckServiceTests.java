package dev.group4.api;

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
    private PotluckService potluckService;

    @Test
    @Order(1)
    void schedulePotluckTest() {
        Potluck newPotLuck = new Potluck("", System.currentTimeMillis() + 1000L, "username",true);
        PotLuckServiceTests.testPotluck = newPotLuck;
        potluckService.schedulePotluck(newPotLuck);
        Assertions.assertNotEquals("", newPotLuck.getId());
    }

    @Test
    @Order(2)
    void getAllPotlucksTest(){
        Potluck test1 = new Potluck("Second", 0L, "username", true);
        Potluck test2 = new Potluck("Third", 0L, "username", true);
        Potluck test3 = new Potluck("Fourth", 0L, "username", true);

        potluckService.schedulePotluck(test1);
        potluckService.schedulePotluck(test2);
        potluckService.schedulePotluck(test3);

        List<Potluck> list1 = potluckService.getAllPotlucks();
        System.out.println(list1);

    }


    @Test
    @Order(3)
    void changeTimeTest(){
        Potluck retrievedPotLuck = testPotluck;
        potluckService.changePotluckTime(retrievedPotLuck, System.currentTimeMillis() + 3000L);
        Assertions.assertEquals(3000L, retrievedPotLuck.getDateTime());
    }
//    @Test
//    @Order(3)
//    void changeTimeTest() {
//
//        Potluck retrievedPotLuck = potluckService.get
//        potluck = potluckService.changePotluckTime(potluck, 3000);
//        Assertions.assertEquals(3000, potluck.getDateTime());
//    }



//    @Test
//    @Order(2)
//    void getAllPotlucksTest(){
//        List<Potluck> list = this.potluckRepo.findAll();
//        System.out.println(list);
//    }


//
//    @Test
//    @Order(2)
//    void getAllPotlucksTest() {
//        List<Potluck> list = potluckService.getAllPotlucks();
//        Assertions.assertNotNull(list.get(0).getId());
//    }
//
//    @Test
//    @Order(3)
//    void changeTimeTest() {
//        potluck = potluckService.changePotluckTime(potluck, 3000);
//        Assertions.assertEquals(3000, potluck.getDateTime());
//    }
//
//    @Test
//    @Order(4)
//    void cancelTest() {
//        Assertions.assertTrue(potluckService.cancelPotluck(potluck));
//    }
}