package com.vakya.taskmanagement;


import com.vakya.taskmanagement.controllers.TaskController;
import com.vakya.taskmanagement.dtos.*;
import com.vakya.taskmanagement.excception.TaskNotFoundException;
import com.vakya.taskmanagement.models.Task;
import com.vakya.taskmanagement.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask_Success(){
        TaskRequestDto requestDto = new TaskRequestDto();
        requestDto.setTitle("Task Title");
        requestDto.setDescription("Task Description");
        requestDto.setStatus("Todo");
        requestDto.setPriority("High");
        requestDto.setDueDate(new Date());

        Task task = new Task();
        when(taskService.createTask(anyString(), anyString(), anyString(), anyString(), any(Date.class))).thenReturn(task);

        ResponseEntity<TaskResponseDto> response = taskController.createTask(requestDto);
        assertNotNull(response);
        assertEquals(ResponseStatus.SUCCESS, response.getBody().getResponseStatus());
        assertEquals(task, response.getBody().getTask());
    }

    @Test
    public void testCreateTask_Failure() {
        TaskRequestDto requestDto = new TaskRequestDto();
        requestDto.setTitle("Task Title");
        requestDto.setDescription("Task Description");
        requestDto.setStatus("Todo");
        requestDto.setPriority("High");
        requestDto.setDueDate(new Date());

        when(taskService.createTask(anyString(), anyString(), anyString(), anyString(), any(Date.class))).thenThrow(new RuntimeException());

        ResponseEntity<TaskResponseDto> response = taskController.createTask(requestDto);
        assertNotNull(response);
        assertEquals(ResponseStatus.FAILURE, response.getBody().getResponseStatus());
    }

    @Test
    public void testUpdateTask_Success() throws TaskNotFoundException {
        Long taskId = 1L;
        UpdateTaskRequestDto requestDto = new UpdateTaskRequestDto();
        requestDto.setTitle("Updated Title");
        requestDto.setDescription("Updated Description");
        requestDto.setStatus("In Progress");
        requestDto.setPriority("Medium");

        Task task = new Task();
        when(taskService.updateTask(anyLong(), anyString(), anyString(), anyString(), anyString())).thenReturn(task);

        ResponseEntity<UpdateTaskResponseDto> response = taskController.updateTask(taskId, requestDto);
        assertNotNull(response);
        assertEquals(ResponseStatus.SUCCESS, response.getBody().getResponseStatus());
        assertEquals(task, response.getBody().getTask());
    }

    @Test
    public void testUpdateTask_Failure() throws TaskNotFoundException {
        Long taskId = 1L;
        UpdateTaskRequestDto requestDto = new UpdateTaskRequestDto();
        requestDto.setTitle("Updated Title");
        requestDto.setDescription("Updated Description");
        requestDto.setStatus("In Progress");
        requestDto.setPriority("Medium");

        when(taskService.updateTask(anyLong(), anyString(), anyString(), anyString(), anyString())).thenThrow(new RuntimeException());

        ResponseEntity<UpdateTaskResponseDto> response = taskController.updateTask(taskId, requestDto);
        assertNotNull(response);
        assertEquals(ResponseStatus.FAILURE, response.getBody().getResponseStatus());
    }

    @Test
    public void testGetTasks() {
        List<Task> tasks = Collections.singletonList(new Task());
        when(taskService.getTasks(anyString(), anyString(), any(Date.class), anyString())).thenReturn(tasks);

        List<Task> response = taskController.getTasks("Todo", "High", new Date(), "Task");
        assertNotNull(response);
        assertEquals(tasks, response);
    }

    @Test
    public void testDeleteTask_Success() throws TaskNotFoundException {
        Long taskId = 1L;
        Task task = new Task();
        when(taskService.deleteProducts(taskId)).thenReturn(task);

        Task response = taskController.deleteProducts(taskId);
        assertNotNull(response);
        assertEquals(task, response);
    }

    @Test
    public void testDeleteTask_Failure() throws TaskNotFoundException {
        Long taskId = 1L;
        when(taskService.deleteProducts(taskId)).thenThrow(new TaskNotFoundException("Task not found"));

        TaskNotFoundException thrown = assertThrows(TaskNotFoundException.class, () -> taskController.deleteProducts(taskId));
        assertEquals("Task not found", thrown.getMessage());
    }

    @Test
    public void testGetTasksPagination() {
        GetTaskRequestDto requestDto = new GetTaskRequestDto();
        requestDto.setNumberOfResults(10);
        requestDto.setOffset(0);

        Page<Task> tasksPage = new PageImpl<>(Collections.singletonList(new Task()));
        when(taskService.getTaskss(anyInt(), anyInt())).thenReturn(tasksPage);

        ResponseEntity<Page<Task>> response = taskController.getTaskss(requestDto);
        assertNotNull(response);
        assertTrue(response.getBody().hasContent());
    }

}
