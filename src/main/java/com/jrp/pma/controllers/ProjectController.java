package com.jrp.pma.controllers;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService proService;
    @Autowired
    EmployeeService empService;

    @GetMapping//("/")
    public String displayProjects(Model model){

        List<Project> projects = proService.getAll();
        model.addAttribute("projects", projects);

        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model){

        Project aProject = new Project();
        model.addAttribute("project", aProject);

        List<Employee> employees = empService.getAll();
        model.addAttribute("allEmployees", employees);

        return "projects/new-project";
    }

    @PostMapping("/save" )
    public String createProject(Project project, /*@RequestParam List<Long> employees,*/ Model model){

        proService.save(project);

//        Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
//        for (Employee emp: chosenEmployees){
//            emp.setProject(project);
//            empRepo.save(emp);
//        }

        //use a redirect to prevent duplicate submissions
        return "redirect:/projects";
    }

    @RequestMapping
    public String testIndex(){
        return "index";
    }

}
