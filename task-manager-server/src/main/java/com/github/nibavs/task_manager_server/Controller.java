package com.github.nibavs.task_manager_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class Controller {
    @Autowired
    private TaskRepository taskRepository;
    //Comment
    //Second comment
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskRepository.findById(id)
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
