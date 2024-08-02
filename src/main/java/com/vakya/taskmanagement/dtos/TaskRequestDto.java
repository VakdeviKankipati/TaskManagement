package com.vakya.taskmanagement.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskRequestDto {
    private String title;
    private String description;
    private String status; // Todo, In Progress, Done
    private String priority;
    private Date dueDate;
}
