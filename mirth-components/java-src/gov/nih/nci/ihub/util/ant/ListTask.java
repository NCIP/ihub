package gov.nih.nci.ihub.util.ant;

import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.model.Channel;

/**
 * an ant task to list all mirth channels
 *
 * 
 */

public class ListTask extends AbstractMirthTask
{
	
	/* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
	
	public void executeTask() throws BuildException
	{
		try {
			connectClient();
			commandListAll();
			disconnectClient();
		} 
		catch( ClientException e ) {
			throw( new BuildException( "Mirth client exception caught: " + e.getMessage(), e ) );
		} 
	}
	
	private void commandListAll() throws ClientException 
	{
		List<Channel> allChannels = client.getChannel( null );
		
		System.out.println("\tID\t\t\t\t\tEnabled\t\tName");
		
		String enable = "";
		
		for( Iterator<Channel> iter = allChannels.iterator(); iter.hasNext(); ) {
			Channel channel = iter.next();
			if( channel.isEnabled() ) {
				enable = "ENABLED";
			} else {
				enable = "DISABLED";
			}
			
			System.out.println( "\t"+channel.getId() + "\t" + enable + "\t\t" + channel.getName() );
		}
	}
	
	
}
