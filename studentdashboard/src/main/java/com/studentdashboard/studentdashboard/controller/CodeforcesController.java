package com.studentdashboard.studentdashboard.controller;

import com.studentdashboard.studentdashboard.service.CodeforcesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/codeforces")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CodeforcesController {

    private final CodeforcesService codeforcesService;

    @GetMapping("/stats/{handle}")
    public ResponseEntity<?> getStats(@PathVariable String handle) {
        return ResponseEntity.ok(codeforcesService.getCodeforcesStats(handle));
    }

    @GetMapping("/contests/{handle}")
    public ResponseEntity<?> getContests(@PathVariable String handle) {
        return ResponseEntity.ok(codeforcesService.getRecentContests(handle));
    }
}