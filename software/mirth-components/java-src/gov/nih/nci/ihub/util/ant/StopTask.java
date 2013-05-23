/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.util.ant;

import org.apache.tools.ant.BuildException;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.model.ChannelStatus;
import com.webreach.mirth.model.ChannelStatus.State;

/**
 * an ant task to stop all mirth channels
 *
 * 
 */

public class StopTask extends AbstractMirthTask
{
	
	/* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
	
	public void executeTask() throws BuildException
	{
		try {
			connectClient();
			commandStopAll();
			disconnectClient();
		} 
		catch( ClientException e ) {
			throw( new BuildException( "Mirth client exception caught: " + e.getMessage(), e ) );
		} 
	}
	
	private void commandStopAll() throws ClientException 
	{
		for( ChannelStatus channel : client.getChannelStatusList() ) {
			if( channel.getState().equals( State.STARTED ) || channel.getState().equals( State.PAUSED ) ) {
				client.stopChannel( channel.getChannelId() );
				System.out.println( "Channel " + channel.getName() + " Stopped" );
			}
		}
	}
	
	
}
