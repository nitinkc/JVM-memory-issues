package com.memory.service;

import com.memory.utility.MultiThreadUtility;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ThreadLeakDemoService {
	private static final int threadLeakSize = 1250;

	public void start() {

		log.info("Thread App started");
		int threadCount = 0;
		
		while (threadCount < threadLeakSize) {
			log.info("Thread Started : "+threadCount);
			//MultiThreadUtility.delay(10);// Failed to put thread to sleep.
			threadCount++;

			// creates a new thread that runs indefinitely, without ever terminating
			MultiThreadUtility.foreverThread();//no mechanism in place to terminate or clean up these threads
			//To prevent thread leaks, it's essential to ensure that threads are properly managed and terminated when they are no longer needed.
		}
	}
}
