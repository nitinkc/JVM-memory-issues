package com.memory.service.metaspaceleak;

import javassist.ClassPool;
import org.springframework.stereotype.Service;


@Service
public class MetaspaceLeakService {

    public  void start() throws Exception {
        
    	long startTime = System.currentTimeMillis();
    	
        ClassPool classPool = ClassPool.getDefault();
        for (int i = 0; i < 750_000; i++) {
            
        	if (i % 50_000 == 0) {
        		
                System.out.println(i + " new classes created");
            }
        	
        	// Keep creating classes dynamically!
        	String classname = "com.buggyapp.metaspaceleak.MetaspaceObject" + i;
        	System.out.println(classname + " new classes created");
			classPool.makeClass(classname).toClass();
        }
        
        System.out.println("Program Exited: " + (System.currentTimeMillis() - startTime) / 1000 + " seconds");
    }
}
