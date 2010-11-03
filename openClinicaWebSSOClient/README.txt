Package to enable webapps using tomcat's declarative security, such as OpenClinica to participate in single sign-on with the CCTS suite, while preserving the authorization/permissions that are configured. 

Prerequisites
1)	A working CCTS installation
2)	A working OpenClinica installation
3)      Soulwing cas client v 0.5.3
4)	An OpenClinica user corressponding to the caGrid user should be created. This user should have administrator rights in OpenClinica so that they can add new OpenClinica users corressponding to new caGrid users.   

Build steps:
1.  JDK 1.5 or greater, ant 1.6.x or greater installed
2.  Download soulwing cas client v 0.5.3 
3.  Unzip soulwing websso package to <SW-WEBSSO>
4.  Copy the following dependencies from soulwing cas client into <SW-WEBSSO>/lib:
    catalina.jar
    commons-logging.jar
    servlet-api.jar
    soulwing-casclient-0.5.3.jar
    soulwing-casclient-tomcat-ext-0.5.3.jar

5.  Run the following command to build:
	ant dist

Configuration steps:

1.Generate the certificate and key for the OpenClinica server with the GAARDS utility – refer to the CCTS 1.1 installation guide
2.Generate the webSSO keystore – refer to the CCTS 1.1 installation guide
3.Enable the OpenClinica tomcat container for SSL:
  a.Add an entry similar to the following, replacing keystore parameters, to the <CATALINA_HOME>/conf/server.xml – refer to the tomcat SSL documentation for more information
  <Connector port="8443" maxHttpHeaderSize="8192"
	               maxThreads="150" minSpareThreads="25" maxSpareThreads="75"
	               enableLookups="false" disableUploadTimeout="true"
	               acceptCount="100" debug="0" scheme="https" secure="true"
	               clientAuth="false" sslProtocol="TLS" 
	               keystoreFile="C:\yourkeystoredirectory\webSSOKeystore"
                       keystorePass, such as ="webSSOkeystorePasswd"  />

  b.Perform step 7 “Establish trust with WebSSO CAS Server“ of the webSSO developers guide which can be found at 	
    http://www.cagrid.org/display/websso12/Developers+Guide#DevelopersGuide-Step7%3AEstablishTrustwithWebSSOCASServer
4.Follow soulwing client directions at 	http://www.soulwing.org/tomcat-cas.jsp with the following modifications, and remember to change the login-config entry in the web.xml to  
  <auth-method>CAS</auth-method>
  a) In the "Install library JAR files" sections, add the following jar copies:
     - uams-soulwing-casclient.jar to <CATALINA_HOME>/common/lib
     - uams-soulwing-websso-casclient-tomcat-ext.jar to <CATALINA_HOME>/server/lib       
  b) In the "Augment Tomcat's default authenticators" section replace
	CAS=org.soulwing.cas.apps.tomcat.CasAuthenticator
      with
	CAS=edu.uams.soulwing.cas.apps.tomcat.CasAuthenticator
  c) In the "Configure a resource Valve" section, the entry should be:
    <Valve className="org.soulwing.cas.apps.tomcat.ResourceValve"
	      config="CasProtocolConfiguration"
	      authenticatorClass="org.soulwing.cas.filter.ServiceValidationAuthenticator" />


5.Add the following servlet entry to the web.xml:
    <servlet>
        <servlet-name>HostNameCheckDisablingServlet</servlet-name>
        <display-name>HostNameCheckDisablingServlet</display-name>
        <description>Address host name check issue</description>
        <servlet-class>edu.uams.soulwing.cas.client.HostNameCheckDisablingServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
