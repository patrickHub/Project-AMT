package ch.heigvd.amt.mvcdemo.services;

import javax.ejb.Local;
import java.sql.SQLException;

/**
 * Created by Patrick-PC on 17.10.2016.
 */
@Local
public interface AuthorizationManagerServiceLocal {

    public boolean isUserAuthorized(String login, String password, String uri) throws SQLException;
}
