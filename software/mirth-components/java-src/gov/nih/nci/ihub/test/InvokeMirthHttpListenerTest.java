package gov.nih.nci.ihub.test;

import static org.junit.Assert.fail;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InvokeMirthHttpListenerTest {

	@Test
	public void testInvokeMirthHttpListener() {

		HttpClient client = new HttpClient();
		//client.getParams().setParameter("http.useragent", "Test Client");

		BufferedReader br = null;

		PostMethod method = new PostMethod("http://localhost");
		method.addParameter("synchronous_msg", "<request><metadata><uid>66b20b26-dc51-11df-9972-ddc780619c0d</uid><sourceformat>DELIMITED</sourceformat><targetformat>HL7V3</targetformat></metadata><payload>Santosh,Giambi,08/20/1968,Male,Duke</payload></request>");
		method.addRequestHeader("mirth.http.user", "tomcatusr");
		method.addRequestHeader("mirth.http.password", "changeme");

		try {
			int returnCode = client.executeMethod(method);

			if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
				System.err
						.println("The Post method is not implemented by this URI");
				// still consume the response body
				method.getResponseBodyAsString();
			} else {
				br = new BufferedReader(new InputStreamReader(method
						.getResponseBodyAsStream()));
				String readLine;
				while (((readLine = br.readLine()) != null)) {
					System.out.println(readLine);
				}
			}
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			method.releaseConnection();
			if (br != null)
				try {
					br.close();
				} catch (Exception fe) {
				}
		}

	}
}