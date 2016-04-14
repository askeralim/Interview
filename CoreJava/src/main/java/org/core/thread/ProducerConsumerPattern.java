package org.core.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumerPattern {

	public static void main(String args[]) {
		// Creating shared object
		BlockingQueue sharedQueue = new LinkedBlockingQueue();

		// Creating Producer and Consumer Thread
		Thread prodThread1 = new Thread(new Producer(sharedQueue, 0));
		Thread prodThread2 = new Thread(new Producer(sharedQueue, 20));
		Thread consThread = new Thread(new Consumer(sharedQueue));

		// Starting producer and Consumer thread
		prodThread1.start();
		prodThread2.start();
		consThread.start();
	}
}

// Producer Class in java
class Producer implements Runnable {

	private final BlockingQueue sharedQueue;
	private final int startPoint;

	public Producer(BlockingQueue sharedQueue, int startPoint) {
		this.sharedQueue = sharedQueue;
		this.startPoint = startPoint;
	}

	public void run() {
		for (int i = startPoint; i < startPoint+10; i++) {
			try {
				System.out.println("Produced: " + i);
				sharedQueue.put(i);
			} catch (InterruptedException ex) {
				Logger.getLogger(Producer.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

}

// Consumer Class in Java
class Consumer implements Runnable {

	private final BlockingQueue sharedQueue;

	public Consumer(BlockingQueue sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	public void run() {
		while (true) {
			try {
				System.out.println("Consumed: " + sharedQueue.take());
			} catch (InterruptedException ex) {
				Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

}
