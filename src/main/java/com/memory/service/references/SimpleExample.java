package com.memory.service.references;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleExample {

	public static void main (String argsp[]) throws Exception {
	
		A a = new A();
		B b = new B();
		
		log.info("Objects created!");
		Thread.sleep(1 * 60 * 1000);
	}
}
