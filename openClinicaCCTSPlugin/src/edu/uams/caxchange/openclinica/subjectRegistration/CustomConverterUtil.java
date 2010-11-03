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
