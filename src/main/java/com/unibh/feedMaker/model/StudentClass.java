package com.unibh.feedMaker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Student_Class")
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private ClassEntity classEntity;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;
}

