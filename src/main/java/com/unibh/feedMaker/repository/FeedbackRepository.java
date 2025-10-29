package com.unibh.feedMaker.repository;

import com.unibh.feedMaker.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT AVG(f.score) FROM Feedback f WHERE f.teacher.id = :teacherId")
    Double findAverageScoreByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT AVG(f.score) FROM Feedback f WHERE f.teacher.id = :teacherId AND f.createdAt BETWEEN :startDate AND :endDate")
    Double findAverageScoreByTeacherAndPeriod(@Param("teacherId") Long teacherId,
                                              @Param("startDate") LocalDateTime startDate,
                                              @Param("endDate") LocalDateTime endDate);
}


