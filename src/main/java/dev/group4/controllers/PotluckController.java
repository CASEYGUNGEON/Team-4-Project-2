package dev.group4.controllers;


import dev.group4.aspects.InvalidTimeException;
import dev.group4.aspects.Secured;
import dev.group4.entities.Potluck;
import dev.group4.services.PotluckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController
public class PotluckController {

    @Autowired
    private PotluckService potluckService;

    @Secured
    @PostMapping("/potlucks")
    public Potluck createPotluck(@RequestBody Potluck potluck){
        try {
            return this.potluckService.schedulePotluck(potluck);
        } catch (InvalidTimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/potlucks")
    public List<Potluck> getAllPublicPotlucks(){
        return potluckService.getAllPublicPotlucks();
    }
}
/*As a registered User I can edit my potluck date/time
PATCH {host}/potluck/{id}    @secured
As a registered User I can delete my potlucks
DELETE {host}/potlucks/{potluck_id}   @secured
As a registered User I can create a sharable link that goes directly to my potluck
GET {host}/potlucks/{potluck_id} (has a button) @secured
As a registered user I can create private potlucks that require a shareable link to be seen
POST  {host}/potlucks/{potluck_id}?=visibility @secured
*/