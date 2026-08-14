package com.studentdashboard.studentdashboard.service;

import com.studentdashboard.studentdashboard.model.JobApplication;
import com.studentdashboard.studentdashboard.model.User;
import com.studentdashboard.studentdashboard.repository.JobRepository;
import com.studentdashboard.studentdashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobApplication addJob(Long userId, String companyName, String role, String notes) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        JobApplication job = new JobApplication();
        job.setCompanyName(companyName);
        job.setRole(role);
        job.setStatus("APPLIED");
        job.setNotes(notes);
        job.setUser(user);

        return jobRepository.save(job);
    }

    public JobApplication updateStatus(Long jobId, String status) {
        JobApplication job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        job.setStatus(status);
        return jobRepository.save(job);
    }

    public List<JobApplication> getJobs(Long userId) {
        return jobRepository.findByUserId(userId);
    }

    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }
}