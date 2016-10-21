package ch.heigvd.amt.mvcdemo.rest.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick-PC on 09.10.2016.
 */
public class UserDTO {
    private String login;
    private String email;
    private List<String> currentUsers = new ArrayList<>();

    public UserDTO(){

    }
    public UserDTO(String email, String login) {
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email= email;
    }

}
