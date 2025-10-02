package com.creatorshub.service;

import com.creatorshub.dto.*;
import com.creatorshub.model.User;
import com.creatorshub.repository.UserRepository;
import com.creatorshub.security.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthService(UserRepository userRepo, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public AuthResponse login(AuthRequest req) {
        var userOpt = userRepo.findByUsername(req.username());
        if (userOpt.isEmpty()) throw new RuntimeException("Invalid credentials");
        var user = userOpt.get();
        if (!encoder.matches(req.password(), user.getPassword())) throw new RuntimeException("Invalid credentials");
        String token = jwtUtils.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest req) {
        if (userRepo.existsByUsername(req.username())) throw new RuntimeException("Username taken");
        User u = new User();
        u.setUsername(req.username());
        u.setPassword(encoder.encode(req.password()));
        u.setFullName(req.fullName());
        u.setRoles(Set.of("ROLE_USER"));
        userRepo.save(u);
        String token = jwtUtils.generateToken(u.getUsername());
        return new AuthResponse(token);
    }
}
