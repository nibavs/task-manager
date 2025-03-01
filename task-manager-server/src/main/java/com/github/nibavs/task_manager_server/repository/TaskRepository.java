package com.github.nibavs.task_manager_server.repository;

import com.github.nibavs.task_manager_server.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUser_Username(String username);
    Optional<Task> findByIdAndUser_Username(Long taskId, String username);
}
