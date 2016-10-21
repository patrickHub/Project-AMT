package ch.heigvd.amt.mvcdemo.rest;

import sun.java2d.pipe.SpanShapeRenderer;

import javax.ws.rs.ApplicationPath;
import java.lang.reflect.Array;
import java.util.*;


/**
 * @author Patrick Deslé Djomo
 * @version 1.0
 * @description
 */
@ApplicationPath("/api")
public class RESTApplication extends javax.ws.rs.core.Application {

    public Map<String, Object> geProperties(){
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.disableMoxyJson", true);
        return  properties;
    }

}

