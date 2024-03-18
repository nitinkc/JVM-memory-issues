package com.memory.utility;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MultiThreadUtility {

    public static void delay(long delayMilliSeconds)  {
        try{
            Thread.sleep(delayMilliSeconds);
        }catch (Exception e){
            log.info("Exception is :" + e.getMessage());
        }
    }

    public static void logShortMessage(String message){
        log.info( message + ": " + Thread.currentThread().getName());
    }

    public static void logMessage(String message){
        log.info(message + " : "  + Thread.currentThread() );
    }

    public static void foreverThread() {
        long numberOfHours = 1;
        Thread appThread = new Thread(()-> delay(numberOfHours * 60 * 1000));
        appThread.start();
        logMessage("foreverThread started ");
    }
}
