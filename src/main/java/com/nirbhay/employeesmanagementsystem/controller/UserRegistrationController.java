package com.nirbhay.employeesmanagementsystem.controller;

import com.nirbhay.employeesmanagementsystem.dto.UserRegistrationDto;


import com.nirbhay.employeesmanagementsystem.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping()
    public String showRegistrationForm(){
        return "registration";

    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")UserRegistrationDto registrationDto){
        try {
            userService.save(registrationDto);
            return "redirect:/registration?success";
        } catch (DataIntegrityViolationException | ConstraintViolationException | SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            return "redirect:/registration?error";
        }


    }
}
