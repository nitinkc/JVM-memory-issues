package com.memory.service;

import com.memory.utility.GeneralUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OOMNoCrashService {

	public void start() throws Exception {
		 Instrumentation instrumentation = null;

		try {
			Map<String, byte[]> map = new HashMap<>();
			long counter = 0;
			while (true) {//Keep filling until JVM Crashes
				counter++;
				map.put("key-" + counter,  new byte[1024 * 1024]);
				log.info("Allocated 1 MB. Total Memory: " + Runtime.getRuntime().totalMemory() / (1024 * 1024) + " MB");
			}
		} catch (Throwable e) {
			log.info(e.getClass() + " caught OOM Error! Application will not crash.");
			log.info("Need to clean Heap so that other requests can be served");
		}
	}
}
