package bg.uni_sofia.fmi.corejava.NetworkStressTester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import bg.uni_sofia.fmi.corejava.NetworkStressTester.exceptions.UnexpectedResponse;

public class Connections {
	private RequestResponseInfo information;

	public Connections(RequestResponseInfo information) {
		this.information = information;

	}

	private Socket createSocket(String lineWithHost, int port) throws UnknownHostException, IOException {
		String host = lineWithHost.substring(6);
		return new Socket(host, port);
	}

	public void sendRequest() throws UnknownHostException, IOException, UnexpectedResponse {

		// try with resources automatically close resources
		try (Socket socket = createSocket(information.getHost(), information.getPort());

				// PrintWriter - to send messages to the server
				PrintWriter pw = new PrintWriter(socket.getOutputStream());

				// BufferedReader - to get messages/response from the server
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

			// sending request;
			// using 'println' instead of 'print' to print entries to separate
			// lines.
			pw.println(information.getRequest());
			pw.println(information.getHost());

			// HTTP request should end in a blank line
			pw.println("");
			pw.flush();

			String firstLine = br.readLine();

			// print just the first line of the server response
			System.out.println(firstLine);

			if (!firstLine.equals(information.getExpectedResponse())) {
				throw new UnexpectedResponse("The response was unexpected!");

			}
		}

	}
}
