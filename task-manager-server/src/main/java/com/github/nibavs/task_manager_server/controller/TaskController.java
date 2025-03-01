package com.github.nibavs.task_manager_server.controller;

import com.github.nibavs.task_manager_server.entity.Task;
import com.github.nibavs.task_manager_server.exception.TaskNotFoundException;
import com.github.nibavs.task_manager_server.repository.UserRepository;
import com.github.nibavs.task_manager_server.repository.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
@Tag(name = "Task", description = "Operations with tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Get all tasks")
    @GetMapping
    public List<Task> getAllTasks(@AuthenticationPrincipal String username) {
        return taskRepository.findAllByUser_Username(username);
    }

    @Operation(summary = "Get task by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id, @AuthenticationPrincipal String username) {
        return taskRepository.findByIdAndUser_Username(id, username)
                .map(task -> ResponseEntity.ok().body(task))
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Operation(summary = "Create new task")
    @PostMapping
    public Task createTask(@RequestBody Task task, @AuthenticationPrincipal String username) {
        task.setUser(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username)));

        return taskRepository.save(task);
    }

    @Operation(summary = "Delete task by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @AuthenticationPrincipal String username) {
        return taskRepository.findByIdAndUser_Username(id, username)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Operation(summary = "Update task by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody Task task, @AuthenticationPrincipal String username) {
        return taskRepository.findByIdAndUser_Username(id, username)
                .map(t -> {
                    t.setTitle(task.getTitle());
                    t.setDescription(task.getDescription());
                    t.setStatus(task.getStatus());
                    taskRepository.save(t);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

}
