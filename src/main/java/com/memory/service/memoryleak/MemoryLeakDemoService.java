package com.memory.service.memoryleak;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemoryLeakDemoService {

	static Class1 class1 = new Class1();

	public void start() {
	
		try {
			class1.grow();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		log.info("Application terminated");
	}
}
