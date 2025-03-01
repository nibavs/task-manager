package com.github.nibavs.task_manager_server.controller;

import com.github.nibavs.task_manager_server.entity.User;
import com.github.nibavs.task_manager_server.exception.UserAlreadyExistsException;
import com.github.nibavs.task_manager_server.exception.InvalidUsernameOrPasswordException;
import com.github.nibavs.task_manager_server.jwt.JwtUtil;
import com.github.nibavs.task_manager_server.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@Tag(name = "Auth", description = "Sign in / sign up")
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Sign in, get JWT token")
    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody User user) {
        User foundedUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(InvalidUsernameOrPasswordException::new);

        if (!foundedUser.getPassword().equals(user.getPassword())) {
            throw new InvalidUsernameOrPasswordException();
        }

        String token = jwtUtil.generateToken(foundedUser.getUsername());
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Sign up, get JWT token")
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(user.getUsername());
        }

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }
}
