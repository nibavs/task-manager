package com.github.nibavs.task_manager_server.controller;

import com.github.nibavs.task_manager_server.entity.Task;
import com.github.nibavs.task_manager_server.repository.PersonRepository;
import com.github.nibavs.task_manager_server.repository.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
@Tag(name = "Task", description = "Operations with tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PersonRepository personRepository;

    @Operation(summary = "Get all tasks")
    @GetMapping
    public List<Task> getAllTasks(@AuthenticationPrincipal String username) {
        return taskRepository.findAllByPerson_Login(username);
    }

    @Operation(summary = "Get task by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id, @AuthenticationPrincipal String username) {
        return taskRepository.findByIdAndPerson_Login(id, username)
                .map(task -> ResponseEntity.ok().body(task))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new task")
    @PostMapping
    public Task createTask(@RequestBody Task task, @AuthenticationPrincipal String username) {
        task.setPerson(personRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username)));

        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @AuthenticationPrincipal String username) {
        return taskRepository.findByIdAndPerson_Login(id, username)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody Task task, @AuthenticationPrincipal String username) {
        return taskRepository.findByIdAndPerson_Login(id, username)
                .map(t -> {
                    t.setTitle(task.getTitle());
                    t.setDescription(task.getDescription());
                    t.setStatus(task.getStatus());
                    taskRepository.save(t);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
