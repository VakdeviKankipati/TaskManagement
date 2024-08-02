package com.vakya.taskmanagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Task extends BaseModel{

    private String title;
    private String description;
    private String status;
    private String priority;
    private Date dueDate;

    @ManyToOne
    private User user;
    //private Long userId;

}
