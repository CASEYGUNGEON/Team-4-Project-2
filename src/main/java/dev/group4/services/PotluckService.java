package dev.group4.services;

import dev.group4.aspects.InvalidTimeException;
import dev.group4.entities.Potluck;
import dev.group4.entities.StatusType;

import java.util.List;

public interface PotluckService {
    /**
     * A method to schedule a Potluck in the Potluck Application
     * @param potluck the Potluck to schedule
     * @return the scheduled Potluck
     * @throws InvalidTimeException The Time specified for the potluck was not valid.
     */
    Potluck schedulePotluck(Potluck potluck) throws InvalidTimeException;
    List<Potluck> getAllPotlucks();
    //List<Potluck> getAllPotlucksByStatus(StatusType status);
    Potluck changePotluckTime(Potluck potluck, long epoch);
    Potluck changePotluckTime(String id, long epoch);
    boolean cancelPotluck(Potluck potluck);
    boolean cancelPotluck(String id);

}
