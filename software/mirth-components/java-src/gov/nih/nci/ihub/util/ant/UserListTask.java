/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.util.ant;

import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.model.User;

/**
 * an ant task to list all mirth users
 *
 * @author andrzej@coalese.com
 */

public class UserListTask extends AbstractMirthTask
{
	
	/* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
	
	public void executeTask() throws BuildException
	{
		try {
			connectClient();
			commandUserList();
			disconnectClient();
		} 
		catch( ClientException e ) {
			throw( new BuildException( "Mirth client exception caught: " + e.getMessage(), e ) );
		} 
	}
	
	private void commandUserList() throws ClientException 
	{
		List<User> users = client.getUser( null );
		
		System.out.println("\tID\tUser\t\tName\t\t\tEmail");
		
		for( Iterator<User> iter = users.iterator(); iter.hasNext(); ) {
			User user = iter.next();
			System.out.println("\t" + user.getId() + "\t" + user.getUsername() + "\t\t" + user.getFirstName() + "\t\t\t" + user.getEmail() );
		}
	}
	
	
}
