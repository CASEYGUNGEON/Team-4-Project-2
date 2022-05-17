package dev.group4.api;

import dev.group4.aspects.InvalidCredentialException;
import dev.group4.aspects.InvalidTimeException;
import dev.group4.entities.Item;
import dev.group4.entities.Potluck;
import dev.group4.entities.StatusType;
import dev.group4.entities.User;
import dev.group4.repos.ItemRepo;
import dev.group4.repos.PotluckRepo;
import dev.group4.repos.UserRepo;
import dev.group4.services.ItemServiceImpl;
import dev.group4.services.PotluckServiceImpl;
import dev.group4.services.UserServiceImpl;
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
    @Autowired
    private PotluckServiceImpl potluckService;
    @Autowired
    private PotluckRepo potluckRepo;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepo userRepo;

    static User testUser;
    static Potluck testPotluck;




    //THIS IS A HARD-WIRED VALUE FOR THE POTLUCK ID, YOU WILL CURRENTLY NEED TO OVERWRITE THE POTLUCK ID WITH AN EXISTING VALUE FROM YOUR OWN DATABASE
    static Item item = new Item ("notgeneratedid","IceCream", "WANTED", "Ron from Accounting","1322f481-5b03-49a2-84d1-7a80e967c1e3");




    @Test
    @Disabled
    void registerTest() throws InvalidCredentialException {
        itemService.registerItem(item);
        Item testItem = this.itemRepo.findItemByDescription("IceCream");
        Assertions.assertNotEquals("notgeneratedid",testItem.getId());
        System.out.println(testItem);
        itemRepo.delete(testItem);
    }


    @Test
    @Disabled
    void registerTestCreatingUserAndPotluck() throws InvalidCredentialException, InvalidTimeException {
        User user = new User ("UsernameUnique!", "Password123!");
        testUser= userService.registerUser(user);

        Potluck potluck = new Potluck("first",System.currentTimeMillis() + 1000000000L, testUser.getUsername(), true);
        testPotluck = potluckService.schedulePotluck(potluck);

        Item testItem = new Item ("frist","Jello","WANTED","Stacey's Mom", testPotluck.getId());
        testItem = itemService.registerItem(testItem);
        Assertions.assertNotEquals("frist", item.getId());

        itemRepo.delete(item);
        potluckRepo.delete(testPotluck);
        userRepo.delete(testUser);
    }

    @Test
    @Disabled
    void getByIdTest() throws InvalidCredentialException {
        Item testItem = itemService.registerItem(item);
        Item temp = itemService.getItemById(testItem.getId());
        Assertions.assertNotNull(temp);
        itemRepo.delete(testItem);
    }

    @Test
    @Disabled
    void replaceTest() throws InvalidCredentialException {
        Item testItem = itemService.registerItem(item);
        testItem.setDescription("Pizza Ball");
        System.out.println(testItem);
        Assertions.assertEquals((testItem.getDescription()), "Pizza Ball");
        itemRepo.delete(testItem);
    }

    @Test
    @Disabled
    void supplierTest() throws InvalidCredentialException {
        Item testItem = itemService.registerItem(item);

        item = itemService.updateSupplier(testItem, "Your Mom");
        Assertions.assertEquals("Your Mom", item.getSupplier());
        itemRepo.delete(testItem);
    }

    @Test
    @Disabled
    void deleteTest() throws InvalidCredentialException {
        Item testItem = itemService.registerItem(item);
        Assertions.assertTrue(itemService.deleteItem(testItem));
    }
    ////Negative Tests Here

    @Test
    @Disabled
    void SQLException(){
        Item badItem = new Item ("notgeneratedid","IceCream", "WANTED", "Ron from Accounting","memes");
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> itemService.registerItem(badItem));
    }

    @Test
    @Disabled
    void BlankDescription(){
        Item badItem = new Item ("notgeneratedid","", "WANTED", "Ron from Accounting","1322f481-5b03-49a2-84d1-7a80e967c1e3");
        Assertions.assertThrows(InvalidCredentialException.class, () -> itemService.registerItem(badItem));
    }

}
