package com.unibh.feedMaker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"user\"")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private String type; // STUDENT, TEACHER, COORDINATOR

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}

