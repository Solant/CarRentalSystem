package by.skakun.carrentalsystem.util;

import java.util.ResourceBundle;

/**
 *
 * @author Skakun
 * 
 * Manager for retrieving database connection settings, pages' paths information
 */
public class ConfigurationManager {
  private final static ResourceBundle resourceBundle = 
                         ResourceBundle.getBundle("properties.config");

    /**
     *
     */
    public ConfigurationManager(){};
    
    /**
     *
     * @param key
     * @return
     */
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }   
}
