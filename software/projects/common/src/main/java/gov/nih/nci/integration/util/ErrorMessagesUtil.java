package gov.nih.nci.integration.util;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Util class to get the error message for the error code for the current locale
 * 
 * @author chandrasekaravr
 * 
 */
public final class ErrorMessagesUtil {

    private static ResourceBundle rb = null;

    private ErrorMessagesUtil() {
        // private constructor
    }

    /**
     * Utility method to initialize the resource bundle
     * 
     * @param msgsLocation - Custom location where the messages folder is present with the list of 'errorMessages' for
     *            each locale
     */
    public static void init(String msgsLocation) {
        try {
            CustomUrlClassLoader ccl = new CustomUrlClassLoader(
                    CustomUrlClassLoader.class.getClassLoader().getParent(), msgsLocation);
            rb = ResourceBundle.getBundle("messages.errorMessages", Locale.getDefault(), ccl);
        } catch (MalformedURLException e) {
        }
    }

    /**
     * Returns the error message string for the error code
     * 
     * @param errorCode - String value representing the error code
     * @return String value representing the error message
     */
    public static String getMessage(String errorCode) {
        // if ResourceBundle is not loaded correctly load the default one in the classpath
        if (rb == null) {
            rb = ResourceBundle.getBundle("messages.errorMessages");
        }
        return rb.getString(errorCode);
    }
}
