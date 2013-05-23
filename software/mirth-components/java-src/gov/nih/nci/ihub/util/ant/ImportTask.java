/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.util.ant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.BuildException;

import com.webreach.mirth.client.core.ClientException;
import com.webreach.mirth.model.Channel;
import com.webreach.mirth.model.CodeTemplate;
import com.webreach.mirth.model.ServerConfiguration;
import com.webreach.mirth.model.converters.ObjectXMLSerializer;
import com.webreach.mirth.model.util.ImportConverter;
import com.webreach.mirth.util.PropertyVerifier;


/**
 * an ant task to import stuff into Mirth
 *
 * 
 */

public class ImportTask extends AbstractMirthTask
{
	private final String		TYPE_CHANNEL	= "channel";
	private final String		TYPE_CONFIG		= "config";
	private final String		TYPE_SCRIPT		= "script";
	private final String        TYPE_TEMPLATE   = "template";
	
	private final String		SCRIPT_DEPLOY	= "Deploy";
	private final String		SCRIPT_PRE		= "Preprocessor";
	private final String		SCRIPT_POST		= "Postprocessor";
	private final String		SCRIPT_SHUTDOWN	= "Shutdown";
	
	
	protected 	  String        type        	= TYPE_CHANNEL;
	protected	  String		script			= "";
	protected	  String		filename		= "";
	protected	  boolean		force			= false;
	
	
	/**
     * @param type
     */
	public void setType( String type )
	{
		this.type = type;
	}
	
	
	/**
     * @param script
     */
	public void setScript( String script )
	{
		this.script = script;
	}
	
	
	/**
     * @param filename
     */
	public void setFilename( String filename )
	{
		this.filename = filename;
	}
	
	
	/**
     * @param force
     */
	public void setForce( boolean force )
	{
		this.force = force;
	}
	
	
	/* (non-Javadoc)
   * @see org.apache.tools.ant.Task#execute()
   */
	
	public void executeTask() throws BuildException
	{
		try {
			if( filename.length() > 0 ) {
				if( type.equalsIgnoreCase( TYPE_CHANNEL ) ) {
					commandImportChannel();
				} else if( type.equalsIgnoreCase( TYPE_CONFIG ) ) {
					commandImportConfig();
				} else if( type.equalsIgnoreCase( TYPE_TEMPLATE ) ) {
					commandImportTemplate();
				} else if( type.equalsIgnoreCase( TYPE_SCRIPT ) ) {
					if( script.length() > 0 ) {
						commandImportScript();	
					} else {
						throw( new BuildException( "No script type specified" ) );
					}
				} else {
					throw( new BuildException( "Invalid Import Type specified: " + type ) );
				}
			} else {
				throw( new BuildException( "No filename specified" ) );
			}
			
		} 
		catch( ClientException e ) {
			throw( new BuildException( "Mirth client exception caught: " + e.getMessage(), e ) );
		} 
	}
	
	
	
	private void commandImportChannel() throws ClientException, BuildException 
	{
		connectClient();
		
		File fXml = new File( filename );
		
		if( !fXml.exists() ) {
			throw( new BuildException( "" + filename + " not found" ) );
		} else if( !fXml.canRead() ) {
			throw( new BuildException( "cannot read " + filename ) );
		} else {
			doImportChannel( fXml, force );
		}
		
		disconnectClient();
	}
	
	private void commandImportTemplate() throws ClientException, BuildException 
	{
		connectClient();
		
		File fXml = new File( filename );
		
		if( !fXml.exists() ) {
			throw( new BuildException( "" + filename + " not found" ) );
		} else if( !fXml.canRead() ) {
			throw( new BuildException( "cannot read " + filename ) );
		} else {
			doImportTemplate( fXml );
		}
		
		disconnectClient();
		
	}
	
	
	private void commandImportConfig() throws ClientException, BuildException 
	{
		connectClient();
		
		File fXml = new File( filename );
		
		if( !fXml.exists() ) {
			throw( new BuildException( "" + filename + " not found" ) );
		} else if (!fXml.canRead()) {
			throw( new BuildException( "cannot read " + filename ) );
		} else {
			ObjectXMLSerializer serializer = new ObjectXMLSerializer();
			try {
				client.setServerConfiguration( ( ServerConfiguration)serializer.fromXML( readFile( fXml ) ) );
			} 
			catch( IOException e ) {
				throw( new BuildException( "cannot read " + filename ) );
			}
		}
		
		System.out.println( "Configuration Import Complete." );
		
		disconnectClient();
	}
	
	
	private void commandImportScript() throws ClientException, BuildException 
	{
		connectClient();
		
		if( script.equalsIgnoreCase( SCRIPT_DEPLOY ) ) {
			script = SCRIPT_DEPLOY;
		} else if( script.equalsIgnoreCase( SCRIPT_PRE ) ) {
			script = SCRIPT_PRE;
		} else if ( script.equalsIgnoreCase( SCRIPT_POST ) ) {
			script = SCRIPT_POST;
		} else if( script.equalsIgnoreCase( SCRIPT_SHUTDOWN ) ) {
			script = SCRIPT_SHUTDOWN;
		} else {
			throw( new BuildException( "Invalid Script Type specified: " + script ) );
		}
		
		File fXml = new File( filename );
		
		if( !fXml.exists() ) {
			throw( new BuildException( "" + filename + " not found" ) );
		} else if( !fXml.canRead() ) {
			throw( new BuildException( "Cannot read " + filename ) );
		} else {
			doImportScript( script, fXml );
		}
		
		System.out.println( script + " script import complete" );
		
		disconnectClient();
	}
	
	
	private void doImportScript( String name, File scriptFile ) throws ClientException, BuildException  
	{
		String script = "";
		
		try {
			script = readFile( scriptFile) ;
		} 
		catch( Exception e ) {
			throw( new BuildException( "Invalid script file." ) );
		}
		
		Map<String, String> scriptMap = new HashMap<String, String>();
		
		scriptMap.put( name, script );
		client.setGlobalScripts( scriptMap );
	}
	
	private void doImportTemplate( File importFile) throws ClientException, BuildException
	{
		String templateXML = "";
		try {
			templateXML = readTemplateFile(importFile);
		}catch(Exception e1) {
			throw new BuildException("Invalid code template file.");
		}
		
		ObjectXMLSerializer serializer = new ObjectXMLSerializer();
        List<CodeTemplate> templates = new ArrayList<CodeTemplate>();
        try {
        	templates =  (List<CodeTemplate>)serializer.fromXML(templateXML);
        }catch(Exception e) {
        	throw new BuildException("Invalid code template file.");
        }
        client.updateCodeTemplates(templates);
        
        System.out.println("Code templates imported successfully.");
		
	}
	
	
	private void doImportChannel( File importFile, boolean force ) throws ClientException, BuildException 
	{
		String channelXML = "";
		
		try {			
			channelXML = ImportConverter.convertChannelFile( importFile );
		} 
		catch( Exception e1 ) {
			throw( new BuildException( "Invalid channel file." ) );
		}
		
		ObjectXMLSerializer serializer = new ObjectXMLSerializer();
		Channel importChannel;
		
		try {			
			importChannel = (Channel)serializer.fromXML( channelXML.replaceAll( "\\&\\#x0D;\\n", "\n" ).replaceAll( "\\&\\#x0D;", "\n" ) );
			PropertyVerifier.checkChannelProperties( importChannel );
			//System.out.println("----- Check 2.2-----");
			try{
			PropertyVerifier.checkConnectorProperties(importChannel, client.getConnectorMetaData() );
			} catch (Exception err){
				
			}
			//System.out.println("----- Check 2.3-----");			
		} 
		catch( Exception e ) {
			throw( new BuildException( "invalid channel file." ) );
		}
		
		String channelName = importChannel.getName();
		
		if( !checkChannelName( channelName, client.getGuid() ) ) {
			if( !force ) {
				throw( new BuildException( "Channel already exists: " + importChannel.getName() ) );
			} else {
				for( Channel channel : client.getChannel( null ) ) {
					if( channel.getName().equalsIgnoreCase( channelName ) ) {
						importChannel.setId( channel.getId() );
					}
				}
			}
		}
		importChannel.setVersion( client.getVersion() );
		client.updateChannel( importChannel, true );
		
		System.out.println( "Channel '" + channelName + "' imported successfully." );
	}
	
	
	public boolean checkChannelName( String name, String id ) throws ClientException 
	{
		boolean ret = true;
		
		if( name.equals( "" ) ) {
			ret = false;
		} else {
			for( Channel channel : client.getChannel( null ) ) {
				if( channel.getName().equalsIgnoreCase( name ) && !channel.getId().equals( id ) ) {
					ret = false;
					break;
				}
			}
		}
		
		return( ret );
	}
	
	public static String readTemplateFile(File file) throws IOException 
	{
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
		StringBuilder contents = new StringBuilder();
	    char[] buf = new char[1024];
        int numRead = 0;
		while ((numRead=reader.read(buf))!= -1) {
			String readData = String.valueOf(buf,0,numRead);
			contents.append(readData);
			buf = new char[1024];
		}
		reader.close();
		
		return contents.toString();
	}
	
	public static String readFile( File file ) throws IOException 
	{
		BufferedReader reader = new BufferedReader( new FileReader( file ) );
		StringBuilder contents = new StringBuilder();
		String line = null;
		
		try {
			while( ( line = reader.readLine() ) != null)  {
				contents.append( line + "\n" );
			}
		} finally {
			reader.close();
		}
		
		return( contents.toString() );
	}
	
}