package com.vakya.taskmanagement.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskRequestDto {
    private String title;
    private String description;
    private String status;
    private String priority;
}
