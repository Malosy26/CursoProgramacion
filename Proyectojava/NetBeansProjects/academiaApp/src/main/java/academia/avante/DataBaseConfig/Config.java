/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academia.avante.DataBaseConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author dvr
 */
public class Config {
    
    private static Config instance;
    private Properties prop; 
    
    
    private Config(){
        prop = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            
            prop.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }//END CONSTRUCTOR
    
    
    public static synchronized Config getInstance(){
        
        if(instance == null){
            instance = new Config();
        }
        
        return instance;
        
        
    }
    
    public Connection getConection()  throws SQLException {
        
        Connection con = DriverManager.getConnection(
        prop.getProperty("db.url"),
        prop.getProperty("db.user"),
        prop.getProperty("db.password"));
        
        return con;
    }
    
    
    
}