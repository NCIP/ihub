/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
public class Mirth extends Thread {
    private Logger logger = Logger.getLogger(this.getClass());
    private boolean running = false;
    private Properties mirthProperties = null;
    private Properties versionProperties = null;
    private MuleManager muleManager = null;
    private Server httpServer = null;
    private Server servletContainer = null;
    private CommandQueue commandQueue = CommandQueue.getInstance();
    private MirthManager manager = new MirthManager();

    private ConfigurationController configurationController = ControllerFactory.getFactory().createConfigurationController();
    private ChannelController channelController = ControllerFactory.getFactory().createChannelController();
    private UserController userController = ControllerFactory.getFactory().createUserController();
    private MessageObjectController messageObjectController = ControllerFactory.getFactory().createMessageObjectController();
    private ChannelStatisticsController channelStatisticsController = ControllerFactory.getFactory().createChannelStatisticsController();
    private ExtensionController extensionController = ControllerFactory.getFactory().createExtensionController();
    private MigrationController migrationController = ControllerFactory.getFactory().createMigrationController();
    private MonitoringController monitoringController = ControllerFactory.getFactory().createMonitoringController();
    private EventController eventController = ControllerFactory.getFactory().createEventController();

    public static void main(String[] args) {
        Mirth mirth = new Mirth();
        mirth.run();
        System.exit(0);
    }

    public MirthManager getManager() {
        return this.manager;
    }

    public void run() {
        if (initResources()) {
            logger.info("starting mirth server...");

            // Check the ports to see if they are already in use
            boolean httpPort = testPort(PropertyLoader.getProperty(mirthProperties, "http.port"), "http.port");
            boolean httpsPort = testPort(PropertyLoader.getProperty(mirthProperties, "https.port"), "https.port");
            boolean jmxPort = testPort(PropertyLoader.getProperty(mirthProperties, "jmx.port"), "jmx.port");

            if (!httpPort || !httpsPort || !jmxPort) {
                return;
            }

            running = true;

            // add the start command to the queue
            CommandQueue.getInstance().clear();
            CommandQueue.getInstance().addCommand(new Command(Command.Operation.START_SERVER));

            Runtime.getRuntime().addShutdownHook(new ShutdownHook());

            // pulls commands off of the command queue
            while (running) {
                Command command = commandQueue.getCommand();

                if (command.getOperation().equals(Command.Operation.START_SERVER)) {
                    startup();
                } else if (command.getOperation().equals(Command.Operation.SHUTDOWN_SERVER)) {
                    shutdown();
                } else if (command.getOperation().equals(Command.Operation.START_ENGINE)) {
                    startMule();
                } else if (command.getOperation().equals(Command.Operation.STOP_ENGINE)) {
                    stopMule();
                } else if (command.getOperation().equals(Command.Operation.RESTART_ENGINE)) {
                    restartMule();
                }
            }
        } else {
            logger.error("could not initialize resources");
        }
    }

    /**
     * Returns true if the resources required by the server have been
     * sucessfully loaded
     * 
     * @return true if the resources required by the server have been
     *         sucessfully loaded
     */
    public boolean initResources() {
        mirthProperties = PropertyLoader.loadProperties("mirth");
        versionProperties = PropertyLoader.loadProperties("version");

        return (mirthProperties != null);
    }

    /**
     * Starts up the server.
     * 
     */
    public void startup() {
        startWebServer();
        messageObjectController.removeAllFilterTables();
        eventController.removeAllFilterTables();
        extensionController.uninstallExtensions();
        migrationController.migrate();
        migrationController.migrateExtensions();
        channelStatisticsController.start();
        channelController.loadChannelCache();
        configurationController.loadEncryptionKey();
        userController.resetUserStatus();
        monitoringController.initPlugins();
        extensionController.startPlugins();

        // disable the velocity logging
        Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogSystem");
        startMule();
        printSplashScreen();
    }

    /**
     * Shuts down the server.
     * 
     */
    public void shutdown() {
        logger.info("shutting down mirth due to normal request");
        stopMule();
        channelStatisticsController.updateAllStatistics();
        // do one last update to the stats table
        stopWebServer();
        extensionController.stopPlugins();
        channelStatisticsController.shutdown();
        stopDatabase();
        running = false;
    }

    /**
     * Restarts the Mule server. This is accomplished by stopping and starting
     * the server.
     * 
     */
    private void restartMule() {
        logger.debug("retarting mule");
        stopMule();
        startMule();
    }

    /**
     * Starts the Mule server.
     * 
     */
    private void startMule() {
        String configurationFilePath = null;

        try {
            configurationFilePath = configurationController.getLatestConfiguration().getAbsolutePath();
        } catch (Exception e) {
            logger.error("Could not retrieve latest configuration.", e);
            return;
        }

        try {
            // clear global map and do channel deploy scripts if the user
            // specified to
            if (configurationController.getServerProperties().getProperty("server.resetglobalvariables") == null || configurationController.getServerProperties().getProperty("server.resetglobalvariables").equals("1"))
                GlobalVariableStore.getInstance().globalVariableMap.clear();
        } catch (Exception e) {
            logger.warn("Could not clear the global map.", e);
        }

        configurationController.setEngineStarting(true);

        try {
            logger.debug("starting mule with configuration file: " + configurationFilePath);

            // disables validation of Mule configuration files
            System.setProperty("org.mule.xml.validate", "false");
            VMRegistry.getInstance().rebuild();
            MuleXmlConfigurationBuilder builder = new MuleXmlConfigurationBuilder();

            List<Channel> channels = channelController.getChannel(null);
            configurationController.compileScripts(channels);

            configurationController.executeGlobalDeployScript();
            configurationController.executeChannelDeployScripts(channelController.getChannel(null));

            muleManager = (MuleManager) builder.configure(configurationFilePath);

        } catch (Exception e) {
            logger.warn("Error deploying channels.", e);

            // if deploy fails, log to system events
            SystemEvent event = new SystemEvent("Error deploying channels.");
            event.setLevel(SystemEvent.Level.HIGH);
            event.setDescription(StackTracePrinter.stackTraceToString(e));
            eventController.logSystemEvent(event);

            // remove the errant configuration
            configurationController.deleteLatestConfiguration();
        }

        configurationController.setEngineStarting(false);
    }

    /**
     * Stops the Mule server.
     * 
     */
    private void stopMule() {
        logger.debug("stopping mule");

        if (muleManager != null) {
            try {
                if (muleManager.isStarted()) {
                    configurationController.executeChannelShutdownScripts(channelController.getChannel(null));
                    configurationController.executeGlobalShutdownScript();

                    muleManager.stop();
                }
            } catch (Exception e) {
                logger.error(e);
            } finally {
                logger.debug("disposing mule instance");
                muleManager.dispose();
            }
        }
    }

    /**
     * Starts the Jetty web server.
     * 
     */
    private void startWebServer() {
        logger.debug("starting jetty web server");

        try {
            // this disables validaiton of the web.xml file
            // which causes exceptions when Mirth is run
            // behind a firewall and the resources cannot be
            // accessed
            System.setProperty("org.mortbay.xml.XmlParser.NotValidating", "true");
            // this disables a "form too large" error for occuring by setting
            // form size to infinite
            System.setProperty("org.mortbay.http.HttpRequest.maxFormContentSize", "0");

            httpServer = new Server();
            servletContainer = new Server();

            // add HTTPS listener
            SslSocketConnector sslListener = new SslSocketConnector();

            String ciphers = PropertyLoader.getProperty(mirthProperties, "https.ciphers");
            if (ciphers != null && !ciphers.equals("")) {
                //sslListener.setCipherSuites(ciphers.split(","));

            }

            sslListener.setPort(Integer.valueOf(PropertyLoader.getProperty(mirthProperties, "https.port")).intValue());
            sslListener.setKeystore(ControllerFactory.getFactory().createConfigurationController().getBaseDir() + System.getProperty("file.separator") + PropertyLoader.getProperty(mirthProperties, "https.keystore"));
            sslListener.setPassword(PropertyLoader.getProperty(mirthProperties, "https.password"));
            sslListener.setKeyPassword(PropertyLoader.getProperty(mirthProperties, "https.keypassword"));
            sslListener.setSslKeyManagerFactoryAlgorithm(PropertyLoader.getProperty(mirthProperties, "https.algorithm"));
            sslListener.setSslTrustManagerFactoryAlgorithm(PropertyLoader.getProperty(mirthProperties, "https.algorithm"));
            sslListener.setKeystoreType(PropertyLoader.getProperty(mirthProperties, "https.keystoretype"));
            servletContainer.addConnector(sslListener);

            // add HTTP listener
            SocketConnector listener = new SocketConnector();
            listener.setPort(Integer.valueOf(PropertyLoader.getProperty(mirthProperties, "http.port")).intValue());
            httpServer.addConnector(listener);

            // Load the context path property and remove the last char if it is
            // a '/'.
            String contextPath = PropertyLoader.getProperty(mirthProperties, "context.path");
            if (contextPath.lastIndexOf('/') == (contextPath.length() - 1)) {
                contextPath = contextPath.substring(0, contextPath.length() - 1);
            }

            // Create the lib context
            ContextHandler libContext = new ContextHandler();
            libContext.setContextPath(contextPath + "/client-lib");
            httpServer.addHandler(libContext);

            // Serve static content from the lib context
            File extensions = new File(ExtensionController.getExtensionsPath());
            String libPath = ControllerFactory.getFactory().createConfigurationController().getBaseDir() + System.getProperty("file.separator") + "client-lib";

            libContext.setResourceBase(libPath);
            libContext.addHandler(new ResourceHandler());

            // Create the extensions context
            ContextHandler extensionsContext = new ContextHandler();
            extensionsContext.setContextPath(contextPath + "/extensions");
            httpServer.addHandler(extensionsContext);

            // Serve static content from the extensions context
            String extensionsPath = extensions.getPath();
            extensionsContext.setResourceBase(extensionsPath);
            extensionsContext.addHandler(new ResourceHandler());

            // Create the public_html context
            ContextHandler publicContext = new ContextHandler();
            publicContext.setContextPath(contextPath + "");
            httpServer.addHandler(publicContext);

            String publicPath = ControllerFactory.getFactory().createConfigurationController().getBaseDir() + System.getProperty("file.separator") + "public_html";
            publicContext.setResourceBase(publicPath);
            publicContext.addHandler(new ResourceHandler());

            // Create a normal servlet container
            ServletHandler servlets = new ServletHandler();
            //ContextHandler servletContext = new ContextHandler();
            Context servletContext = new Context();
            servletContext.setContextPath(contextPath + "");
            servletContext.setServletHandler(servlets);
            httpServer.addHandler(servletContext);
            servlets.addServletWithMapping("com.webreach.mirth.server.servlets.WebStartServlet", "/webstart.jnlp");
            servlets.addServletWithMapping("com.webreach.mirth.server.servlets.ActivationServlet", "/activation.jnlp");
            //servlets.addServlet("WebStart", "/webstart.jnlp", "com.webreach.mirth.server.servlets.WebStartServlet");
            //servlets.addServlet("Activation", "/activation.jnlp", "com.webreach.mirth.server.servlets.ActivationServlet");
            // Servlets for backwards compatibility
            servlets.addServletWithMapping("com.webreach.mirth.server.servlets.WebStartServlet", "/webstart");
            servlets.addServletWithMapping("com.webreach.mirth.server.servlets.ActivationServlet", "/activation");
            //servlets.addServlet("WebStart", "/webstart", "com.webreach.mirth.server.servlets.WebStartServlet");
            //servlets.addServlet("Activation", "/activation", "com.webreach.mirth.server.servlets.ActivationServlet");

            // Create a secure servlet container
            ServletHandler secureServlets = new ServletHandler();
           //ContextHandler secureServletContext = new ContextHandler();
            Context secureServletContext = new Context();
            secureServletContext.setSessionHandler(new SessionHandler(new HashSessionManager()));
            secureServletContext.setContextPath(contextPath + "");
            secureServletContext.setServletHandler(secureServlets);
            servletContainer.addHandler(secureServletContext);
   
            // Map a servlet onto the container
   
            secureServlets.addServletWithMapping("com.webreach.mirth.server.servlets.AlertServlet", "/alerts");
            secureServlets.addServletWithMapping("com.webreach.mirth.server.servlets.ChannelServlet", "/channels");
            secureServlets.addServletWithMapping("com.webreach.mirth.server.servlets.ChannelStatisticsServlet","/channelstatistics");
            secureServlets.addServletWithMapping("com.webreach.mirth.server.servlets.ChannelStatusServlet","/channelstatus");
            secureServlets.addServletWithMapping("com.webreach.mirth.server.servlets.CodeTemplateServlet", "/codetemplates");
            secureServlets.addServletWithMapping("com.webreach.mirth.server.servlets.ConfigurationServlet", "/configuration");
            secureServlets.addServletWithMapping("com.webreach.mirth.server.servlets.MessageObjectServlet", "/messages");
            secureServlets.addServletWithMapping("com.webreach.mirth.server.servlets.ExtensionServlet", "/extensions");
            secureServlets.addServletWithMapping("com.webreach.mirth.server.servlets.SystemEventServlet", "/events");
            secureServlets.addServletWithMapping("com.webreach.mirth.server.servlets.UserServlet", "/users");
            /*
            secureServlets.addServlet("Alerts", "/alerts", "com.webreach.mirth.server.servlets.AlertServlet");
            secureServlets.addServlet("Channels", "/channels", "com.webreach.mirth.server.servlets.ChannelServlet");
            secureServlets.addServlet("ChannelStatistics", "/channelstatistics", "com.webreach.mirth.server.servlets.ChannelStatisticsServlet");
            secureServlets.addServlet("ChannelStatus", "/channelstatus", "com.webreach.mirth.server.servlets.ChannelStatusServlet");
            secureServlets.addServlet("CodeTemplates", "/codetemplates", "com.webreach.mirth.server.servlets.CodeTemplateServlet");
            secureServlets.addServlet("Configuration", "/configuration", "com.webreach.mirth.server.servlets.ConfigurationServlet");
            secureServlets.addServlet("MessageObject", "/messages", "com.webreach.mirth.server.servlets.MessageObjectServlet");
            secureServlets.addServlet("Extensions", "/extensions", "com.webreach.mirth.server.servlets.ExtensionServlet");
            secureServlets.addServlet("SystemEvent", "/events", "com.webreach.mirth.server.servlets.SystemEventServlet");
            secureServlets.addServlet("Users", "/users", "com.webreach.mirth.server.servlets.UserServlet");
            */
            
            // start the web server
            httpServer.start();
            servletContainer.start();

            logger.info("started jetty web server on ports: " + listener.getPort() + ", " + sslListener.getPort());
        } catch (Exception e) {
            logger.warn("Could not start web server.", e);
        }
    }

    /**
     * Stops the Jetty web server.
     * 
     */
    private void stopWebServer() {
        logger.debug("stopping jetty web server");

        try {
            httpServer.stop();
            servletContainer.stop();
        } catch (Exception e) {
            logger.warn("Could not stop web server.", e);
        }
    }

    private void stopDatabase() {
        String database = PropertyLoader.getProperty(mirthProperties, "database");

        if (database.equals("derby")) {
            boolean gotException = false;

            try {
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
            } catch (SQLException sqle) {
                if (sqle != null && sqle.getSQLState() != null && sqle.getSQLState().equals("XJ015")) {
                    gotException = true;
                }
            }

            if (gotException) {
                System.out.println("Database shut down normally.");
            }
        }
    }

    private class ShutdownHook extends Thread {
        public void run() {
            shutdown();
        }
    }

    /**
     * Displays the splash screen information, including the server version and
     * build date, to the system console.
     * 
     */
    private void printSplashScreen() {
        String version = PropertyLoader.getProperty(versionProperties, "mirth.version");
        String buildDate = PropertyLoader.getProperty(versionProperties, "mirth.date");
        System.out.println("Mirth Connect " + version + " (" + buildDate + ") server successfully started: " + (new Date()).toString());
        System.out.println("This product was developed by Mirth Corporation (http://www.mirthcorp.com) and its contributors (c)2005-" + Calendar.getInstance().get(Calendar.YEAR) + ".");
        System.out.println("Running Java " + System.getProperty("java.version") + " on " + System.getProperty("os.name") + " (" + System.getProperty("os.version") + ", " + System.getProperty("os.arch") + ") with charset " + Charset.defaultCharset() + ".");
    }

    /**
     * Test a port to see if it is already in use.
     * 
     * @param port
     *            The port to test.
     * @param name
     *            A friendly name to display in case of an error.
     * @return
     */
    private boolean testPort(String port, String name) {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(Integer.parseInt(port));
        } catch (NumberFormatException ex) {
            logger.error(name + " port is invalid: " + port);
            return false;
        } catch (IOException ex) {
            logger.error(name + " port is already in use: " + port);
            return false;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    logger.error("Could not close test socket for " + name + ": " + port);
                    return false;
                }
            }
        }
        return true;
    }

}