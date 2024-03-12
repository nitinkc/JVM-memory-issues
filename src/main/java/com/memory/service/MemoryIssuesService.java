package com.memory.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MemoryIssuesService {

    private List<byte[]> memoryLeakingList = new ArrayList<>();

    public void simulateOOM() {
        try {
            // Allocate memory until an OutOfMemoryError occurs
            while (true) {
                byte[] array = new byte[1024 * 1024]; // Allocate 1 MB
                memoryLeakingList.add(array);
                log.info("Creating :: size :: " + memoryLeakingList.size());
                Thread.sleep(100); // Slow down allocation to make it easier to observe
                log.info("Allocated 1 MB. Total Memory: " + Runtime.getRuntime().totalMemory() / (1024 * 1024) + " MB");
            }
        } catch (OutOfMemoryError e) {
            log.error("OutOfMemoryError occurred. Generating heap dump...");
            // No need to handle the exception, just proceed to generate the heap dump
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
