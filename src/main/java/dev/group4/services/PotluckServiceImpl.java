package dev.group4.services;

import dev.group4.aspects.InvalidTimeException;
import dev.group4.entities.Potluck;
import dev.group4.entities.StatusType;
import dev.group4.repos.PotluckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class PotluckServiceImpl implements PotluckService{
    @Autowired
    private PotluckRepo potluckRepo;


    @Override
    public Potluck schedulePotluck(Potluck potluck) throws InvalidTimeException {
        if(potluck.getDateTime()<= System.currentTimeMillis())
            throw new InvalidTimeException("The time you wish to schedule the potluck has already passed.");
        List<Long> potlucks = potluckRepo.findAll().stream().map(Potluck::getDateTime).collect(Collectors.toList());
        for(long time : potlucks){
            if( potluck.getDateTime() - 3600000  >= time  && potluck.getDateTime() +3600000 <=  time  )
                throw new InvalidTimeException("The time you wish to schedule the potluck " +
                        "is within an hour of a currently scheduled Potluck");
        }
        potluck = potluckRepo.save(potluck);
        return potluck;
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
