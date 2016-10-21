package ch.heigvd.amt.mvcdemo.services;

import ch.heigvd.amt.mvcdemo.model.User;
import ch.heigvd.amt.mvcdemo.rest.UserRessource;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Patrick Deslé Djomo
 * @version 1.0
 * @description this stateless session bean class implements the user DAO interface
 */
@Stateless
public class UserManagerService implements UserManagerServiceLocal {

    //dependency injection
    @Resource(lookup = "java:/jdbc/MCVDemo")
    private DataSource dataSource;

    private static final Logger LOG = Logger.getLogger(UserRessource.class.getName());

    /**
     * @description methode to save a new user to database
     * @param user the user to save
     * @return int
     * @throws DuplicateResourceException
     * @throws ResourceNotFoundException
     */
    @Override
    public int registerUser(User user) throws DuplicateResourceException{

        try { // we check weither the login already existed
            if(getUser(user.getLogin()) != null){
                throw new DuplicateResourceException();
            }
        } catch (ResourceNotFoundException e) {  // if not then we can register the user

            // SQL query
            String sql = "INSERT INTO user(email, login, password)" +
                    "VALUES (?,?,?)";
            try(Connection con = dataSource.getConnection(); // we get the connection from the pool
                PreparedStatement prepstmt =  con.prepareStatement(sql)){
                prepstmt.setString(1, user.getEmail());
                prepstmt.setString(2, user.getLogin());
                prepstmt.setString(3, user.getPassword());

                prepstmt.executeUpdate(); // we create and submit sql query
                prepstmt.close();
                con.close(); // we return the connection to the pool
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        return getUser(user.getLogin(), user.getPassword()).getId();
    }

    @Override
    public void updateUser(String login, String password) throws ResourceNotFoundException {
        getUser(login); // we check if the login exist. if not RessourceNotFoundException will be throw
        String sql = "UPDATE user SET password = ?" +
                     "WHERE login = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement prepStat = con.prepareStatement(sql)){
            prepStat.setString(1, password);
            prepStat.setString(2, login);
            prepStat.executeUpdate();
            prepStat.close();
            con.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(int id, String login) throws ResourceNotFoundException, DuplicateResourceException {
        getUser(id);  // we check if the id exist. if not RessourceNotFoundException will be throw
        try {
            if(getUser(login) != null){
                throw new DuplicateResourceException();
            }
        } catch (ResourceNotFoundException e) {

            String sql = "UPDATE user SET login = ?" +
                    "WHERE userID = ?";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement prepStat = con.prepareStatement(sql)){
                prepStat.setString(1, login);
                prepStat.setInt(2, id);
                prepStat.executeUpdate();
                prepStat.close();
                con.close();
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    @Override
    public void updateUser(int id, String login, String password) throws ResourceNotFoundException, DuplicateResourceException {
        getUser(id);  // we check if the id exist. if not RessourceNotFoundException will be throw
        try {
            if(getUser(login) != null){
                throw new DuplicateResourceException();
            }
        } catch (ResourceNotFoundException e) {
            String sql = "UPDATE user SET login = ?, password = ?" +
                         "WHERE userID = ?";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement prepStat = con.prepareStatement(sql)){
                prepStat.setString(1, login);
                prepStat.setString(2, password);
                prepStat.setInt(3, id);
                prepStat.executeUpdate();
                prepStat.close();
                con.close();
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * @description methode to update the user attributes that owne the id gived in parameter
     * @param id user id
     * @param user user with new attribute to update
     * @throws ResourceNotFoundException
     * @throws DuplicateResourceException
     */
    @Override
    public void updateUser(int id, User user) throws ResourceNotFoundException, DuplicateResourceException {
        getUser(id);  // we check if the id exist. if not RessourceNotFoundException will be throw
        try{
            if(getUser(user.getLogin())!= null){ // we also check if the login alredy existed
                throw new DuplicateResourceException();
            }
        }catch (ResourceNotFoundException e){ // if not we update the user login
            // SQL query
            String sql = "UPDATE user SET email = ?, login = ?, password = ?" +
                         "WHERE userID = ?";
            try (Connection con = dataSource.getConnection(); // we get the connection from the pool
                 PreparedStatement prepState = con.prepareStatement(sql)){
                prepState.setString(1, user.getEmail());
                prepState.setString(2, user.getLogin());
                prepState.setString(3, user.getPassword());
                prepState.setInt(4, id);
                prepState.executeUpdate(); // we create and submit the SQL query
                prepState.close();
                con.close(); // we return the connection to the pool
            }catch (SQLException ex){
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * @description methode to delete user that owne the id gived in parameter
     * @param id id user
     * @throws ResourceNotFoundException
     */
    @Override
    public void deleteUser(int id) throws ResourceNotFoundException {
        // SQL query
        String sql = "DELETE  " +
                "FROM user " +
                "WHERE userID = ?";
        try(Connection con = dataSource.getConnection(); // we get the connection from the pool
            PreparedStatement prepstmt =  con.prepareStatement(sql)){
            prepstmt.setInt(1, id);
            int  result = prepstmt.executeUpdate(); // we create and submit the SQL query

            if(result == 0){ // we check weither we have been able to delete the user
                throw new ResourceNotFoundException();
            }
            prepstmt.close(); // we return the connection to the pool
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @description methode to get user that owne the id gived in parameter
     * @param id user id
     * @throws ResourceNotFoundException
     * @return User
     */
    @Override
    public User getUser(int id) throws ResourceNotFoundException {

        // SQL query
        String sql = "SELECT userID, email, login, password " +
                "FROM user " +
                "WHERE userID = ?";
        User user = null;
        try(Connection con = dataSource.getConnection(); // we get the connection from the pool
            PreparedStatement prepstmt =  con.prepareStatement(sql)){
            prepstmt.setInt(1, id);

            ResultSet set = prepstmt.executeQuery(); // we submit the SQL query
            if(!set.next()){ // we check weither we have been able to find the user with the id
                throw new ResourceNotFoundException();
            }
            // we get the user
            user = new User(set.getInt("userID"), set.getString("email"), set.getString("login"), set.getString("password"));
            prepstmt.close();
            con.close(); // we return the connection to the pool

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    /**
     * @description methode to get user that owne the login gived in parameter
     * @param login user login
     * @throws ResourceNotFoundException
     * @return User
     */
    @Override
    public User getUser(String login)  throws  ResourceNotFoundException{

        //SQL query
        String sql = "SELECT email, login, password " +
                "FROM user " +
                "WHERE login = ?";
        User user = null;
        try(Connection con = dataSource.getConnection(); // we get connection from the pool
            PreparedStatement prepstmt =  con.prepareStatement(sql)){

            prepstmt.setString(1, login);

            ResultSet set = prepstmt.executeQuery(); // we submit the SQL query
            if(!set.next()){ // we check weither we have been able to get the user with the login
                throw new ResourceNotFoundException();
            }
            user = new User(set.getString("email"), set.getString("login"), set.getString("password"));
            prepstmt.close();
            con.close(); // we return the connection to the pool
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    /**
     * @description methode to get user that owne the login and password gived in parameters
     * @param login user login
     * @param password user password
     * @return User
     */
    @Override
    public User getUser(String login, String password){

        //SQL query
        String sql = "SELECT email, login, password " +
                "FROM user " +
                "WHERE login = ? AND password = ?";
        User user = null;
        try(Connection con = dataSource.getConnection(); // we get the connection from the pool
            PreparedStatement prepstmt =  con.prepareStatement(sql)){

            prepstmt.setString(1, login);
            prepstmt.setString(2, password);

            ResultSet set = prepstmt.executeQuery(); // we submit the SQL connection
            set.next();
            user = new User(set.getString("email"), set.getString("login"), set.getString("password"));
            prepstmt.close();
            con.close(); // we return the connnection to the pool
        }
        catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }

        return user;
    }

    /**
     * @description methode to get users list stored in the database
     * @return List<User>
     */
    @Override
    public List<User> getUsers()  {
        String sql = "SELECT email, login, password  " +
                "FROM user ";
        User user = null;
        List<User> users = new LinkedList<>();
        try(Connection con = dataSource.getConnection(); // we get the connection from the pool
            PreparedStatement prepstmt =  con.prepareStatement(sql)){

            ResultSet set = prepstmt.executeQuery(); // we create and submit the SQL query
            // we scroll through the tabular result set
            while (set.next()){
                users.add(new User(set.getString("email"), set.getString("login"), set.getString("password")));
            }
            prepstmt.close();
            con.close(); // we return the connection to the pool

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }
}


