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
import com.webreach.mirth.model.Channel;

/**
 * an ant task to clear messages from all mirth channels
 *
 * 
 */

public class ClearTask extends AbstractMirthTask
{
	
	/* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
	
	public void executeTask() throws BuildException
	{
		try {
			connectClient();
			commandClearAll();
			disconnectClient();
		} 
		catch( ClientException e ) {
			throw( new BuildException( "Mirth client exception caught: " + e.getMessage(), e ) );
		} 
	}
	
	private void commandClearAll() throws ClientException 
	{
		List<Channel> channels = client.getChannel(null);
		
		for( Iterator<Channel> iter = channels.iterator(); iter.hasNext(); ) {
			Channel channel = (Channel) iter.next();
			client.clearMessages( channel.getId() );
		}
		
		System.out.println( "Channel Messages Cleared" );
	}
	
	
}
