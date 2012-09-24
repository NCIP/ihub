package gov.nih.nci.integration.validation;

import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * This class is used to read the configuration for schematron validation
 * 
 * @author Vinodh
 * 
 */
@Configuration
public class ValidationConfig {

    @Value("${ihub.validation.schematron.extract.failures.xsl}")
    private String extractFailuresSchematronXsl;

    @Value("${integration.transformer.xsl.baseClassPath}")
    private String xslBaseClassPath;

    @Autowired
    private ApplicationContext context;

    /**
     * Schematron Validator
     * 
     * @return schematronValidator SchematronValidator
     * @throws IntegrationException exception
     */
    @Bean
    @Scope("prototype")
    public SchematronValidator schematronValidator() throws IntegrationException {
        final XSLTTransformer extractFailuresTransformer = context.getBean(XSLTTransformer.class);
        extractFailuresTransformer.initTransformer(extractFailuresSchematronXsl, xslBaseClassPath);

        final XSLTTransformer evaluateRulesTransformer = context.getBean(XSLTTransformer.class);
        return new SchematronValidator(evaluateRulesTransformer, extractFailuresTransformer, xslBaseClassPath);
    }
}
