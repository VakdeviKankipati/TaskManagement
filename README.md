Task Management System
This project is a Task Management System built with Spring Boot, implementing user authentication using JWT (JSON Web Token). The application allows users to register, log in, create tasks, and filter/search tasks.

## Features

- User Registration and Authentication
  - Users can register with a username and password.
  - Passwords are securely hashed.
  - Users can log in and receive a JWT token for authenticated requests.
- Task Management
  - Users can create tasks with details like title, description, status, priority, and due date.
  - Filtering and searching functionality for tasks based on status, priority, due date, title, or description.

## Project Structure
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── vakya/
│   │           └── taskmanagement/
│   │               ├── controllers/
│   │               │   └── UserController.java
│   │               │   └── TaskController.java
│   │               ├── filters/
│   │               │   └── JwtRequestFilter.java
│   │               ├── models/
│   │               │   ├── User.java
│   │               │   ├── Task.java
│   │               ├── repositories/
│   │               │   ├── UserRepository.java
│   │               │   ├── TaskRepository.java
|   |               ├──security.services/
|   |               |   ├── UserDetailsServiceImpl.java
│   │               ├── services/
│   │               │   ├── UserService.java
│   │               │   ├── UserServiceImpl.java
│   │               │   ├── TaskService.java
│   │               │   ├── TaskServiceImpl.java
│   │               ├── utils/
│   │               │   └── JwtUtil.java
│   │               ├── TaskManagementApplication.java
│   │               └── config/
│   │                   └── SecurityConfig.java
│   ├── resources/
│   │   └── application.properties
└── test/
    └── java/
        └── com/
            └── vakya/
                └── taskmanagement/
 └──docker-compose.yml
 └──Dockerfile
 └──pom.xml
