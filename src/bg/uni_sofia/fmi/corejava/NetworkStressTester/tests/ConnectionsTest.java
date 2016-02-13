package bg.uni_sofia.fmi.corejava.NetworkStressTester.tests;

import org.junit.Before;
import org.junit.Test;

import bg.uni_sofia.fmi.corejava.NetworkStressTester.Connections;
import bg.uni_sofia.fmi.corejava.NetworkStressTester.FilesInput;
import bg.uni_sofia.fmi.corejava.NetworkStressTester.RequestResponseInfo;

public class ConnectionsTest {
	private FilesInput filesInput;
	private RequestResponseInfo requestResponseInfo;
	private Connections conn;

	@Before
	public void setUp() throws Exception {
		filesInput = new FilesInput("WrongFile.txt", "expected_response.txt");
		requestResponseInfo = new RequestResponseInfo(filesInput);

	}

	@Test(expected = Exception.class)
	public void test() throws Exception {
		conn = new Connections(requestResponseInfo);
		conn.sendRequest();
	}

}
