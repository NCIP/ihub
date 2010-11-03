package com.izforge.izpack.util;

import java.sql.DriverManager;


public class PostgresConnection extends DatabaseConnection{

    String dbDriver = "org.postgresql.Driver";

    public PostgresConnection(String dbServer, String dbPort, String dbName, String dbUser, String dbPassword )
    {
    	dbUrl =dbUrl.append("jdbc:postgresql://").append(dbServer).append(":").append(dbPort).append("/").append(dbName);
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

