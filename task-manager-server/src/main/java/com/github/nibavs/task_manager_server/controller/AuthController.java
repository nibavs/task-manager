package com.github.nibavs.task_manager_server.controller;

import com.github.nibavs.task_manager_server.exception.UserNotFoundException;
import com.github.nibavs.task_manager_server.jwt.JwtUtil;
import com.github.nibavs.task_manager_server.entity.Person;
import com.github.nibavs.task_manager_server.repository.PersonRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Sign in, get JWT token")
    @PostMapping("api/auth/signin")
    public ResponseEntity<?> signIn(@RequestBody Person person) {
        Person foundedPerson = personRepository.findByLogin(person.getLogin())
                .orElseThrow(() -> new UserNotFoundException(person.getLogin()));

        if (foundedPerson.getPasswordHash().equals(person.getPasswordHash())) {
            String token = jwtUtil.generateToken(foundedPerson.getLogin());
            return ResponseEntity.ok(token);
        }

        // use global error handling throw...

        return ResponseEntity.notFound().build();
    }

}
