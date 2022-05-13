package dev.group4.api;

import dev.group4.aspects.InvalidCredentialException;
import dev.group4.entities.Item;
import dev.group4.entities.Potluck;
import dev.group4.entities.StatusType;
import dev.group4.repos.ItemRepo;
import dev.group4.services.ItemServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;


@SpringBootTest
public class ItemServiceTests {
    @Autowired
    private ItemServiceImpl itemService;
    @Autowired
    private ItemRepo itemRepo;
    //THIS IS A HARD-WIRED VALUE FOR THE POTLUCK ID, YOU WILL CURRENTLY NEED TO OVERWRITE THE POTLUCK ID WITH AN EXISTING VALUE FROM YOUR OWN DATABASE
    static Item item = new Item ("notgeneratedid","IceCream", StatusType.WANTED, "Ron from Accounting","1322f481-5b03-49a2-84d1-7a80e967c1e3");


    @Test
    void registerTest() throws InvalidCredentialException {
        itemService.registerItem(item);
        Item testItem = this.itemRepo.findItemByDescription("IceCream");
        Assertions.assertNotEquals("notgeneratedid",testItem.getId());
        System.out.println(testItem);
        itemRepo.delete(testItem);
    }

    @Test
    void getByIdTest() throws InvalidCredentialException {
        Item testItem = itemService.registerItem(item);
        Item temp = itemService.getItemById(testItem.getId());
        Assertions.assertNotNull(temp);
        itemRepo.delete(testItem);
    }

    @Test
    void replaceTest() throws InvalidCredentialException {
        Item testItem = itemService.registerItem(item);
        testItem.setDescription("Pizza Ball");
        System.out.println(testItem);
        Assertions.assertEquals((testItem.getDescription()), "Pizza Ball");
        itemRepo.delete(testItem);

    }

    @Test
    void supplierTest() throws InvalidCredentialException {
        Item testItem = itemService.registerItem(item);

        item = itemService.updateSupplier(testItem, "Your Mom");
        Assertions.assertEquals("Your Mom", item.getSupplier());
        itemRepo.delete(testItem);
    }

    @Test
    void deleteTest() {
        Assertions.assertTrue(itemService.deleteItem(item));
    }


    ////SHOULD BE WRONG TESTS BEGIN HERE////////////SHOULD BE WRONG TESTS BEGIN HERE//////////////////////SHOULD BE WRONG TESTS BEGIN HERE/////////////////////

    @Test
    void SQLException(){
        Item badItem = new Item ("notgeneratedid","IceCream", StatusType.WANTED, "Ron from Accounting","memes");
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> itemService.registerItem(badItem));
    }

    @Test
    void BlankDescription(){
        Item badItem = new Item ("notgeneratedid","", StatusType.WANTED, "Ron from Accounting","1322f481-5b03-49a2-84d1-7a80e967c1e3");
        Assertions.assertThrows(InvalidCredentialException.class, () -> itemService.registerItem(badItem));

    }


}
