package com.nirbhay.employeesmanagementsystem.controller;

import com.nirbhay.employeesmanagementsystem.model.Employee;
import com.nirbhay.employeesmanagementsystem.service.EmployeeService;
import javafx.application.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    @Autowired
    private EmployeeService employeeService;

    /* Display list of employess */
    @GetMapping("/")
    public String viewHomePage(Model model, String salary){
        LOGGER.info("Entered medhod-viewHomePage");
        return findPaginated(1,"firstName","asc",salary,model);
    }
    /* Create model attribute to bind the data */
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "newEmployee";
    }

    /* Save new Employee to the Database */
    /*@RequestMapping(method = RequestMethod.POST)*/
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/searchEmployeeForm")
    public String searchEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "searchEmployeeForm";
    }

    /* Get Employee by salary */
   /* @GetMapping("/showEmployeeBySalary/{salary}")
    public String findBySalary(@PathVariable(value = "salary") String salary){
        employeeService.findEmployeesBySalary(salary);
        return "redirect:/";
    }*/


    /* Update the employ details */
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){
        Employee employee = employeeService.getEmployeeByID(id);
        model.addAttribute("employee",employee);
        return "updateEmployee";
    }

    /* Delete the employee from the Database */
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id){
        this.employeeService.deleteEmployeeByID(id);
        return "redirect:/";
    }

    /* This method is use to pagination of the list */
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                String salary,
                                Model model){
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNo,pageSize,sortField,sortDir);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        if(salary != null){
            model.addAttribute("listEmployees" ,employeeService.findEmployeesBySalary(salary));
        }else {
            model.addAttribute("listEmployees", listEmployees);
        }
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "index";

    }

}
