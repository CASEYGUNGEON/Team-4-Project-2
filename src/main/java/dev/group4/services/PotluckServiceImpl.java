package dev.group4.services;

import dev.group4.aspects.InvalidTimeException;
import dev.group4.entities.Potluck;
import dev.group4.repos.PotluckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Service
public class PotluckServiceImpl implements PotluckService{
    @Autowired
    private PotluckRepo potluckRepo;

    @Override
    public Potluck schedulePotluck(Potluck potluck) throws InvalidTimeException {
        if(validateTime(potluck.getDateTime()))
            potluck = potluckRepo.save(potluck);
        return potluck;
    }

    private boolean validateTime(long timeToValidate) throws InvalidTimeException {
        if(timeToValidate<= System.currentTimeMillis()) {
            System.out.println(System.currentTimeMillis());
            throw new InvalidTimeException("The time you wish to schedule the potluck has already passed.");
        }
        List<Long> potlucks = potluckRepo.findAll().stream().map(Potluck::getDateTime).collect(Collectors.toList());
        for(long time : potlucks){
            if(timeToValidate <=  time + 3600000 )
                throw new InvalidTimeException("The time you wish to schedule the potluck " +
                        "is within an hour of a currently scheduled Potluck");
        }
        return true;
    }

    @Override
    public Potluck getPotluckById(String id) throws NullPointerException{
        Optional<Potluck> potluck = potluckRepo.findById(id);
        if(potluck.isPresent())
            return potluck.get();
        throw new NullPointerException("Potluck id was not found:" + id);//aspect will handle errors
    }

    @Override
    public List<Potluck> getAllPublicPotlucks() {
        List<Potluck> potlucks = potluckRepo.findAll();
        return potluckRepo.findPotluckByVisibility(true);
        //return potlucks.stream().filter(Potluck::getVisibility).collect(Collectors.toList());
    }

    @Override
    public List<Potluck> getPotlucksByCreator(String creator) {
        List<Potluck> potlucks = potluckRepo.findPotluckByCreatorId(creator);
        return potlucks;
    }

    public Potluck changePotluckTime(Potluck potluck) throws InvalidTimeException {
        long epoch = potluck.getDateTime();
        if(validateTime(potluck.getDateTime())) {
            potluck= getPotluckById(potluck.getId());
            potluck.setDateTime(epoch);
            potluck = potluckRepo.save(potluck);
        }
        return potluck;
    }

    @Override
    public boolean cancelPotluck(Potluck potluck) throws NullPointerException {
        return cancelPotluck(potluck.getId());
    }

    @Override
    public boolean cancelPotluck(String id) throws NullPointerException{
        Potluck potluck = getPotluckById(id);
        if(potluck!=null) {
            potluckRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
