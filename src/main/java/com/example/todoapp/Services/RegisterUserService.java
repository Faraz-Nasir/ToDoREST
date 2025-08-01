package com.example.todoapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todoapp.Entities.RegisterUserEntity;
import com.example.todoapp.Repositories.RegisterUserRepository;

@Service
public class RegisterUserService {

    @Autowired 
    RegisterUserRepository registerUserRepository;

    @Autowired 
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    public String registerUser(RegisterUserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        registerUserRepository.save(userEntity);    
        return "User was saved";
    }
    public String loginUser(RegisterUserEntity userEntity){
        RegisterUserEntity userObjReturnedFromDB=registerUserRepository.findByUsername(userEntity.getUsername());
        if(userObjReturnedFromDB!=null){
            if(passwordEncoder.matches(userEntity.getPassword(),userObjReturnedFromDB.getPassword())){
                System.out.println("Username:"+ userEntity.getUsername()+", has logged in successfully");
                
                return jwtService.createToken(userEntity.getUsername());
            }else{
                System.out.println("Incorrect Credentials");
            }
        }
        return null;
    }
    public boolean validateUser(String token,String username){
        if(jwtService.validateToken(token, username)){
            return true;
        }else{
            return false;
        }
    }
}
