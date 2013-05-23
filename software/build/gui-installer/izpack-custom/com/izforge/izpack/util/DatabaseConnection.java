/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
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
