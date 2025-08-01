package com.example.todoapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.Entities.TasksEntity;

public interface TaskRepository extends JpaRepository<TasksEntity,Long> {
}
