package com.unibh.feedMaker.repository;

import com.unibh.feedMaker.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
}

