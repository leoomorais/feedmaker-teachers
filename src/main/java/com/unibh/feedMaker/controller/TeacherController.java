package com.unibh.feedMaker.controller;

import com.unibh.feedMaker.dto.TeacherDTO;
import com.unibh.feedMaker.model.User;
import com.unibh.feedMaker.service.TeacherService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllTeachers() {
        return service.getAllTeachers();
    }

    @GetMapping("/{id}")
    public User getTeacher(@PathVariable Long id) {
        return service.getTeacherById(id).orElseThrow();
    }

    @PostMapping
    public User createTeacher(@RequestBody TeacherDTO dto) {
        return service.createTeacher(dto);
    }

    @PutMapping("/{id}")
    public User updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO dto) {
        return service.updateTeacher(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        service.deleteTeacher(id);
    }
}

