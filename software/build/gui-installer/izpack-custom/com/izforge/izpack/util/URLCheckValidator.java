/*
 * IzPack - Copyright 2001-2008 Julien Ponge, All Rights Reserved.
 * 
 * http://izpack.org/
 * http://izpack.codehaus.org/
 * 
 * Copyright 2004 Thorsten Kamann
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izforge.izpack.util;

import com.izforge.izpack.panels.ProcessingClient;
import com.izforge.izpack.panels.Validator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;




/**
 * A validator to check whether a port is available (free) on the localhost.
 * <p/>
 * This validator can be used for rule input fields in the UserInputPanel to make sure that the port
 * the user entered is not in use.
 *
 * @author thorque
 */
public class URLCheckValidator implements Validator
{

    public boolean validate(ProcessingClient client)
    {
    	String urlInput = null;         
       	urlInput = client.getFieldContents(0);
   
        if ((urlInput == null) || (urlInput.length() == 0))
        {
            return false;
        }
       	
      try
        {
    	  URL url = new URL(urlInput);    	  
          System.out.println("Testing to see if URL connects::"+ urlInput);
          HttpURLConnection conn = (HttpURLConnection)url.openConnection();
          System.out.println("Created HttpURLConnection object");
          conn.connect();
          System.out.println("connecting..");
	      int code  =conn.getResponseCode();
	      if (code >= 400)
	      {
	    	  return false;
	      }
          System.out.println("disconnecting..");
          conn.disconnect();
          System.out.println("disconnected");
          return true;
        }
        catch (Exception ex)
        {
        	ex.printStackTrace(); 
        	return false;
        	 
        }
    }
}
    	