package gov.nih.nci.ihub.util.ant;

import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.model.User;

/**
 * an ant task to chane password for a mirth user
 * 
 */

public class UserChangePasswordTask extends AbstractMirthTask
{
	protected 	String          userid      = "";
	protected 	String          pswd        = "";
	
	
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
	
	
	/* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
	
	public void executeTask() throws BuildException
	{
		try {
			if( userid.length() > 0 ) {
				connectClient();
				commandUserChangePassword();
				disconnectClient();
			} else {
				throw( new BuildException( "Userid no specified" ) );
			}
		} 
		catch( ClientException e ) {
			throw( new BuildException( "Mirth client exception caught: " + e.getMessage(), e ) );
		} 
	}
	
	private void commandUserChangePassword() throws ClientException 
	{
		List<User> users = client.getUser( null );
		
		for( Iterator<User> iter = users.iterator(); iter.hasNext(); ) {
			User u = iter.next();
			if( u.getId().toString().equalsIgnoreCase( userid ) || u.getUsername().equalsIgnoreCase( userid ) ) {
				client.updateUser( u, pswd );
				System.out.println("User \"" + u.getUsername() + "\" password changed.");
				break;
			}
		}
	}
	
	
}
