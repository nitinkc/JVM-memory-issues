package com.memory.springbootmemoryissues.service.sampleapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SampleAppDemoService {

	public static int THRESHOLD = 1000;
	
	public static Map<Long, String> s_map = new ConcurrentHashMap<>();
	
	public static void start() {
		
		try {
						
			for (int counter = 1; counter <= 5; ++counter) {
				
				Producer producer = new Producer();
				producer.setName("Producer-" + counter);
				producer.start();
			}
			
			Consumer consumer = new Consumer();
			consumer.setName("Consumer");
			consumer.start();
		} catch (Throwable t) {
			
			log.info("SampleAppDemo Failed!!");
			t.printStackTrace();
		}		
	}
}
