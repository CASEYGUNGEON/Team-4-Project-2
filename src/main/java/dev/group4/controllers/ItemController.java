package dev.group4.controllers;


import dev.group4.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    //TODO ADD ROUTES
}
