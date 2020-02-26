package com.jrp.pma.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository proRepo;
    @Autowired
    EmployeeRepository emplRepo;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        Map<String, Object> map = new HashMap<>();

        List<Project> projects = proRepo.findAll();
        model.addAttribute("projects", projects);

        List<ChartData> projectData = proRepo.projectStatus();

        // Lets convert projectData into a json structure for use in javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String projectDataJson = objectMapper.writeValueAsString(projectData);
        //expected json ex.: [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]

        model.addAttribute("projectStatusCnt", projectDataJson);

//        List<Employee> employees = emplRepo.findAll();
//        model.addAttribute("employees", employees);
        List<EmployeeProject> employeesProjectCnt = emplRepo.employeeProjects();
        model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);

        return "main/home";
    }

}
