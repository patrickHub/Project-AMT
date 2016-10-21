package ch.heigvd.amt.mvcdemo.services;

import ch.heigvd.amt.mvcdemo.model.User;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.sql.SQLException;

/**
 * Created by Patrick-PC on 17.10.2016.
 */
@Singleton
public class AuthorizationManagerService implements AuthorizationManagerServiceLocal {

    @EJB
    private UserManagerServiceLocal userManagerService;
    @Override
    public boolean isUserAuthorized(String login, String password, String uri) throws SQLException {
        User user = null;
        user = userManagerService.getUser(login, password);
        if(user == null) {
            return false;
        }
        return true;
    }
}
