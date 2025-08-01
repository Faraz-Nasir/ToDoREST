package com.example.todoapp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TasksEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long task_id;
    private long userId;
    private String taskDescription;
    private Long taskPriority;

}
