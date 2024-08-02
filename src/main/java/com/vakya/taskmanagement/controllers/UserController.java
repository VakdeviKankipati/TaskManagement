package com.vakya.taskmanagement.controllers;

import com.vakya.taskmanagement.dtos.*;
import com.vakya.taskmanagement.dtos.ResponseStatus;
import com.vakya.taskmanagement.models.User;
import com.vakya.taskmanagement.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public UserResponseDto registerUser(@RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = new UserResponseDto();
        try {
            User user = userService.registerUser(
                       requestDto.getUsername(),
                        requestDto.getPassword()
            );
            responseDto.setUser(user);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    @PostMapping("/login")
    public UserResponseDto loginUser(@RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = new UserResponseDto();
        try{
            String token = userService.loginUser(requestDto.getUsername(), requestDto.getPassword());
            User user = userService.findByUsername(requestDto.getUsername());
            responseDto.setUser(user);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            // Include the token in the response
            responseDto.setToken(token);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }





}
