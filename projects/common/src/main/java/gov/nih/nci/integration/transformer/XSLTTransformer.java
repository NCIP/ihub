package gov.nih.nci.integration.transformer;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;

/**
 * @author chandrasekaravr
 */
public class XSLTTransformer {

    /**
     * Transformer using XSLT.
     */
    private Transformer transformer = null;
    
    private TransformerFactory factory = null;

    /**
     * Constructor
     *
     * @param transformer xslt transformer
     */
    public XSLTTransformer(Transformer transformer) {
        this.transformer = transformer;   
        this.factory = null;
    }
    
    /**
     * Constructor
     *
     * @param TransformerFactory xslt transformer factory
     */
    public XSLTTransformer(TransformerFactory transformerFactory) {
        this.factory = transformerFactory;        
    }
   
    /**
     * Initializes the transformer to be used 
     * @param xslFileName - xslFileName for the transformer
     * @param xslBasePath - Base xsl file path for resolver
     * @throws IntegrationException - exception thrown if any
     */
    public void initTransformer(String xslFileName, String xslBasePath) throws IntegrationException {
    	if(factory == null) {
    		throw new IntegrationException(IntegrationError._1060);
    	}
    	if(StringUtils.isEmpty(xslFileName)) {
    		throw new IntegrationException(IntegrationError._1061);
    	}
    	if(StringUtils.isEmpty(xslBasePath)) {
    		xslBasePath = ".";
    	}	
    
    	try {
			transformer = factory.newTransformer(factory.getURIResolver().resolve(xslFileName, xslBasePath));
		} catch (TransformerConfigurationException e) {
			throw new IntegrationException(IntegrationError._1025, e);
		} catch (TransformerException e) {
			throw new IntegrationException(IntegrationError._1026, e);
		}
    }

    /**
     * Performs xsl transformation on source XML
     *
     * @param params the set of parameters to pass to the transformation, may be null
     * @param in XML input stream
     * @param out XML output stream
     * @throws IntegrationException exception
     */
    public void transform(Map<String, String> params, InputStream in, OutputStream out) throws IntegrationException {
        if (params != null) {
            for (Entry<String, String> entry : params.entrySet()) {
                transformer.setParameter(entry.getKey(), entry.getValue());
            }
        }  
     
        try {
			transformer.transform(new StreamSource(in), new StreamResult(out));
		} catch (TransformerException e) {
			throw new IntegrationException(IntegrationError._1027, e);
		}
    }
}

