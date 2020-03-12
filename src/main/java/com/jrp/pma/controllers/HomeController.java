package com.jrp.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Value("${version}")
    private String var;

    @Autowired
    ProjectService proService;
    @Autowired
    EmployeeService empService;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("versionNumber", var);

        Map<String, Object> map = new HashMap<>();

        List<Project> projects = proService.getAll();
        model.addAttribute("projects", projects);

        List<ChartData> projectData = proService.getProjectStatus();

        // Lets convert projectData into a json structure for use in javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String projectDataJson = objectMapper.writeValueAsString(projectData);
        //expected json ex.: [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]

        model.addAttribute("projectStatusCnt", projectDataJson);

//        List<Employee> employees = emplRepo.findAll();
//        model.addAttribute("employees", employees);
        List<EmployeeProject> employeesProjectCnt = empService.employeeProjects();
        model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);

        return "main/home";
    }

}
