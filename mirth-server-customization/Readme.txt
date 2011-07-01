This folder contains the changes made to the MirthConnect v1.8.2 code to make it work with Jetty 6.1. Jetty 6.1 is required to deploy on the embedded CXF service. The iHub installation scripts deploy the Jetty 6.1 jars onto MirthConnect and remove the Jetty jars included with the MirthConnect distribution.

The MirthConnect customization include modifications to the Mirth.java file included in this folder. This class is included in compiled form under the mirth-components/ihub-custom/mirth-server.jar file. 

The mirth-server.jar file can be recreated using the following steps:

- Check out the MirthConnect v1.8.2 tag from the following location http://www.mirthproject.org/svn/tags/1.8.2
- Replace the Mirth.java file in the check out version with file in this folder. The Mirth.java file will be located under server/src folder.
- Compile the MirthConnect code base to create the jar.