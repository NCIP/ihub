package com.izforge.izpack.panels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Properties;

import com.izforge.izpack.installer.InstallData;
import com.izforge.izpack.installer.InstallerFrame;
import com.izforge.izpack.installer.IzPanel;

/**
 * This class is an IzPack custom panel that can be used to compare the string contents
 * of a column (specified with SQL) with a user-specified string threshold value. The
 * panel does not display in the GUI, it is only used to do logic and set the specified
 * IzPack variable to the specified value if the comparison if true. The panel supports
 * the following comparison operators:
 * <ul>
 * <li>equals</li>
 * <li>lessThan</li>
 * <li>greaterThan</li>
 * <li>lessThanOrEquals</li>
 * <li>greaterThanOrEquals</li>
 * </ul>
 *
 * Panels are configured via properties in the MySQLColumnValueAnalyzerPanel.properties file,
 * specified on a per-instance basis like this: <instanceNumber>.<property name>.  Here is an
 * example configuration:
 * <ul>
 * <li>1.operator=lessThan</li>
 * <li>1.threshold.value=2.2.0</li>
 * <li>1.sql=select raw_value from config_parameter where param \= 'SCHEMA_VERSION';</li>
 * <li>1.database.server.variable.name=database.server</li>
 * <li>1.database.port.variable.name=database.port</li>
 * <li>1.database.name.variable.name=database.name</li>
 * <li>1.database.user.variable.name=database.user</li>
 * <li>1.database.password.variable.name=database.password</li>
 * <li>1.izpack.variable.to.set=should.show.duplicate.hybridization.details.panel.variable.name</li>
 * <li>1.izpack.variable.value.to.set=true</li>
 * </ul>
 *
 * For a complete example of how this panel can be used, see the IzPack installer code here:
 * https://gforge.nci.nih.gov/svnroot/caarray2/trunk/software/gui-installer
 *
 * @author harleyda@mail.nih.gov
 *
 */
public class MySQLColumnValueAnalyzerPanel extends IzPanel {

    protected int instanceNumber;

    protected static int instanceCount = 0;

    public static final String CONFIGURATION_PROPERTIES_FILE = "MySQLColumnValueAnalyzerPanel.properties";
    public static final String OPERATOR_PROPERTY_NAME = "operator";
    public static final String EQUALS_OPERATOR_IDENTIFIER = "EQUALS";
    public static final String LESS_THAN_OPERATOR_IDENTIFIER = "LESS_THAN";
    public static final String GREATER_THAN_OPERATOR_IDENTIFIER = "GREATER_THAN";
    public static final String LESS_THAN_OR_EQUALS_OPERATOR_IDENTIFIER = "LESS_THAN_OR_EQUALS";
    public static final String GREATER_THAN_OR_EQUALS_OPERATOR_IDENTIFIER = "GREATER_THAN_OR_EQUALS";
    public static final String THRESHOLD_VALUE_PROPERTY_NAME = "threshold.value";
    public static final String SQL_PROPERTY_NAME = "sql";
    public static final String DATABASE_SERVER_VARIABLE_NAME_PROPERTY_NAME = "database.server.variable.name";
    public static final String DATABASE_PORT_VARIABLE_NAME_PROPERTY_NAME = "database.port.variable.name";
    public static final String DATABASE_NAME_VARIABLE_NAME_PROPERTY_NAME = "database.name.variable.name";
    public static final String DATABASE_USER_VARIABLE_NAME_PROPERTY_NAME = "database.user.variable.name";
    public static final String DATABASE_PASSWORD_VARIABLE_NAME_PROPERTY_NAME = "database.password.variable.name";
    public static final String IZPACK_VARIABLE_TO_SET_PROPERTY_NAME = "izpack.variable.to.set";
    public static final String IZPACK_VARIABLE_VALUE_TO_SET_PROPERTY_NAME = "izpack.variable.value.to.set";
    public static final String DEFAULT_IZPACK_VARIABLE_VALUE_TO_SET_PROPERTY_NAME = "true";
    private static final String MYSQL_DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DOT_STRING = ".";
    private static final String NULL_STRING = "";

    public MySQLColumnValueAnalyzerPanel(final InstallerFrame parent, final InstallData idata) {
        super(parent, idata);
        instanceCount++;
        this.instanceNumber = instanceCount;
    }

    public void panelActivate() {
        analyzeColumnValue();
        parent.skipPanel();
    }

    private void analyzeColumnValue() {
        Properties instanceProperties = getInstancePtoperties();
        String databaseColumnValue = getDatabaseColumnValue(instanceProperties);
        if(null == databaseColumnValue || databaseColumnValue.trim().length() == 0) {
            System.err.println("No column data returned, so no analysis will be done.");
            return;
        }
        String thresholdValue = instanceProperties.getProperty(THRESHOLD_VALUE_PROPERTY_NAME);
        String operator = instanceProperties.getProperty(OPERATOR_PROPERTY_NAME);
        boolean conditionWasMet = OperatorEnum.valueOf(operator).evaluate(databaseColumnValue, thresholdValue);
        if(conditionWasMet) {
            String izPackVariableToSet = instanceProperties.getProperty(IZPACK_VARIABLE_TO_SET_PROPERTY_NAME);
            String izPackVariableValueToSet = instanceProperties.getProperty(IZPACK_VARIABLE_VALUE_TO_SET_PROPERTY_NAME);
            if(null == izPackVariableValueToSet || izPackVariableValueToSet.trim().length() == 0) {
                izPackVariableValueToSet = DEFAULT_IZPACK_VARIABLE_VALUE_TO_SET_PROPERTY_NAME;
            }
            idata.setVariable(izPackVariableToSet, izPackVariableValueToSet);
        }
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

    private String getDatabaseColumnValue(final Properties instanceProperties) {
        String databaseColumnValue = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String databaseUsername = idata.getVariable(instanceProperties.getProperty(DATABASE_USER_VARIABLE_NAME_PROPERTY_NAME));
            String databasePassword = idata.getVariable(instanceProperties.getProperty(DATABASE_PASSWORD_VARIABLE_NAME_PROPERTY_NAME));
            String databaseName = idata.getVariable(instanceProperties.getProperty(DATABASE_NAME_VARIABLE_NAME_PROPERTY_NAME));
            String databasePort = idata.getVariable(instanceProperties.getProperty(DATABASE_PORT_VARIABLE_NAME_PROPERTY_NAME));
            String databaseServer = idata.getVariable(instanceProperties.getProperty(DATABASE_SERVER_VARIABLE_NAME_PROPERTY_NAME));
            String databaseUrl = "jdbc:mysql://" + databaseServer + ":" + databasePort + "/" + databaseName;
            String sql = instanceProperties.getProperty(SQL_PROPERTY_NAME);
            Class.forName(MYSQL_DRIVER_NAME);
            connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            databaseColumnValue = resultSet.getString(1);
        }
        catch(Exception exception) {
            System.err.println("Cannot get database column value: " + exception.getMessage());
            exception.printStackTrace(System.err);
        }
        finally {
            try {
                if(null != resultSet) {
                    resultSet.close();
                }
                if(null != statement) {
                    statement.close();
                }
                if(null != connection) {
                    connection.close();
                }
            }
            catch(Exception exception) {
                System.err.println("Cannot close database resources: " + exception.getMessage());
                exception.printStackTrace(System.err);
            }
        }
        return databaseColumnValue;
    }

    public static enum OperatorEnum {

        EQUALS {
            public boolean evaluate(String stringToCompare, String stringToCompareAgainst) {
                return stringToCompare.equalsIgnoreCase(stringToCompareAgainst);
            }
        },

        GREATER_THAN {
            public boolean evaluate(String stringToCompare, String stringToCompareAgainst) {
                return stringToCompare.compareTo(stringToCompareAgainst) > 0;
            }
        },

        LESS_THAN {
            public boolean evaluate(String stringToCompare, String stringToCompareAgainst) {
                return stringToCompare.compareTo(stringToCompareAgainst) < 0;
            }
        },

        GREATER_THAN_OR_EQUALS {
            public boolean evaluate(String stringToCompare, String stringToCompareAgainst) {
                return stringToCompare.equalsIgnoreCase(stringToCompareAgainst) || stringToCompare.compareTo(stringToCompareAgainst) > 0;
            }
        },

        LESS_THAN_OR_EQUALS {
            public boolean evaluate(String stringToCompare, String stringToCompareAgainst) {
                return stringToCompare.equalsIgnoreCase(stringToCompareAgainst) || stringToCompare.compareTo(stringToCompareAgainst) < 0;
            }
        };

        private String operatorIdentifierString;

        public abstract boolean evaluate(String stringToCompare, String stringToCompareAgainst);

    }

}