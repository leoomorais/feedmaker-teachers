package com.unibh.feedMaker.repository;

import com.unibh.feedMaker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByType(String type); // Para buscar professores
}

