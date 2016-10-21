package ch.heigvd.amt.mvcdemo.services;

import javax.ejb.Local;
import java.sql.SQLException;


/**
 * @author Patrick Deslé Djomo
 * @version 1.0
 * @description this local seesion bean will be used to create tests users for the project
 */
@Local
public interface TestDataServiceLocal {

    /**
     * @description methode to generate tests users
     * @param numberOfUser nomber of tests users to generate
     */
    public void generateTestData(int numberOfUser);

}
