package org.core.string;

public class StringPrepend {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	       // String format below will add leading zeros (the %0 syntax) 
	       // to the number above. 
	       // The length of the formatted string will be 7 characters.

	       String formatted = String.format("%07d", Integer.valueOf("3"));

	       System.out.println("Number with leading zeros: " + formatted);

	}

}
