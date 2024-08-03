package com.memory.springbootmemoryissues.service;

import com.memory.springbootmemoryissues.utility.MultiThreadUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeadLockDemoService {

	public void start() {

		log.info("Deadlock App started");
		Thread thread1 = new Thread(this::method1);
		thread1.start();

		Thread thread2 = new Thread(this::method2);
		thread2.start();
	}

	public synchronized void method1() {

		try {
			MultiThreadUtility.logMessage("Method 1 invoked on");
			// Sleep for 10 seconds
			MultiThreadUtility.delay(1 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.method2();
	}

	public synchronized void method2() {

		try {
			MultiThreadUtility.logMessage("Method 2 invoked on");
			// Sleep for 10 seconds
			MultiThreadUtility.delay(1 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.method1();
	}
}