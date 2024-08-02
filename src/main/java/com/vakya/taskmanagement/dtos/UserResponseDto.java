package com.vakya.taskmanagement.dtos;

import com.vakya.taskmanagement.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private User user;
    private String token;
    private ResponseStatus responseStatus;
}
