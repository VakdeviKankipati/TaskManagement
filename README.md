# Task Management System
This project is a Task Management System built with Spring Boot, implementing user authentication using JWT (JSON Web Token). The application allows users to register, log in, create tasks, and filter/search tasks.

## Features

- User Registration and Authentication
  - Users can register with a username and password.
  - Passwords are securely hashed.
  - Users can log in and receive a JWT token for authenticated requests.
- Task Management
  - Users can create tasks with details like title, description, status, priority, and due date.
  - Filtering and searching functionality for tasks based on status, priority, due date, title, or description.
# Implementation
- Created mvc patter structure in the form of Cobtrollers, Dtos, Models, Services.
- Created UserController and TaskController in Controller package
- Created Dtos and Repositories
- Created UserService and TaskServices in Service package
- Created Test Cases in the UserControllerTest and TaskControllerTest and implements Unti Test cases
- Created DockerFile and docker-compose.yml files
- Generated Pagenation and also tested.
- Grenerated all dependencies
