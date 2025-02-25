package com.github.nibavs.task_manager_server.controller;

import com.github.nibavs.task_manager_server.JwtUtil;
import com.github.nibavs.task_manager_server.Person;
import com.github.nibavs.task_manager_server.PersonRepository;
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

    @PostMapping("api/auth/signin")
    public ResponseEntity<String> signIn(@RequestBody Person person) {
        Person foundedPerson = personRepository.findByLogin(person.getLogin());
        if (foundedPerson != null && foundedPerson.getPasswordHash().equals(person.getPasswordHash())) {
            String token = jwtUtil.generateToken(foundedPerson.getLogin());
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("api/auth/signin/getLogin")
    public ResponseEntity<String> getLoginFromToken(@RequestBody Person person) {
        System.out.println(person.getLogin());

        try {
            return ResponseEntity.ok(jwtUtil.getUsernameFromToken(person.getLogin()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("api/auth/signin/verify")
    public ResponseEntity<Boolean> verifyToken(@RequestBody Person person) {
        System.out.println(person.getLogin());

        try {
            return ResponseEntity.ok(jwtUtil.validateToken(person.getLogin()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
