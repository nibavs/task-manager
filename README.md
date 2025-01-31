# Task Manager ✏️📃

## Description
This project is a task management application that allows users to add, delete, edit, and view tasks stored in a database. Each task has a status and a title. The application is built using Vue.js for the frontend, Spring Boot for the backend, and MySQL as the database. Additionally, Swagger has been used to generate API documentation for the backend.

## Technologies Used

- **Frontend:**
  - Vue3.js
  - Pinia 
  - TypeScript
  - Vue Router

- **Backend:**
  - Spring Boot
  - Spring Data JPA 
  - Swagger 
  - Maven 

- **Database:**
  - MySQL

## Features

- Add new tasks with a title and status
- Edit existing tasks
- Delete tasks from the database
- View all tasks stored in the system
- API documentation generated with Swagger
- State management with Pinia
- Routing implemented with Vue Router

## Installation and Running

1. Clone the repository:
   ```bash
   git clone https://github.com/nibavs/task-manager.git
2. Make sure you have Java 11+, Maven for server and Node.js for client installed
3. Create a .env file in the client folder of the project and define the server parameters:
   ```bash
   VITE_API_BASE_URL="your_server_url_here"
   VITE_API_TASKS_ENPOINT="your_server_url_here/tasks"
4. Create a application.properties file in the task-manager-server/src/main/resources and define the database connection parameters (MySQL example):
   ```bash
    spring.application.name=task-manager-server
    spring.datasource.url="jdbc:mysql://your_database_url/task-manager"
    spring.datasource.username=root
    spring.datasource.password="your-password"
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
5. Run the server using Maven in the server directory:
   ```bash
   mvn spring-boot:run
6. Run the client using NPM in the client directory:
   ```bash
   npm run dev
7. To make the application work, you will need a configured MySQL database with the following table for tasks:
   ```bash
    CREATE TABLE task (
        id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        description TEXT,
        status VARCHAR(50) DEFAULT 'NEW',
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );