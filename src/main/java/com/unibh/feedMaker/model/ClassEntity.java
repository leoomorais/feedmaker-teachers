package com.unibh.feedMaker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Class")
@Data
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    private String name;
    private String category;
}

