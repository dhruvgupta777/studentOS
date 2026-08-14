package com.studentdashboard.studentdashboard.controller;

import com.studentdashboard.studentdashboard.service.LeetCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leetcode")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LeetCodeController {

    private final LeetCodeService leetCodeService;

    @GetMapping("/stats/{username}")
    public ResponseEntity<?> getStats(@PathVariable String username) {
        return ResponseEntity.ok(leetCodeService.getLeetCodeStats(username));
    }
}