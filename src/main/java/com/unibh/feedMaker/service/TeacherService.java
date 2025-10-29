package com.unibh.feedMaker.service;

import com.unibh.feedMaker.dto.TeacherDTO;
import com.unibh.feedMaker.model.User;
import com.unibh.feedMaker.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public TeacherService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllTeachers() {
        return repository.findByType("TEACHER");
    }

    public Optional<User> getTeacherById(Long id) {
        return repository.findById(id);
    }

    public User createTeacher(TeacherDTO dto) {
        User teacher = new User();
        teacher.setName(dto.getName());
        teacher.setUsername(dto.getUsername());
        teacher.setPassword(passwordEncoder.encode(dto.getPassword()));
        teacher.setType("TEACHER");
        return repository.save(teacher);
    }

    public User updateTeacher(Long id, TeacherDTO dto) {
        User teacher = repository.findById(id).orElseThrow();
        teacher.setName(dto.getName());
        teacher.setUsername(dto.getUsername());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            teacher.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return repository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        repository.deleteById(id);
    }
}

