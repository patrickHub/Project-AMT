package ch.heigvd.amt.mvcdemo.services;

/**
 * Created by Patrick-PC on 19.10.2016.
 */
public class ResourceNotFoundException extends Exception {
    public String getMessage(){
        return "ERROR: Ressouce not Founded !";
    }
}
