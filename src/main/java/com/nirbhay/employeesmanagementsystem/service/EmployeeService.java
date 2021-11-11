package com.nirbhay.employeesmanagementsystem.service;
import java.util.List;
import com.nirbhay.employeesmanagementsystem.model.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeByID(long id);
    void deleteEmployeeByID(long id);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortFiled, String sortDirection);
    List<Employee> findEmployeesBySalary(String salary);
}
