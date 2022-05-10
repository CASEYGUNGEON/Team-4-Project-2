package dev.group4.services;

import dev.group4.entities.Item;
import dev.group4.entities.StatusType;
import dev.group4.repos.ItemRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemServiceTests {

    @Autowired
    private ItemRepo itemRepo;


    @Test
    void createItem() {
        Item item = new Item ("buttchecks","Pizza", StatusType.WANTED, "Ron from Accounting","PotluckId");
    }
}
