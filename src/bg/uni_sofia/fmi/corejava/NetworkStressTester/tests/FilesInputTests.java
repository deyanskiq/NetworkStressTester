package bg.uni_sofia.fmi.corejava.NetworkStressTester.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bg.uni_sofia.fmi.corejava.NetworkStressTester.FilesInput;

public class FilesInputTests {
	private FilesInput filesInput;

	@Before
	public void setUp() {
		filesInput = new FilesInput("request.txt", "expected_response.txt");
	}

	@Test
	public void getRequestFileShouldReturnRequestTXT() {
		assertEquals("resources\\request.txt", filesInput.getRequestFile());
	}

	@Test
	public void getExpectedResponseFileShouldReturnExpectedResponseTXT() {
		assertEquals("resources\\expected_response.txt", filesInput.getExpectedResponseFile());
	}

}