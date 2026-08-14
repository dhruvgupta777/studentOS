package com.studentdashboard.studentdashboard.controller;

import com.studentdashboard.studentdashboard.model.Semester;
import com.studentdashboard.studentdashboard.model.Subject;
import com.studentdashboard.studentdashboard.service.GpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/gpa")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GpaController {

    private final GpaService gpaService;

    @PostMapping("/semester")
    public ResponseEntity<?> addSemester(@RequestBody SemesterRequest request) {
        Semester semester = gpaService.addSemester(
                request.userId(),
                request.semesterName(),
                request.semesterNumber()
        );
        return ResponseEntity.ok(Map.of("id", semester.getId(), "semesterName", semester.getSemesterName(), "semesterNumber", semester.getSemesterNumber(), "sgpa", semester.getSgpa()));
    }

    @PostMapping("/subject")
    public ResponseEntity<?> addSubject(@RequestBody SubjectRequest request) {
        Subject subject = gpaService.addSubject(
                request.semesterId(),
                request.subjectName(),
                request.marksObtained(),
                request.totalMarks(),
                request.credits()
        );
        return ResponseEntity.ok(subject);
    }

    @GetMapping("/semesters/{userId}")
    public ResponseEntity<?> getSemesters(@PathVariable Long userId) {
        return ResponseEntity.ok(gpaService.getSemesters(userId));
    }

    @GetMapping("/cgpa/{userId}")
    public ResponseEntity<?> getCgpa(@PathVariable Long userId) {
        return ResponseEntity.ok(gpaService.calculateCgpa(userId));
    }

    record SemesterRequest(Long userId, String semesterName, Integer semesterNumber) {}
    record SubjectRequest(Long semesterId, String subjectName, Double marksObtained, Double totalMarks, Double credits) {}
}