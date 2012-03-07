package gov.nih.nci.integration.cacis.inbound;

import gov.nih.nci.ihub.cacis.inbound.DefaultAcceptMessage;
import gov.nih.nci.ihub.cacis.inbound.DefaultAcceptMessageService;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBException;

/**
 * A client to submit request to DefaultAcceptMessage webservice of Mirth
 * @author vinodh.rc
 *
 */
public class DefaultAcceptMessageClient {

    public String acceptMessage(String wsdl, String requestStr) throws JAXBException, MalformedURLException {
               
        final URL wsdlUrl = new URL(wsdl);
        final DefaultAcceptMessage service = new
                DefaultAcceptMessageService(wsdlUrl).getDefaultAcceptMessagePort();

        return service.acceptMessage(requestStr);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        DefaultAcceptMessageClient client = new DefaultAcceptMessageClient();
        try {
            String res = client.acceptMessage("http://localhost:12085/services/Mirth?wsdl", "<trim>testtrim</trim>");
            System.out.println("response is " + res);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
