package gov.nih.nci.ihub.util.ant;

import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.model.Channel;

/**
 * an ant task to reset stats for all mirth channels
 *
 * 
 */

public class ResetStatsTask extends AbstractMirthTask
{
	
	/* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
	
	public void executeTask() throws BuildException
	{
		try {
			connectClient();
			commandResetStatsAll();
			disconnectClient();
		} 
		catch( ClientException e ) {
			throw( new BuildException( "Mirth client exception caught: " + e.getMessage(), e ) );
		} 
	}
	
	private void commandResetStatsAll() throws ClientException 
	{
		List<Channel> channels = client.getChannel(null);
	
		for( Iterator<Channel> iter = channels.iterator(); iter.hasNext(); ) {
			Channel channel = (Channel) iter.next();
			client.clearStatistics( channel.getId(), true, true, true, true, true, true );
		}
		
		System.out.println( "Channel Stats Reset" );
	}
	
	
}
