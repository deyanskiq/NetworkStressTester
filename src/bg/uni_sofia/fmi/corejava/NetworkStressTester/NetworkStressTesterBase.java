package bg.uni_sofia.fmi.corejava.NetworkStressTester;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import bg.uni_sofia.fmi.corejava.NetworkStressTester.exceptions.UnexpectedResponse;

public class NetworkStressTesterBase implements Runnable {

	private static AtomicLong longestResponseTime = new AtomicLong(0L);
	public static AtomicBoolean isBroken = new AtomicBoolean(false);
	private CyclicBarrier cyclicBarrier;
	private Connections conn;

	public NetworkStressTesterBase(Connections conn, CyclicBarrier cyclicBarrier) {
		this.conn = conn;
		this.cyclicBarrier = cyclicBarrier;
	}

	public void stressNetwork() {
		try {
			Long start = System.currentTimeMillis();
			conn.sendRequest();
			Long respondingTime = System.currentTimeMillis() - start;
			System.out.println(Thread.currentThread().getName() + " Time: " + respondingTime + "ms");

			if (respondingTime > longestResponseTime.longValue()) {
				longestResponseTime.set(respondingTime);
			}
		} catch (IOException | UnexpectedResponse e) {
			System.out.println();
			System.out.println("The request could not be sent.");
			System.out.println(e.getMessage());

			// Atomically sets the value to the given updated value if the
			// current value == the expected value.
			isBroken.compareAndSet(false, true);
		}

	}

	@Override
	public void run() {
		try {
			// Waits until all parties (threads) have invoked await on this
			// barrier
			this.cyclicBarrier.await();
			this.stressNetwork();

		} catch (InterruptedException | BrokenBarrierException e) {
			System.out.println(e.getMessage());

		}
	}

	public static Long getLongestResponseTime() {
		return longestResponseTime.longValue();
	}
}
