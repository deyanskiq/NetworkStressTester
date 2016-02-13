package bg.uni_sofia.fmi.corejava.NetworkStressTester;

/**
 * 
 * @author DDenchev: Network stress tester
 * 
 *         Program loaded stress a network server. The network stress tester is
 *         a client that can send multiple simultaneous requests to a test
 *         server. Applications are the same. The content of the application can
 *         be read from a prepared text file. After sending requests must wait
 *         for answers. If the answer begins with a row (can be read from a
 *         prepared another file), the application shall be considered
 *         successful. If it starts with the contents of the file with the
 *         expected response, the application shall be deemed to have failed.
 *         Any error (timeout, closing the connection from the server before
 *         being sent response) means a failed request. After receiving the
 *         response, the connection must be closed (unless already closed by the
 *         server).
 * 
 *         The programs finds how many successful simultaneous requests can
 *         tolerate server without a single failure among them. Also the program
 *         finds what is the biggest response time in this case.
 */
public class Main {
	public static void main(String[] args) {

		NetworkStressTester test = new NetworkStressTester("request.txt", "expected_response.txt");
		// NetworkStressTester test2 = new NetworkStressTester("GET
		// http://java.voidland.org/ HTTP/1.1", "HTTP/1.1 200 OK",
		// "Host: java.voidland.org", 80);

		try {
			test.beginToStress();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
