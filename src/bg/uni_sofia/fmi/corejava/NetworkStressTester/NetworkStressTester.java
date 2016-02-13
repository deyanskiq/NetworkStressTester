package bg.uni_sofia.fmi.corejava.NetworkStressTester;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NetworkStressTester {

	private RequestResponseInfo information;
	private FilesInput files;
	private Connections conn;
	private int numberOfRequests = 1;

	public NetworkStressTester(String request, String expectedResponse, String host, int port) {
		information = new RequestResponseInfo(request, expectedResponse, host, port);
		conn = new Connections(information);

	}

	public NetworkStressTester(String requestFile, String expectedResponseFile) {
		files = new FilesInput(requestFile, expectedResponseFile);
		information = new RequestResponseInfo(files);
		conn = new Connections(information);

	}

	public void beginToStress() throws InterruptedException {
		while (true) {

			// The barrier is called cyclic because it can be re-used after the
			// waiting threads are released.
			CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfRequests);

			// An Executor that provides methods to manage termination and
			// methods that can produce a Future for tracking progress of one or
			// more asynchronous tasks.
			ExecutorService executor = Executors.newFixedThreadPool(numberOfRequests);
			NetworkStressTesterBase networkStressTesterBase = new NetworkStressTesterBase(conn, cyclicBarrier);

			for (int i = 0; i < numberOfRequests; i++) {

				// execute the given command at some time in the future
				executor.execute(networkStressTesterBase);

			}
			// Initiates an orderly shutdown in which previously submitted tasks
			// are executed, but no new tasks will be accepted
			executor.shutdown();

			// Blocks until all tasks have completed execution after a shutdown
			// request;
			// Long.MAX_VALUE =timeout (the maximum time to wait) ;
			// TimeUnit.NANOSECONDS = unit(the time unit of the timeout
			// argument)
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

			if (NetworkStressTesterBase.isBroken.get()) {
				break;
			}

			numberOfRequests++;
		}
		System.out.println("Maximum number of successful request: " + (numberOfRequests - 1));
		System.out.println("Longest time for response: " + NetworkStressTesterBase.getLongestResponseTime() + " ms");
	}
}
