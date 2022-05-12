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
    @PostMapping("{host}/potlucks")
    public Potluck createPotluck(@RequestBody Potluck potluck){
        try {
            return this.potluckService.schedulePotluck(potluck);
        } catch (InvalidTimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("{host}/potlucks")
    public List<Potluck> getAllPublicPotlucks(){
        return potluckService.getAllPublicPotlucks();
    }
}
