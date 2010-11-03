package com.izforge.izpack.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    StringBuffer dbUrl = new StringBuffer();
    StringBuffer dbSystemUrl = new StringBuffer();
    Connection dbConnection= null;
    
    public boolean isValidConnection()
    {
    	try 
    	{
			if (dbConnection != null && !dbConnection.isClosed())
			{
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
}
