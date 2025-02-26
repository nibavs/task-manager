package com.github.nibavs.task_manager_server.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "person")
@Schema(description = "Person model")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Person ID (automatically generated on the server side)", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Person's login")
    private String login;

    @NotNull
    @Column(name = "password_hash")
    @Schema(description = "Hashed password")
    private String passwordHash;

    @Schema(description = "Time of creation", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    public Person() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getLogin() {
        return login;
    }

    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    public @NotNull String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(@NotNull String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public @NotNull LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NotNull LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
