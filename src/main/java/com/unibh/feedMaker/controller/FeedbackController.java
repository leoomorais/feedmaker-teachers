package com.unibh.feedMaker.controller;

import com.unibh.feedMaker.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    @GetMapping("/average/{teacherId}")
    public Double getAverage(@PathVariable Long teacherId) {
        return service.getAverageScore(teacherId);
    }

    @GetMapping("/average/{teacherId}/period")
    public Double getAverageByPeriod(@PathVariable Long teacherId,
                                     @RequestParam LocalDate start,
                                     @RequestParam LocalDate end) {
        return service.getAverageScoreByPeriod(teacherId, start, end);
    }
}


