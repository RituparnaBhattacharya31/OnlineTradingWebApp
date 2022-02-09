/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trading.ot.core;

/**
 *
 * @author Adity
 */
public class DbConnectionProvider {

    private static DbConnectionProvider dbConnectionProvider;

    private DbConnectionProvider() {
        
       // Do we need to create constructor
    }

    
    
    public static DbConnectionProvider getInstance() {
        if (getDbConnectionProvider() == null) {
            setDbConnectionProvider(new DbConnectionProvider()); //calling the constructor
        }
        return getDbConnectionProvider();
    }

    public void getDbConnection() {
        
        ConfigFileReader configFileReader = new ConfigFileReader();
        ConfigParam configParam = configFileReader.getConfigParam();
        System.out.println("userid =" + configParam.getUserid());
    }

    /**
     * @return the dbConnectionProvider
     */
    public static DbConnectionProvider getDbConnectionProvider() {
        return dbConnectionProvider;
    }

    /**
     * @param aDbConnectionProvider the dbConnectionProvider to set
     */
    public static void setDbConnectionProvider(DbConnectionProvider aDbConnectionProvider) {
        dbConnectionProvider = aDbConnectionProvider;
    }

}
