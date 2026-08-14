package com.studentdashboard.studentdashboard.controller;

import com.studentdashboard.studentdashboard.model.JobApplication;
import com.studentdashboard.studentdashboard.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class JobController {

    private final JobService jobService;

    @PostMapping("/add")
    public ResponseEntity<?> addJob(@RequestBody JobRequest request) {
        return ResponseEntity.ok(jobService.addJob(
                request.userId(),
                request.companyName(),
                request.role(),
                request.notes()
        ));
    }

    @PutMapping("/status/{jobId}")
    public ResponseEntity<?> updateStatus(@PathVariable Long jobId, @RequestBody StatusRequest request) {
        return ResponseEntity.ok(jobService.updateStatus(jobId, request.status()));
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<?> getJobs(@PathVariable Long userId) {
        return ResponseEntity.ok(jobService.getJobs(userId));
    }

    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok("Job deleted successfully");
    }

    record JobRequest(Long userId, String companyName, String role, String notes) {}
    record StatusRequest(String status) {}
}