package com.github.nibavs.task_manager_server.repository;

import com.github.nibavs.task_manager_server.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByLogin(String login);
}
