package com.memory.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class CPUSpikeDemoService {

	public void startWithThread() {
		IntStream.range(0, 10)
				.forEach(counter -> {
					new Thread(() -> {
						log.info("Starting CPU Spike");
						try {
							execute();
						} catch (InterruptedException e) {
							log.error("Interrupted while executing CPU spike", e);
							Thread.currentThread().interrupt(); // Preserve interrupted status
						}
					}).start();
				});
	}

	public void startWithExecutor() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		for (int i = 0; i < 10; i++) {
			executorService.submit(() -> {
				log.info("Starting CPU Spike");
				try {
					execute();
				} catch (InterruptedException e) {
					log.error("Interrupted while executing CPU spike", e);
					//Thread.currentThread().interrupt(); // Preserve interrupted status
				}
			});
		}

		executorService.shutdown();
	}
	public void execute() throws InterruptedException {
		while (true) {
			doSomething();
		}
	}

	public static void doSomething() {
		// Simulate CPU-intensive task
	}
}
