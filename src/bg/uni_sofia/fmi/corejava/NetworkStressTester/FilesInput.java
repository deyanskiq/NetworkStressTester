package bg.uni_sofia.fmi.corejava.NetworkStressTester;

import java.nio.file.Paths;

public class FilesInput {

	private String requestFile;
	private String expectedResponseFile;
	private static final String RESOURCE = "resources";

	public FilesInput(String requestFile, String expectedResponseFile) {
		String requestPath = Paths.get(RESOURCE, requestFile).toString();
		String expectedResponsePath = Paths.get(RESOURCE, expectedResponseFile).toString();
		this.requestFile = requestPath;
		this.expectedResponseFile = expectedResponsePath;

	}

	public String getRequestFile() {
		return requestFile;
	}

	public void setRequestFile(String requestFile) {
		this.requestFile = requestFile;
	}

	public String getExpectedResponseFile() {
		return expectedResponseFile;
	}

	public void setExpectedResponseFile(String expectedResponseFile) {
		this.expectedResponseFile = expectedResponseFile;
	}
}
