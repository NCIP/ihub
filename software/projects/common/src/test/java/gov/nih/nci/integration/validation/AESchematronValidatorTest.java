package gov.nih.nci.integration.validation;

import gov.nih.nci.integration.exception.IntegrationException;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import org.apache.cxf.helpers.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Schematron validation for xml
 * 
 * @author <a href="mailto:vinodh.rc@semanticbits.com">Vinodh Chandrasekaran</a>
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext-common-test.xml")
public class AESchematronValidatorTest {

    @Value("${ihub.ae.cda.schematron.rules.xsl}")
    private String cdaSchematronXsl;

    @Value("${integration.transformer.xsl.baseClassPath}")
    private String xslBaseClassPath;

    @Autowired
    private SchematronValidator validator;

    @Before
    public void setup() throws IntegrationException {
        validator.initTransformer(cdaSchematronXsl, xslBaseClassPath);
    }

    /**
     * checks xsd validation with valid xml
     * 
     * @throws IOException - exception thrown if any
     * @throws IntegrationException - exception thrown if any
     */
    @Test
    public void validateValidXML() throws IOException, IntegrationException {
        final InputStream validXmlFileStream = getClass().getClassLoader().getResourceAsStream("ae-schematron-test.xml");
        final String xmlString = IOUtils.toString(validXmlFileStream);
        Assert.assertNotNull(xmlString);
        final String output = validator.validate(xmlString);
        Assert.assertNotNull(output);
        Assert.assertEquals("", output);

        closeStream(validXmlFileStream);
    }

    /**
     * checks xsd validation with invalid xml
     * 
     * @throws IOException - exception thrown if any
     * @throws IntegrationException - exception thrown if any
     */
    @Test
    public void validateInValidXML() throws IOException, IntegrationException {
        final InputStream inValidXmlFileStream = getClass().getClassLoader().getResourceAsStream(
                "ae-schematron-failure-test.xml");
        final String xmlString = IOUtils.toString(inValidXmlFileStream);
        Assert.assertNotNull(xmlString);
        final String output = validator.validate(xmlString);
        Assert.assertNotNull(output);
        final InputStream expOutputFileStream = getClass().getClassLoader().getResourceAsStream(
                "ae-schematron-failure-test-output.txt");
        final String expOutput = IOUtils.toString(expOutputFileStream);
        Assert.assertEquals(expOutput, output);

        closeStream(inValidXmlFileStream);
        closeStream(expOutputFileStream);
    }

    /**
     * Run the test when schematron xsl name is blank
     * 
     * @throws IOException - exception thrown if any
     * @throws IntegrationException - exception thrown if any
     */
    @Test
    public void invalidXslFileName() throws IOException {
        final InputStream validXmlFileStream = getClass().getClassLoader().getResourceAsStream("schematron-test.xml");
        final String xmlString = IOUtils.toString(validXmlFileStream);
        Assert.assertNotNull(xmlString);
        String output = null;
        try {
            validator.initTransformer("", xslBaseClassPath);
            output = validator.validate(xmlString);
        } catch (IntegrationException e) {
        }

        Assert.assertNull(output);
        closeStream(validXmlFileStream);
    }

    private void closeStream(Closeable s) {
        try {
            if (s != null) {
                s.close();
            }
        } catch (IOException e) {
            // do nothing
        }
    }

}
