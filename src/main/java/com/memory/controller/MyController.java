package com.memory.controller;

import com.memory.service.MemoryIssuesService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MyController {
    private MemoryIssuesService memoryIssuesService;

    @GetMapping("/induceOOM")
    public void getRefDataByIds( Map<String, List<Integer>> refIds){

         memoryIssuesService.simulateOOM();
    }
}
