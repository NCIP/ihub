package gov.nih.nci.ihub.util.ant;

import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.model.ChannelStatus;

/**
 * an ant task to get Mirth server status
 * 
 */

public class StatusTask extends AbstractMirthTask
{
	
	/* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
	
	public void executeTask() throws BuildException
	{
		try {
			connectClient();
			commandStatus();
			disconnectClient();
		} 
		catch( ClientException e ) {
			throw( new BuildException( "Mirth client exception caught: " + e.getMessage(), e ) );
		} 
	}
	
	private void commandStatus() throws ClientException 
	{
		System.out.println( "\tID\t\t\t\t\tStatus\t\tEnabled\t\tName" );
		
		List<ChannelStatus> channels = client.getChannelStatusList();
		
		for( Iterator<ChannelStatus> iter = channels.iterator(); iter.hasNext(); ) {
			ChannelStatus channel = iter.next();
			
			System.out.println( "\t" +channel.getChannelId() + "\t" + channel.getState().toString() + "\t\t" + getChannelEnabledString( channel ) + "\t\t" + channel.getName() );
		}
	}

	
	
}
