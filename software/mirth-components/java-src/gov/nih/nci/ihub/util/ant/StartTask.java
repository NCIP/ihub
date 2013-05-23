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
 * an ant task to start all mirth channels
 *
 * 
 */

public class StartTask extends AbstractMirthTask
{
	
	/* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
	
	public void executeTask() throws BuildException
	{
		try {
			connectClient();
			commandStartAll();
			disconnectClient();
		} 
		catch( ClientException e ) {
			throw( new BuildException( "Mirth client exception caught: " + e.getMessage(), e ) );
		} 
	}
	
	private void commandStartAll() throws ClientException 
	{
		for( ChannelStatus channel : client.getChannelStatusList() ) {
			if( channel.getState().equals( State.STOPPED ) || channel.getState().equals( State.PAUSED ) ) {
				if( channel.getState().equals( State.PAUSED ) ) {
					client.resumeChannel( channel.getChannelId() );
					System.out.println( "Channel " + channel.getName() + " Resumed" );
				} else {
					client.startChannel( channel.getChannelId() );
					System.out.println( "Channel " + channel.getName() + " Started" );
				}
			}		
		}
	}
	
	
}
