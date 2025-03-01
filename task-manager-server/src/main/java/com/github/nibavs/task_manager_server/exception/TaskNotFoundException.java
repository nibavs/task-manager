package com.github.nibavs.task_manager_server.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(long taskId) {super("Task with ID " + taskId + " not found");}
}
