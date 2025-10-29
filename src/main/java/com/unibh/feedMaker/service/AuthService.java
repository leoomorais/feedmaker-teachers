package com.unibh.feedMaker.service;

import com.unibh.feedMaker.dto.LoginRequest;
import com.unibh.feedMaker.dto.LoginResponse;
import com.unibh.feedMaker.model.User;
import com.unibh.feedMaker.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }

        return new LoginResponse("Login realizado com sucesso", user.getId(), user.getType());
    }
}


