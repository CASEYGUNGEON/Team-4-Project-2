package dev.group4.controllers;


import dev.group4.aspects.InvalidTimeException;
import dev.group4.aspects.LoggingAspect;
import dev.group4.entities.Potluck;
import dev.group4.services.PotluckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class PotluckController {

    @Autowired
    private PotluckService potluckService;

    //@Secured
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

    //@Secured
    @PatchMapping("/potluck/{potluck_id}")
    public Potluck changePotluck(@RequestBody Potluck potluck,@PathVariable String potluck_id){
        potluck.setId(potluck_id);
        try {
            return potluckService.changePotluckTime(potluck);
        } catch (InvalidTimeException e) {
            throw new RuntimeException(e);
        }
    }

    //@Secured
    @DeleteMapping("/potlucks/{potluck_id}")
    public void deletePotluck(@PathVariable String potluck_id){
        potluckService.cancelPotluck(potluck_id);
    }

    //@Secured
    @GetMapping("/potlucks/{potluck_id}")
    public Potluck shareLink(@PathVariable String potluck_id) {
        return potluckService.getPotluckById(potluck_id);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleInvalidTimeException(Throwable e){
        LoggingAspect.LogError(e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage().substring(e.getMessage().indexOf(":")+1));
    }
}

