package dev.group4.api;

import dev.group4.entities.Item;
import dev.group4.entities.Potluck;
import dev.group4.entities.StatusType;
import dev.group4.repos.ItemRepo;
import dev.group4.services.ItemServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class ItemServiceTests {
    @Autowired
    private ItemServiceImpl itemService;
    @Autowired
    private ItemRepo itemRepo;
    //THIS IS A HARD-WIRED VALUE FOR THE POTLUCK ID, YOU WILL CURRENTLY NEED TO OVERWRITE THE POTLUCK ID WITH AN EXISTING VALUE FROM YOUR OWN DATABASE
    static Item item = new Item ("buttchecks","IceCream", StatusType.WANTED, "Ron from Accounting","1322f481-5b03-49a2-84d1-7a80e967c1e3");


    @Test
    void registerTest() {
        itemService.registerItem(item);
        Item testItem = this.itemRepo.findItemByDescription("IceCream");
        Assertions.assertNotEquals("buttchecks",testItem.getId());
        itemRepo.delete(item);
    }

    @Test
    void getByIdTest() {
        Item temp = itemService.getItemById(item.getId());
        Assertions.assertNotNull(temp);
    }

    @Test

    void replaceTest() {
        item.setDescription("Ice cream");

    }

    @Test

    void supplierTest() {
        item = itemService.updateSupplier(item);
        Assertions.assertEquals("Your mom", item.getSupplier());
    }

    @Test
    @Order(5)
    void deleteTest() {
        Assertions.assertTrue(itemService.deleteItem(item));
    }
}
