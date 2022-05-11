package dev.group4.services;

import dev.group4.entities.Potluck;
import dev.group4.entities.StatusType;
import dev.group4.repos.PotluckRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PotluckServiceImpl implements PotluckService{
    @Autowired
    private PotluckRepo potluckRepo;


    @Override
    public Potluck schedulePotluck(Potluck potluck) {
        return null;
    }

    @Override
    public List<Potluck> getAllPotlucks() {
        return null;
    }

    /*
    @Override
    public List<Potluck> getAllPotlucksByStatus(StatusType status) {
        return null;
    }
    */

    @Override
    public Potluck changePotluckTime(Potluck potluck, long epoch) {
        return changePotluckTime(potluck.getId(), epoch);
    }

    @Override
    public Potluck changePotluckTime(String id, long epoch) {
        return null;
    }

    @Override
    public boolean cancelPotluck(Potluck potluck) {
        return cancelPotluck(potluck.getId());
    }

    @Override
    public boolean cancelPotluck(String id) {
        return false;
    }
}
