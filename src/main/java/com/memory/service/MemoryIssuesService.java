package com.memory.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.System.exit;

@Service
@Slf4j
public class MemoryIssuesService {

    private List<byte[]> memoryLeakingList = new ArrayList<>();
    private AtomicBoolean oomReached = new AtomicBoolean(false);
    private CompletableFuture<?> memoryLeakTask;


    public void simulateMemoryLeak() {
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
            exit(1);
            // No need to handle the exception, just proceed to generate the heap dump
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void processFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            // Process each line
        }
        // Reader is not closed, leading to a resource leak
        // Add this line to close the reader
        // reader.close();
    }

    // Method scheduled to run periodically
    //@Scheduled(fixedDelay = 1000)
    public void runMemoryLeakTask() {
        CompletableFuture.runAsync(this::simulateMemoryLeak);
    }

}
