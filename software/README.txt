This project is for building the iHub project and its sub-projects present under the project folder

Starting a new project under iHub project
=========================================
1) To start with the new project, from the 'software' folder, run, "ant create-project -Dnew.project.name=<new_project_name>".
2) This will create a new project setup under 'projects' folder in the name '<new_project_name>'
3) You can localize the build.properties for your environment by copying it as local-build.properties
4) When you checkin, remember to svnignore build, bin and local-build.properties

Setting properties
===================
1) There are 4 property files loaded for each project in the following order
	a) software/projects/<project>/local-build.properties - Make a copy of the software/projects/<project>/build.properties with the properties required to be overridden for local environment for this specific <project>
	b) software/projects/<project>/build.properties - Properties for the <project>. Global properties from software/build.properties can be overridden here for this specific project <project>
	c) software/local-build.properties - Make a copy of the software/local-build.properties.template with the properties required to be overridden for local environment
	d) software/build.properties - Global properties common for all projects
	
Installation
===============

Please refer to the following deployment guide for instructions in the page TRANSCEND iHub Documentation, https://wiki.nci.nih.gov/display/TRANSCENDiHub/TRANSCEND+iHub