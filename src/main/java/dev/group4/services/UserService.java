package dev.group4.services;

import dev.group4.aspects.InvalidCredentialException;
import dev.group4.dtos.UserInfo;
import dev.group4.entities.User;

public interface UserService {
    /**
     * A method that allows the creation of a new user in the Potluck Application
     * @param user The User to create and their username and password
     * @return a User if the username and password are valid
     * @throws InvalidCredentialException the username or password were invalid
     */
    User registerUser(User user) throws InvalidCredentialException;

    /**
     * A method that allows the user to login to their Potluck Account
     * @param authorization the Authorization String which contains the decrypted username and password
     * @return a User if credentials are valid
     * @throws InvalidCredentialException The password or username was incorrect
     */
    UserInfo login(String authorization) throws InvalidCredentialException;
}
