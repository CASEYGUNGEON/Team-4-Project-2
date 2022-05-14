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
    Potluck getPotluckById(String id);
    List<Potluck> getAllPublicPotlucks();
    List<Potluck> getPotlucksByCreator(String creator);
    //List<Potluck> getAllPotlucksByStatus(StatusType status);
    Potluck changePotluckTime(Potluck potluck) throws InvalidTimeException;
    boolean cancelPotluck(Potluck potluck);
    boolean cancelPotluck(String id);

}
