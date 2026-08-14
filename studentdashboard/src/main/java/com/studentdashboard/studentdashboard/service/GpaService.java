package com.studentdashboard.studentdashboard.service;

import com.studentdashboard.studentdashboard.model.Semester;
import com.studentdashboard.studentdashboard.model.Subject;
import com.studentdashboard.studentdashboard.model.User;
import com.studentdashboard.studentdashboard.repository.SemesterRepository;
import com.studentdashboard.studentdashboard.repository.SubjectRepository;
import com.studentdashboard.studentdashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GpaService {

    private final SemesterRepository semesterRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public Semester addSemester(Long userId, String semesterName, Integer semesterNumber) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Semester semester = new Semester();
        semester.setSemesterName(semesterName);
        semester.setSemesterNumber(semesterNumber);
        semester.setUser(user);
        semester.setSgpa(0.0);

        return semesterRepository.save(semester);
    }

    public Subject addSubject(Long semesterId, String subjectName, Double marksObtained, Double totalMarks, Double credits) {
        Semester semester = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new RuntimeException("Semester not found"));

        Double percentage = (marksObtained / totalMarks) * 100;
        Double gradePoints = calculateGradePoints(percentage);

        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        subject.setMarksObtained(marksObtained);
        subject.setTotalMarks(totalMarks);
        subject.setCredits(credits);
        subject.setGradePoints(gradePoints);
        subject.setSemester(semester);

        Subject saved = subjectRepository.save(subject);
        updateSgpa(semesterId);
        return saved;
    }

    private void updateSgpa(Long semesterId) {
        List<Subject> subjects = subjectRepository.findBySemesterId(semesterId);

        double totalCredits = 0;
        double totalWeightedPoints = 0;

        for (Subject s : subjects) {
            totalCredits += s.getCredits();
            totalWeightedPoints += s.getGradePoints() * s.getCredits();
        }

        double sgpa = totalCredits > 0 ? totalWeightedPoints / totalCredits : 0;

        Semester semester = semesterRepository.findById(semesterId).get();
        semester.setSgpa(Math.round(sgpa * 100.0) / 100.0);
        semesterRepository.save(semester);
    }

    private Double calculateGradePoints(Double percentage) {
        return Math.round((percentage / 10.0) * 100.0) / 100.0;
    }

    public List<Semester> getSemesters(Long userId) {
        return semesterRepository.findByUserId(userId);
    }

    public Double calculateCgpa(Long userId) {
        List<Semester> semesters = semesterRepository.findByUserId(userId);
        if (semesters.isEmpty()) return 0.0;

        double total = semesters.stream()
                .mapToDouble(Semester::getSgpa)
                .average()
                .orElse(0.0);

        return Math.round(total * 100.0) / 100.0;
    }
}