package com.java.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/v1")
public class WelcomeController {
    @GetMapping("/messages")
    public String getMessage(){
        return "Welcome to web application using Spring boot";
    }
    @GetMapping("/currentdate")
    public String getDate(){
        return LocalDate.now().toString();
    }
    @GetMapping("/currenttime")
    public String getTime(){
        return LocalTime.now().toString();
    }

    /**
     * Devtools
     * @return
     */
    @GetMapping("/welcme")
    public String welcome(){
        return "Welcome to swagger!!";
    }
}
