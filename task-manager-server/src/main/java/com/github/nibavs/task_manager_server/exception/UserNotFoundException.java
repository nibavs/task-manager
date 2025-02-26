package com.github.nibavs.task_manager_server.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String login) {
        super("User: " + login + " not found");
    }
}
