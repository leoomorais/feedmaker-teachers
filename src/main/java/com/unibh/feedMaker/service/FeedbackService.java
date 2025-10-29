package com.unibh.feedMaker.service;

import com.unibh.feedMaker.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class FeedbackService {
    private final FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public Double getAverageScore(Long teacherId) {
        return repository.findAverageScoreByTeacherId(teacherId);
    }

    public Double getAverageScoreByPeriod(Long teacherId, LocalDate start, LocalDate end) {
        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(23, 59, 59);
        return repository.findAverageScoreByTeacherAndPeriod(teacherId, startDateTime, endDateTime);
    }
}


