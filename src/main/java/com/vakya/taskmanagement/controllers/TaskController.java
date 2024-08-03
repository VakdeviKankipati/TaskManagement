package com.vakya.taskmanagement.controllers;

import com.vakya.taskmanagement.dtos.*;
import com.vakya.taskmanagement.dtos.ResponseStatus;
import com.vakya.taskmanagement.excception.TaskNotFoundException;
import com.vakya.taskmanagement.models.Task;
import com.vakya.taskmanagement.services.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto responseDto = new TaskResponseDto();
        try{
            Task task = taskService.createTask(
                    taskRequestDto.getTitle(),
                    taskRequestDto.getDescription(),
                    taskRequestDto.getStatus(),
                    taskRequestDto.getPriority(),
                    taskRequestDto.getDueDate()
            );
            responseDto.setTask(task);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return ResponseEntity.ok(responseDto);
    }


    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<UpdateTaskResponseDto> updateTask(@PathVariable Long taskId, @RequestBody UpdateTaskRequestDto requestDto) {
        UpdateTaskResponseDto responseDto = new UpdateTaskResponseDto();
        try{
            Task task = taskService.updateTask(
                    taskId,
                    requestDto.getTitle(),
                    requestDto.getDescription(),
                    requestDto.getStatus(),
                    requestDto.getPriority()
            );
            responseDto.setTask(task);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) Date dueDate,
            @RequestParam(required = false) String search
    ){

        return taskService.getTasks(status, priority, dueDate, search);
    }

    @DeleteMapping("/tasks/{taskId}")
    public Task deleteProducts(@PathVariable("taskId") Long taskId) throws TaskNotFoundException {
        return taskService.deleteProducts(taskId);
    }

    @GetMapping("/tasks/pagination")
    public ResponseEntity<Page<Task>> getTaskss(@RequestBody GetTaskRequestDto requestDto)  {
        /*Page<Task> tasksPage = taskService.getTaskss(

                requestDto.getPage(),
                requestDto.getSize()
        );
        return ResponseEntity.ok(tasksPage);*/
        return ResponseEntity.of(Optional.ofNullable(taskService.getTaskss(
                requestDto.getNumberOfResults() , requestDto.getOffset()
        )));
    }




}
