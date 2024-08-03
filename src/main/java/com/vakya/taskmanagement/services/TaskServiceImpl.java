package com.vakya.taskmanagement.services;

import com.vakya.taskmanagement.excception.TaskNotFoundException;
import com.vakya.taskmanagement.models.Task;
import com.vakya.taskmanagement.repositories.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }
    @Override
    public Task createTask(String title, String description, String status, String priority, Date dueDate) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setPriority(priority);
        task.setDueDate(dueDate);
        task.setCreated_at(new Date());
        task.setUpdated_at(new Date());
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, String title, String description, String status, String priority) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId).orElseThrow(()->new TaskNotFoundException("task not fount"));
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setPriority(priority);
        task.setUpdated_at(new Date());
        return taskRepository.save(task);

    }

    @Override
    public List<Task> getTasks(String status, String priority, Date dueDate, String search) {
        if (status != null || priority != null || dueDate != null || search != null) {
            return taskRepository.findByFilters(status, priority, dueDate, search);
        }
        return taskRepository.findAll();
    }

    @Override
    public Task deleteProducts(Long taskId) throws TaskNotFoundException {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (!taskOptional.isPresent()) {
            throw new TaskNotFoundException("Task not found");
        }
        Task task = taskOptional.get();
        taskRepository.delete(task);
        return task;
    }
    public Page<Task> getTaskss(int numberOfProducts, int offset) {
        Page<Task> tasks = taskRepository.findAll(
                PageRequest.of((offset/numberOfProducts), numberOfProducts)
        );
        return tasks;
    }



}
