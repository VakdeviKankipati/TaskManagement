package com.vakya.taskmanagement.excception;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String message){
        super(message);
    }
}
