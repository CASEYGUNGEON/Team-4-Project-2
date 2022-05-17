package dev.group4.services;

import dev.group4.aspects.InvalidCredentialException;
import dev.group4.aspects.InvalidTimeException;
import dev.group4.entities.Item;

import java.util.List;

public interface ItemService {
    /**
     * A method to register an item to a Potluck
     * @param item the Item to register
     * @return the registered item
     * @throws InvalidCredentialException The item could not be registered.
     */
    Item registerItem(Item item) throws InvalidCredentialException;

    /**
     *A method to get an item with a specific id
     * @param id the ID for the item to find
     * @return the specific Item
     * @throws NullPointerException The specified item could not be found.
     **/
    Item getItemById(String id);

    /**
     *A method to get items of a specific Potluck
     * @param potluckId the ID of the Potluck
     * @return the items within the specified potluck
     **/
    List<Item> getItemsByPotluck(String potluckId);

    /**
     *A method to replace a specific item
     * @param item is the Item to be replaced
     * @return the specific item that was used for replacement
     **/
    Item replaceItem(Item item);

    /**
     *A method to update the supplier of a specific item
     * @param item the item that you will be updating
     * @param supplier the supplier that will be replacing the existing supplier in the item
     * @return the item with the supplier updated
     **/
    Item updateSupplier(Item item, String supplier);

    /**
     *A method to delete a specific item
     * @param item is the Item you intend on deleting
     * @return true or false if the item was deleted
     **/
    boolean deleteItem(Item item);

    /**
     *A method to delete a specific item
     * @param id is the ID of the item you intend on deleting
     * @return true or false if the item was deleted
     **/
    boolean deleteItem(String id);
}
