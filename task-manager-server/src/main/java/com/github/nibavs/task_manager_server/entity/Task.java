package com.github.nibavs.task_manager_server.entity;

import com.github.nibavs.task_manager_server.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Schema(description = "Task model")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Task ID (automatically generated on the server side)", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(description = "Task title", example = "Wash dishes")
    private String title;

    @Schema(description = "Task description", example = "Dishes after dinner are dirty, needs to be cleaned up.")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Schema(description = "Task status",example = "NEW", allowableValues = {"NEW", "IN_PROGRESS", "COMPLETED"})
    private TaskStatus status;

    @Column(name = "created_at", nullable = false)
    @Schema(description = "Task creation date (automatically generated on the server side)", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "User who's task is it", accessMode = Schema.AccessMode.READ_ONLY)
    private User user;

    // Date of creation setting
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Task() { //
    } 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
