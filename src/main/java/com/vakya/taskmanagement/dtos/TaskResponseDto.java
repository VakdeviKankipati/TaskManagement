package com.vakya.taskmanagement.dtos;

import com.vakya.taskmanagement.models.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDto {
    private Task task;
    private ResponseStatus responseStatus;
}
