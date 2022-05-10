package dev.group4.services;

import dev.group4.entities.Potluck;
import dev.group4.services.PotluckServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PotLuckServiceTests {
    PotluckServiceImpl potluckService = new PotluckServiceImpl();
    Potluck potluck = new Potluck("", "aaaa", 2000,);

    @Test
    @Order(1)
    void schedulePotluckTest() {
        potluck = potluckService.schedulePotluck(potluck);
        Assertions.assertNotEquals("", potluck.getId());
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
        potluck = potluckService.changePotluckTime(potluck, 3000);
        Assertions.assertEquals(3000, potluck.getDateTime());
    }

    @Test
}