package org.core.thread;

import java.util.Hashtable;

public class LockExample {
	private boolean isLocked = false;
	public void lock() throws InterruptedException {
		while(!isLocked){
			wait();
		}
		isLocked = true;
	}
	public void unLock(){
		isLocked = false;
		notifyAll();
	}
}
