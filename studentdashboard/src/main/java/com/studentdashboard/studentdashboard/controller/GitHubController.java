package com.studentdashboard.studentdashboard.controller;

import com.studentdashboard.studentdashboard.service.GitHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GitHubController {

    private final GitHubService gitHubService;

    @GetMapping("/stats/{username}")
    public ResponseEntity<?> getStats(@PathVariable String username) {
        return ResponseEntity.ok(gitHubService.getGitHubStats(username));
    }

    @GetMapping("/repos/{username}")
    public ResponseEntity<?> getRepos(@PathVariable String username) {
        return ResponseEntity.ok(gitHubService.getRepositories(username));
    }
}