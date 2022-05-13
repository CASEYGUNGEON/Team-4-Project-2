package dev.group4.services;

import dev.group4.entities.Item;

public interface ItemService {
    Item registerItem(Item item);
    Item getItemById(String id);
    Item replaceItem(Item item);
    Item updateSupplier(Item item, String supplier);
    boolean deleteItem(Item item);
    boolean deleteItem(String id);
}
