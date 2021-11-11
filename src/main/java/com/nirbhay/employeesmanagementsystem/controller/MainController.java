package com.nirbhay.employeesmanagementsystem.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    /* This method is uses to login to the application */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}