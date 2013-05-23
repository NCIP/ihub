/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package com.izforge.izpack.util;

public interface LogFileTailerListener
{
  /**
   * A new line has been added to the tailed log file
   * 
   * @param line   The new line that has been added to the tailed log file
   */
  public void newLogFileLine( String line );
}

