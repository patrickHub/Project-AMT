package ch.heigvd.amt.mvcdemo.rest.dto;

/**
 * Created by Patrick-PC on 19.10.2016.
 */
public class UserPasswordDTO {
    private String email;
    private String login;
    private String password;

    public UserPasswordDTO(){}

    public UserPasswordDTO(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
