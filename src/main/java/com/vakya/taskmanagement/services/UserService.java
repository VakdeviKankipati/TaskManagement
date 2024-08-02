package com.vakya.taskmanagement.services;

import com.vakya.taskmanagement.models.User;

public interface UserService {

    User registerUser(String name, String password);

    String loginUser(String username, String password) throws Exception;
    User findByUsername(String username);

}
