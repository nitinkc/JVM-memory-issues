package com.memory.springbootmemoryissues.service.metaspaceleak;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MetaspaceLeakService {

    /*
     Metaspace is the memory space in the Java Virtual Machine (JVM) where class metadata, such as class definitions,
     methods, and constants, are stored.

     A Metaspace leak occurs when the application dynamically generates classes at runtime,
     but fails to release them properly, leading to the exhaustion of Metaspace memory.
     */
    public  void start() {
        
    	long startTime = System.currentTimeMillis();
    	
        ClassPool classPool = ClassPool.getDefault();
        for (int i = 0; i < 750_000; i++) {
        	
        	// Keep creating classes dynamically!
        	String classname = "com.memory.service.metaspaceleak.ClassForMetaspace";
        	log.info(i + " :: " +classname + " new classes created");
            CtClass ctClass = null;
            try {
                ctClass = classPool.makeClass(classname);
                Class<?> aClass = ctClass.toClass();
            } catch (CannotCompileException e) {
                log.error(e.getMessage());
            }finally {
               // ctClass.detach();//Simulating metaspace Leak
            }
        }
        
        log.info("Program Exited: " + (System.currentTimeMillis() - startTime) / 1000 + " seconds");
    }
}
