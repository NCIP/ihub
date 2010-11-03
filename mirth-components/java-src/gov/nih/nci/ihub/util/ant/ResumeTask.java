package gov.nih.nci.ihub.util.ant;

import org.apache.tools.ant.BuildException;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.model.ChannelStatus;
import com.webreach.mirth.model.ChannelStatus.State;

/**
 * an ant task to resume all mirth channels
 *
 * 
 */

public class ResumeTask extends AbstractMirthTask
{
	
	/* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
	
	public void executeTask() throws BuildException
	{
		try {
			connectClient();
			commandResumeAll();
			disconnectClient();
		} 
		catch( ClientException e ) {
			throw( new BuildException( "Mirth client exception caught: " + e.getMessage(), e ) );
		} 
	}
	
	private void commandResumeAll() throws ClientException 
	{
		for( ChannelStatus channel : client.getChannelStatusList() ) {
			if( channel.getState().equals( State.PAUSED ) ) {
				client.resumeChannel( channel.getChannelId() );
				System.out.println( "Channel " + channel.getName() + " Resumed" );
			}			
		}
	}
	
	
}
