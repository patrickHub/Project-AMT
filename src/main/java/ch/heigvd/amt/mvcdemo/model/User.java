package ch.heigvd.amt.mvcdemo.model;

/**
 * @author Patrick Deslé Djomo
 * @version 1.0
 * @description This class is represent the user that we are going manipulate during this project
 */
public class User {
    private String login;
    private String password;
    private String email;
    private int id;

    /**
     * @description constructor with parameter without id
     * @param email the email user
     * @param login the login user
     * @param password the password user
     */
    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    /**
     * @description constructor with parameters with id
     * @param id the id user
     * @param email the email user
     * @param login the login user
     * @param password the password user
     */
    public User(int id, String email, String login, String password) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    /*
        getters
     */
    public String getEmail(){return email;}
    public int getId(){return id;}
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    /*
        setters
     */
    public void setEmail(String email){this.email = email;}
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return
                "User{" +
                "email='" + email + '\'' +
                "username='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
