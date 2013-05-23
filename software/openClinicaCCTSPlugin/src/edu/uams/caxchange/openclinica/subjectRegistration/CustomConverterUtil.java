/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package edu.uams.caxchange.openclinica.subjectRegistration;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis2.databinding.utils.ConverterUtil;

public class CustomConverterUtil extends ConverterUtil {
	
	public static String defaultFormat = "yyyy-MM-dd";
	
    public static String convertToString(Date value)
    {
    	SimpleDateFormat fmt = new SimpleDateFormat(defaultFormat);
    	return fmt.format(value);
    }

}
