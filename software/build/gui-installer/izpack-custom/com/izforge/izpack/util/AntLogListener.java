/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package com.izforge.izpack.util;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildListener;

public class AntLogListener implements BuildListener {

	public void buildFinished(BuildEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Build " + arg0.getProject().getName() +  " finished!");
	}

	public void buildStarted(BuildEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Build " + arg0.getProject().getName() +  " Statted!");
	}

	public void messageLogged(BuildEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void targetFinished(BuildEvent arg0) {
		String targetName=arg0.getTarget().getName();
		System.out.println("Target " + targetName +  " finished!");
	    if(targetName != null && targetName.equals("install-application"))
		{
			LogFileTailer.tailing=false;
		}
	}

	public void targetStarted(BuildEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void taskFinished(BuildEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void taskStarted(BuildEvent arg0) {
		// TODO Auto-generated method stub

	}

}
