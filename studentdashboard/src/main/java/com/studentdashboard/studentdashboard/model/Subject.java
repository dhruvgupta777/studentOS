package com.studentdashboard.studentdashboard.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;
    private Double marksObtained;
    private Double totalMarks;
    private Double credits;
    private Double gradePoints;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;
}