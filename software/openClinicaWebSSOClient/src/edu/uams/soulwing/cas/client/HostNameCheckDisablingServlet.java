/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package edu.uams.soulwing.cas.client;

import javax.servlet.http.HttpServlet;

import javax.net.ssl.HttpsURLConnection;

import javax.servlet.ServletConfig;

import javax.servlet.ServletException;


/**
 * @author Dan Sobkoviak
 * Initialize webapp to disable host name checking
 *
 */
public class HostNameCheckDisablingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException { 

	HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
	}
}
