package org.ws;

import java.util.concurrent.Callable;

public class NetworkClassLoader extends ClassLoader {
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.findClass(name);
	}
}

class MyCallable implements Callable<Long> {
	  @Override
	  public Long call() throws Exception {
	    long sum = 0;
	    for (long i = 0; i <= 100; i++) {
	      sum += i;
	    }
	    return sum;
	  }

	} 