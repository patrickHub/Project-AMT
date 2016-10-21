package ch.heigvd.amt.mvcdemo.services;

/**
 * Created by Patrick-PC on 19.10.2016.
 */
public class DuplicateResourceException extends Exception {
    public String getMessage(){
        return "ERROR: Ressource already existed !";
    }
}
