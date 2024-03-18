package com.memory.service;

import java.util.UUID;

public class LimitlessArray {

	public static void main(String[] args) {
		  
	    System.out.println("The beginning");

		// Get the runtime object
		Runtime runtime = Runtime.getRuntime();

		// Get the maximum memory size in bytes
		long maxMemoryBytes = runtime.maxMemory();

		// Convert bytes to megabytes
		long maxMemoryMegabytes = maxMemoryBytes / (1024 * 1024);

		// Print the maximum memory size
		System.out.println("Maximum heap size: " + maxMemoryMegabytes + " MB");

		//java.lang.OutOfMemoryError: Requested array size exceeds VM limit
	    String[] strArray = new String[Integer.MAX_VALUE]; //crashes-right away

	    for (int i=0; i<Integer.MAX_VALUE; i++) {
	    	strArray[i] = UUID.randomUUID().toString();
	    }
	    
	    System.out.println("The end");
	 
	}	
}
