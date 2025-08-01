package com.example.todoapp.Controllers;

import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.Entities.RegisterUserEntity;
import com.example.todoapp.Services.JWTService;
import com.example.todoapp.Services.RegisterUserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/home")
public class loginPage {
    @Autowired
    RegisterUserService registerUserService;

    @PostMapping("/new-user")
    public String registerUser(@RequestBody RegisterUserEntity newUser) {
        registerUserService.registerUser(newUser);
        return "A new user has been registered";
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody RegisterUserEntity loginUser) {
        String token=registerUserService.loginUser(loginUser);
        if(token!=null){
            return ResponseEntity.ok().header("JWTtoken",token).body("Login Successfull");
        }else{
            return ResponseEntity.status(401).body("Not Authorized");
        }
    }
    @GetMapping("/tasks")
    public String isUserLoggedIn(@RequestHeader("JWTtoken") String Authtoken,@RequestBody RegisterUserEntity user) {
        if(Authtoken==null){
            return "Are you new here?";
        }else{
            if(registerUserService.validateUser(Authtoken, user.getUsername())){
                return "User was already logged in";
            }else{
                return "You are not new here, but you need to login in again";
            }
        }
    }
    
    
    
    
}
