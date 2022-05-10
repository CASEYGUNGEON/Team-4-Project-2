package dev.group4.controllers;


import dev.group4.services.PotluckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class PotluckController {

    @Autowired
    private PotluckService potluckService;
    /*As a registered User I can create a potluck (date and time required)
    POST {host}/potlucks   @secured


    As a registered User I can add items to potlucks I created
    POST {host}/potlucks/{potluck_id}/items   @secured


    As a registered User I can remove items from potlucks I created
    DELETE POST {host}/potlucks/{potluck_id}/items   @secured


    As a guest I can view all potlucks
    GET {host}/potlucks    //query param for status


    As a guest I can add an item to a potluck, put down my name as the supplier
    POST {host}/potlucks/{potluck_id}/items
    PATCH {host}/potlucks/{potluck_id}/{item_id}*/

    //TODO ADD ROUTES
}
