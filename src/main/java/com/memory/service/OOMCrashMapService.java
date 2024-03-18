package com.memory.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OOMCrashMapService {
	public void start() {
		Map<String, byte[]> map = new HashMap<>();
		long counter = 0;
		while (true) {//Keep filling until JVM Crashes
			counter++;
			map.put("key-" + counter,  new byte[1024 * 1024]);
			log.info("Allocated 1 MB. Total Memory: " + Runtime.getRuntime().totalMemory() / (1024 * 1024) + " MB");
		}
	}
}
