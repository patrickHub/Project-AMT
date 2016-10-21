package ch.heigvd.amt.mvcdemo.services;

/**
 * Created by Patrick-PC on 05.10.2016.
 */

import ch.heigvd.amt.mvcdemo.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 * @author Patrick Deslé Djomo
 * @version 1.0
 * @description this stateless session bean implememts the TestDataServiceLocal i
 *               nterface in other to generate tests users.
 */
@Stateless
public class TestDataService implements TestDataServiceLocal {

    @EJB // we as to the server to ineject a dependency to the service
    private UserManagerServiceLocal userManagerService;
    private int nberOfCurrentUsers = 0 ;

    /**
     * @description methode to generate tests users
     * @param numberOfUsers nomber of tests users to generate
     */
    public void generateTestData(int numberOfUsers) {
        int LastNberOfCurrentUser = nberOfCurrentUsers;
        nberOfCurrentUsers += numberOfUsers;

        // we generate tests users
        for(int i = LastNberOfCurrentUser + 1; i<= nberOfCurrentUsers; i++){
            try {
                userManagerService.registerUser(new User("login-"+ i + "@"+ "gmail.ch", "login-" + i, "password-" + i));
            } catch (DuplicateResourceException e) {
                e.printStackTrace();
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
