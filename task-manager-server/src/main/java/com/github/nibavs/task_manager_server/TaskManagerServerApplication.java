package com.github.nibavs.task_manager_server;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Task Manager API",   // Новый заголовок
				version = "1.0",
				description = "API documentation for task management."
		)
)
public class TaskManagerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerServerApplication.class, args);
	}

}
