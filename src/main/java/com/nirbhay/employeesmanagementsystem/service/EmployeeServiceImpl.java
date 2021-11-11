package com.nirbhay.employeesmanagementsystem.service;

import com.nirbhay.employeesmanagementsystem.model.Employee;
import com.nirbhay.employeesmanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    /* Service implementation to get all the employees from the table */
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /* Service implementation to save employee */
    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    /* Service implementation to get employee by employeeID*/
    @Override
    public Employee getEmployeeByID(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent()){
            employee = optional.get();
        }else{
            throw new RuntimeException(" Employee not found for id :: "+ id);
        }
        return employee;
    }

    /* Service implementation to delete employee by employeeID*/
    @Override
    public void deleteEmployeeByID(long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortFiled, String sortDirection) {
        Sort sort = Sort.by(sortFiled).ascending();
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
        return this.employeeRepository.findAll(pageable);
    }

    /* Service implementation to find the employees by salary info */
    @Override
    public List<Employee> findEmployeesBySalary(String salary) {
        return employeeRepository.findEmployeesBySalary(salary);
    }
}
