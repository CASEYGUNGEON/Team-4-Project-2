package dev.group4.controllers;


import dev.group4.aspects.Secured;
import dev.group4.entities.Item;
import dev.group4.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Component
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Secured
    @PostMapping("{host}/potlucks/{potluck_id}/items")
    public Item addItem(@RequestBody Item item,@PathVariable String potluck_id){
        item.setPotluckId(potluck_id);
        return itemService.registerItem(item);
    }

    @Secured
    @DeleteMapping("{host}/potlucks/{potluck_id}/items")
    public boolean deleteItem(@RequestBody Item item,  @PathVariable String potluck_id){
        item.setPotluckId(potluck_id);
        return itemService.deleteItem(item);
    }

/*
    @PostMapping(" {host}/potlucks/{potluck_id}/items")
    public Item addGuestItem
    As a guest I can add an item to a potluck, put down my name as the supplier
    POST {host}/potlucks/{potluck_id}/items
    PATCH {host}/potlucks/{potluck_id}/items/{item_id}*/

}
