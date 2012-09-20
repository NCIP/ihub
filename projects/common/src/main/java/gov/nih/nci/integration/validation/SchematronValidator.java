package gov.nih.nci.integration.validation;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Schematron based validation for xml
 * @author <a href="mailto:vinodh.rc@semanticbits.com">Vinodh Chandrasekaran</a>
 *
 */
public class SchematronValidator {
    
    /**
     * The evaluateRules Transformer.
     */
    private final XSLTTransformer evaluateRulesTransformer;

    /**
     * The extractErrors Transformer.
     */
    private final XSLTTransformer extractFailuresTransformer;
    
    private static final Logger LOG = LoggerFactory.getLogger(SchematronValidator.class);
    
    /**
     * Constructor that takes transformers to evaluate rules and extract errors
     * @param evaluateRulesTransformer - XSL Transformer for evaluating rules
     * @param extractFailuresTransformer - XSL transformer for extracting errors
     * @param xslBasePath - Base xsl file path for resolver
     */
    public SchematronValidator(XSLTTransformer evaluateRulesTransformer, 
            XSLTTransformer extractFailuresTransformer, String xslBasePath) {
        super();
        this.evaluateRulesTransformer = evaluateRulesTransformer;
        this.extractFailuresTransformer = extractFailuresTransformer;
    }
    
    /**
     * Initializes the transformer to be used
     * 
     * @param schRulesXslFileName - schematron rules xsl FileName  for the transformer
     * @param xslBasePath - Base xsl file path for resolver
     * @throws IntegrationException - exception thrown if any
     */
    public void initTransformer(String schRulesXslFileName, String xslBasePath)
            throws IntegrationException {
        String basePath = xslBasePath;
        
        if (StringUtils.isEmpty(schRulesXslFileName)) {
            LOG.error("XSLTTransformer..xslFileName is NULL while initializing the transformer ");
            throw new IntegrationException(IntegrationError._1061);
        }
        if (StringUtils.isEmpty(xslBasePath)) {          
            basePath = ".";           
        }
        
        evaluateRulesTransformer.initTransformer(schRulesXslFileName, basePath);
    }
    
    /**
     * validates xml string with schematron
     * @param xmlString - source xml string
     * @return String - Schematron validation output
     * @throws IntegrationException - Exception if there are any
     */
    public String validate(String xmlString) throws IntegrationException {

        try {
            final CachedOutputStream cos = new CachedOutputStream();

            // Step 1. Apply XSLT to candidate        
            evaluateRulesTransformer.transform(null, new ByteArrayInputStream(xmlString.getBytes()), cos);

            // Step 2. Extract failures
            // Result message is small. Don't need cachedoutputstream.
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            extractFailuresTransformer.transform(null, cos.getInputStream(), baos);

            return new String(baos.toByteArray());
        } catch (IOException e) {
            throw new IntegrationException(IntegrationError._1100, e.getMessage(), e);
        }
    }

}

