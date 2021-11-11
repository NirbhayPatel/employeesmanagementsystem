package com.nirbhay.employeesmanagementsystem.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.nirbhay.employeesmanagementsystem.dto.UserRegistrationDto;
import com.nirbhay.employeesmanagementsystem.model.User;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService extends UserDetailsService{

    User save(UserRegistrationDto registrationDto) throws DataIntegrityViolationException, ConstraintViolationException,SQLIntegrityConstraintViolationException;
}
