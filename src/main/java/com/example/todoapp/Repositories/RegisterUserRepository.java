package com.example.todoapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.Entities.RegisterUserEntity;

public interface RegisterUserRepository extends JpaRepository<RegisterUserEntity,Long>  {
    RegisterUserEntity findByUsername(String username);
}
