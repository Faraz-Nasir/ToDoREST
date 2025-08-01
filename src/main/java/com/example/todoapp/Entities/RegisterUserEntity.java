package com.example.todoapp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="REGISTER_USER")
public class RegisterUserEntity {
    @Id
    private long userId;

    private String username;
    private String password;
}
