package gov.nih.nci.ihub.util.ant;

import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.model.User;

/**
 * an ant task to add a mirth user
 *
 * 
 */

public class UserAddTask extends AbstractMirthTask
{
	protected 	String          userid      = "";
	protected 	String          pswd        = "";
	protected 	String          name        = "";
	protected 	String          email       = "";
	
	
	/**
     * @param pswd
     */
	public void setPswd( String pswd )
	{
		this.pswd = pswd;
	}
	
	
	/**
     * @param userid
     */
	public void setUserid( String userid )
	{
		this.userid = userid;
	}
	
	/**
     * @param name
     */
	public void setName( String name )
	{
		this.name = name;
	}
	
	
	/**
     * @param email
     */
	public void setEmail( String email )
	{
		this.email = email;
	}
	
	
	/* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
	
	public void executeTask() throws BuildException
	{
		try {
			if( userid.length() > 0 ) {
				connectClient();
				commandUserAdd();
				disconnectClient();
			} else {
				throw( new BuildException( "Userid no specified" ) );
			}
		} 
		catch( ClientException e ) {
			throw( new BuildException( "Mirth client exception caught: " + e.getMessage(), e ) );
		} 
	}
	
	private void commandUserAdd() throws ClientException, BuildException
	{
		User newUser = new User();
		
		newUser.setUsername( userid );
		newUser.setFirstName( name );
		newUser.setEmail( email );
		
		List<User> users = client.getUser( null );
		
		for( Iterator<User> iter = users.iterator(); iter.hasNext(); ) {
			User luser = iter.next();
			if( luser.getUsername().equalsIgnoreCase( userid ) ) {
				throw( new BuildException( "Unable to add user: userid already in use: " + userid ) );
			}
		}
		
		try {
			client.updateUser( newUser, pswd );
			System.out.println( "User \"" + userid + "\" added successfully." );
		} 
		catch( Exception e ) {
			throw( new BuildException( "Unable to add user \"" + userid + "\": " + e ) );
		}
	}
	
	
}
