package com.github.nibavs.task_manager_server.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("User \"" + username + "\" already exists");
    }
}
