/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.transformer;

import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.util.AnyBasePathURIResolver;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TransformerConfig {
		
	@Value("${integration.transformer.xsl.baseClassPath}")
    private String xslBaseClassPath;
	
	/**
	 * prototype bean scope name constant
	 */
	private static final String PROTOTYPE="prototype";
	
    /**
     * {@inheritDoc}
     */
    public URIResolver xslUriResolver() {
        return new AnyBasePathURIResolver(xslBaseClassPath);
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public TransformerFactory xslTransformerFactory() {
        final TransformerFactory tf = TransformerFactory.newInstance();
        tf.setURIResolver(xslUriResolver());
        return tf;
    }

    /**
     * XSLT Transformer
     * 
     * @return xsltTransformer transformer
     * @throws IntegrationException exception
     */
    @Bean
    @Scope(PROTOTYPE)
    public XSLTTransformer xsltTransformer() throws IntegrationException {
        return new XSLTTransformer(xslTransformerFactory());
    }
    
}

