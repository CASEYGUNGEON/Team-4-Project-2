package dev.group4.services;

import dev.group4.entities.Item;
import dev.group4.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.MissingResourceException;
import java.util.Optional;

@Component
@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepo itemRepo;

    //TODO As a whole, needs more thrown exceptions

    @Override
    public Item registerItem(Item item) throws IllegalArgumentException{
        if (item.getDescription().length() == 0){
            throw new IllegalArgumentException("Description cannot be empty");
        }

        return this.itemRepo.save(item);
    }

    @Override
    public Item getItemById(String id) throws RuntimeException {
        Optional<Item> possibleItem = this.itemRepo.findById(id);

        if(possibleItem.isPresent()){
            return possibleItem.get();
        }
        else {
            throw new RuntimeException("No Item was found with that Id");
        }



    }

    @Override
    public Item replaceItem(Item item) {
        Item itemToUpdate = this.itemRepo.findById(item.getId()).get();
        itemToUpdate.setDescription(item.getDescription());
        //TODO Is this only asking to replace the item as in, only what item is being brought, or a "Replace everything not the ID";


        return this.itemRepo.save(itemToUpdate);
    }


    @Override
    public Item updateSupplier(Item item, String supplier) {
        Item itemToUpdate = this.itemRepo.findById(item.getId()).get();
        itemToUpdate.setSupplier(supplier);
        return this.itemRepo.save(itemToUpdate);
    }

    @Override
    public boolean deleteItem(Item item) {
        return deleteItem(item.getId());
    }

    @Override
    public boolean deleteItem(String id) {
        return false;
    }
}
