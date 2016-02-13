package bg.uni_sofia.fmi.corejava.NetworkStressTester;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RequestResponseInfo {

	private String request;
	private String expectedResponse;
	private String host;
	private int port;
	public static final int PORT = 80;

	public RequestResponseInfo(String request, String expectedResponse, String host, int port) {
		this.request = request;
		this.expectedResponse = expectedResponse;
		this.host = host;
		this.port = port;

	}

	public RequestResponseInfo(FilesInput files) {
		setRequestInfoFromFile(files.getRequestFile());
		setExpectedResponseInfoFromFile(files.getExpectedResponseFile());
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getExpectedResponse() {
		return expectedResponse;
	}

	public void setExpectedResponse(String expenctedResponse) {
		this.expectedResponse = expenctedResponse;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	private void setRequestInfoFromFile(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			setRequest(br.readLine());
			setHost(br.readLine());
			setPort(PORT);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setExpectedResponseInfoFromFile(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			setExpectedResponse(br.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
