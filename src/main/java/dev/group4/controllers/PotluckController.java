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
@CrossOrigin("*")
public class PotluckController {

    @Autowired
    private PotluckService potluckService;

    //@Secured
    @PostMapping("/potlucks")
    public Potluck createPotluck(@RequestBody Potluck potluck) throws InvalidTimeException {
            return potluckService.schedulePotluck(potluck);
    }

    @GetMapping("/potlucks")
    public List<Potluck> getAllPublicPotlucks(){

        return potluckService.getAllPublicPotlucks();
    }

    @GetMapping("/users/{id}/potlucks")
    public List<Potluck> getAllPrivatePotlucks(@PathVariable String id){
        return potluckService.getPotlucksByCreator(id);
    }

    //@Secured
    @PatchMapping("/potlucks/{potluck_id}")
    public Potluck changePotluck(@RequestBody Potluck potluck,@PathVariable String potluck_id) throws InvalidTimeException {
        potluck.setId(potluck_id);
            return potluckService.changePotluckTime(potluck);

    }

    //@Secured
    @DeleteMapping("/potlucks/{potluck_id}")
    public void deletePotluck(@PathVariable String potluck_id){
        potluckService.cancelPotluck(potluck_id);
    }

    //@Secured
    @GetMapping("/potlucks/{potluck_id}")
    public Potluck getPotluckById(@PathVariable String potluck_id) {
        return potluckService.getPotluckById(potluck_id);
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

