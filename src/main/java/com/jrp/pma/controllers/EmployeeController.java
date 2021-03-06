package com.jrp.pma.controllers;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

//    @Autowired
//    public void setEmplRepo(EmployeeRepository emplRepo) {
//        this.emplRepo = emplRepo;
//    }

//    public EmployeeController(EmployeeRepository emplRepo) {
//        this.emplRepo = emplRepo;
//    }

    @GetMapping//("/")
    public String displayEmployees(Model model){

        List<Employee> employees = (List<Employee>) empService.getAll();
        model.addAttribute("employees", employees);

        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model){

        Employee aEmployee = new Employee();
        model.addAttribute("employee", aEmployee);

        return "employees/new-employee";
    }

    @PostMapping("/save" )
    public String createEmployee(Employee employee, Model model){

        empService.save(employee);

        //use a redirect to prevent duplicate submissions
        return "redirect:/employees";
    }

    
}
