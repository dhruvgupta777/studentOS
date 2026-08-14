package com.studentdashboard.studentdashboard.repository;

import com.studentdashboard.studentdashboard.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    List<Semester> findByUserId(Long userId);
}