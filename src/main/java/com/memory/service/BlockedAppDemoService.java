package com.memory.service;

import com.memory.utility.MultiThreadUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
@Slf4j
public class BlockedAppDemoService {

	public void startBlockingApp() {
		log.info("App started");
		// Create a stream of 10 integers from 0 to 9
		IntStream.range(0, 10)
				// For each integer in the stream, launch a thread
				.forEach(counter -> {
					MultiThreadUtility.logMessage("Starting thread ");
					Thread appThread = new Thread(this::getSomething);
					appThread.start();
				});

		log.info("All Threads have started");
		log.info("App became unresponsive");
	}


	public synchronized void getSomething() {
		// first thread will acquire the lock and go to sleep
		// No other thread would be able to enter this method.
		while (true) {
			try {
				log.info("Thread going to sleep acquiring the lock ");
				MultiThreadUtility.delay(1000 *10*60);//600 secs
			} catch (Exception e) {}
		}
	}
}
