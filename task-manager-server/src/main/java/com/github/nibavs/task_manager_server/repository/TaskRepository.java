package com.github.nibavs.task_manager_server.repository;

import com.github.nibavs.task_manager_server.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByPerson_Login(String login);
    Optional<Task> findByIdAndPerson_Login(Long taskId, String login);
}
