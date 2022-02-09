/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trading.ot.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Adity
 */
public class ConnectionManager {
    
    public static Connection getConnection() {
        
        ConfigFileReader configFileReader = new ConfigFileReader();
        Connection con=null;
        ConfigParam configParam = configFileReader.getConfigParam();
        
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(configParam.getHost()+":"+configParam.getPort()+"/"
                    +configParam.getDbName(),configParam.getUserid(),configParam.getPassword());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return con;
    }
}
