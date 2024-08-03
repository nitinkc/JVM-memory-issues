package com.memory.springbootmemoryissues.service.memoryleak;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class MapManager {

	HashMap<Object, Object> myMap = new HashMap<>();
	public static final long TIME_IN_MINUTES = 5 * 60 * 1000;
	String str = "test".repeat(100);

	public void grow() {
		try {
			createAndDestroyObjects();
			//createObjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	/**
	 * This method creates and destroys objects for 5 minutes.
	 * This method will not cause memory leak.
	 */
	public void createAndDestroyObjects() throws Exception {
		
		long counter = 0;
		long startTime = System.currentTimeMillis();

		while (true) {
			if (counter % 1000 == 0) {
				Thread.sleep(100);
				myMap.clear();
				log.info("Deleted 1000 Records from map!");
				
				if ((System.currentTimeMillis() - startTime) > TIME_IN_MINUTES) {
					// Exit the loop after TIME_IN_MINUTES
					break;
				}
			}
			myMap.put("key" + counter, 	+ counter + str);
			++counter;
		}
	}

	
	public void createObjects() throws Exception {
		long counter = 0;
		
		while (true) {
			myMap.put("key" + counter, 	+ counter + str);
			++counter;
		}
	}
}
