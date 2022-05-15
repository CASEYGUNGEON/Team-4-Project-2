package dev.group4.controllers;


import dev.group4.aspects.InvalidCredentialException;
import dev.group4.aspects.LoggingAspect;
import dev.group4.aspects.Secured;
import dev.group4.entities.Item;
import dev.group4.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Component
@RestController
@CrossOrigin("*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //@Secured
    @PostMapping("/potlucks/{potluck_id}/items")
    public Item addItem(@RequestBody Item item,@PathVariable String potluck_id) throws InvalidCredentialException {
        item.setPotluckId(potluck_id);
        return itemService.registerItem(item);
    }

    @GetMapping("/potlucks/{potluck_id}/items")
    public List<Item> getItems(@PathVariable String potluck_id){
        return itemService.getItemsByPotluck(potluck_id);
    }


    //@Secured
    @DeleteMapping("/potlucks/{potluck_id}/items")
    public boolean deleteItem(@RequestBody Item item,  @PathVariable String potluck_id){
        item.setPotluckId(potluck_id);
        return itemService.deleteItem(item);
    }

    @PostMapping(" /potlucks/{potluck_id}/items")
    public Item addGuestItem(@RequestBody Item item,@PathVariable String potluck_id) throws InvalidCredentialException {
        item.setPotluckId(potluck_id);
        return itemService.registerItem(item);
    }

    @PatchMapping("/potlucks/{potluck_id}/items")
    public Item claimItem(@RequestBody Item item,@PathVariable String potluck_id){
        item.setPotluckId(potluck_id);
        return itemService.updateSupplier(item, item.getSupplier());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleInvalidTimeException(Throwable e){
        LoggingAspect.LogError(e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getCause().getMessage());
    }
}
