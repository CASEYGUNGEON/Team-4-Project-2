package dev.group4.services;

import dev.group4.aspects.InvalidCredentialException;
import dev.group4.entities.Item;

import java.util.List;

public interface ItemService {
    Item registerItem(Item item) throws InvalidCredentialException;
    Item getItemById(String id);
    List<Item> getItemsByPotluck(String potluckId);
    Item replaceItem(Item item);
    Item updateSupplier(Item item, String supplier);
    boolean deleteItem(Item item);
    boolean deleteItem(String id);
}
