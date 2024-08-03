package com.vakya.taskmanagement.services;

import com.vakya.taskmanagement.excception.TaskNotFoundException;
import com.vakya.taskmanagement.models.Task;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface TaskService {

    Task createTask(String title, String description, String status, String priority,Date dueDate);
    Task updateTask(Long taskId, String title, String description, String status, String priority) throws TaskNotFoundException;
    List<Task> getTasks(String status, String priority, Date dueDate, String search);

    Task deleteProducts(Long taskId) throws TaskNotFoundException;


    Page<Task> getTaskss(int numberOfProducts, int offset) ;
}
