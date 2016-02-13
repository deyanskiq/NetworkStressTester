package bg.uni_sofia.fmi.corejava.NetworkStressTester.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bg.uni_sofia.fmi.corejava.NetworkStressTester.FilesInput;
import bg.uni_sofia.fmi.corejava.NetworkStressTester.RequestResponseInfo;

public class RequestResponseInfoTests {
	private FilesInput inputFiles;
	private RequestResponseInfo requestResponseInformation;

	@Before
	public void setUp() {
		inputFiles = new FilesInput("request.txt", "expected_response.txt");
		requestResponseInformation = new RequestResponseInfo(inputFiles);
	}

	@Test
	public void getRequestShouldReturnCorrectHTTPRequest() {
		assertEquals("GET http://java.voidland.org/ HTTP/1.1", requestResponseInformation.getRequest());
	}

	@Test
	public void getExpectedResponseShouldReturnCorrectHTTPResponse() {
		assertEquals("HTTP/1.1 200 OK", requestResponseInformation.getExpectedResponse());
	}

	@Test
	public void getHostShouldReturnCorrectHostName() {
		assertEquals("Host: java.voidland.org", requestResponseInformation.getHost());
	}

	@Test
	public void getPortShouldReturnCorrectPortNumber() {
		assertEquals(80, requestResponseInformation.getPort());
	}
}