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

    /**
     *  A method to get a Potluck using its specific ID in the Application
     * @param id the ID of the Potluck to find
     * @return the Potluck
     * @throws NullPointerException The specific Potluck was not found
     */

    Potluck getPotluckById(String id);

    /**
     * A method to get all Potlucks in the database
     * @return all the Potlucks
     */
    List<Potluck> getAllPublicPotlucks();

    /**
     * A method to get Potlucks for a specific creator
     * @param creator the Creator of the Potlucks to find
     * @return all the Potlucks for the specific creator
     */
    List<Potluck> getPotlucksByCreator(String creator);
    //List<Potluck> getAllPotlucksByStatus(StatusType status);

    /**
     *A method to change the time of a specific Potluck
     * @param potluck the Potluck that will have its time changed
     * @return the Potluck with its time changed
     * @throws InvalidTimeException The time specified for the Potluck was not valid
     **/
    Potluck changePotluckTime(Potluck potluck) throws InvalidTimeException;

    /**
     *A method to cancel a specific potluck
     * @param potluck the Potluck that you will be cancelling
     * @return true or false if the Potluck was successfully cancelled
     * @throws NullPointerException if the Potluck was not successfully cancelled
     **/
    boolean cancelPotluck(Potluck potluck);

    /**
     *A method to cancel a specific potluck
     * @param id the Potluck that you will be cancelling
     * @return true or false if the Potluck was successfully cancelled
     * @throws NullPointerException if the Potluck was not successfully cancelled
     **/
    boolean cancelPotluck(String id);

}
