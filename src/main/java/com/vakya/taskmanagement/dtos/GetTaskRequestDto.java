package com.vakya.taskmanagement.dtos;

import com.vakya.taskmanagement.models.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GetTaskRequestDto {
    private int numberOfResults;
    private int offset;
}
