package ch.heigvd.amt.mvcdemo.services;

import ch.heigvd.amt.mvcdemo.model.User;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Patrick Deslé Djomo
 * @version 1.0
 * @description This local session bean represente the interface of user DAO
 */
@Local
public interface UserManagerServiceLocal {

    /**
     * @description methode to save a new user to database
     * @param user the user to save
     * @return int
     * @throws DuplicateResourceException
     * @throws ResourceNotFoundException
     */
    public int registerUser(User user) throws DuplicateResourceException, ResourceNotFoundException;

    /**
     * @description methode to update the user attributes that owne the id gived in parameter
     * @param id user id
     * @param user user with new attribute to update
     * @throws ResourceNotFoundException
     * @throws DuplicateResourceException
     */
    public void updateUser(int id, User user) throws ResourceNotFoundException, DuplicateResourceException;

    /**
     * @description methode to get user that owne the login and password gived in parameters
     * @param login user login
     * @param password user password
     * @return User
     */
    public User getUser(String login, String password) throws ResourceNotFoundException;
    /**
     * @description methode to get user that owne the id gived in parameter
     * @param id user id
     * @throws ResourceNotFoundException
     * @return User
     */
    public User getUser(int id) throws ResourceNotFoundException;
    /**
     * @description methode to get user that owne the login gived in parameter
     * @param login user login
     * @throws ResourceNotFoundException
     * @return User
     */
    public User getUser(String login) throws ResourceNotFoundException;
    /**
     * @description methode to delete user that owne the id gived in parameter
     * @param id id user
     * @throws ResourceNotFoundException
     */
    public void deleteUser(int id) throws ResourceNotFoundException;

    /**
     * @description methode to get users list stored in the database
     * @return List<User>
     */
    public List<User> getUsers();

}
