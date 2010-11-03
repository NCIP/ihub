package com.izforge.izpack.util;

import java.sql.DriverManager;


public class MySQLConnection extends DatabaseConnection{

    String dbDriver = "com.mysql.jdbc.Driver";
    
    public MySQLConnection(String dbServer, String dbPort, String dbSystemUser, String dbPassword )
    {
    	dbSystemUrl =dbSystemUrl.append("jdbc:mysql://").append(dbServer).append(":").append(dbPort);
    	try
    	{
    		Class.forName(dbDriver).newInstance();
    		dbConnection=DriverManager.getConnection(dbSystemUrl.toString(),dbSystemUser,dbPassword);
    	}
		catch( Exception e ){
			e.printStackTrace();
		}
    }
    public MySQLConnection(String dbServer, String dbPort, String dbName, String dbUser, String dbPassword )
    {
    	dbUrl =dbUrl.append("jdbc:mysql://").append(dbServer).append(":").append(dbPort).append("/").append(dbName);
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

