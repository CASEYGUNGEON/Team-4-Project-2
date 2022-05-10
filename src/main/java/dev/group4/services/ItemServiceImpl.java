package dev.group4.services;

import dev.group4.entities.Item;
import dev.group4.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepo itemRepo;

    @Override
    public Item registerItem(Item item) {
        return null;
    }

    @Override
    public Item getItemById(String id) {
        return null;
    }

    @Override
    public Item replaceItem(Item item) {
        return null;
    }

    @Override
    public Item updateSupplier(String supplier) {
        return null;
    }

    @Override
    public boolean deleteItem(String id) {
        return false;
    }
}
