package dev.group4.services;

import dev.group4.entities.Item;
import dev.group4.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public Item registerItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public Item getItemById(String id) {
        Optional<Item> item = itemRepo.findById(id);
        if(item.isPresent())
            return item.get();
        throw new NullPointerException("Item not found, id: " + id);
    }

    @Override
    public Item replaceItem(Item item) {
        return itemRepo.save(item);
    }


    @Override
    public Item updateSupplier(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public boolean deleteItem(Item item) {
        return deleteItem(item.getId());
    }

    @Override
    public boolean deleteItem(String id) {
        Item item = getItemById(id);
        if(item != null) {
            itemRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
