package com.memory.controller;

import com.memory.service.MemoryIssuesService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MyController {
    private MemoryIssuesService memoryIssuesService;

    @GetMapping("/induceOOM")
    public void getRefDataByIds(Map<String, List<Integer>> refIds) {

        memoryIssuesService.simulateOOM();
    }

    @PostMapping("/process")
    public ResponseEntity<String> processFile() {
       // @RequestParam("filePath")

        String filePath = "src/main/resources/text.txt";
        try {
            memoryIssuesService.processFile(filePath);
            return ResponseEntity.ok("File processing started for: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process file: " + e.getMessage());
        }
    }
}