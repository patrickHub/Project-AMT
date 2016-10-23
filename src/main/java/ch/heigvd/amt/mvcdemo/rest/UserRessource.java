package ch.heigvd.amt.mvcdemo.rest;

import ch.heigvd.amt.mvcdemo.model.User;
import ch.heigvd.amt.mvcdemo.rest.dto.UserDTO;
import ch.heigvd.amt.mvcdemo.rest.dto.UserPasswordDTO;
import ch.heigvd.amt.mvcdemo.services.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Patrick Deslé Djomo
 * @version 1.0
 * @description this stateless session bean implememt the REST-JAX API
 */
@Stateless
@Path("/users")
public class UserRessource {

    // injection dependency
    @EJB
    private UserManagerServiceLocal userManagerService;

    @Context
    UriInfo uriInfo;
    private static final Logger LOG = Logger.getLogger(UserRessource.class.getName());

    /**
     * @description this mehtide is used to get the list of user that are stored in the DB
     * @return list<UserDTO>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getUsers() {
        List<User> users = userManagerService.getUsers();
        List<UserDTO> tmpList = new LinkedList<>();
        for (int i= 0; i<users.size(); i++){
            tmpList.add(toDTO(users.get(i)));
        }
        return tmpList;
    }

    /**
     * @param id user id to get
     * @description this mehtide is used to get the user by just give his id
     * @return UserDTO
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUserById(@PathParam("id") int id) {
        UserDTO user = null;
        try {
            user = toDTO(userManagerService.getUser(id));
        } catch (ResourceNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return user;
    }

    /**
     * @description this mehtide is used to insert a user into the DB
     * @param userDTO user to save
     * @return int
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public long addUser(UserPasswordDTO userDTO)  {
        long id = 0;

        try {
            id = userManagerService.registerUser(fromDTO(userDTO));
        } catch (DuplicateResourceException e) {
            e.printStackTrace();
        } catch (ResourceNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return id;
    }

    /**
     * @description this mehtide is used to update user proprietiesby just gie his id
     * @param id  user id
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO updateUser(@PathParam("id") int id, UserPasswordDTO user)
    {
        UserDTO userUpdate = null;
        try {
            userManagerService.updateUser(id, fromDTO(user));
            userUpdate = toDTO(userManagerService.getUser(id));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        } catch (DuplicateResourceException e) {
            e.printStackTrace();
        }
        return userUpdate;
    }

    /**
     * @description this methode is used to delete a user in the DB will his id
     * @param id user id
     */
    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") int id) {
        try {
            userManagerService.deleteUser(id);
        }catch (ResourceNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * @description this mehtide is used to get a User from a UserDTO
     * @param userDTO userDTO
     * @return User
     */
    private User fromDTO(UserPasswordDTO userDTO) {
        return new User(userDTO.getEmail(), userDTO.getLogin(), userDTO.getPassword());
    }

    /**
     * @description this mehtide is used to get a UserDTO from a User
     * @param user  user
     */
    private UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO(user.getEmail(), user.getLogin());
        return userDTO;
    }


}


