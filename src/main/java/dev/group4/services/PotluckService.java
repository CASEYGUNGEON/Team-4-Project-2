package dev.group4.services;

import dev.group4.entities.Potluck;
import dev.group4.entities.StatusType;

import java.util.List;

public interface PotluckService {
    Potluck schedulePotluck(Potluck potluck);
    List<Potluck> getAllPotlucks();
    //List<Potluck> getAllPotlucksByStatus(StatusType status);
    Potluck changePotluckTime(Potluck potluck, long epoch);
    Potluck changePotluckTime(String id, long epoch);
    boolean cancelPotluck(Potluck potluck);
    boolean cancelPotluck(String id);

}
