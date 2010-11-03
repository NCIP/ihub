package com.izforge.izpack.util;

import java.sql.DriverManager;


public class OracleConnection extends DatabaseConnection{

    String dbDriver = "oracle.jdbc.driver.OracleDriver";
    
    public OracleConnection(String dbServer, String dbPort, String dbName, String dbUser, String dbPassword )
    {
    	dbUrl =dbUrl.append("jdbc:oracle:thin:@").append(dbServer).append(":").append(dbPort).append(":").append(dbName);
    	try
    	{	
    		Class.forName(dbDriver).newInstance();
    		dbConnection=DriverManager.getConnection(dbUrl.toString(),dbUser,dbPassword);
    	}
		catch( Exception e ){
			e.printStackTrace();
		}
    }
}

