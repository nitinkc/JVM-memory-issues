package com.memory.service;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class LimitlessArray {

	public static void main(String[] args) {
		  
	    log.info("The beginning");

		// Get the runtime object
		Runtime runtime = Runtime.getRuntime();

		// Get the maximum memory size in bytes
		long maxMemoryBytes = runtime.maxMemory();

		// Convert bytes to megabytes
		long maxMemoryMegabytes = maxMemoryBytes / (1024 * 1024);

		// Print the maximum memory size
		log.info("Maximum heap size: " + maxMemoryMegabytes + " MB");

		//java.lang.OutOfMemoryError: Requested array size exceeds VM limit
	    String[] strArray = new String[Integer.MAX_VALUE]; //crashes-right away

	    for (int i=0; i<Integer.MAX_VALUE; i++) {
	    	strArray[i] = UUID.randomUUID().toString();
	    }
	    
	    log.info("The end");
	 
	}	
}
