package com.izforge.izpack.panels;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import com.izforge.izpack.adaptator.IXMLElement;
import com.izforge.izpack.adaptator.IXMLParser;
import com.izforge.izpack.adaptator.impl.XMLParser;
import com.izforge.izpack.installer.InstallData;
import com.izforge.izpack.installer.InstallerFrame;
import com.izforge.izpack.installer.IzPanel;

/**
 * This class is an IzPack custom panel that can be used to extract the following information
 * from a JBoss MySQL datasource configuration XML file, and set the respective specified
 * IzPack variables:
 * <ul>
 * <li>hostname</li>
 * <li>port</li>
 * <li>database name</li>
 * <li>username</li>
 * <li>password</li>
 * </ul>
 *
 * The panel does not display in the GUI, it is only used to do extract the info and set the
 * specified respective IzPack variables. Panels are configured via properties in the
 * MySQLDatabaseInfoExtractionPanel.properties file, specified on a per-instance basis like
 * this: <instanceNumber>.<property name>.  Here is an example configuration:
 * <ul>
 * <li>1.application.base.path.variable.name=existing.installation.home</li>
 * <li>1.datasource.xml.file.path.relative.to.application.base.path=jboss-4.0.5.GA/server/default/deploy/caarray-mysql-ds.xml</li>
 * <li>1.database.server.variable.name=database.server</li>
 * <li>1.database.port.variable.name=database.port</li>
 * <li>1.database.name.variable.name=database.name</li>
 * <li>1.database.user.variable.name=database.user</li>
 * <li>1.database.password.variable.name=database.password</li>
 * </ul>
 *
 * For a complete example of how this panel can be used, see the IzPack installer code here:
 * https://gforge.nci.nih.gov/svnroot/caarray2/trunk/software/gui-installer
 *
 * @author harleyda@mail.nih.gov
 *
 */
public class MySQLDatabaseInfoExtractionPanel extends IzPanel {

    protected int instanceNumber;

    protected static int instanceCount = 0;

    public static final String CONFIGURATION_PROPERTIES_FILE = "MySQLDatabaseInfoExtractionPanel.properties";
    public static final String APP_BASE_PATH_VARIABLE_NAME_PROPERTY_NAME = "application.base.path.variable.name";
    public static final String DATASOURCE_XML_FILE_PATH_RELATIVE_TO_APP_BASE_PATH_PROPERTY_NAME = "datasource.xml.file.path.relative.to.application.base.path";
    public static final String DATABASE_SERVER_VARIABLE_NAME_PROPERTY_NAME = "database.server.variable.name";
    public static final String DATABASE_PORT_VARIABLE_NAME_PROPERTY_NAME = "database.port.variable.name";
    public static final String DATABASE_NAME_VARIABLE_NAME_PROPERTY_NAME = "database.name.variable.name";
    public static final String DATABASE_USER_VARIABLE_NAME_PROPERTY_NAME = "database.user.variable.name";
    public static final String DATABASE_PASSWORD_VARIABLE_NAME_PROPERTY_NAME = "database.password.variable.name";
    private static final String LOCAL_TX_DATASOURCE_ELEMENT_NAME = "local-tx-datasource";
    private static final String CONNECTION_URL_ELEMENT_NAME = "connection-url";
    private static final String USER_NAME_ELEMENT_NAME = "user-name";
    private static final String PASSWORD_ELEMENT_NAME = "password";
    private static final String SLASH = "/";
    private static final String COLON = ":";
    private static final String DOUBLE_SLASH = "//";
    private static final String DOT_STRING = ".";
    private static final String NULL_STRING = "";

    public MySQLDatabaseInfoExtractionPanel(final InstallerFrame parent, final InstallData idata) {
        super(parent, idata);
        instanceCount++;
        this.instanceNumber = instanceCount;
    }

    public void panelActivate() {
        extractMySqlDatabaseInfo();
        parent.skipPanel();
    }

    private Properties getInstancePtoperties() {
        Properties instanceProperties = new Properties();
        try {
            Properties allProperties = new Properties();
            allProperties.load(parent.getResource(CONFIGURATION_PROPERTIES_FILE));
            for(Enumeration propertyNamesEnumeration = allProperties.keys(); propertyNamesEnumeration.hasMoreElements();) {
                String propertyName = (String)propertyNamesEnumeration.nextElement();
                String instanceIdentifier = instanceNumber + DOT_STRING;
                if(propertyName.startsWith(instanceIdentifier)) {
                    String cleanedPropertyName = propertyName.replaceAll(instanceIdentifier, NULL_STRING);
                    String propertyValue = allProperties.getProperty(propertyName);
                    instanceProperties.put(cleanedPropertyName, propertyValue);
                }
            }
        }
        catch(Exception exception) {
            System.err.println("Cannot get instance properties: " + exception.getMessage());
            exception.printStackTrace(System.err);
        }
        return instanceProperties;
    }

    private void extractMySqlDatabaseInfo() {
        Properties instanceProperties = getInstancePtoperties();
        IXMLElement datasourcesXMLElement = parseDatasourceXmlFile(instanceProperties);
        IXMLElement localTxDatasourceXMLElement = datasourcesXMLElement.getChildrenNamed(LOCAL_TX_DATASOURCE_ELEMENT_NAME).get(0);
        IXMLElement connectionUrlXMLElement = localTxDatasourceXMLElement.getChildrenNamed(CONNECTION_URL_ELEMENT_NAME).get(0);
        String connectionUrl = connectionUrlXMLElement.getContent();
        IXMLElement userNameXMLElement = localTxDatasourceXMLElement.getChildrenNamed(USER_NAME_ELEMENT_NAME).get(0);
        String userName = userNameXMLElement.getContent();
        String databaseUserIzPackVariableName = instanceProperties.getProperty(DATABASE_USER_VARIABLE_NAME_PROPERTY_NAME);
        IXMLElement passwordXMLElement = localTxDatasourceXMLElement.getChildrenNamed(PASSWORD_ELEMENT_NAME).get(0);
        String password = passwordXMLElement.getContent();
        String databasePasswordIzPackVariableName = instanceProperties.getProperty(DATABASE_PASSWORD_VARIABLE_NAME_PROPERTY_NAME);
        String hostName = getHostNameFromConnectionUrl(connectionUrl);
        String databaseServerIzPackVariableName = instanceProperties.getProperty(DATABASE_SERVER_VARIABLE_NAME_PROPERTY_NAME);
        String port = getPortFromConnectionUrl(connectionUrl);
        String databasePortIzPackVariableName = instanceProperties.getProperty(DATABASE_PORT_VARIABLE_NAME_PROPERTY_NAME);
        String databaseName = getDatabaseNameFromConnectionUrl(connectionUrl);
        String databaseNameIzPackVariableName = instanceProperties.getProperty(DATABASE_NAME_VARIABLE_NAME_PROPERTY_NAME);
        idata.setVariable(databaseUserIzPackVariableName, userName);
        idata.setVariable(databasePasswordIzPackVariableName, password);
        idata.setVariable(databaseServerIzPackVariableName, hostName);
        idata.setVariable(databasePortIzPackVariableName, port);
        idata.setVariable(databaseNameIzPackVariableName, databaseName);
    }

    private IXMLElement parseDatasourceXmlFile(Properties instanceProperties) {
    	IXMLElement rootXMLElement = null;
        FileInputStream fileInputStream = null;
        try {
            String applicationBasePath = idata.getVariable(instanceProperties.getProperty(APP_BASE_PATH_VARIABLE_NAME_PROPERTY_NAME));
            String datasourceXmlFilePathRelativeToApplicationBasePath = instanceProperties.getProperty(DATASOURCE_XML_FILE_PATH_RELATIVE_TO_APP_BASE_PATH_PROPERTY_NAME);
            fileInputStream = new FileInputStream(new File(applicationBasePath + File.separator + datasourceXmlFilePathRelativeToApplicationBasePath));

            /*
            StdXMLParser parser = new StdXMLParser();

            parser.setBuilder(XMLBuilderFactory.createXMLBuilder());
            parser.setValidator(new NonValidator());
            parser.setReader(new StdXMLReader(fileInputStream));
            rootXMLElement = (IXMLElement) parser.parse();
            */
            IXMLParser parser = new XMLParser();
            rootXMLElement = parser.parse(fileInputStream);
        }
        catch(Exception exception) {
            System.err.println("Cannot parse datasource XML file: " + exception.getMessage());
            exception.printStackTrace(System.err);
        }
        finally {
            if(null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException ioException) {
                    System.err.println("Cannot close FileInputStream for datasource XML file: " + ioException.getMessage());
                    ioException.printStackTrace(System.err);
                }
            }
        }
        return rootXMLElement;
    }

    private String getHostNameFromConnectionUrl(String connectionUrl) {
        int index1 = connectionUrl.indexOf(DOUBLE_SLASH);
        int index2 = connectionUrl.lastIndexOf(COLON);
        return connectionUrl.substring(index1 + 2, index2);
    }

    private String getPortFromConnectionUrl(String connectionUrl) {
        int index1 = connectionUrl.lastIndexOf(COLON);
        int index2 = connectionUrl.lastIndexOf(SLASH);
        return connectionUrl.substring(index1 + 1, index2);
    }

    private String getDatabaseNameFromConnectionUrl(String connectionUrl) {
        int index = connectionUrl.lastIndexOf(SLASH);
        return connectionUrl.substring(index + 1, connectionUrl.length());
    }

}